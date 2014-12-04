package uk.software.blogreader;

import uk.software.blogreader.R;

import uk.software.blogreader.image.*;
import uk.software.parser.*;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ListActivity extends Activity {
	
	Application myApp;
	RSSFeed feed;
	ListView lv;
	CustomListAdapter adapter;
	String Uri = null;
	private static final String TAG = "MyActivity";
	//GoogleAnalyticsTracker tracker; //Using GA Tracker
    //private EasyTracker easyTracker = null; //Using Easy Tracker
  
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		//EasyTracker.getInstance(this).activityStart(this);
		
	}


	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		//EasyTracker.getInstance(this).activityStop(this);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;

		}
		//Intent that will return to previous activity.
				Intent myIntent = new Intent(getApplicationContext(),MainActivity.class );
				myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			    startActivityForResult(myIntent, 0);

				return super.onOptionsItemSelected(item);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.feed_list);
		//easyTracker = EasyTracker.getInstance(ListActivity.this);
		
		myApp = getApplication();

		// Get feed form the file
		feed = (RSSFeed) getIntent().getExtras().get("feed");

		// Initialize the variables:
		lv = (ListView) findViewById(R.id.listView);
		lv.setVerticalFadingEdgeEnabled(true);

		// Set an Adapter to the ListView
		adapter = new CustomListAdapter(this);
		lv.setAdapter(adapter);
	
		// Start your statistics tracking
        //tracker = GoogleAnalyticsTracker.getInstance();     
      
        //tracker.start("UA-46208653-1", this); // Start the tracker in manual dispatch mode.
        //tracker.start("UA-46208653-1", 30, this);   //Tracker started  with a dispatch interval of 5 seconds for real-time tracking
		//tracker.trackPageView("/SSI Blog Page");
		
		// Set on item click listener to the ListView
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				int pos = arg2;
				
				// actions to be performed when a list item clicked
			    //tracker.trackPageView(feed.getItem(pos).getLink());   
		        //tracker.trackEvent("Clicks","ListItem", "Blog Clicked", 0);
		        
		        //easyTracker.send(MapBuilder.createEvent("Click", "List Item Clicked", feed.getItem(pos).getLink(), null).build());
		        
				Bundle bundle = new Bundle();
				bundle.putSerializable("feed", feed);
				Intent intent = new Intent(ListActivity.this,
						DetailActivity.class);
				intent.putExtras(bundle);
				intent.putExtra("pos", pos);
				startActivity(intent);
				
				
				
			}
		});
		//Show up button in the action bar.
				setupActionBar();

	}
	private void setupActionBar() {
		// TODO Auto-generated method stub
	  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			
			getActionBar().setDisplayHomeAsUpEnabled(true);
		
	}
}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		adapter.notifyDataSetChanged();		
		//tracker.dispatch();
		//tracker.stop();
	}

	class CustomListAdapter extends BaseAdapter {

		private LayoutInflater layoutInflater;
		public ImageLoader imageLoader;

		public CustomListAdapter(ListActivity activity) {

			layoutInflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			imageLoader = new ImageLoader(activity.getApplicationContext());
		}

		@Override
		public int getCount() {

			// Set the total list item count
			return feed.getItemCount();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			// Inflate the item layout and set the views
			View listItem = convertView;
			int pos = position;
			if (listItem == null) {
				listItem = layoutInflater.inflate(R.layout.list_item, null);
			}

			// Initialize the views in the layout
			ImageView iv = (ImageView) listItem.findViewById(R.id.thumb);
			
			// set the ImageView opacity to 50%
			TextView tvTitle = (TextView) listItem.findViewById(R.id.title);
			TextView tvDate = (TextView) listItem.findViewById(R.id.date);

			Log.v(TAG, "Image Link is:"+feed.getItem(pos).getImage());
			
			imageLoader.DisplayImage(feed.getItem(pos).getImage(), iv, iv.getMeasuredWidth(), iv.getMeasuredHeight());
			
		
			tvTitle.setText(feed.getItem(pos).getTitle());
			tvDate.setText(feed.getItem(pos).getDate().substring(4, 16));
            
			return listItem;
		}

	}


}
