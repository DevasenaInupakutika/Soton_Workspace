package com.view;

import java.security.KeyStore.LoadStoreParameter;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebViewApp extends Activity {
    /** Called when the activity is first created. */
	WebView wv ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        wv = (WebView) findViewById(R.id.webview);
        wv.loadUrl("http://www.google.com");
    }
}