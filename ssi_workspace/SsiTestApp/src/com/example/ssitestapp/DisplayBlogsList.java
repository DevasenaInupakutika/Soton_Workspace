package com.example.ssitestapp;

import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import java.util.*;

public class DisplayBlogsList extends Activity {
	
	private String RSSFEEDURL = "http://www.software.ac.uk/blog/rss";
	RSSFeed feed;
	 
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_blogs_list);
		
		ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() == null
        && !conMgr.getActiveNetworkInfo().isConnected()
        && !conMgr.getActiveNetworkInfo().isAvailable()) {
        // No connectivity - Show alert
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(
        "Unable to reach server, \nPlease check your connectivity.")
        .setTitle("TD RSS Reader")
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
	
      private class AsyncLoadXMLFeed extends AsyncTask {
    	  
    	  @Override
  		protected Object doInBackground(Object... arg0) {
  			// TODO Auto-generated method stub
  			//Obtain RSS feed.
  			DOMParser myParser = new DOMParser();
  			feed = myParser.parseXml(RSSFEEDURL);
  			
  			return null;
  		}
  		
  		protected void onPostExecute(Void result) {
  			super.onPostExecute(result);
  			 
  			Bundle bundle = new Bundle();
  			bundle.putSerializable("feed", feed);
  			 
  			// launch List activity
  			Intent intent = new Intent(DisplayBlogsList.this, ListActivity.class);
  			intent.putExtras(bundle);
  			startActivity(intent);
  			 
  			// kill this activity
  			finish();
  			}	

	}
	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_blogs_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
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
		//Intent that will return to previous activity.
		
		Intent myIntent = new Intent(getApplicationContext(),MainActivity.class );
	    startActivityForResult(myIntent, 0);
	    
		return super.onOptionsItemSelected(item);
	}

}
