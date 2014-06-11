package com.example.ssitestapp;

import android.os.Build;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

//import com.google.analytics.tracking.android.EasyTracker;

public class MainActivity extends Activity {

	/* (non-Javadoc)
	 * @see android.app.Activity#onStart()
	 */
	
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
			//NavUtils.navigateUpFromSameTask(this);
			switch(item.getItemId())
			{
			case android.R.id.home:
				//Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
				super.onBackPressed();
			}
		return true;
	}
		//Intent that will return to previous activity.
				Intent myIntent = new Intent(getApplicationContext(),MainActivity.class );
				myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivityForResult(myIntent, 0);
			   
			    return super.onOptionsItemSelected(item);
		
	}


	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		//EasyTracker.getInstance(this).activityStart(this); 
	}


	/* (non-Javadoc)
	 * @see android.app.Activity#onStop()
	 */
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		//EasyTracker.getInstance(this).activityStop(this);  
	}

	//Button eventsButton,blogButton,testButton;
	//public final static String EXTRA_MESSAGE = "com.example.ssitestapp.MESSAGE";
	
		
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent blogspage=new Intent(this,DisplayBlogsList.class);
    	startActivity(blogspage);
    	
    	ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
		
		// Show the Up button in the action bar.
		setupActionBar();
       
       // eventsButton = (Button) findViewById(R.id.events);   
        //blogButton = (Button) findViewById(R.id.blog);
        //testButton = (Button) findViewById(R.id.tb);
     
    }
	
    
    private void setupActionBar() {
		// TODO Auto-generated method stub
    	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		
	}
    }


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
  /*  public void eventNotifier(View v){
    	
    	Intent eventspage=new Intent(this,DisplayEventsList.class);
		startActivity(eventspage);
		
    }
    
    public void blogPosts(View v){
    	
    	Intent blogspage=new Intent(this,DisplayBlogsList.class);
    	startActivity(blogspage);
    }
    
    public void testBlogNotifier(View v){
    	
    	Intent refreshpage=new Intent(this,PullToRefreshActivity.class);
		startActivity(refreshpage);
    	
    }*/
    
}
