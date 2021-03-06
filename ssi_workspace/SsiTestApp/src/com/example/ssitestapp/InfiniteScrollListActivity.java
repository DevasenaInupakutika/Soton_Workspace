package com.example.ssitestapp;

import java.util.List;

import com.example.ssitestapp.InfiniteScrollListView.LoadingMode;
import com.example.ssitestapp.InfiniteScrollListView.StopPosition;
import com.example.ssitestapp.BlogListAdapter.NewPageListener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

public class InfiniteScrollListActivity extends Activity{
		
	// A setting for how many items should be loaded at once from the server (software.ac.uk)
	
	private static final int SERVER_SIDE_BATCH_SIZE = 10;
	private static final String RSSFEEDURL = "http://www.software.ac.uk/blog/rss-all";
	
	private InfiniteScrollListView lv;
	private ImageView iv;
	private ImageLoader imageLoader;
	private TextView tvTitle;
	private TextView tvDate;
	
	private BlogListAdapter bloglistAdapter;
	private Handler handler;
	private BlogRemoteService blogRemoteService;
	
	private AsyncTask<Void, Void, List<RSSItem>> fetchAsyncTask;
	private RSSFeed feed;
	
	private LoadingMode loadingMode = LoadingMode.SCROLL_TO_BOTTOM;
	private StopPosition stopPosition = StopPosition.REMAIN_UNCHANGED;

	public InfiniteScrollListActivity(){
		super();
		// Get feed from the file
				feed = (RSSFeed) getIntent().getExtras().get("feed");
	}
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feed_list);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		handler = new Handler();
		
		// Initialise the variables
		lv = (InfiniteScrollListView) findViewById(R.id.listView);
		lv.setVerticalFadingEdgeEnabled(true);
		
		lv.setLoadingMode(loadingMode);
		LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		lv.setLoadingView(layoutInflater.inflate(R.layout.activity_display_blogs_list,null));
		
		bloglistAdapter = new BlogListAdapter(new NewPageListener(){

			@Override
			public void onScrollNext() {
				// TODO Auto-generated method stub
				fetchAsyncTask = new AsyncTask<Void,Void,List<RSSItem>>(){

					@Override
					protected void onPreExecute() {
						// Loading lock to allow only one instance of loading
                    	bloglistAdapter.lock();
					}
					@Override
					protected List<RSSItem> doInBackground(Void... params) {
						// TODO Auto-generated method stub
						List<RSSItem> result;
						//Mimic loading from a remote service.
						if(loadingMode == LoadingMode.SCROLL_TO_TOP){
							result = blogRemoteService.getNextBlogBatch(SERVER_SIDE_BATCH_SIZE);
						}
						else{
							result = blogRemoteService.getNextBlogBatch(SERVER_SIDE_BATCH_SIZE);
						}
						return result;
					}
					
					@Override
                    protected void onPostExecute(List<RSSItem> result) {
        				if (isCancelled() || result == null || result.isEmpty()) {
        					bloglistAdapter.notifyEndOfList();
        				} else {
        					// Add data to the placeholder
        					if (loadingMode == LoadingMode.SCROLL_TO_TOP) {
        						bloglistAdapter.addEntriesToTop(result);
        					} else {
            					bloglistAdapter.addEntriesToBottom(result);
        					}
        					// Add or remove the loading view depend on if there might be more to load
        					if (result.size() < SERVER_SIDE_BATCH_SIZE) {
        						bloglistAdapter.notifyEndOfList();
        					} else {
        						bloglistAdapter.notifyHasMore();
        					}
        					// Get the focus to the specified position when loading completes
        					if (loadingMode == LoadingMode.SCROLL_TO_TOP) {
        						switch(stopPosition) {
        							case REMAIN_UNCHANGED:
                						lv.setSelection(result.size());
                						break;
        							case START_OF_LIST:
        								lv.setSelection(result.size() < SERVER_SIDE_BATCH_SIZE ? 0 : 1);
                    					break;
        							case END_OF_LIST:
                						lv.setSelection(1);
    	        						lv.smoothScrollToPosition(bloglistAdapter.getCount());
        						}
        					} else {
        						if (stopPosition != StopPosition.REMAIN_UNCHANGED) {
        							lv.smoothScrollToPosition(stopPosition == StopPosition.START_OF_LIST ? 0 : (result.size() < SERVER_SIDE_BATCH_SIZE ? bloglistAdapter.getCount() : bloglistAdapter.getCount() - 2));
        						}
        					}
        				}
                    };
                    @Override
                    protected void onCancelled() {
                    	// Tell the adapter it is end of the list when task is cancelled
    					bloglistAdapter.notifyEndOfList();
                    }
				}.execute();
			}		
				
			@Override
			public View getInfiniteScrollListView(int position,
					View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				// Customize the row for list view
				if(convertView == null) {
					LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					convertView = layoutInflater.inflate(R.layout.list_item, null);
				}
				
				RSSItem blog = (RSSItem) bloglistAdapter.getItem(position);
				
				if (blog != null) {
				    
					iv = (ImageView) convertView.findViewById(R.id.thumb);
					tvTitle = (TextView) convertView.findViewById(R.id.title);
					tvDate = (TextView) convertView.findViewById(R.id.date);
					
					//Set the views in the layout
					imageLoader.DisplayImage(blog.getImage(), iv);
					tvTitle.setText(blog.getTitle());
					tvDate.setText(blog.getDate());
					
				}
				
				return convertView;
			}
			
			
		});
		
		lv.setAdapter(bloglistAdapter);
		
		//Display blog when a list item is clicked.
		
		lv.setOnItemClickListener(new OnItemClickListener(){
			
			public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
				handler.post(new Runnable() {
					@Override
					public void run() {
						int pos = position;
						Intent intent = new Intent(InfiniteScrollListActivity.this,DetailActivity.class);
						intent.putExtra("pos", pos);
						startActivity(intent);
						
					}
			
		});
		
		//Scrolling logic implementation when list view is scrolled to TOP/ BOTTOM
	
			
	
			}
		});
		
}
	

	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//Load first page to start
		bloglistAdapter.onScrollNext();
	}
		
		
	
}
