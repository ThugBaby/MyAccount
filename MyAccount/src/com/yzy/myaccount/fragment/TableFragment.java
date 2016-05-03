package com.yzy.myaccount.fragment;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.yzy.myaccount.R;
import com.yzy.myaccount.pagetable.SQLiteUtility;
import com.yzy.myaccount.pagetable.SimpleTable;
import com.yzy.myaccount.util.DBOpenHelper;

public class TableFragment extends Fragment  {
	private LinearLayout linearLayout;
	private String[][] items;
	private  ImageButton nButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.table,null);
		//******与上面进行比较View view = inflater.inflate(R.layout.table,container,false);
		linearLayout = (LinearLayout)view.findViewById(R.id.list);
        //要查询的字段和对应显示的名称
		SimpleDateFormat df = new SimpleDateFormat("M月d日");
		String time=df.format(new java.util.Date());
        items = new String[][]{
        	{"time", time},
        	{"money","收支金额"},
        	{"type", "类别"},
        	
        };       
        Calendar calendar=Calendar.getInstance();
  	 //获得当前时间的月份，月份从0开始所以结果要加1
   		int cmonth=calendar.get(Calendar.MONTH)+1;
      	 SimpleDateFormat d=new SimpleDateFormat("yyyy-'"+cmonth+"'-dd");
      	String dd=d.format(new java.util.Date());
        //插入一些数据测试用
      
       //SQLiteUtility.inserttest(getActivity());//fragment里面使用getActivity()获取上下文
        //查询语句
       // String sql = "select *from (select * from tb_inaccount left join tb_outaccount on tb_inaccount.date=tb_outaccount.date) where date='"+dd+"' order by time asc;";
      	String sql="select * from tb_statistic where date='"+dd+"'";
        Log.i("你",sql);
        //创建表格视图，只需要传入几个参数
        SimpleTable simpleTable = new SimpleTable(getActivity(), items, sql);
        linearLayout.addView(simpleTable);
        return view;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
//		nButton.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Toast.makeText(getActivity(),"fddfd",Toast.LENGTH_LONG).show();
//			}
//		});
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
	}

 



}
