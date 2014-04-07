package com.example.ssitestapp;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
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
import android.webkit.WebViewClient;
import android.widget.ScrollView;
import android.widget.TextView;

public class DetailActivity extends Activity{

	/* (non-Javadoc)
	 * @see android.app.Activity#onSaveInstanceState(android.os.Bundle)
	 */
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onBackPressed()
	 */
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

	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	RSSFeed feed;
	TextView title;
	WebView desc;
	 
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
		Intent myIntent = new Intent(this,DisplayBlogsList.class );
		myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    startActivity(myIntent);
	    finish();
	    
		return super.onOptionsItemSelected(item);
	}
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
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
	          
	         // Set the views
	          title.setText(feed.getItem(pos).getTitle());
	          if(savedInstanceState == null)
	          {
	            desc.loadDataWithBaseURL("http://software.ac.uk", feed.getItem(pos).getDescription(), "text/html", "UTF-8", null);
	            
	          }
	          ActionBar actionBar = getActionBar();
	          actionBar.setDisplayHomeAsUpEnabled(true);
	  		
	  		// Show the Up button in the action bar.
	  		  setupActionBar();
	
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		//super.onSaveInstanceState(outState);
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
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}
}
