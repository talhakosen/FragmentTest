package com.example.fragmenttest;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends Activity implements OnSeasonSelectedListener,OnDragListener,OnTouchListener {
	Drawable enterShape;
	Drawable normalShape;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_container_layout);

		enterShape = getResources().getDrawable(R.drawable.shape_droptarget);
		normalShape = getResources().getDrawable(R.drawable.shape);

		FragmentManager fm = getFragmentManager();
		Fragment1 fragment1 = (Fragment1) fm.findFragmentById(R.id.container1_1);

		if (fragment1 == null) {
			FragmentTransaction ft = fm.beginTransaction();
			//ft.replace(R.id.container1_1, new Fragment1());
			ft.replace(R.id.container1_2, new Fragment2());
			ft.replace(R.id.container1_3, new Fragment3());
			ft.replace(R.id.container2_1, new Fragment4());
			ft.replace(R.id.container2_2, new Fragment5());
			ft.replace(R.id.container2_3, new Fragment6());
			ft.replace(R.id.container3_1, new Fragment7());
			ft.replace(R.id.container3_2, new Fragment8());
			ft.replace(R.id.container3_3, new Fragment9());
			ft.commit();
		}
		
		
		findViewById(R.id.container1_1).setOnTouchListener(this);
		findViewById(R.id.container1_2).setOnTouchListener(this);
		findViewById(R.id.container1_3).setOnTouchListener(this);
		
		findViewById(R.id.container1_1).setOnDragListener(this);
		findViewById(R.id.container1_2).setOnDragListener(this);
		findViewById(R.id.container1_3).setOnDragListener(this);
		
	

	}

	@Override
	public void onSeasonSelected(Season season) {
		// TODO Auto-generated method stub

	}

	public boolean onTouch(View view, MotionEvent motionEvent) {
		if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
			ClipData data = ClipData.newPlainText("", "");
			DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
			view.startDrag(data, shadowBuilder, view, 0);
			view.setVisibility(View.INVISIBLE);
			return true;
		} else {
			return false;
		}
	}

	public boolean onDrag(View v, DragEvent event) {
		int action = event.getAction();
		switch (action) {
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
			try {

				// Dropped, reassign View to ViewGroup
				View view = (View) event.getLocalState();
				ViewGroup owner = (ViewGroup) view.getParent();
				owner.removeView(view);				
				FrameLayout container = (FrameLayout) v;
				container.addView(view);
				view.setVisibility(View.VISIBLE);
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			break;
		case DragEvent.ACTION_DRAG_ENDED:
			v.setBackgroundDrawable(normalShape);
		default:
			break;
		}
		return true;
	}
}
