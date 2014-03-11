package com.proj;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;

public class gallerybaseadapter extends BaseAdapter {

	Integer gal[] = new Integer[]{R.drawable.icon ,R.drawable.projicon};
	private Context c ;
	
	public gallerybaseadapter(gellaeryApp s){
		c = s ;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return gal.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImageView img = new ImageView(c);
		img.setLayoutParams(new Gallery.LayoutParams(150,150));
		img.setScaleType(ImageView.ScaleType.FIT_XY);
		img.setImageResource(gal[position]);
		return img;
	}

}
