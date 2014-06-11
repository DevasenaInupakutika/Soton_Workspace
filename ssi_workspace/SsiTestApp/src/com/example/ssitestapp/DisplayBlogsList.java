package com.example.ssitestapp;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

public class DisplayBlogsList extends Activity {

    
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		
		//Intent that will return to previous activity.
		Intent myIntent = new Intent(getApplicationContext(),MainActivity.class);
		myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivityForResult(myIntent,0);
		return super.onOptionsItemSelected(item);
	}
	
	private static final String RSSFEEDURL = "http://www.software.ac.uk/blog/rss-all";
    private static final String TAG = "MyActivity";
	RSSFeed feed;
    
	//Progress Bar Functionality
	private ProgressBar mProgress;
	private int mProgressStatus=0;
	private Handler mHandler = new Handler();
	private TextView textView;	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_blogs_list);
		
		
		mProgress = (ProgressBar)findViewById(R.id.progress_bartop);
		textView = (TextView)findViewById(R.id.textview1);
		
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
          //new AsyncLoadXMLFeed().execute();
        	
        	AsyncLoadXMLFeed my_task = new AsyncLoadXMLFeed();
        	
        	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) 
        		
    			my_task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Void[])null);
        	else
        		my_task.execute((Void[])null);
    	    
        	
        }

	}

	private class AsyncLoadXMLFeed extends AsyncTask<Void,Void,Void>{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			//Threading to improve performance of app. Starting lengthy operation in a background thread.
			
			new Thread(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					while(mProgressStatus < 100){
						mProgressStatus += 1;
						
						//Update Progress Bar
						mHandler.post(new Runnable(){

							@Override
							public void run() {
								// TODO Auto-generated method stub
								//Obtain feed
							
								mProgress.setProgress(mProgressStatus);
								textView.setText(mProgressStatus + "%");
								
							}
							
							
						});
						try{
							Thread.sleep(1000);
						}
						catch(InterruptedException e){
							e.printStackTrace();
						}
					}
					
				}
				
			}).start();
			
			DOMParser myParser = new DOMParser();
			String xml = myParser.getXmlFromUrl(RSSFEEDURL);
            feed = myParser.parseXml(xml,0);
			
			return null;
		}	
		/* (non-Javadoc)
		 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
		 */
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			Bundle bundle = new Bundle();
			bundle.putSerializable("feed", feed);
			
			
			//Launch ListActivity
			Intent list_intent = new Intent(DisplayBlogsList.this,ListActivity.class);
		    list_intent.putExtras(bundle);
			startActivity(list_intent);
	    
			//Kill this activity.
			finish();

		}

		
		
	}
	
	
}