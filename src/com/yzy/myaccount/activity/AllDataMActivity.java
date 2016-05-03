package com.yzy.myaccount.activity;

import org.omg.CORBA.Request;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yzy.myaccount.R;
import com.yzy.myaccount.pagetable.SimpleTable;
public class AllDataMActivity extends Activity{
	private LinearLayout linearLayout;
	private String[][] items;	
	private ImageView imageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.managetable);
		 linearLayout = (LinearLayout)findViewById(R.id.managelist);
		 imageView=(ImageView)findViewById(R.id.topback1);
	       imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		 //要查询的字段和对应显示的名称
	    items = new String[][]{
	    		{"date","日期"},
	    		{"time", "时间"},
	        	{"money","收支详情"},
	        	{"type", "类别"},
	    };
	    //插入一些数据测试用,！！错误所在。。。
	    //SQLiteUtility.inserttest(getActivity());//fragment里面使用getActivity()获取上下文
	    //查询语句
	    String sql = "select * from tb_statistic";
	    //创建分页表格视图，只需要传入几个参数
	    SimpleTable simpleTable = new SimpleTable(this, items, sql, 15);
	    linearLayout.addView(simpleTable);
	}	   
}
