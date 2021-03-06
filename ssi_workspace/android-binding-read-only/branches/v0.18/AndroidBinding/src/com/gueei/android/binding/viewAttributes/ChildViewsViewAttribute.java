package com.gueei.android.binding.viewAttributes;


import android.view.View;
import android.widget.ViewAnimator;

import com.gueei.android.binding.ViewAttribute;

public class ChildViewsViewAttribute extends ViewAttribute<ViewAnimator, View[]>{

	public ChildViewsViewAttribute(ViewAnimator view) {
		super(View[].class, view, "childViews");
	}

	@Override
	protected void doSetAttributeValue(Object newValue) {
		if (newValue == null){
			getView().removeAllViews();
			return;
		}
		if (newValue instanceof View[]){
			getView().removeAllViews();
			for(View v: (View[])newValue){
				getView().addView(v);
			}
			return;
		}
	}

	@Override
	public View[] get() {
		int count = getView().getChildCount();
		View[] result = new View[count];
		for(int i=0; i<count; i++)
			result[i] = getView().getChildAt(i);
		return result;
	}
}