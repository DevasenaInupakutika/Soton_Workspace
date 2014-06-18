package com.example.ssitestapp;

import java.util.List;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class BlogListActivity extends Activity implements OnItemClickListener  {
	
	ListView lv;
	List<RSSBlog> arrayList;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feed_list);

		initComponents();

		if (Utils.isNetworkAvailable(this)) {
			new MyRssReadTask()
					.execute("http://www.software.ac.uk/blog/rss-all");

		} else {
			Toast.makeText(this, "No Network Connection!!!", Toast.LENGTH_SHORT)
					.show();
		}

	}

	private void initComponents() {
		lv = (ListView) findViewById(R.id.listView);
		lv.setOnItemClickListener(this);

	}

	class MyRssReadTask extends AsyncTask<String, Void, Void> {
		ProgressDialog waitingDialog;

		@Override
		protected void onPreExecute() {
			waitingDialog = new ProgressDialog(BlogListActivity.this);
			waitingDialog.setMessage("Loading...");
			waitingDialog.show();
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(String... urls) {

			arrayList = new BlogParser().getData(urls[0]);

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			waitingDialog.dismiss();
			setDataToListView();
			super.onPostExecute(result);
		}
	}

	protected void setDataToListView() {
		if (null != arrayList && arrayList.size() != 0) {
			CustomBlogAdapter objBlogAdapter = new CustomBlogAdapter(
					BlogListActivity.this, R.layout.list_item, arrayList);

			lv.setAdapter(objBlogAdapter);
		} else {
			showToast("Error to get Data From WebService!!!");
		}

	}

	void showToast(String msg) {
		Toast.makeText(BlogListActivity.this, msg, Toast.LENGTH_SHORT).show();
	}


	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		int pos = arg2;
		final RSSBlog objBlog = (RSSBlog) arrayList.get(pos);
		
		Intent intent = new Intent(BlogListActivity.this,
				DetailActivity.class);

		intent.putExtra("title", objBlog.getTitle());
		//intent.putExtra("description", objBlog.getDescription());
		intent.putExtra("pubdate", objBlog.getPubDate());
		intent.putExtra("link", objBlog.getLink());
		intent.putExtra("image", objBlog.getImage());

		startActivity(intent);
		
	}

}
