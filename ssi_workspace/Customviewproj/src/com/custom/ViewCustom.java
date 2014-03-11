package com.custom;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient.CustomViewCallback;

public class ViewCustom extends Activity {
    /** Called when the activity is first created. */
	//CustomView cv ; // this is used when use only javacode
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
       /* cv = new CustomView(this);
        * // this is used when use only javacode
        setContentView(cv);
       */ 
    }
}