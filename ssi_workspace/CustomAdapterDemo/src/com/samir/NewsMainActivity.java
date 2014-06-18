package com.samir;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class NewsMainActivity extends Activity implements OnItemClickListener {
	/** Called when the activity is first created. */

	ListView listview;
	List<NewsBean> arrayList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

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
		listview = (ListView) findViewById(android.R.id.list);
		listview.setOnItemClickListener(this);

	}

	class MyRssReadTask extends AsyncTask<String, Void, Void> {
		ProgressDialog waitingDialog;

		@Override
		protected void onPreExecute() {
			waitingDialog = new ProgressDialog(NewsMainActivity.this);
			waitingDialog.setMessage("Loading...");
			waitingDialog.show();
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(String... urls) {

			arrayList = new NewsParser().getData(urls[0]);

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
			NewsRowAdapter objNewsRowAdapter = new NewsRowAdapter(
					NewsMainActivity.this, R.layout.row, arrayList);

			listview.setAdapter(objNewsRowAdapter);
		} else {
			showToast("Error to get Data From WebService!!!");
		}

	}

	void showToast(String msg) {
		Toast.makeText(NewsMainActivity.this, msg, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		showAlertDialogOnListClick(position);
	}

	private void showAlertDialogOnListClick(int position) {

		final NewsBean objBean = (NewsBean) arrayList.get(position);

		AlertDialog alert = new AlertDialog.Builder(this).create();
		alert.setTitle("Position is :: " + position);
		alert.setMessage("Title is :: " + objBean.getTitle());

		alert.setButton("OK", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();

				Intent intent = new Intent(NewsMainActivity.this,
						NewsDetail.class);

				intent.putExtra("title", objBean.getTitle());
				intent.putExtra("description", objBean.getDescription());
				intent.putExtra("pubdate", objBean.getPubDate());
				intent.putExtra("link", objBean.getLink());

				startActivity(intent);

			}
		});
		alert.setButton2("Cancel", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				dialog.dismiss();

			}
		});

		alert.show();
	}
}