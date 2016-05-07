package com.yzy.myaccount.activity;

import com.yzy.myaccount.R;
import com.yzy.myaccount.dao.Tb_atypeDao;
import com.yzy.myaccount.dao.Tb_typeDao;
import com.yzy.myaccount.util.ActivityManager;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class AddTypeActivity extends Activity  {
  private Button s;
  private TextView text;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE); // 无标题
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addtype);
		setFinishOnTouchOutside(true);
		s=(Button)findViewById(R.id.addb);
		final Tb_typeDao dao=new Tb_typeDao(this);
		final Tb_atypeDao adao=new Tb_atypeDao(this);
		text=(TextView)findViewById(R.id.addt);
		s.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			   adao.add(text.getText().toString());
				dao.add(text.getText().toString());
				Intent intent=new Intent(AddTypeActivity.this,TypeActivity.class);
				startActivity(intent);
				finish();
				TypeActivity.instance.finish();
			}
		});
	}

}
