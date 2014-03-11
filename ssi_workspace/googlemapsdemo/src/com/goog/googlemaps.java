package com.goog;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class googlemaps extends MapActivity {
    /** Called when the activity is first created. */
	MapView mv;
	LinearLayout ll;
	View zv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mv =(MapView) findViewById(R.id.map);
        ll = (LinearLayout) findViewById(R.id.root2);
        
       zv =  mv.getZoomControls();
       
       //ll.addView(zv ,new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT),LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT));
      ll.addView(zv,new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT ,LayoutParams.WRAP_CONTENT));
      mv.displayZoomControls(true);
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		
		return false;
	}
}