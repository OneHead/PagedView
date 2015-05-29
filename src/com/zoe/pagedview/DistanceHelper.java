package com.zoe.pagedview;

import android.content.Context;
import android.os.SystemClock;

public class DistanceHelper {
	private int startX;
	private int startY;
	private int distanceX;
	private int distanceY;
	private long startTime;
	private boolean isFinish;
	private long duration;

	private long currentX;
	private long currentY;

	public long getCurrentX() {
		return currentX;
	}

	public void setCurrentX(long currentX) {
		this.currentX = currentX;
	}

	public DistanceHelper(Context context) {
		isFinish = false;
	}

	public void startScroll(int startX, int startY, int distanceX, int distanceY) {
		this.startX = startX;
		this.startY = startY;
		this.distanceX = distanceX;
		this.distanceY = distanceY;
		this.startTime = SystemClock.uptimeMillis();
		this.duration = 200;
		this.isFinish = false;

	}

	public boolean computeScrollOffset() {
		if (isFinish) {
			return isFinish;
		}
		long passTime = SystemClock.uptimeMillis() - startTime;

		if (passTime < duration) {
			currentX = startX + distanceX * passTime / duration;
			currentY = startY + distanceX * passTime / duration;
		} else {
			currentX = startX + distanceX;
			currentY = startY + distanceY;
			isFinish = true;
		}
		return false;
	}

}
