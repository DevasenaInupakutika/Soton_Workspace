package com.example.ssitestapp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.widget.ScrollView;
import android.widget.TextView;

public class DetailActivity extends Activity{
	
	static final String RSSFEEDURL = "http://www.software.ac.uk/blog/rss-all";
	RSSFeed feed;
	TextView title;
	WebView desc;
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if(desc.canGoBack()){
			desc.goBack();
		}
		else{
			super.onBackPressed();
		}

	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}

		//Trial block to eliminate NullPointerException when navigating back to ListActivity.
		
		//DOMParser myParser = new DOMParser();
		//String xml = myParser.getXmlFromUrl(RSSFEEDURL);
		//feed = myParser.parseXml(xml);
		
		//Intent that will return to previous activity.
		
		//Bundle bundle = new Bundle();
		//bundle.putSerializable("feed", feed);
		
		Intent myIntent = new Intent(this,ListActivity.class);
		myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		//myIntent.putExtras(bundle);
	    startActivityForResult(myIntent,0);
	    
	    finish();
		return super.onOptionsItemSelected(item);
	}
	
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	          super.onCreate(savedInstanceState);
	          setContentView(R.layout.detail);
	 
	// Enable the vertical fading edge (by default it is disabled)
	          ScrollView sv = (ScrollView) findViewById(R.id.sv);
	          sv.setVerticalFadingEdgeEnabled(true);
	 
	// Get the feed object and the position from the Intent
	          feed = (RSSFeed) getIntent().getExtras().get("feed");
	          int pos = getIntent().getExtras().getInt("pos");
	  		
	// Initialise the views
	          title = (TextView) findViewById(R.id.title);
	          desc = (WebView) findViewById(R.id.desc);
	          
	// set webview properties and enabling Javascript
	          WebSettings ws = desc.getSettings();
	          ws.setLayoutAlgorithm(LayoutAlgorithm.NORMAL);
	          ws.getPluginState();
	          ws.setPluginState(PluginState.ON);
	          ws.setJavaScriptEnabled(true);
	          desc.setWebViewClient(new SsiAppWebViewClient());
	          ws.setBuiltInZoomControls(true);
	       
	         title.setText(" ");
	         if(savedInstanceState == null)
	         {	    
	        	//desc.loadDataWithBaseURL("http://www.software.ac.uk/blog", feed
	     			//	.getItem(pos).getDescription(), "text/html", "UTF-8", null);
	        	desc.loadUrl(feed.getItem(pos).getLink());
	        	 
	          }	
	          ActionBar actionBar = getActionBar();
	          actionBar.setDisplayHomeAsUpEnabled(true);

	  		// Show the Up button in the action bar.
	  		  setupActionBar();

	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		 super.onSaveInstanceState(outState);
		 desc.saveState(outState);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    switch (keyCode) {
	        case KeyEvent.KEYCODE_BACK:
	            if(desc.canGoBack() == true)

	                desc.goBack();
	            else finish();
	            break;

	        default:
	            break;
	    }
	    return super.onKeyDown(keyCode, event);
	}

	
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
		
			getActionBar().setDisplayHomeAsUpEnabled(true);
		
	}
	}

}
