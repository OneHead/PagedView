package com.zoe.pagedview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.ViewGroup;

public class PagedView extends ViewGroup implements OnGestureListener {
	private final String TAG = "PagedView";
	private final boolean DEBUG = false;
	private int mCurrentIndex;
	private float mDownX;
	private DistanceHelper mDistanceHelper;
	GestureDetector detector = new GestureDetector(getContext(), this);

	public PagedView(Context context) {
		this(context, null);
	}

	public PagedView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public PagedView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		mDistanceHelper = new DistanceHelper(getContext());
	}

	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int count = getChildCount();
		for (int i = 0; i < count; i++) {
			getChildAt(i).layout(getWidth() * i, 0, getWidth() * (i + 1), getHeight());
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		detector.onTouchEvent(event);
		switch (event.getAction()) {

		case MotionEvent.ACTION_UP:
			// Log.i(TAG, "up");
			int tmpId = 0;
			if ((event.getX() - mDownX) > getWidth() / 2) {
				tmpId = mCurrentIndex - 1;
			} else if (mDownX - event.getX() > getWidth() / 2) {
				tmpId = mCurrentIndex + 1;
			} else {
				tmpId = mCurrentIndex;
			}
			int childCount = getChildCount();
			mCurrentIndex = tmpId < 0 ? 0 : (tmpId > childCount - 1 ? childCount - 1 : tmpId);
			// scrollTo(mCurrentIndex * getWidth(), 0);
			moveTo();
			break;

		default:
			break;
		}
		return true;
	}

	private void moveTo() {
		int distance = mCurrentIndex * getWidth() - getScrollX();
		mDistanceHelper.startScroll(getScrollX(), 0, distance, 0);
		invalidate();
	}

	@Override
	public boolean onDown(MotionEvent e) {
		if (DEBUG)
			Log.i(TAG, "onDown");
		mDownX = e.getX();
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		if (DEBUG)
			Log.i(TAG, "onShowPress");

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		if (DEBUG)
			Log.i(TAG, "onSingleTapUp");
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
		if (DEBUG)
			Log.i(TAG, "onScroll");
		// Log.i(TAG, "onScroll=" + distanceX + "," + distanceY);
		scrollBy((int) distanceX, 0);
		return false;
	}

	public void onLongPress(MotionEvent e) {
		if (DEBUG)
			Log.i(TAG, "onLongPress");

	}

	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		if (DEBUG)
			Log.i(TAG, "onFling");
		Log.i("Gmw", velocityX + "");
		return false;
	}

	public void scrollNext() {

	}

	public void scrollLast() {

	}

	public void setCurrent(int index) {
		mCurrentIndex = index;
		scrollTo(index * getWidth(), 0);
		invalidate();
		// requestLayout();
	}

	public int getCurrent() {
		return mCurrentIndex;
	}

	@Override
	public void computeScroll() {
		Log.i("Gmw", "computeScroll");
		if (!mDistanceHelper.computeScrollOffset()) {
			int newX = (int) mDistanceHelper.getCurrentX();
			scrollTo(newX, 0);
			invalidate();
		}
		// super.computeScroll();
	}
}
