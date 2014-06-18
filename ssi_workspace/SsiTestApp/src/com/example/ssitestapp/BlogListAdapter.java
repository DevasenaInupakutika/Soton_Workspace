package com.example.ssitestapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;

public class BlogListAdapter extends InfiniteScrollListAdapter {
	
	// A placeholder for all the data points
		private List<RSSItem> entries = new ArrayList<RSSItem>();
		private NewPageListener newPageListener;
		
		// A demo listener to pass actions from view to adapter
		public static abstract class NewPageListener {
			public abstract void onScrollNext();
			public abstract View getInfiniteScrollListView(int position, View convertView, ViewGroup parent);
		}

		public BlogListAdapter(NewPageListener newPageListener) {
			this.newPageListener = newPageListener;
		}

		public void addEntriesToTop(List<RSSItem> entries) {
			// Add entries in reversed order to achieve a sequence used in most of messaging/chat apps
			if (entries != null) {
				Collections.reverse(entries);
			}
			// Add entries to the top of the list
			this.entries.addAll(0, entries);
			notifyDataSetChanged();
		}

		public void addEntriesToBottom(List<RSSItem> entries) {
			// Add entries to the bottom of the list
			this.entries.addAll(entries);
			notifyDataSetChanged();
		}

		public void clearEntries() {
			// Clear all the data points
			this.entries.clear();
			notifyDataSetChanged();
		}


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return entries.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return entries.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	protected void onScrollNext() {
		// TODO Auto-generated method stub
		if (newPageListener != null) {
			newPageListener.onScrollNext();
		}
		
	}

	@Override
	public View getInfiniteScrollListView(int position, View convertView,
			ViewGroup parent) {
		// TODO Auto-generated method stub
		if (newPageListener != null) {
			return newPageListener.getInfiniteScrollListView(position, convertView, parent);
		}
		return convertView;
	}

}
