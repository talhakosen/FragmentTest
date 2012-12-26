package com.example.fragmenttest;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class Fragment1 extends Fragment  {

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
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.fragment1, container, false);
	}

	private void setSeason(Season season) {
		currentSeason = season;
		onSeasonSelectedListener.onSeasonSelected(season);
	}

	

}
