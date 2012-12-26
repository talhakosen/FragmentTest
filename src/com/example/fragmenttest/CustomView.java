package com.example.fragmenttest;

import java.util.ArrayList;

import android.content.ClipData;
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
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class CustomView extends View {
	private Paint paint, paintBorder, paintText;
	private ArrayList<Point> points;
	private ArrayList<ArrayList> strokes;
	Drawable enterShape;
	Drawable normalShape;

	public CustomView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		points = new ArrayList();
		strokes = new ArrayList();
		paint = createPaint(Color.GREEN, 8);
		enterShape = getResources().getDrawable(R.drawable.shape_droptarget);
		normalShape = getResources().getDrawable(R.drawable.shape);
	}

	public CustomView(Context context, AttributeSet attrs) {
		super(context, attrs);
		points = new ArrayList();
		strokes = new ArrayList();
		paint = createPaint(Color.GREEN, 8);
		enterShape = getResources().getDrawable(R.drawable.shape_droptarget);
		normalShape = getResources().getDrawable(R.drawable.shape);
	
	}

	public CustomView(Context context) {
		super(context);
		points = new ArrayList();
		strokes = new ArrayList();
		paint = createPaint(Color.GREEN, 8);
		enterShape = getResources().getDrawable(R.drawable.shape_droptarget);
		normalShape = getResources().getDrawable(R.drawable.shape);
	}

	@Override
	public void onDraw(Canvas c) {
		super.onDraw(c);
		this.setBackgroundColor(Color.WHITE);
		paintBorder = createPaint(Color.BLACK, 1);
		c.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paintBorder);

		paintText = createPaint(Color.RED, 5);
		c.drawText("1", getMeasuredWidth() / 2, getMeasuredHeight() / 2,
				paintText);

		for (ArrayList stroke : strokes) {
			drawStroke(stroke, c);
		}

		drawStroke(points, c);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getActionMasked() == MotionEvent.ACTION_MOVE) {
			points.add(new Point((int) event.getX(), (int) event.getY()));
			invalidate();
		}

		if (event.getActionMasked() == MotionEvent.ACTION_UP) {
			this.strokes.add(points);
			points = new ArrayList();
		}

		return true;
	}

	private void drawStroke(ArrayList stroke, Canvas c) {
		if (stroke.size() > 0) {
			Point p0 = (Point) stroke.get(0);
			for (int i = 1; i < stroke.size(); i++) {
				Point p1 = (Point) stroke.get(i);
				c.drawLine(p0.x, p0.y, p1.x, p1.y, paint);
				p0 = p1;
			}
		}
	}

	private Paint createPaint(int color, float width) {
		Paint temp = new Paint();
		temp.setStyle(Paint.Style.STROKE);
		temp.setAntiAlias(true);
		temp.setColor(color);
		temp.setStrokeWidth(width);
		temp.setStrokeCap(Cap.ROUND);
		return temp;
	}

	
}