package com.samir;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class NewsRowAdapter extends ArrayAdapter<NewsBean> {

	private Activity activity;
	private List<NewsBean> items;
	private NewsBean objBean;

	public NewsRowAdapter(Activity act, int resource, List<NewsBean> arrayList) {
		super(act, resource, arrayList);

		this.activity = act;
		this.items = arrayList;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHolder holder;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.row, null);

			holder = new ViewHolder();
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		if ((items == null) || ((position + 1) > items.size()))
			return view;

		objBean = items.get(position);

		
		holder.title = (TextView) view.findViewById(R.id.txttitle);
		holder.description = (TextView) view.findViewById(R.id.txtdesc);

		// Button in row with click....
		holder.btn_check = (Button) view.findViewById(R.id.btnnext);
		holder.btn_check.setText("Button : " + position);
		holder.btn_check.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				NewsBean objNewsBean = (NewsBean) items.get(position);

				Intent intent = new Intent(activity, NewsDetail.class);
				intent.putExtra("title", objNewsBean.getTitle());
				intent.putExtra("description", objNewsBean.getDescription());
				intent.putExtra("pubdate", objNewsBean.getPubDate());
				intent.putExtra("link", objNewsBean.getLink());

				activity.startActivity(intent);

			}
		});

		if (holder.title != null && null != objBean.getTitle()
				&& objBean.getTitle().trim().length() > 0) {
			holder.title.setText(Html.fromHtml(objBean.getTitle()));
		}
		if (holder.description != null && null != objBean.getDescription()
				&& objBean.getDescription().trim().length() > 0) {
			holder.description.setText(Html.fromHtml(objBean.getDescription()));
		}

		
		// Even and odd Row...
		if ((position % 2) == 0) {
			view.setBackgroundResource(R.drawable.bg_list_even);
		} else {
			view.setBackgroundResource(R.drawable.bg_list_odd);
		}
		
		return view;
	}

	public class ViewHolder {

		public TextView title;
		public TextView description;
		public Button btn_check;

	}

}
