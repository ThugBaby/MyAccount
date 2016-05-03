package com.yzy.myaccount.activity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.actionbarsherlock.app.SherlockActivity;
import com.yzy.myaccount.R;
import com.yzy.myaccount.dao.Tb_inaccountDao;
import com.yzy.myaccount.fragment.BarChartFragment;
import com.yzy.myaccount.fragment.LeftFragment;
import com.yzy.myaccount.fragment.addAccountFrag;
import com.yzy.myaccount.model.Tb_inaccount;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddAccount extends FragmentActivity {
     
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addmain);
	//////设置为true点击区域外消失
		setFinishOnTouchOutside(true);
		   WindowManager m = getWindowManager();    
	       Display d = m.getDefaultDisplay();  //为获取屏幕宽、高    
	       LayoutParams p = getWindow().getAttributes(); //获取对话框当前的参数值    
	       p.height = (int) (d.getHeight() * 0.4);   //高度设置为屏幕的1.0   
	       p.width = (int) (d.getWidth() * 0.8);    //宽度设置为屏幕的0.8 	       	      
	       getWindow().setAttributes(p); 
	       //使用FragmentManager进行内容显示
		   getSupportFragmentManager().beginTransaction()
			.replace(R.id.aa, new addAccountFrag()).commit();
		   
}
}
