package com.example.ssitestapp;

import java.io.Serializable;

import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import javax.xml.parsers.*;
import org.xml.sax.*;


public class MainActivity extends Activity {

	Button eventsButton,blogButton,evalButton;
	public final static String EXTRA_MESSAGE = "com.example.ssitestapp.MESSAGE";
		
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eventsButton = (Button) findViewById(R.id.events);   
        blogButton = (Button) findViewById(R.id.blog);
     
     
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
    
    
    
}
