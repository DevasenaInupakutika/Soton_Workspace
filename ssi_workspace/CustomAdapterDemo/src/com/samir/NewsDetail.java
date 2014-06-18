package com.samir;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class NewsDetail extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);

		Bundle b = this.getIntent().getExtras();

		String title = b.getString("title");
		String desc = b.getString("description");
		String date = b.getString("pubdate");
		final String link = b.getString("link");
		date = date.substring(0, 16);

		TextView tvtitle = (TextView) findViewById(R.id.tvtitle);
		TextView tvdesc = (TextView) findViewById(R.id.tvdesc);
		TextView tvdate = (TextView) findViewById(R.id.tvdate);

		Button btnWeb = (Button) findViewById(R.id.btngotolink);
		btnWeb.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link)));
			}
		});

		tvtitle.setText(title);
		tvdesc.setText(desc);
		tvdate.setText(Utils.getDateFormate(date));

		setTitle(title);
	}

}
