package com.alaram;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class Allaram extends Activity {
    /** Called when the activity is first created. */
	PendingIntent pi ;
	AlarmManager am ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        am = (AlarmManager) getSystemService(Context.ALARM_SERVICE); //static method as am is the predfined
        
        Intent i = new Intent(getApplicationContext() , alramrecevie.class);
        
         pi = PendingIntent.getBroadcast(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+10000, 1000, pi);
    }
}