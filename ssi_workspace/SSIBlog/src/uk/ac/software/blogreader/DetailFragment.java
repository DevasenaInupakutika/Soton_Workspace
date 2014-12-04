package uk.ac.software.blogreader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.net.URL;

import uk.ac.software.blogreader.image.ImageLoader;
import uk.ac.software.parser.RSSFeed;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class DetailFragment extends Fragment {
	private int fPos;
	RSSFeed fFeed;
	private static final String TAG = "MyActivity";

	
	public ImageLoader imageLoader;
	
	URL blogURL;
	InputStream is;
	BufferedReader br;
	String line = null;
	StringBuffer sb;
	WebView desc;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		fFeed = (RSSFeed) getArguments().getSerializable("feed");
		fPos = getArguments().getInt("pos");

		imageLoader = new ImageLoader(getActivity().getApplicationContext());
	
	}
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater
				.inflate(R.layout.detail_fragment, container, false);
		
		// Initialise views
				TextView title = (TextView) view.findViewById(R.id.title);
				final ImageView iv = (ImageView) view.findViewById(R.id.iv); 
				desc = (WebView) view.findViewById(R.id.desc);

				// Enable the vertical fading edge (by default it is disabled)
				ScrollView sv = (ScrollView) view.findViewById(R.id.sv);
				sv.setVerticalFadingEdgeEnabled(true);

				// Set webview properties
				WebSettings ws = desc.getSettings();
				
				desc.setHorizontalScrollBarEnabled(true);
				ws.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
				ws.setAppCacheMaxSize( 5 * 1024 * 1024 ); // 5MB
				ws.setAppCachePath( getActivity().getApplicationContext().getCacheDir().getAbsolutePath());
				ws.setAllowFileAccess(true);
				ws.setAppCacheEnabled(true);
				ws.setJavaScriptEnabled(true);
				ws.setCacheMode( WebSettings.LOAD_DEFAULT ); // load online by default
				ws.setLightTouchEnabled(false);
				ws.setPluginState(PluginState.ON);
				
				
				ws.setSupportZoom(true);
				ws.setBuiltInZoomControls(true);  //Zoom in/ out when pinched 
				ws.setDomStorageEnabled(true);
				
				// Set the views
				title.setText(fFeed.getItem(fPos).getTitle());
				
				//Extracting part of SSI blog html page through Jsoup in AsyncLoadLinkFeed thread
				
				//new AsyncLoadLinkFeed().execute(); //Uncomment this statement when using AsyncTask and calling in background
				
				Log.v(TAG, "Detailed Activity Image Link is:"+fFeed.getItem(fPos).getImage());
				
				imageLoader.DisplayImage(fFeed.getItem(fPos).getImage(), iv,400,300);
				
				iv.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						loadPhoto(iv, 400, 400); //It will give the image id
					}
					
					
					
				} );
				
				
				//ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
				
				//Method I which caches only few images (might be ones which are accessed when in online mode)
				//load Online
				/*if(connMgr.getActiveNetworkInfo() != null && connMgr.getActiveNetworkInfo().isConnected() == true)
				{
					ws.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
					desc.loadDataWithBaseURL("file:///android_asset/",fFeed.getItem(fPos).getDescription(), "text/html", "UTF-8", null );
				}*/
				
				
				//load in offline mode
				/*else{
					ws.setCacheMode(WebSettings.LOAD_CACHE_ONLY);
					desc.loadDataWithBaseURL("file:///android_asset/",fFeed.getItem(fPos).getDescription(), "text/html", "UTF-8", null );
				
				}*/
				
				//Method II
				/*if(connMgr.getActiveNetworkInfo() == null || !connMgr.getActiveNetworkInfo().isConnected()){
				
					ws.setAppCachePath( getActivity().getApplicationContext().getCacheDir().getAbsolutePath());
					ws.setCacheMode(WebSettings.LOAD_DEFAULT);
					desc.loadDataWithBaseURL("file:///android_asset/",fFeed.getItem(fPos).getDescription(), "text/html", "UTF-8", null );	
				}
				else{
					
					ws.setCacheMode(WebSettings.LOAD_CACHE_ONLY);
					desc.loadDataWithBaseURL("file:///android_asset/",fFeed.getItem(fPos).getDescription(), "text/html", "UTF-8", null );
				}*/
				
				//Method III
				if ( !isNetworkAvailable() ) { // loading offline
				    ws.setCacheMode( WebSettings.LOAD_CACHE_ELSE_NETWORK );
				}
				desc.loadDataWithBaseURL("file:///android_asset/",fFeed.getItem(fPos).getDescription(), "text/html", "UTF-8", null );
				
				return view;
	}
	private boolean isNetworkAvailable() {
		// TODO Auto-generated method stub
		ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService( Context.CONNECTIVITY_SERVICE );
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null;
		
	}
	
	private void loadPhoto(ImageView imageView,int width, int height){


		AlertDialog.Builder imageDialog = new AlertDialog.Builder(this.getActivity());
		
		LayoutInflater inflater = (LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View layout = inflater.inflate(R.layout.imagepop, (ViewGroup) this.getActivity().findViewById(R.id.layout_root));
		
		ImageView image = (ImageView) layout.findViewById(R.id.fullimage);
		//image.setImageDrawable(tempImageView.getDrawable());
		imageLoader.DisplayImage(fFeed.getItem(fPos).getImage(),image,width,height);
		
		imageDialog.setView(layout);
		
		imageDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				
			}
		});
		imageDialog.create();
		imageDialog.show();
	
	
	}

}
