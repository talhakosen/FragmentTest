package com.example.fragmenttest;

import android.app.Activity;
import android.app.Fragment;

/**
 * MOVED TO PA4AD_Ch04_Seasons
 */
public class SeasonFragment extends Fragment {

	public interface OnSeasonSelectedListener {
		public void onSeasonSelected(Season season);
	}

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

	private void setSeason(Season season) {
		currentSeason = season;
		onSeasonSelectedListener.onSeasonSelected(season);
	}

}
