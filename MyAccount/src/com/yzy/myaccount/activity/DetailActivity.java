package com.yzy.myaccount.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.yzy.myaccount.R;
import com.yzy.myaccount.dao.Tb_inaccountDao;
import com.yzy.myaccount.dao.Tb_outaccountDao;
import com.yzy.myaccount.model.Tb_flag;
import com.yzy.myaccount.model.Tb_inaccount;
import com.yzy.myaccount.model.Tb_outaccount;
import com.yzy.myaccount.model.Tb_statistic;

public class DetailActivity extends Activity {
	private TextView money;
	private TextView dtime;
	private TextView type;
	private TextView pay;
	private TextView  note;
	private TextView   paytext;
	public void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
		money=(TextView)findViewById(R.id.dMoney);
		dtime=(TextView)findViewById(R.id.dtime);
		type=(TextView)findViewById(R.id.dtype);
		pay=(TextView)findViewById(R.id.dpay);
		note=(TextView)findViewById(R.id.dnote);
		paytext=(TextView)findViewById(R.id.dtextPay);
	//////设置为true点击区域外消失
		setFinishOnTouchOutside(true);
		   WindowManager m = getWindowManager();    
	       Display d = m.getDefaultDisplay();  //为获取屏幕宽、高    
	       LayoutParams p = getWindow().getAttributes(); //获取对话框当前的参数值    
	       p.height = (int) (d.getHeight() * 0.4);   //高度设置为屏幕的1.0   
	       p.width = (int) (d.getWidth() * 0.8);    //宽度设置为屏幕的0.8 	       	      
	       getWindow().setAttributes(p); 
 
           Intent intent = this.getIntent(); 
         Bundle bundle =intent.getExtras();
         String time=bundle.getString("time");
         Tb_inaccountDao dao=new Tb_inaccountDao(this);
         Tb_outaccountDao oDao=new Tb_outaccountDao(this);
         Tb_inaccount inaccount=dao.getPerBytime(time);
         Tb_outaccount outaccount=oDao.getPerBytime(time);
         if(inaccount!=null)
         {
        	 money.setText("收入:"+inaccount.getMoney()+"元");
        	 dtime.setText(inaccount.getDate()+"  "+time);
        	 type.setText(inaccount.getType());
        	 paytext.setText("付款方:");
        	 pay.setText(inaccount.getHandler());
        	 note.setText(inaccount.getMark());
         }
         else if(outaccount!=null) {
        	 money.setText("收入:"+outaccount.getMoney()+"元");
        	 dtime.setText(outaccount.getDate()+"  "+time);
        	 type.setText(outaccount.getType());
        	 paytext.setText("地点:");
        	 pay.setText(outaccount.getAddress());
        	 note.setText(outaccount.getMark());
		}
}
}
