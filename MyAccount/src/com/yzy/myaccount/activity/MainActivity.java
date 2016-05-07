package com.yzy.myaccount.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.CanvasTransformer;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.yzy.myaccount.R;
import com.yzy.myaccount.dao.Tb_atypeDao;
import com.yzy.myaccount.dao.Tb_inaccountDao;
import com.yzy.myaccount.dao.Tb_typeDao;
import com.yzy.myaccount.fragment.ColorFragment;
import com.yzy.myaccount.fragment.LeftFragment;
import com.yzy.myaccount.fragment.TodayFragment;
import com.yzy.myaccount.fragment.addAccountFrag;
import com.yzy.myaccount.fragment.menuFragment;
import com.yzy.myaccount.model.Tb_inaccount;
import com.yzy.myaccount.pagetable.SQLiteUtility;
import com.yzy.myaccount.pagetable.SimpleTable;
import com.yzy.myaccount.util.ActivityManager;
import com.yzy.myaccount.util.DBOpenHelper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.StrictMode;
import android.provider.CalendarContract.Colors;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @author yzy
 * @description 主界面
 */
public class MainActivity extends SlidingFragmentActivity implements
		OnClickListener {
	private ImageView topButton;
	private Fragment mContent;
	private TextView topTextView;
	private Button licaiButton;
	static Calendar calendar = Calendar.getInstance();
	static int cmonth = calendar.get(Calendar.MONTH) + 1;
	static SimpleDateFormat d = new SimpleDateFormat("yyyy-'" + cmonth + "'-dd");
	public final static String date = d.format(new Date());
	ActivityManager exitM;
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		new AlertDialog.Builder(this).setTitle("确认退出吗？")
				.setIcon(android.R.drawable.ic_dialog_info)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// 点击“确认”后的操作
						//MainActivity.this.finish();该方法无法彻底结束
						//此方法可行
						exitM.exit();

					}
				})
				.setNegativeButton("返回", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// 点击“返回”后的操作,这里不设置没有任何操作
						dialog.dismiss();
					}
				}).show();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE); // 无标题
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//初始化数据
		 Tb_typeDao  dao=new Tb_typeDao(this);
		 Tb_atypeDao  adao=new Tb_atypeDao(this);
		 if(dao.getType(1)==null)
		 {
			 dao.add("工资");
			    dao.add("外出");
			    dao.add("贪污");
			    dao.add("腐败");
			    dao.add("其他");
			   
		 }
		 if(!adao.getType(1))
		 {
			 adao.add("工资");
			    adao.add("外出");
			    adao.add("贪污");
			    adao.add("腐败");
			    adao.add("其他");
		 }
		 exitM= ActivityManager.getInstance();
		exitM.addActivity(MainActivity.this);
		// 强制在主线程执行更新
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		initSlidingMenu(savedInstanceState);
		topButton = (ImageView) findViewById(R.id.topButton);
		topButton.setOnClickListener(this);
		topTextView = (TextView) findViewById(R.id.topTv);
		licaiButton = (Button) findViewById(R.id.licai);
		// 错误已解决
		// 显示首页是要保存状态，否则直接在主页跳转，会出现空状态异常
		switchConent(new menuFragment(), "家庭记账");
		licaiButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, lcActivity.class);
				startActivity(intent);
			}
		});

	}

	/**
	 * 初始化侧边栏
	 */
	private void initSlidingMenu(Bundle savedInstanceState) {
		// 如果保存的状态不为空则得到之前保存的Fragment，否则实例化MyFragment
		if (savedInstanceState != null) {
			mContent = getSupportFragmentManager().getFragment(
					savedInstanceState, "mContent");
		}

		if (mContent == null) {
			mContent = new menuFragment();
		}

		// 设置左侧滑动菜单
		setBehindContentView(R.layout.menu_frame_left);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.menu_frame, new LeftFragment()).commit();

		// 实例化滑动菜单对象
		SlidingMenu sm = getSlidingMenu();
		// 设置可以左右滑动的菜单
		sm.setMode(SlidingMenu.LEFT);
		// 设置滑动阴影的宽度
		sm.setShadowWidthRes(R.dimen.shadow_width);
		// 设置滑动菜单阴影的图像资源
		sm.setShadowDrawable(null);
		// 设置滑动菜单视图的宽度
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		// 设置渐入渐出效果的值
		sm.setFadeDegree(0.35f);
		// 设置触摸屏幕的模式,这里设置为全屏
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		// 设置下方视图的在滚动时的缩放比例
		sm.setBehindScrollScale(0.0f);
		CanvasTransformer mTransformer = new CanvasTransformer() {
			@Override
			public void transformCanvas(Canvas canvas, float percentOpen) {
				float scale = (float) (percentOpen * 0.25 + 0.75);
				canvas.scale(scale, scale, canvas.getWidth() / 2,
						canvas.getHeight() / 2);
			}
		};
		sm.setBehindCanvasTransformer(mTransformer);// 设置动画
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		getSupportFragmentManager().putFragment(outState, "mContent", mContent);
	}

	/**
	 * 切换Fragment
	 * 
	 * @param fragment
	 */
	public void switchConent(Fragment fragment, String title) {
		mContent = fragment;
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.mainLayout, fragment).commit();// R.id.content_frame不是R.layout.content_frame
		getSlidingMenu().showContent();
		topTextView.setText(title);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.topButton:
			toggle();
			break;

		default:
			break;
		}
	}

	class ColorPagerAdapter extends FragmentPagerAdapter {

		private ArrayList<Fragment> mFragments;

		private final int[] COLORS = new int[] { R.color.red, R.color.green,
				R.color.blue, R.color.white, R.color.black };

		public ColorPagerAdapter(FragmentManager fm) {
			super(fm);
			mFragments = new ArrayList<Fragment>();
			for (int color : COLORS)
				mFragments.add(new ColorFragment(color));// �����ɫ
		}

		@Override
		public int getCount() {
			return mFragments.size();
		}

		@Override
		public Fragment getItem(int position) {
			return mFragments.get(position);
		}

	}

}
