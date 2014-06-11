package com.example.ssitestapp;

import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
//import com.google.analytics.tracking.android.Log;

public class SsiAppWebViewClient extends WebViewClient {

	/* (non-Javadoc)
	 * @see android.webkit.WebViewClient#shouldOverrideUrlLoading(android.webkit.WebView, java.lang.String)
	 */
	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		// TODO Auto-generated method stub
		
		if(Uri.parse(url).getHost().contains("software.ac.uk") || !(Uri.parse(url).getHost().contains("software.ac.uk"))){
		    
			return false;
		}

		Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
		intent.setData(Uri.parse(url));
		view.getContext().startActivity(intent);
	
		return true;
	}
	
}
