package com.example.ssitestapp;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ListActivity extends Activity {

	
	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			//NavUtils.navigateUpFromSameTask(this);
			switch(item.getItemId())
			{
			case android.R.id.home:
				//Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
				super.onBackPressed();
			}
			return true;
		}
		//return true;
		
		//Intent that will return to previous activity.
		Intent myIntent = new Intent(getApplicationContext(),MainActivity.class );
	    startActivityForResult(myIntent, 0);
	   
		return super.onOptionsItemSelected(item);
	}

	Application myApp;
	RSSFeed feed;
	ListView lv = null;
	CustomListAdapter adapter = null;
	boolean flag_loading = true;
	boolean loadingMore = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.feed_list);
		// Get feed from the file
		feed = (RSSFeed) getIntent().getExtras().get("feed");
		// Initialise the variables:
		lv = (ListView) findViewById(R.id.listView);
		lv.setVerticalFadingEdgeEnabled(true);
		
		myApp = getApplication();
		
		adapter = new CustomListAdapter(this);
		lv.setAdapter(adapter);
		
		
		//Endless Scrolling list.
		lv.setOnScrollListener(new OnScrollListener() {

			@Override
			
	        public void onScrollStateChanged(AbsListView view, int scrollState) {

                  //Do Nothing.
	        }
          
	        @Override
	        public void onScroll(AbsListView view, int firstVisibleItem,
	                int visibleItemCount, int totalItemCount) {

	        	//what is the bottom item that is visible
	        	int lastInScreen = firstVisibleItem+visibleItemCount;
	        	//is the bottom item visible & not loading more already ? Load more !
	            if( (lastInScreen == totalItemCount) && !(loadingMore))
	            {
	                
	            }
	        }

			/*private void additems() {
				// TODO Auto-generated method stub
				lv.setAdapter(adapter);
				flag_loading = false;
				adapter.notifyDataSetChanged();
			}*/
			
		
	    });
		
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
				ActionBar actionBar = getActionBar();
		        actionBar.setDisplayHomeAsUpEnabled(true);
				
				// Show the Up button in the action bar.
				setupActionBar();
	}


	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

  // @Override
    protected void onDestroy() {
		super.onDestroy();
		adapter.imageLoader.clearCache();
		adapter.notifyDataSetChanged();
		}
	
	class CustomListAdapter extends BaseAdapter{

		private LayoutInflater layoutInflater;
		public ImageLoader imageLoader;
		
		 
		public CustomListAdapter(ListActivity activity) {
		 
		layoutInflater = (LayoutInflater) activity
		                  .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader = new ImageLoader(activity.getApplicationContext());
		
		
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return feed.getItemCount();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View listItem = convertView;
				int pos = position;
				if (listItem == null) {
				          listItem = layoutInflater.inflate(R.layout.list_item, null);
				}
				 // Initialise the views in the layout
				ImageView iv = (ImageView) listItem.findViewById(R.id.thumb);
				TextView tvTitle = (TextView) listItem.findViewById(R.id.title);
				TextView tvDate = (TextView) listItem.findViewById(R.id.date);
				 
				// Set the views in the layout
				imageLoader.DisplayImage(feed.getItem(pos).getImage(), iv);
				tvTitle.setText(feed.getItem(pos).getTitle());
				
				//Including day and date.
				tvDate.setText(feed.getItem(pos).getDate().substring(0, 16));
			    return listItem;
			
		}
	}
}
