package com.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CustomView extends View {

//this method( is called when we use just java class	
	public CustomView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		System.out.println("in constructor of customview");
	}

// this mehtod(default constructor with argumentset) is called when we use xml layout	
	public CustomView(Context c, AttributeSet as) {
		super(c, as);
		// TODO Auto-generated constructor stub
		System.out.println("in argument constructor");
	}
	
	
	
	// the ondraw and onmeasure are used to draw the view
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		//super.onDraw(canvas);
		
		Paint p ; // to draw anything we need to use paint .
		p = new Paint();
		p.setColor(Color.GREEN);
		p.setAntiAlias(true); // this method is used to give the smooth edges 
		canvas.drawRect(100, 200, 300, 100, p);
		System.out.println("in on draw method");
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		//super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		setMeasuredDimension(200, 300);
		System.out.println("on measure method");
	}
}
