package com.example.fragmenttest;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ClipData;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.GridView;

public class MainActivity extends Activity implements OnSeasonSelectedListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_container_layout);

		FragmentManager fm = getFragmentManager();
		Fragment1 fragment1 = (Fragment1) fm.findFragmentById(R.id.container1_1);

		if (fragment1 == null) {
			FragmentTransaction ft = fm.beginTransaction();
			ft.replace(R.id.container1_1, new Fragment1());
			ft.commit();
		}
	}

	@Override
	public void onSeasonSelected(Season season) {
		// TODO Auto-generated method stub

	}

}
