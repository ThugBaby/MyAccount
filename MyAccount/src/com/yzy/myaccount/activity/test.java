package com.yzy.myaccount.activity;


import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.yzy.myaccount.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class test extends SherlockActivity {
	public static Intent newInstance(Activity activity) {
		Intent intent = new Intent(activity, test.class);
		return intent;
	}
	private Handler mHandler;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setTitle("test");
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
		ColorDrawable color = new ColorDrawable(Color.BLACK);
		color.setAlpha(128);
		getSupportActionBar().setBackgroundDrawable(color);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		mHandler = new Handler();
		setContentView(R.layout.frag_about);
		this.getWindow().setBackgroundDrawableResource(android.R.color.black);
	}
	@Override
	public void onResume() {
		super.onResume();
		getSupportActionBar().show();
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
