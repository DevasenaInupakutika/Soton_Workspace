package com.example.ssitestapp;

import android.os.Bundle;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ListActivity extends Activity {

	
	Application myApp;
	RSSFeed feed;
	ListView lv;
	CustomListAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feed_list);
		
		myApp = getApplication();
		 
		// Get feed form the file
		feed = (RSSFeed) getIntent().getExtras().get("feed");
		 
		// Initialise the variables:
		lv = (ListView) findViewById(R.id.listView);
		lv.setVerticalFadingEdgeEnabled(true);
		 
		// Set an Adapter to the ListView
		adapter = new CustomListAdapter(this);
		lv.setAdapter(adapter);
		
		//Setting onItem click listener to list view.
		lv.setOnItemClickListener(new OnItemClickListener(){
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				int pos = arg2;
				 
				Bundle bundle = new Bundle();
				bundle.putSerializable("feed", feed);
				Intent intent = new Intent(ListActivity.this,
				DetailActivity.class);
				intent.putExtras(bundle);
				intent.putExtra("pos", pos);
				startActivity(intent);
			
				
			}

		});
	}
	
	protected void onDestroy() {
		super.onDestroy();
		adapter.imageLoader.clearCache();
		adapter.notifyDataSetChanged();
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
		TextView tvTitle = (TextView) listItem.findViewById(R.id.title);
		TextView tvDate = (TextView) listItem.findViewById(R.id.date);
		 
		// Set the views in the layout
		imageLoader.DisplayImage(feed.getItem(pos).getImage(), iv);
		tvTitle.setText(feed.getItem(pos).getTitle());
		tvDate.setText(feed.getItem(pos).getDate());
		 
		return listItem;
		}
		}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
		return true;
	}

}
