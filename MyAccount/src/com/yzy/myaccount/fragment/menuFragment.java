package com.yzy.myaccount.fragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.yzy.myaccount.R;
import com.yzy.myaccount.activity.AddAccount;
import com.yzy.myaccount.activity.AllDataMActivity;
import com.yzy.myaccount.activity.DetailActivity;
import com.yzy.myaccount.activity.EditActivity;
import com.yzy.myaccount.activity.FlowActivity;
import com.yzy.myaccount.activity.MainActivity;
import com.yzy.myaccount.activity.ManageActivity;
import com.yzy.myaccount.activity.TypeActivity;
import com.yzy.myaccount.activity.test;
import com.yzy.myaccount.dao.Tb_inaccountDao;
import com.yzy.myaccount.dao.Tb_outaccountDao;
import com.yzy.myaccount.model.Tb_inaccount;
import com.yzy.myaccount.model.Tb_outaccount;
import com.yzy.myaccount.model.Tb_statistic;
import com.yzy.myaccount.util.DBOpenHelper;

import android.R.integer;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.hardware.Camera.Face;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.SearchView.OnSuggestionListener;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SearchView.OnQueryTextListener;

public class menuFragment extends Fragment {
	private List<View> listViews; // Tab页面列表
	private ImageView cursor;// 动画图片
	private TextView t1, t2, t3;// 页卡头标
	private int offset = 0;// 动画图片偏移量
	private int currIndex = 0;// 当前页卡编号
	private int bmpW;// 动画图片宽度
	private ViewPager viewPage;
	private View view;
	private MainActivity fca;
	private List<Tb_statistic> list;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		fca = (MainActivity) getActivity();

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.menu, null);
		InitImageView();
		InitTextView();
		InitViewPager();
		return view;
	}

	/**
	 * 初始化头标
	 */
	private void InitTextView() {
		t1 = (TextView) view.findViewById(R.id.text1);
		t2 = (TextView) view.findViewById(R.id.text2);
		t3 = (TextView) view.findViewById(R.id.text3);

		t1.setOnClickListener(new MyOnClickListener(0));
		t2.setOnClickListener(new MyOnClickListener(1));
		t3.setOnClickListener(new MyOnClickListener(2));
	}

	/**
	 * 头标点击监听
	 */
	public class MyOnClickListener implements View.OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;

		}

		@Override
		public void onClick(View v) {
			viewPage.setCurrentItem(index);
			cColor(index);
		}
	}

	/**
	 * 初始化ViewPager
	 */
	private void InitViewPager() {

		viewPage = (ViewPager) view.findViewById(R.id.viewpager);
		listViews = new ArrayList<View>();

		LayoutInflater mInflater = fca.getLayoutInflater();
		listViews.add(mInflater.inflate(R.layout.main, null));
		listViews.add(mInflater.inflate(R.layout.count, null));
		listViews.add(mInflater.inflate(R.layout.manageitem, null));

		viewPage.setAdapter(new MyPagerAdapter(listViews));
		viewPage.setCurrentItem(0);
		fca.getSlidingMenu()
				.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		viewPage.setOnPageChangeListener(new MyOnPageChangeListener());
	}
	/**
	 * ViewPager适配器
	 */
	public class MyPagerAdapter extends PagerAdapter {
		public List<View> mListViews;

		public MyPagerAdapter(List<View> mListViews) {
			this.mListViews = mListViews;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(mListViews.get(arg1));
		}

		@Override
		public void finishUpdate(View arg0) {
		}

		@Override
		public int getCount() {
			return mListViews.size();
		}

		@Override
		public Object instantiateItem(View arg0, int position) {
			switch (position) {
			case 0:
				ImageButton bt = (ImageButton) mListViews.get(position)
						.findViewById(R.id.write);
				bt.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// // TODO Auto-generated method stub
						try {

							Intent intent = new Intent(
									(MainActivity) getActivity(),
									AddAccount.class);

							startActivity(intent);
						} catch (Exception e) {

							e.printStackTrace();
							Log.i("aa", e.getMessage());
						}
					}
				});
				// 获取月份
				Calendar calendar = Calendar.getInstance();
				// 获得当前时间的月份，月份从0开始所以结果要加1
				int cmonth = calendar.get(Calendar.MONTH) + 1;
				int min = 0,
				mout = 0,
				min1 = 0,
				mout1 = 0,
				mrest = 0,
				min2 = 0,
				mout2 = 0;

				final SearchView sView = (SearchView) mListViews.get(position)
						.findViewById(R.id.search_view);
				sView.setIconifiedByDefault(false);
				sView.setSubmitButtonEnabled(false);
				sView.setQueryHint("查询");
				sView.setOnSuggestionListener(new OnSuggestionListener() {
					
					@Override
					public boolean onSuggestionSelect(int arg0) {
						// TODO Auto-generated method stub						
						return false;
					}
					
					@Override
					public boolean onSuggestionClick(int arg0) {
						// TODO Auto-generated method stub
						//Toast.makeText(getActivity(),list.get(arg0).getTime(), 1000).show();
						Intent  intent=new Intent(getActivity(),DetailActivity.class);
						intent.putExtra("time",list.get(arg0).getTime());
						startActivity(intent);
						return false;
					}
				});
				sView.setOnQueryTextListener(new OnQueryTextListener() {

					@Override
					public boolean onQueryTextChange(String str) {

						getData(sView, str);
						return false;

					}

					@Override
					public boolean onQueryTextSubmit(String str) {

						Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT)
								.show();

						return false;
					}

				});
				TextView in = (TextView) mListViews.get(position).findViewById(
						R.id.Min);
				TextView out = (TextView) mListViews.get(position)
						.findViewById(R.id.Mout);
				TextView rest = (TextView) mListViews.get(position)
						.findViewById(R.id.rest);
				TextView m1 = (TextView) mListViews.get(position).findViewById(
						R.id.m1);
				TextView m2 = (TextView) mListViews.get(position).findViewById(
						R.id.m2);
				TextView m3 = (TextView) mListViews.get(position).findViewById(
						R.id.m3);
				TextView mt1 = (TextView) mListViews.get(position)
						.findViewById(R.id.mt1);
				TextView mt2 = (TextView) mListViews.get(position)
						.findViewById(R.id.mt2);
				TextView mt3 = (TextView) mListViews.get(position)
						.findViewById(R.id.mt3);
				ArrayList<Float> rArrayList = getData(3);// 获取进3个月盈余
				mt1.setText("" + rArrayList.get(0));
				mt2.setText("" + rArrayList.get(1));
				mt3.setText("" + rArrayList.get(2));
				calendar.add(Calendar.MONTH, -2);
				int c = calendar.get(Calendar.MONTH) + 1;
				Tb_inaccountDao dao = new Tb_inaccountDao(getActivity());
				Tb_outaccountDao odaOutaccount = new Tb_outaccountDao(
						getActivity());
				List list = dao.getLTData(c);
				Iterator iterator = list.iterator();
				while (iterator.hasNext()) {
					Tb_inaccount ia = (Tb_inaccount) iterator.next();
					min1 += (int) ia.getMoney();

				}
				List list1 = odaOutaccount.getLTData(c);
				Iterator iterator1 = list1.iterator();
				while (iterator1.hasNext()) {
					Tb_outaccount oa = (Tb_outaccount) iterator1.next();

					mout1 += (float) oa.getMoney();

				}
				mrest = min1 - mout1;
				m1.setText("" + mrest);
				mt1.setText(c + "月");
				calendar = Calendar.getInstance();
				calendar.add(Calendar.MONTH, -1);
				c = calendar.get(Calendar.MONTH) + 1;
				list = dao.getLTData(c);
				iterator = list.iterator();
				while (iterator.hasNext()) {
					Tb_inaccount ia = (Tb_inaccount) iterator.next();
					min2 += (int) ia.getMoney();

				}
				list1 = odaOutaccount.getLTData(c);
				iterator1 = list1.iterator();
				while (iterator1.hasNext()) {
					Tb_outaccount oa = (Tb_outaccount) iterator1.next();

					mout2 += (float) oa.getMoney();

				}
				mrest = min2 - mout2;
				m2.setText("" + mrest);
				mt2.setText(c + "月");
				calendar = Calendar.getInstance();
				c = calendar.get(Calendar.MONTH) + 1;
				mt3.setText(c + "月");

				list = dao.getLTData(c);
				iterator = list.iterator();
				while (iterator.hasNext()) {
					Tb_inaccount ia = (Tb_inaccount) iterator.next();
					min += (int) ia.getMoney();

				}
				Log.i("min", "" + min);
				in.setText("" + min);

				list1 = odaOutaccount.getLTData(c);
				iterator1 = list1.iterator();
				while (iterator1.hasNext()) {
					Tb_outaccount oa = (Tb_outaccount) iterator1.next();

					mout += (float) oa.getMoney();

				}
				out.setText("" + mout);
				mrest = min - mout;
				rest.setText("" + mrest);
				m3.setText("" + mrest);
				break;
			case 1:
				getActivity().getSupportFragmentManager().beginTransaction()
						.replace(R.id.mchart, new ChartFragment()).commit();
				ImageButton flowButton = (ImageButton) mListViews.get(position)
						.findViewById(R.id.flow);
				final Button cpie = (Button) mListViews.get(position).findViewById(
						R.id.cpie);
				final Button cbar = (Button) mListViews.get(position).findViewById(
						R.id.cbar);
				final Button clist = (Button) mListViews.get(position).findViewById(
						R.id.clist);
				flowButton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(getActivity(),
								FlowActivity.class);
						startActivity(intent);
					}
				});
				cbar.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						cbar.setBackgroundColor(Color.rgb(175,225,30));
						cpie.setBackgroundColor(Color.rgb(250,243,191));
						clist.setBackgroundColor(Color.rgb(250,243,191));
						getActivity().getSupportFragmentManager()
								.beginTransaction()
								.replace(R.id.mchart, new CountBarFragment())
								.commit();
					}
				});
				cpie.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						cbar.setBackgroundColor(Color.rgb(250,243,191));
						cpie.setBackgroundColor(Color.rgb(175,225,30));
						clist.setBackgroundColor(Color.rgb(250,243,191));
						// TODO Auto-generated method stub
						getActivity().getSupportFragmentManager()
								.beginTransaction()
								.replace(R.id.mchart, new ChartFragment())
								.commit();
					}
				});
				clist.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						cbar.setBackgroundColor(Color.rgb(250,243,191));
						cpie.setBackgroundColor(Color.rgb(250,243,191));
						clist.setBackgroundColor(Color.rgb(175,225,30));
						// TODO Auto-generated method stub
						getActivity().getSupportFragmentManager()
								.beginTransaction()
								.replace(R.id.mchart, new CountListFragment())
								.commit();
					}
				});
				break;
			case 2:
				View view = (View) mListViews.get(position).findViewById(
						R.id.tvmdata);
				View view2 = (View) mListViews.get(position).findViewById(
						R.id.tvadata);
				View view4 = (View) mListViews.get(position).findViewById(
						R.id.tvtype);
				view.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(getActivity(),
								ManageActivity.class);

						startActivity(intent);
					}
				});
				view2.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(getActivity(),
								AllDataMActivity.class);

						startActivity(intent);
					}
				});
				view4.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(getActivity(),
								TypeActivity.class);

						startActivity(intent);
					}
				});
				break;
			default:
				break;
			}
			((ViewPager) arg0).addView(mListViews.get(position), 0);
			return mListViews.get(position);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == (arg1);
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
		}
	}

	/**
	 * 初始化动画
	 */
	private void InitImageView() {
		cursor = (ImageView) view.findViewById(R.id.cursor);
		bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.a)
				.getWidth();// 获取图片宽度
		DisplayMetrics dm = new DisplayMetrics();
		fca.getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;// 获取分辨率宽度
		offset = (screenW / 3 - bmpW) / 2;// 计算偏移量
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		cursor.setImageMatrix(matrix);// 设置动画初始位置
	}

	/**
	 * 页卡切换监听
	 */
	public class MyOnPageChangeListener implements OnPageChangeListener {

		int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
		int two = one * 2;// 页卡1 -> 页卡3 偏移量

		@Override
		public void onPageSelected(int arg0) {
			Animation animation = null;
			switch (arg0) {
			case 0:
				cColor(0);

				if (currIndex == 1) {
					animation = new TranslateAnimation(one, 0, 0, 0);
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, 0, 0, 0);
				}
				fca.getSlidingMenu().setTouchModeAbove(
						SlidingMenu.TOUCHMODE_FULLSCREEN);
				break;
			case 1:
				cColor(1);
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, one, 0, 0);
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, one, 0, 0);
				}
				fca.getSlidingMenu().setTouchModeAbove(
						SlidingMenu.TOUCHMODE_MARGIN);
				break;
			case 2:
				cColor(2);
				if (currIndex == 0) {
					animation = new TranslateAnimation(offset, two, 0, 0);
				} else if (currIndex == 1) {
					animation = new TranslateAnimation(one, two, 0, 0);
				}
				fca.getSlidingMenu().setTouchModeAbove(
						SlidingMenu.TOUCHMODE_MARGIN);
				break;
			}
			currIndex = arg0;
			animation.setFillAfter(true);// True:图片停在动画结束位置
			animation.setDuration(300);
			cursor.startAnimation(animation);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}

	/**
	 * 改变颜色
	 */
	public void cColor(int index) {
		switch (index) {
		case 0:
			t1.setTextColor(R.color.slide);
			t2.setTextColor(Color.BLACK);
			t3.setTextColor(Color.BLACK);
			break;
		case 1:
			t1.setTextColor(Color.BLACK);
			t2.setTextColor(R.color.slide);
			t3.setTextColor(Color.BLACK);
			break;
		case 2:
			t1.setTextColor(Color.BLACK);
			t2.setTextColor(Color.BLACK);
			t3.setTextColor(R.color.slide);
			break;
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	private ArrayList<Float> getData(int count) {

		ArrayList<Float> rest = new ArrayList<Float>();
		float p = 0;
		Calendar calendar = Calendar.getInstance();
		int cmonth = calendar.get(Calendar.MONTH) + 1;
		for (int i = 0; i < count; i++) {
			int j = 2 - i;
			calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -j);
			int c = calendar.get(Calendar.MONTH) + 1;
			int inaccount = 0;
			int outaccount = 0;
			Tb_inaccountDao indao = new Tb_inaccountDao(getActivity());
			Tb_outaccountDao oDao = new Tb_outaccountDao(getActivity());
			List list = indao.getLTData(c);
			List list2 = oDao.getLTData(c);
			java.util.Iterator iterator = list.iterator();
			java.util.Iterator iterator1 = list2.iterator();
			while (iterator.hasNext()) {
				Tb_inaccount in = (Tb_inaccount) iterator.next();
				inaccount += in.getMoney();
			}

			while (iterator1.hasNext()) {
				Tb_outaccount out = (Tb_outaccount) iterator1.next();
				outaccount += out.getMoney();
			}
			p = inaccount - outaccount;
			rest.add(i, p);

		}
		return rest;
	}

	public void getData(SearchView sv, String key) {
		Cursor cursor = getTestCursor(key);
		
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(getActivity(),
				R.layout.searchresult, cursor, new String[] { "date","time", 
						"money", "type" }, new int[] { R.id.date,R.id.stime,
						 R.id.smoney,R.id.stype });
    Tb_statistic statistic;
     list=new ArrayList<Tb_statistic>();
    while(cursor.moveToNext())
    {
    	statistic=new Tb_statistic();
    	statistic.setTime(cursor.getString(cursor.getColumnIndex("time")));
    	list.add(statistic);
    }
		sv.setSuggestionsAdapter(adapter);
	}

	public Cursor getTestCursor(String key) {
		Cursor cursor = null;
		DBOpenHelper helper;
		SQLiteDatabase db;
		helper = new DBOpenHelper(getActivity());
		db = helper.getWritableDatabase();
		String querySql = "select * from tb_statistic where tb_statistic.type LIKE '%"+key+"%' or tb_statistic.money LIKE '%"+key+"%'";
		cursor = db.rawQuery(querySql, null);
		return cursor;
	}
}
