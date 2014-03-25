package com.example.ssitestapp;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

public class DisplayBlogsList extends Activity {
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		//return true;
		
		//Intent that will return to previous activity.
		Intent myIntent = new Intent(getApplicationContext(),MainActivity.class );
	    startActivityForResult(myIntent, 0);
		return super.onOptionsItemSelected(item);
	}

	private static final String RSSFEEDURL = "http://www.software.ac.uk/blog/rss-all";
	RSSFeed feed;
	
	//Progress Bar functionality
	
	private static final int PROGRESS = 0x1;
	private ProgressBar mProgress;
	private int mProgressStatus = 0;
	private Handler mHandler = new Handler();
	private TextView textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_blogs_list);
	
		mProgress = (ProgressBar) findViewById(R.id.progress_bartop);
		textView = (TextView) findViewById(R.id.textview1);
		
		//Start lengthy operation in a background thread.
		new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(mProgressStatus < 100){
					
					mProgressStatus += 1;
					
					//Update Progress Bar
					mHandler.post(new Runnable(){
						public void run(){
							mProgress.setProgress(mProgressStatus);
							textView.setText(mProgressStatus+"%");
							
						}
						
					});
					try{
						Thread.sleep(1000);
					}
					catch (InterruptedException e){
						e.printStackTrace();
					}
					
				}
				
			}
			
			
		}).start();
		
		
		ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
		boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        //if (conMgr.getActiveNetworkInfo() == null
        //&& !conMgr.getActiveNetworkInfo().isConnected()
        //&& !conMgr.getActiveNetworkInfo().isAvailable()) {
		if(!isConnected){
        // No connectivity - Show alert
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(
						"Unable to reach server, \nPlease check your connectivity.")
						.setTitle("Software and Research: the Institute's Blog")
						.setCancelable(false)
						.setPositiveButton("Exit",
								new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int id) {
									finish();
								}
						});
         
        AlertDialog alert = builder.create();
        alert.show();
         
        } else {
        // Connected - Start parsing
        new AsyncLoadXMLFeed().execute();
        }
	}
	
	private class AsyncLoadXMLFeed extends AsyncTask<Void, Void, Void>{


		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			//Obtain feed
			DOMParser myParser = new DOMParser();
			feed = myParser.parseXml(RSSFEEDURL);
			return null;
		}	
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			Bundle bundle = new Bundle();
			bundle.putSerializable("feed", feed);
			
			//Launch ListActivity
			Intent intent = new Intent(DisplayBlogsList.this,ListActivity.class);
			intent.putExtras(bundle);
			startActivity(intent);
			
			//Kill this activity.
			finish();
			}

	}
	
	
}
