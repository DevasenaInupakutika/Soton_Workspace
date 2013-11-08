package com.example.ssitestapp;

import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

public class DisplayBlogsList extends Activity {
	
	private String RSSFEEDURL = "http://www.software.ac.uk/blog/rss";
	RSSFeed feed;
	 
	@SuppressWarnings("unchecked")
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
        .setTitle("Blogs Feed")
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
	
      @SuppressWarnings("rawtypes")
	private class AsyncLoadXMLFeed extends AsyncTask {
    	
    	  @Override
  		  protected Object doInBackground(Object... params) {
  			// TODO Auto-generated method stub
  			DOMParser myParser = new DOMParser();
    			feed = myParser.parseXml(RSSFEEDURL);
  			return null;
  		}

		@SuppressWarnings("unchecked")
		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub
			 super.onPostExecute(result);
		     Bundle bundle = new Bundle();
		     bundle.putSerializable("feed", feed);
		     
		     //launch list activity.
		     Intent intent = new Intent(DisplayBlogsList.this, ListActivity.class);
		     intent.putExtras(bundle);
		     startActivity(intent);
		      
		     // kill this activity
		     finish();
		     
		     
		     
		
		}	  		
  		
   	}

}
