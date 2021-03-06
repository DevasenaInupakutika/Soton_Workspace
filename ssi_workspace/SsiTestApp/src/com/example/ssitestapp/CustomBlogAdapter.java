package com.example.ssitestapp;

import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomBlogAdapter extends ArrayAdapter<RSSBlog> {

	
	private Activity activity;
	private List<RSSBlog> items;
	private RSSBlog objBlog;
	
	public CustomBlogAdapter(Activity act, int resource,
			List<RSSBlog> objects) {
		super(act, resource, objects);
		// TODO Auto-generated constructor stub
		this.activity = act;
		this.items = objects;
	}
	
	/* (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = convertView;
		ViewHolder holder;
		ImageLoader imageloader;
		
		if(view == null){
			LayoutInflater inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.list_item, null);
			imageloader = new ImageLoader(activity.getApplicationContext());

			holder = new ViewHolder();
			view.setTag(holder);
		}
		else{
			holder = (ViewHolder) view.getTag();	
		}
		
		if ((items == null) || ((position + 1) > items.size()))
		   return view;
		
		objBlog = items.get(position);
		
		holder.tvtitle = (TextView) view.findViewById(R.id.title);
		holder.tvdate = (TextView) view.findViewById(R.id.date);
		holder.iv = (ImageView) view.findViewById(R.id.thumb);
		
		
		
	
	return view;
	}
	
	public class ViewHolder{
		
		public TextView tvtitle;
		public ImageView iv;
		public TextView tvdate;
		
	}


}
