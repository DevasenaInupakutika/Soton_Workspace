package com.gueei.android.binding.listeners;

import android.view.View;

public class OnLongClickListenerMulticast extends MulticastListener<View.OnLongClickListener> 
	implements View.OnLongClickListener {

	@Override
	public void registerToView(View v) {
		v.setOnLongClickListener(this);
	}

	public boolean onLongClick(View v) {
		boolean returnValue = false;
		for(View.OnLongClickListener l : listeners){
			if (l.onLongClick(v)){
				returnValue = true;
			}
		}
		this.invokeCommands(v);
		return returnValue;
	}
}