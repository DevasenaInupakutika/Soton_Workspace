package com.example.ssitestapp;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class ListActivity extends Activity{
	
	Application myApp;
	RSSFeed feed;
	ListView lv = null;
	CustomListAdapter adapter = null;
	
	private ProgressDialog pDialog;
	private static final String RSSFEEDURL = "http://www.software.ac.uk/blog/rss-all";

	private static final String TAG = "MyActivity";
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
			//super.onBackPressed();
			
		}
		//Intent that will return to previous activity.
				Intent myIntent = new Intent(getApplicationContext(),MainActivity.class );
				myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			    startActivityForResult(myIntent, 0);

				return super.onOptionsItemSelected(item);
	}
	
	//Variables to be used in pagination logic
	private final static int ITEMS_PPAGE = 20;
	boolean loadingMore = true;
	boolean lastPage = false;
	int currentPage = 0;
	int previousTotal = 0;
	int visibleThreshold = 5;
	
	//views in activity
	ImageView iv = null;
	TextView tvTitle = null;
	TextView tvDate = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feed_list);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		// Get feed from the file
		feed = (RSSFeed) getIntent().getExtras().get("feed");
		
		// Initialise the variables
		lv = (ListView) findViewById(R.id.listView);
		lv.setVerticalFadingEdgeEnabled(true);
		
		//myApp = getApplication();
	    
		
		adapter = new CustomListAdapter(ListActivity.this);
		lv.setAdapter(adapter);
		new loadListView().execute();		

		//Endless scrolling list.
		
		lv.setOnScrollListener(new OnScrollListener() {
			
			@Override

	        public void onScrollStateChanged(AbsListView view, int scrollState) {

                  //Do Nothing.
	        }
          
	        @Override
	        public void onScroll(AbsListView view, int firstVisibleItem,
	                int visibleItemCount, int totalItemCount) {
	        	
	        	if(loadingMore){
	        		if (totalItemCount > previousTotal) {
	                    loadingMore = false;
	                    previousTotal = totalItemCount;
	                    currentPage++;
	                    
	                    // Find your own condition in order to know when you 
	                    // have finished displaying all items
	                    if (currentPage + 1 > 50) {
	                        lastPage = true;
	                    }
	                }
	        	}
	        	
	        	if (!lastPage && !loadingMore && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
	                // I load the next page of blogs using a background task,
	                // but you can call any function here.
	                new addListItemsAsyncTask().execute();
	                loadingMore = true;
	            }
	        	

	        }


	    });
	
		//Setting onItem click listener to List View.
		
		lv.setOnItemClickListener(new OnItemClickListener(){
		
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// actions to be performed when a list item clicked
				int pos = arg2;
				Bundle bundle = new Bundle();
				bundle.putSerializable("feed", feed);
				Intent intent = new Intent(ListActivity.this,DetailActivity.class);
				intent.putExtras(bundle);
				intent.putExtra("pos", pos);
				startActivity(intent);
				}

			
		});
	
		//Show up button in the action bar.
		//setupActionBar();
			
	}
	
  /*private void setupActionBar() {
		// TODO Auto-generated method stub
	  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			
			getActionBar().setDisplayHomeAsUpEnabled(true);
		
	}
}*/


	/*// @Override
    protected void onDestroy() {
		super.onDestroy();
		//adapter.imageLoader.clearCache();
		adapter.notifyDataSetChanged();
		}*/
	
    public class loadListView extends AsyncTask<Integer, String, String>{

    	/* (non-Javadoc)
		 * @see android.os.AsyncTask#onPreExecute()
		 */
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			 pDialog = new ProgressDialog(ListActivity.this);
	            pDialog.setTitle("Connect to Server");
	            pDialog.setMessage("This process can take a few seconds to a few minutes, depending on your Internet Connection Speed.");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(false);
	            pDialog.show();
		}

		
		@Override
		protected String doInBackground(Integer... params) {
			// TODO Auto-generated method stub
			DOMParser myParser = new DOMParser();
			String xml = myParser.getXmlFromUrl(RSSFEEDURL);
            feed = myParser.parseXml(xml,0);
            
			return null;
		}
		
		/* (non-Javadoc)
		 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
		 */
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			//super.onPostExecute(result);
			
			//ActionBar actionBar = getActionBar();
			//actionBar.setDisplayHomeAsUpEnabled(true);
			
			pDialog.dismiss();
		}
    	
    }
    
    public class addListItemsAsyncTask extends AsyncTask<Integer, String, String>{

    	
		/* (non-Javadoc)
		 * @see android.os.AsyncTask#onPreExecute()
		 */
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(Integer... params) {
			// TODO Auto-generated method stub
			new Thread(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					// Parsing 20 more items and adding them to the adapter
					try{
					
                    //Adding parsed items to adapter.
                    for (int i = currentPage * ITEMS_PPAGE; i < (currentPage + 1) * ITEMS_PPAGE; i++) {
                    	
                    	DOMParser myParser = new DOMParser();
            			String xml = myParser.getXmlFromUrl(RSSFEEDURL);
                        feed = myParser.parseXml(xml,currentPage);
                        
                        ListActivity.this.adapter.getView(i, lv, null);
                        		//String.valueOf(Math.random() * 5000));
                    }
                    ListActivity.this.adapter.notifyDataSetChanged();
					}
					catch (Exception ex){
						ex.printStackTrace();
					}
				}
				
				
			}).start();
			return null;
		}
		

		/* (non-Javadoc)
		 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
		 */
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}

    	
    }
 
	class CustomListAdapter extends BaseAdapter{
		
		private LayoutInflater layoutInflater;
		public ImageLoader imageLoader;
		
		
		public CustomListAdapter(ListActivity activity) {
		 
		layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
				
					// Initialise the views in the layout
					iv = (ImageView) listItem.findViewById(R.id.thumb);
					tvTitle = (TextView) listItem.findViewById(R.id.title);
					tvDate = (TextView) listItem.findViewById(R.id.date);
				 	
				
				}
			
			// Set the views in the layout
			    imageLoader.DisplayImage(feed.getItem(pos).getImage(), iv);
				tvTitle.setText(feed.getItem(pos).getTitle());
			
				Log.v(TAG, "Link is:"+feed.getItem(pos).getLink());
		    
				//Including day and date.
				tvDate.setText(feed.getItem(pos).getDate().substring(4, 16));
			
			
			return listItem;
		
		}
	}
	
	
}
