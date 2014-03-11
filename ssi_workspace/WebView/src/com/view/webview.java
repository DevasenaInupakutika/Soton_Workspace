package com.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class webview extends Activity {
	// this class is used to create without using the xml layout and uses uri
	Uri u ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		u = Uri.parse("http://www.google.com");
		Intent i = new Intent(Intent.ACTION_VIEW , u );
		startActivity(i);
	}

}
