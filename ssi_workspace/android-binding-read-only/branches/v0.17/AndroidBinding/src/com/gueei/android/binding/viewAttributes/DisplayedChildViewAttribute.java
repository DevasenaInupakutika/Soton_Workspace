package com.gueei.android.binding.viewAttributes;


import android.widget.ViewAnimator;

import com.gueei.android.binding.ViewAttribute;

public class DisplayedChildViewAttribute extends ViewAttribute<ViewAnimator, Integer>{

	public DisplayedChildViewAttribute(ViewAnimator view) {
		super(Integer.class, view, "displayedChild");
	}

	@Override
	protected void doSetAttributeValue(Object newValue) {
		int totalView = getView().getChildCount();
		if (newValue == null){
			return;
		}
		if (newValue instanceof Integer){
			int newInt = (Integer)newValue;
			if (newInt < totalView)
				getView().setDisplayedChild(newInt);
			return;
		}
	}

	@Override
	public Integer get() {
		return getView().getDisplayedChild();
	}
}