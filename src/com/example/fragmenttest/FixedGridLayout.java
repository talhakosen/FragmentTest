package com.example.fragmenttest;

// Need the following import to get access to the app resources, since this
// class is in a sub-package.
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * A layout that arranges its children in a grid. The size of the cells is set
 * by the {@link #setCellSize} method and the android:cell_width and
 * android:cell_height attributes in XML. The number of rows and columns is
 * determined at runtime. Each cell contains exactly one view, and they flow in
 * the natural child order (the order in which they were added, or the index in
 * {@link #addViewAt}. Views can not span multiple cells.
 * 
 * <p>
 * This class was copied from the FixedGridLayout Api demo; see that demo for
 * more information on using the layout.
 * </p>
 */
class FixedGridLayout extends ViewGroup {
	int mCellWidth;
	int mCellHeight;

	public FixedGridLayout(Context context) {
		super(context);
	}

	public void setCellWidth(int px) {
		mCellWidth = px;
		requestLayout();
	}

	public void setCellHeight(int px) {
		mCellHeight = px;
		requestLayout();
	}
	
	public void setCellWidthHeight(int length) {
		int w = getMeasuredWidth();
		int h = getMeasuredHeight();
		
		double m = Math.sqrt(length);		
		
		//mCellHeight = length;
		requestLayout();
	}
	
	

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int cellWidthSpec = MeasureSpec.makeMeasureSpec(mCellWidth,MeasureSpec.AT_MOST);
		int cellHeightSpec = MeasureSpec.makeMeasureSpec(mCellHeight,MeasureSpec.AT_MOST);

		int count = getChildCount();
		for (int index = 0; index < count; index++) {
			final View child = getChildAt(index);
			child.measure(cellWidthSpec, cellHeightSpec);
		}
		// Use the size our parents gave us, but default to a minimum size to
		// avoid
		// clipping transitioning children
		int minCount = count > 3 ? count : 3;
		
		setMeasuredDimension(resolveSize(mCellWidth * minCount, widthMeasureSpec),resolveSize(mCellHeight * minCount, heightMeasureSpec));
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int cellWidth = mCellWidth;
		int cellHeight = mCellHeight;
		int columns = (r - l) / cellWidth;
		if (columns < 0) {
			columns = 1;
		}
		int x = 0;
		int y = 0;
		int i = 0;
		
		int count = getChildCount();
		
		for (int index = 0; index < count; index++) {
			final View child = getChildAt(index);

			int w = child.getMeasuredWidth();
			int h = child.getMeasuredHeight();

			int left = x + ((cellWidth - w) / 2);
			int top = y + ((cellHeight - h) / 2);

			child.layout(left, top, left + w, top + h);
			
			if (i >= (columns - 1)) {
				// advance to next row
				i = 0;
				x = 0;
				y += cellHeight;
			} else {
				i++;
				x += cellWidth;
			}
		}
		
	}
}