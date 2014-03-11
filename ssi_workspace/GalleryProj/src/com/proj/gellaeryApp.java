package com.proj;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class gellaeryApp extends Activity {
    /** Called when the activity is first created. */
	gallerybaseadapter gba;
	Gallery gl;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        gba = new gallerybaseadapter(this);
        gl = (Gallery) findViewById(R.id.gallery);
        gl.setAdapter(gba);
        
        gl.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "hi working" + arg2, Toast.LENGTH_LONG).show();		}
        	
		});
    }
    
   
}