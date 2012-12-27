package com.example.fragmenttest;

import java.util.Random;

import android.app.Activity;
import android.app.Fragment;
import android.content.ClipData;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

public class Fragment1 extends Fragment implements OnDragListener,
		OnTouchListener {

	private OnSeasonSelectedListener onSeasonSelectedListener;
	private Season currentSeason;
	Drawable enterShape;
	Drawable normalShape;
	Random rnd = new Random();

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			enterShape = getResources().getDrawable(R.drawable.shape_droptarget);
			normalShape = getResources().getDrawable(R.drawable.shape);
			Season s = new Season();
			s.s = "fragment";
			
			onSeasonSelectedListener = (OnSeasonSelectedListener) activity;
			setSeason(s);
			
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnSeasonSelectedListener");
		}

	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View vi = inflater.inflate(R.layout.fragment1, container, false);

		FrameLayout fr = (FrameLayout) vi.findViewById(R.id.frameLayout1);

		Display display = getActivity().getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;

		FixedGridLayout gr = new FixedGridLayout(getActivity());
		((FixedGridLayout) gr).setCellHeight(150);
		((FixedGridLayout) gr).setCellWidth(150);

		String Sorular[] = { "F-E-N-E-R-B-A-H-Ç-E", "A-N-D-R-O-İ-D", "F-A-C-E-B-O-O-K","Ü-M-İ-T- -B-E-S-E-N" };
		int i1 = rnd.nextInt(Sorular.length - 1);		
		String Soru= Sorular[i1];
		
		String sArray[] = Soru.split("-");
		shuffleArray(sArray);

		for (int i = 0; i < sArray.length; i++) {
			FrameLayout f = new FrameLayout(getActivity());
			f.setLayoutParams(new android.widget.FrameLayout.LayoutParams(300,
					300));
			f.setBackgroundColor(Color.parseColor("#A9BCF5"));
			com.example.fragmenttest.CustomView cView = new CustomView(
					getActivity(), sArray[i]);
			cView.setOnTouchListener(this);
			f.setOnDragListener(this);
			f.setPadding(10, 10, 10, 10);
			f.addView(cView);
			gr.addView(f, Math.min(5, gr.getChildCount()));
		}

		gr.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		fr.addView(gr);
		fr.setClipChildren(false);

		return vi;
	}

	static void shuffleArray(String[] ar) {
		 Random rnd2 = new Random();
		for (int i = ar.length - 1; i >= 0; i--) {
			int index = rnd2.nextInt(i + 1);
			// Simple swap
			String a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}

	private void setSeason(Season season) {
		currentSeason = season;
		onSeasonSelectedListener.onSeasonSelected(season);
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
			v.setBackgroundColor(Color.parseColor("#CEF6EC"));
			break;
		case DragEvent.ACTION_DRAG_EXITED:
			v.setBackgroundColor(Color.parseColor("#A9BCF5"));
			View viewE = (View) event.getLocalState();
			viewE.setVisibility(View.VISIBLE);
			break;
		case DragEvent.ACTION_DROP:
			try {
				// Dropped, reassign View to ViewGroup
				View view = (View) event.getLocalState();
				ViewGroup owner = (ViewGroup) view.getParent();
				FrameLayout container = (FrameLayout) v;

				if (owner != v) {
					owner.removeAllViews();
					View tempView = container.getChildAt(0);
					container.removeAllViews();
					owner.addView(tempView);
					container.addView(view);
				}
				view.setVisibility(View.VISIBLE);

			} catch (Exception e) {
				System.out.println(e.toString());
			}
			break;
		case DragEvent.ACTION_DRAG_ENDED:
			// ((FrameLayout) v.getParent()).setBackgroundDrawable(normalShape);
			v.setBackgroundColor(Color.parseColor("#A9BCF5"));
		default:
			break;
		}
		return true;
	}

}
