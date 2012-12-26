package com.example.fragmenttest;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.drawable.Drawable;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnDragListener;
import android.widget.LinearLayout;

public class MyDragListener implements OnDragListener {
	Drawable enterShape;
	Drawable normalShape;

	public MyDragListener(Context context) {
		enterShape = context.getResources().getDrawable(
				R.drawable.shape_droptarget);
		normalShape = context.getResources().getDrawable(R.drawable.shape);
	}

	@Override
	public boolean onDrag(View v, DragEvent event) {
		int action = event.getAction();
		switch (event.getAction()) {
		case DragEvent.ACTION_DRAG_STARTED:
			// Do nothing
			break;
		case DragEvent.ACTION_DRAG_ENTERED:
			v.setBackgroundDrawable(enterShape);
			break;
		case DragEvent.ACTION_DRAG_EXITED:
			v.setBackgroundDrawable(normalShape);
			break;
		case DragEvent.ACTION_DROP:
			// Dropped, reassign View to ViewGroup
			View view = (View) event.getLocalState();
			ViewGroup owner = (ViewGroup) view.getParent();
			owner.removeView(view);
			LinearLayout container = (LinearLayout) v;
			container.addView(view);
			view.setVisibility(View.VISIBLE);
			break;
		case DragEvent.ACTION_DRAG_ENDED:
			v.setBackgroundDrawable(normalShape);
		default:
			break;
		}
		return true;
	}
}
