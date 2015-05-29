package com.zoe.pagedview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	PagedView mPagedView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mPagedView = (PagedView) findViewById(R.id.pagedview1);

		View view;
		for (int i = 0; i < 5; i++) {
			view = new View(this);
			if (i == 0) {
				view.setBackgroundColor(Color.RED);
			} else if (i == 1) {
				view.setBackgroundColor(Color.BLUE);
			} else if (i == 21) {
				view.setBackgroundColor(Color.GREEN);
			} else if (i == 3) {
				view.setBackgroundColor(Color.YELLOW);
			} else if (i == 4) {
				view.setBackgroundColor(Color.RED);
			}
			mPagedView.addView(view);
		}
		mPagedView.setCurrent(1);
	}

}
