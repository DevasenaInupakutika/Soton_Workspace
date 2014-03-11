package com.prac;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class alaramprac extends Activity {
    /** Called when the activity is first created. */
	AlarmManager am ;
	EditText edt ;
	Button btn ;
	String string = "";
	int a = 0 ;
	PendingIntent pi ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
       edt = (EditText) findViewById(R.id.edit);
       btn = (Button) findViewById(R.id.btn);
       
       
       btn.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			string = edt.getText().toString();
		       
		       a = Integer.parseInt(string);
		       am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		       
		       Intent i = new Intent(getApplicationContext() , Alaramrecevier.class);
		       
		       pi = PendingIntent.getBroadcast(getApplicationContext(), 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
		       am.setRepeating(AlarmManager.RTC_WAKEUP, a, 0, pi);
			
		}
	});
       
    }
}