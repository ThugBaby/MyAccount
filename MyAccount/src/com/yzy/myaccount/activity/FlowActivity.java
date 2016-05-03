package com.yzy.myaccount.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.text.html.HTMLDocument.Iterator;

import com.yzy.myaccount.R;
import com.yzy.myaccount.dao.Tb_flagDao;
import com.yzy.myaccount.dao.Tb_inaccountDao;
import com.yzy.myaccount.dao.Tb_outaccountDao;
import com.yzy.myaccount.dao.Tb_statisticDao;
import com.yzy.myaccount.model.Tb_flag;
import com.yzy.myaccount.model.Tb_outaccount;
import com.yzy.myaccount.model.Tb_statistic;

import android.R.integer;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;

public class FlowActivity extends Activity implements OnScrollListener {

	private ExpandableListView listView;
	private MyExpandableListAdapter mAdapter;
	private ImageView imageView;
	private FrameLayout indicatorGroup;
	private int indicatorGroupId = -1;
	private int indicatorGroupHeight;
	private Map<String, List<List<String>>> groupArray1;
	private List<String> groupArray;
	private List<List<String>> childArray1;
	private List<List<List<String>>> childArray;
	private LayoutInflater mInflater;

	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flowmain);
		// ActionBar bar = getActionBar(); // 获取ActionBar的对象，从这个方法也可知action
		// // bar是activity的一个属性
		// bar.setDisplayHomeAsUpEnabled(true); //
		// 显示返回的箭头，并可通过onOptionsItemSelected()进行监听，其资源ID为android.R.id.home。
		imageView = (ImageView) findViewById(R.id.flowtopback);
		imageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		listView = (ExpandableListView) findViewById(R.id.expandableListView);
		indicatorGroup = (FrameLayout) findViewById(R.id.topGroup);
		// indicatorGroup.setVisibility(View.INVISIBLE);
		groupArray = new ArrayList<String>();
		childArray = new ArrayList<List<List<String>>>();
		initdate();
		mAdapter = new MyExpandableListAdapter();
		listView.setAdapter(mAdapter);
		listView.setOnScrollListener(this);
		listView.setGroupIndicator(null);
		// copy group view to indicator Group
		mInflater.inflate(R.layout.flowlist_item, indicatorGroup, true);
	}

	private void initdate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-");
		String date = sdf.format(new Date());
		Tb_statisticDao dao = new Tb_statisticDao(this);
		List list = dao.getAllData();
		java.util.Iterator iterator = list.iterator();
		groupArray1 = new HashMap<String, List<List<String>>>();
		while (iterator.hasNext()) {
			Tb_statistic statistic = (Tb_statistic) iterator.next();
			for (int i = 1; i <= 31; i++) {
				String a="";
				if(i<10)
				{
					a="0"+i;
				}
				else {
					a=""+i;
				}
				if (statistic.getDate().equals(date + "1-" + a)) {

					setData(statistic);
				}
				else if (statistic.getDate().equals(date + "2-" + a)) {

					setData(statistic);
				}
				else	if (statistic.getDate().equals(date + "3-" + a)) {
					
					setData(statistic);
				}
				else	if (statistic.getDate().equals(date + "4-" + a)) {
					
					setData(statistic);
				}
				else	if (statistic.getDate().equals(date + "5-" + a)) {
					
					setData(statistic);
				}
				else	if (statistic.getDate().equals(date + "6-" + a)) {
					
					setData(statistic);
				}
				else	if (statistic.getDate().equals(date + "7-" + a)) {
					
					setData(statistic);
				}
				else	if (statistic.getDate().equals(date + "8-" + a)) {
					
					setData(statistic);
				}
				else if (statistic.getDate().equals(date + "9-" + a)) {
					
					setData(statistic);
				}
				else	if (statistic.getDate().equals(date + "10-" + a)) {
					
					setData(statistic);
				}
				else	if (statistic.getDate().equals(date + "11-" + a)) {
					
					setData(statistic);
				}
				else if (statistic.getDate().equals(date + "12-" + a)) {
					
					setData(statistic);
				}
			}
		}
		for (Entry<String, List<List<String>>> entry : groupArray1.entrySet()) {
			groupArray.add(entry.getKey());
			childArray.add(entry.getValue());
		}
	}

	public void setData(Tb_statistic statistic) {
		String flagString, text;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-");
		String date = sdf.format(new Date());

		Tb_flagDao flagDao = new Tb_flagDao(this);
		Tb_inaccountDao inDao = new Tb_inaccountDao(this);
		Tb_outaccountDao outDao = new Tb_outaccountDao(this);
		Tb_flag flag;
		if (statistic.getMoney().substring(0, 2).equals("收入")) {
			text = "付款方:" + inDao.getBytime(statistic.getTime());
		} else if (statistic.getMoney().substring(0, 2).equals("支出")) {
			text = "地点:" + outDao.getBytime(statistic.getTime());
		} else {
			text = "";
		}
		flag = flagDao.findbytime(statistic.getTime());

		if (flag == null) {
			flagString = "";
		} else {
			flagString = flag.getFlag();
		}
		String[] item = { statistic.getTime(), statistic.getType(),
				statistic.getMoney(), flagString, text };
		List childItem = new ArrayList<String>();
		for (int index = 0; index < item.length; index++) {
			childItem.add(item[index]);
		}
		if (groupArray1.containsKey(statistic.getDate())) {
			childArray1 = (List<List<String>>) groupArray1.get(statistic
					.getDate());
			childArray1.add(childItem);
			groupArray1.put(statistic.getDate(), childArray1);
		} else {
			childArray1 = new ArrayList<List<String>>();
			childArray1.add(childItem);
			groupArray1.put(statistic.getDate(), childArray1);
		}

	}

	/**
	 * A simple adapter which maintains an ArrayList of photo resource Ids. Each
	 * photo is displayed as an image. This adapter supports clearing the list
	 * of photos and adding a new photo.
	 * 
	 */
	public class MyExpandableListAdapter extends BaseExpandableListAdapter {
		// Sample data set. children[i] contains the children (String[]) for
		// groups[i].

		public Object getChild(int groupPosition, int childPosition) {
			return childArray.get(groupPosition).get(childPosition);
		}

		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		public int getChildrenCount(int groupPosition) {
			return childArray.get(groupPosition).size();
		}

		public LinearLayout getGenericView(String time, String type,
				String money, String flag, String text) {
			// Layout parameters for the ExpandableListView
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT, 100);
			lp.setMargins(240, 0, 0, 0);
			LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT, 100);
			AbsListView.LayoutParams lp1 = new AbsListView.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
			TextView textView1 = new TextView(FlowActivity.this);
			TextView textView0 = new TextView(FlowActivity.this);
			textView0.setLayoutParams(lp2);
			// Center the text vertically
			textView0.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
			// Set the text starting position
			textView0.setPadding(36, 0, 0, 0);
			textView0.setTextColor(Color.BLACK);
			textView0.setText(time);
			textView1.setLayoutParams(lp);
			// Center the text vertically
			textView1.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
			// Set the text starting position
			textView1.setPadding(36, 0, 0, 0);
			textView1.setTextColor(Color.BLACK);
			textView1.setText(type);
			TextView textView2 = new TextView(FlowActivity.this);
			textView2.setLayoutParams(lp);
			// Center the text vertically
			textView2.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
			// Set the text starting position
			textView2.setPadding(36, 0, 0, 0);
			textView2.setTextColor(Color.BLACK);
			textView2.setText(money);
			TextView textView3 = new TextView(FlowActivity.this);
			textView3.setLayoutParams(lp1);
			// Center the text vertically
			textView3.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
			// Set the text starting position
			textView3.setPadding(36, 0, 0, 0);
			textView3.setTextColor(Color.BLACK);
			textView3.setText("备注:" + flag);
			TextView textView4 = new TextView(FlowActivity.this);
			textView4.setLayoutParams(lp1);
			// Center the text vertically
			textView4.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
			// Set the text starting position
			textView4.setPadding(36, 0, 0, 0);
			textView4.setTextColor(Color.BLACK);
			textView4.setText(text);
			LinearLayout oneLayout = new LinearLayout(FlowActivity.this);
			oneLayout.setLayoutParams(lp1);
			oneLayout.setOrientation(LinearLayout.VERTICAL);
			LinearLayout twoLayout = new LinearLayout(FlowActivity.this);
			twoLayout.setLayoutParams(lp1);
			twoLayout.setOrientation(LinearLayout.HORIZONTAL);
			LinearLayout layout1 = new LinearLayout(FlowActivity.this);
			layout1.setLayoutParams(lp1);
			layout1.setOrientation(LinearLayout.VERTICAL);
			oneLayout.addView(twoLayout);
			layout1.addView(textView3);
			layout1.addView(textView4);
			twoLayout.addView(textView0);
			twoLayout.addView(textView1);
			twoLayout.addView(textView2);
			oneLayout.addView(layout1);
			return oneLayout;
		}

		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			List<String> string = childArray.get(groupPosition).get(
					childPosition);// 具体每一条信息

			LinearLayout textView = getGenericView(string.get(0),
					string.get(2), string.get(1), string.get(3), string.get(4));

			return textView;
		}

		public Object getGroup(int groupPosition) {
			return getGroup(groupPosition);
		}

		public int getGroupCount() {
			return groupArray.size();
		}

		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		/**
		 * create group view and bind data to view
		 */
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			View v;
			if (convertView == null) {
				v = mInflater.inflate(R.layout.flowlist_item, null);
			} else {
				v = convertView;
			}
			TextView textView = (TextView) v.findViewById(R.id.textView);

			textView.setText(groupArray.get(groupPosition));
			return v;
		}

		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}

		public boolean hasStableIds() {
			return true;
		}

	}

	/**
	 * here is very importance for indicator group
	 */
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {

		final ExpandableListView listView = (ExpandableListView) view;
		/**
		 * calculate point (0,0)
		 */
		int npos = view.pointToPosition(0, 0);// 其实就是firstVisibleItem
		if (npos == AdapterView.INVALID_POSITION)// 如果第一个位置值无效
			return;

		long pos = listView.getExpandableListPosition(npos);
		int childPos = ExpandableListView.getPackedPositionChild(pos);// 获取第一行child的id
		int groupPos = ExpandableListView.getPackedPositionGroup(pos);// 获取第一行group的id
		if (childPos == AdapterView.INVALID_POSITION) {// 第一行不是显示child,就是group,此时没必要显示指示器
			View groupView = listView.getChildAt(npos
					- listView.getFirstVisiblePosition());// 第一行的view
			indicatorGroupHeight = groupView.getHeight();// 获取group的高度
			indicatorGroup.setVisibility(View.GONE);// 隐藏指示器
		} else {
			indicatorGroup.setVisibility(View.VISIBLE);// 滚动到第一行是child，就显示指示器
		}
		// get an error data, so return now
		if (indicatorGroupHeight == 0) {
			return;
		}
		// update the data of indicator group view
		if (groupPos != indicatorGroupId) {// 如果指示器显示的不是当前group
			mAdapter.getGroupView(groupPos, listView.isGroupExpanded(groupPos),
					indicatorGroup.getChildAt(0), null);// 将指示器更新为当前group
			indicatorGroupId = groupPos;
			// Log.e(TAG, PRE + "bind to new group,group position = " +
			// groupPos);
			// mAdapter.hideGroup(indicatorGroupId); // we set this group view
			// to be hided
			// 为此指示器增加点击事件
			indicatorGroup.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub
					listView.collapseGroup(indicatorGroupId);
				}
			});
		}
		if (indicatorGroupId == -1) // 如果此时grop的id无效，则返回
			return;

		/**
		 * calculate point (0,indicatorGroupHeight) 下面是形成往上推出的效果
		 */
		int showHeight = indicatorGroupHeight;
		int nEndPos = listView.pointToPosition(0, indicatorGroupHeight);// 第二个item的位置
		if (nEndPos == AdapterView.INVALID_POSITION)// 如果无效直接返回
			return;
		long pos2 = listView.getExpandableListPosition(nEndPos);
		int groupPos2 = ExpandableListView.getPackedPositionGroup(pos2);// 获取第二个group的id
		if (groupPos2 != indicatorGroupId) {// 如果不等于指示器当前的group
			View viewNext = listView.getChildAt(nEndPos
					- listView.getFirstVisiblePosition());
			showHeight = viewNext.getTop();
			// Log.e(TAG, PRE +
			// "update the show part height of indicator group:"
			// + showHeight);
		}

		// update group position
		MarginLayoutParams layoutParams = (MarginLayoutParams) indicatorGroup
				.getLayoutParams();
		layoutParams.topMargin = -(indicatorGroupHeight - showHeight);
		indicatorGroup.setLayoutParams(layoutParams);
	}

	public void onScrollStateChanged(AbsListView view, int scrollState) {

	}
	// @Override
	// public boolean onOptionsItemSelected(MenuItem item) {
	// switch (item.getItemId()) {
	// case android.R.id.home: // 对用户按home
	// // icon的处理，本例只需关闭activity，就可返回上一activity，即主activity。
	// Intent i = new Intent(this, MainActivity.class);
	// i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//
	// 清空原来的MainActivity，重新建一个，如果有蓝牙连接的话，就会断开，请注意！！
	// startActivity(i);
	// return true;
	// default:
	// break;
	// }
	// return super.onOptionsItemSelected(item);
	// }
}
