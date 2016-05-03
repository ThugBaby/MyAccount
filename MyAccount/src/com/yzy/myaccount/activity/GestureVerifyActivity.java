package com.yzy.myaccount.activity;
import javax.sound.sampled.DataLine.Info;

import com.yzy.myaccount.R;
import com.yzy.myaccount.dao.Tb_pwdDao;
import com.yzy.myaccount.model.Tb_pwd;
import com.yzy.myaccount.widget.GestureContentView;
import com.yzy.myaccount.widget.GestureDrawline.GestureCallBack;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences.Editor;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * 手势绘制/校验界面
 *
 */
public class GestureVerifyActivity extends Activity implements android.view.View.OnClickListener {
	/** 手机号码*/
	public static final String PARAM_PHONE_NUMBER = "PARAM_PHONE_NUMBER";
	/** 意图 */
	public static final String PARAM_INTENT_CODE = "PARAM_INTENT_CODE";
	private RelativeLayout mTopLayout;
	private TextView mTextTitle;
	private TextView mTextCancel;
	private ImageView mImgUserLogo;
	private TextView mTextPhoneNumber;
	private TextView mTextTip;
	private FrameLayout mGestureContainer;
	private GestureContentView mGestureContentView;
	private TextView mTextForget;
	private TextView mTextOther;
	private String mParamPhoneNumber;
	private long mExitTime = 0;
	private int mParamIntentCode;
    private String pwd="123";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gesture_verify);
//		SharedPreferences sp=getSharedPreferences("password",Context.MODE_PRIVATE);
//		pwd=sp.getString("pwd","123");
//		Log.e("ss",pwd);
		//从数据库获取密码
		Tb_pwdDao dao=new Tb_pwdDao(this);
		Tb_pwd tb_pwd=dao.find();
		if(tb_pwd!=null)
		{
			pwd=tb_pwd.getPassword();
		}
		else{
			Intent  intent =new Intent(GestureVerifyActivity.this,MainActivity.class);
			startActivity(intent);
			finish();
		}
		ObtainExtraData();
		setUpViews();
		setUpListeners();
	}
	
	private void ObtainExtraData() {
		mParamPhoneNumber = getIntent().getStringExtra(PARAM_PHONE_NUMBER);
		mParamIntentCode = getIntent().getIntExtra(PARAM_INTENT_CODE, 0);
	}
	
	private void setUpViews() {
		mTopLayout = (RelativeLayout) findViewById(R.id.top_layout);
		mTextTitle = (TextView) findViewById(R.id.text_title);
		mTextCancel = (TextView) findViewById(R.id.text_cancel);
		mTextTip = (TextView) findViewById(R.id.text_tip);
		mGestureContainer = (FrameLayout) findViewById(R.id.gesture_container);
		mTextForget = (TextView) findViewById(R.id.text_forget_gesture);
		mTextOther = (TextView) findViewById(R.id.text_other_account);
		
		// 初始化一个显示各个点的viewGroup
		mGestureContentView = new GestureContentView(this,true,pwd,
				new GestureCallBack() {

					@Override
					public void onGestureCodeInput(String inputCode) {

					}

					@Override
					public void checkedSuccess() {
						//mGestureContentView.clearDrawlineState(0L);
						Toast.makeText(GestureVerifyActivity.this, "密码正确", Toast.LENGTH_SHORT).show();
						Intent  intent =new Intent(GestureVerifyActivity.this,MainActivity.class);
						startActivity(intent);
						GestureVerifyActivity.this.finish();
						
					}

					@Override
					public void checkedFail() {
						mGestureContentView.clearDrawlineState(1300L);
						mTextTip.setVisibility(View.VISIBLE);
						mTextTip.setText(Html
								.fromHtml("<font color='#c70c1e'>密码错误</font>"));
						// 左右移动动画
						Animation shakeAnimation = AnimationUtils.loadAnimation(GestureVerifyActivity.this, R.anim.shake);
						mTextTip.startAnimation(shakeAnimation);
					}
				});
		// 设置手势解锁显示到哪个布局里面
		mGestureContentView.setParentView(mGestureContainer);
	}
	
	private void setUpListeners() {
		mTextCancel.setOnClickListener(this);
		mTextForget.setOnClickListener(this);
		mTextOther.setOnClickListener(this);
	}
	
//	private String getProtectedMobile(String phoneNumber) {
//		if (TextUtils.isEmpty(phoneNumber) || phoneNumber.length() < 11) {
//			return "";
//		}
//		StringBuilder builder = new StringBuilder();
//		builder.append(phoneNumber.subSequence(0,3));
//		builder.append("****");
//		builder.append(phoneNumber.subSequence(7,11));
//		return builder.toString();
//	}
	
	

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.text_cancel:
			this.finish();
			break;
		default:
			break;
		}
	}
	
}

