package com.example.fragmenttest;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnDragListener;
import android.view.ViewGroup;

public class Fragment6 extends Fragment implements OnDragListener {

	private OnSeasonSelectedListener onSeasonSelectedListener;
	private Season currentSeason;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
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

		return inflater.inflate(R.layout.fragment6, container, false);
	}

	private void setSeason(Season season) {
		currentSeason = season;
		onSeasonSelectedListener.onSeasonSelected(season);
	}

	@Override
	public boolean onDrag(View v, DragEvent event) {		
		return true;

	}

}
