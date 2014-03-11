package com.example.ssitestapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import com.google.analytics.tracking.android.EasyTracker;

public class MainActivity extends Activity {

	/* (non-Javadoc)
	 * @see android.app.Activity#onStart()
	 */
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		EasyTracker.getInstance(this).activityStart(this); 
	}


	/* (non-Javadoc)
	 * @see android.app.Activity#onStop()
	 */
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		EasyTracker.getInstance(this).activityStop(this);  
	}

	Button eventsButton,blogButton,testButton;
	public final static String EXTRA_MESSAGE = "com.example.ssitestapp.MESSAGE";
		
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eventsButton = (Button) findViewById(R.id.events);   
        blogButton = (Button) findViewById(R.id.blog);
        //testButton = (Button) findViewById(R.id.tb);
     
    }
	
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void eventNotifier(View v){
    	
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
    	
    }
    
}
