package com.yzy.myaccount.activity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yzy.myaccount.R;
import com.yzy.myaccount.dao.Tb_inaccountDao;
import com.yzy.myaccount.dao.Tb_outaccountDao;
import com.yzy.myaccount.dao.Tb_statisticDao;
import com.yzy.myaccount.model.Tb_inaccount;
import com.yzy.myaccount.model.Tb_outaccount;
import com.yzy.myaccount.model.Tb_statistic;
import com.yzy.swipemenulistview.SwipeMenu;
import com.yzy.swipemenulistview.SwipeMenuCreator;
import com.yzy.swipemenulistview.SwipeMenuItem;
import com.yzy.swipemenulistview.SwipeMenuListView;
import com.yzy.swipemenulistview.SwipeMenuListView.OnLoadListener;
import com.yzy.swipemenulistview.SwipeMenuListView.OnMenuItemClickListener;
import com.yzy.swipemenulistview.SwipeMenuListView.OnRefreshListener;
import com.yzy.swipemenulistview.SwipeMenuListView.OnSwipeListener;


public class ManageActivity extends Activity implements OnRefreshListener,
		OnLoadListener {

	private List<List<String>> mAppList = new ArrayList<List<String>>();
	private AppAdapter mAdapter;
	private SwipeMenuListView mListView;
	private Tb_statisticDao dao;  
	private List<List<String>> arrayList;
	private int start=0,count=12,i=1;
	private int x=0;
	private ImageView imageView;
	private Tb_inaccountDao indao=new Tb_inaccountDao(this);
	 private      Tb_outaccountDao outda=new Tb_outaccountDao(this);
	// 在a里面设置一个静态的变量instance,初始化为this
	 //在D里面,a.instance.finish()
	 public static ManageActivity instance = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		instance=this;
		  imageView=(ImageView)findViewById(R.id.topback2);
		  onRefresh();
	       imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		dao= new Tb_statisticDao(this);
		mAppList = getData(start,count);
		mListView = (SwipeMenuListView) findViewById(R.id.listView);
		mAdapter = new AppAdapter();
		mListView.setAdapter(mAdapter);
		Intent intent=this.getIntent();
		Bundle bundle=intent.getExtras();
//		int tagString=bundle.getInt("tag");
//		if(tagString==1)
//		{ 
//			finish();
//		}
		SwipeMenuCreator creator = new SwipeMenuCreator() {

			@Override
			public void create(SwipeMenu menu) {
//				SwipeMenuItem openItem = new SwipeMenuItem(
//						getApplicationContext());
//				openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
//						0xCE)));
//				openItem.setWidth(dp2px(90));
//				openItem.setTitle("");
//				openItem.setTitleSize(18);
//				openItem.setTitleColor(Color.WHITE);
//				menu.addMenuItem(openItem);
				SwipeMenuItem deleteItem = new SwipeMenuItem(
						getApplicationContext());
				deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
						0x3F, 0x25)));
				deleteItem.setWidth(dp2px(90));
				deleteItem.setIcon(R.drawable.ic_delete);
				menu.addMenuItem(deleteItem);
			}
		};
		mListView.setMenuCreator(creator);
		mListView.setOnRefreshListener(this);
		mListView.setOnLoadListener(this);
		mListView.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public void onMenuItemClick(int position, SwipeMenu menu, int index) {

				switch (index) {
				case 0:
					Toast.makeText(ManageActivity.this,"删除成功!",1000).show();
				String tagString=mAppList.get(position).get(2).substring(0,2);
					dao.delete(mAppList.get(position).get(0),tagString);
					mAppList.remove(position);
					mAdapter.notifyDataSetChanged();
					break;
				case 1:
					
					mAppList.remove(position);
					
					mAdapter.notifyDataSetChanged();
					break;
				}
			}
		});

		mListView.setOnSwipeListener(new OnSwipeListener() {

			@Override
			public void onSwipeStart(int position) {

			}

			@Override
			public void onSwipeEnd(int position) {

			}
		});

		mListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(),
						position - 1 + " long click", 0).show();
				return false;
			}
		});
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				Intent intent=new Intent(ManageActivity.this,EditActivity.class);
				//Toast.makeText(ManageActivity.this,""+position, 1000).show();
				Tb_inaccount inaccount;
				Tb_outaccount outaccount;
				String type=mAppList.get(position-1).get(2).substring(0,2);
				//Log.i("qas",mAppList.get(position-1).get(0));
						if(type.equals("收入"))
					{
						inaccount=indao.getPerBytime(mAppList.get(position-1).get(0));
						//Log.i("qas",inaccount.getType());
						Bundle bundle=new Bundle();
						bundle.putSerializable("in",inaccount);
						intent.putExtras(bundle);
					}
					else if(type.equals("支出"))
					{
						outaccount=outda.getPerBytime(mAppList.get(position-1).get(0));
						Bundle bundle=new Bundle();
						bundle.putSerializable("out",outaccount);
						intent.putExtras(bundle);
					}
				
				startActivity(intent);
				
			}
		});
	}

	// 数据
	public List<List<String>> getData(int start,int count) {
		
        x=0;
		List list = dao.getScrollData(start, count);
		List list1 = dao.getAllData();
		java.util.Iterator iterator = list.iterator();
		java.util.Iterator iterator1 = list1.iterator();
		arrayList=new ArrayList<List<String>>();
		while (iterator.hasNext()) {
			Tb_statistic statistic = (Tb_statistic) iterator.next();
			String[] item = { statistic.getTime(), statistic.getType(),
					statistic.getMoney(),statistic.getDate()};
			List childItem = new ArrayList<String>();
			for (int index = 0; index < item.length; index++) {
				childItem.add(item[index]);
			}
			arrayList.add(childItem);
		}
		while (iterator1.hasNext()) {
			iterator1.next();
			x++;
		}
		return arrayList;
	}

	class AppAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mAppList.size();
		}

		@Override
		public List<String> getItem(int position) {
			return mAppList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = View.inflate(getApplicationContext(),
						R.layout.item_list_app, null);
				new ViewHolder(convertView);
			}
			ViewHolder holder = (ViewHolder) convertView.getTag();
			List<String> item = getItem(position);
			holder.tv_time.setText(item.get(0));
			holder.tv_type.setText(item.get(1));
			holder.tv_money.setText(item.get(2));
			holder.tv_date.setText(item.get(3));
			return convertView;
		}

		class ViewHolder {
			TextView tv_time;
			TextView tv_money;
			TextView tv_type;
			TextView tv_date;
			public ViewHolder(View view) {
				
				tv_date = (TextView) view.findViewById(R.id.tv0);
				tv_time = (TextView) view.findViewById(R.id.tv1);
				tv_money = (TextView) view.findViewById(R.id.tv2);
				tv_type = (TextView) view.findViewById(R.id.tv3);
				view.setTag(this);
			}
		}
	}

	private int dp2px(int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				getResources().getDisplayMetrics());
	}

	@Override
	public void onRefresh() {
		loadData(SwipeMenuListView.REFRESH);
	}

	private void loadData(final int what) {
		//模拟服务器获取数据
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(700);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Message msg = handler.obtainMessage();
				msg.what = what;
				msg.obj = getData(start,count);
				handler.sendMessage(msg);
				
			}
		}).start();
	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			@SuppressWarnings("unchecked")
			List<List<String>> result = (List<List<String>>) msg.obj;
			switch (msg.what) {
			case SwipeMenuListView.REFRESH:
				i=1;
				mAppList.clear();
				mAppList.addAll(result);
				break;
			case SwipeMenuListView.LOAD:	
				List<List<String>> result1;
				i++;
				int c=0;  
				if(x%count!=0)
				{
					c=(x/count)+1;
				}
				else {
					c=x/count;
				}
				if(i<=c)
				{
					
				 result1=getData(start,count*i);
				mAppList.clear();
				mAppList.addAll(result1);
				}
				else {
					
					mListView.onLoadComplete();
					Toast.makeText(ManageActivity.this,"全部数据加载完毕，共"+x+"条数据!",1000).show();
				}
				break;
			}
			mListView.onRefreshComplete();
			mListView.onLoadComplete();
			mListView.setResultSize(result.size());
			mAdapter.notifyDataSetChanged();
		};
	};

	@Override
	public void onLoad() {
		loadData(SwipeMenuListView.LOAD);
	}

}
