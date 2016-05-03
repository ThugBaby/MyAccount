package com.yzy.myaccount.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.yzy.myaccount.R;
import com.yzy.myaccount.dao.Tb_flagDao;
import com.yzy.myaccount.dao.Tb_inaccountDao;
import com.yzy.myaccount.dao.Tb_outaccountDao;
import com.yzy.myaccount.dao.Tb_statisticDao;
import com.yzy.myaccount.fragment.addAccountFrag;
import com.yzy.myaccount.model.Tb_flag;
import com.yzy.myaccount.model.Tb_inaccount;
import com.yzy.myaccount.model.Tb_outaccount;
import com.yzy.myaccount.model.Tb_statistic;


public class EditActivity extends Activity {
	private  Button save;
	private  Button btcancle;
	private Spinner spinner;
	private  String [] 	mStringArray;
	private TextView money;
	private TextView time;
	private TextView pay;
	private TextView PayOrAddress;
	private TextView note;
	private TextView title;
	private Tb_inaccount inaccount ; 
	private Tb_outaccount outaccount; 
	private Tb_flag flag;
	private Tb_statistic statistic;
	private Tb_outaccountDao outdao=new Tb_outaccountDao(this);
	private Tb_inaccountDao indao=new Tb_inaccountDao(this);
	private Tb_flagDao fdao=new Tb_flagDao(this);
	private Tb_statisticDao statisticDao=new Tb_statisticDao(this);
	public void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit);
	//////设置为true点击区域外消失
		setFinishOnTouchOutside(true);
		   WindowManager m = getWindowManager();    
	       Display d = m.getDefaultDisplay();  //为获取屏幕宽、高    
	       LayoutParams p = getWindow().getAttributes(); //获取对话框当前的参数值    
	       p.height = (int) (d.getHeight() * 0.4);   //高度设置为屏幕的1.0   
	       p.width = (int) (d.getWidth() * 0.8);    //宽度设置为屏幕的0.8 	       	      
	       getWindow().setAttributes(p); 
           save=(Button)findViewById(R.id.bteSave);
           spinner=(Spinner)findViewById(R.id.spinner2);
           money=(TextView)findViewById(R.id.eeditMoney);
           time=(TextView)findViewById(R.id.eeditTime);
           pay=(TextView)findViewById(R.id.eeditPay);
           note=(TextView)findViewById(R.id.eeditNote);
           title=(TextView)findViewById(R.id.etopTv);
           PayOrAddress=(TextView)findViewById(R.id.textPay);
           initSpinner(spinner);
           flag=new Tb_flag();
           statistic=new Tb_statistic();
           Intent intent = this.getIntent(); 
          inaccount=(Tb_inaccount)intent.getSerializableExtra("in");
          //Log.i("ffff",spinner.getChildAt(1));
           outaccount=(Tb_outaccount)intent.getSerializableExtra("out");
          if(inaccount!=null)
          {
        	  title.setText("收入信息修改");
        	  money.setText(""+inaccount.getMoney());
        	  time.setText(inaccount.getTime());
        	  pay.setText(inaccount.getHandler());
        	  note.setText(inaccount.getMark());
        	for(int i=0;i<mStringArray.length;i++)  
        	{ 
        		
        		if(mStringArray[i].equals(inaccount.getType()))
        		{
        			spinner.setSelection(i);//可修改
        		}
        	}
          }
          else  if(outaccount!=null){
        	  title.setText("支出信息修改");
           	  money.setText(""+outaccount.getMoney());//可修改
           	  time.setText(outaccount.getTime());
           	  pay.setText(outaccount.getAddress());//可修改
           	  note.setText(outaccount.getMark());//可修改
           	PayOrAddress.setText("地址:");//
        	  for(int i=0;i<mStringArray.length;i++)  
          	{ 
          		
          		if(mStringArray[i].equals(outaccount.getType()))
          		{
          			spinner.setSelection(i);
          		}
          	}
		}
          // spinner.setSelection(3);
		   save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(inaccount!=null)
				{   
					flag.setTime(time.getText().toString());
					flag.setFlag(note.getText().toString());
					fdao.update(flag);
					statistic.setMoney("收入:"+money.getText().toString());
					statistic.setType(spinner.getSelectedItem().toString());
					statistic.setTime(time.getText().toString());
					statisticDao.update(statistic);
					inaccount.setMoney(Double.parseDouble(money.getText().toString()));
					inaccount.setType(spinner.getSelectedItem().toString());
					inaccount.setHandler(pay.getText().toString());
					inaccount.setMark(note.getText().toString());
				    indao.update(inaccount);
				}
				if(outaccount!=null)
				{
					flag.setTime(time.getText().toString());
					flag.setFlag(note.getText().toString());
					fdao.update(flag);
					statistic.setMoney("支出:"+money.getText().toString());
					statistic.setType(spinner.getSelectedItem().toString());
					statistic.setTime(time.getText().toString());
					statisticDao.update(statistic);
					outaccount.setMoney(Double.parseDouble(money.getText().toString()));
					outaccount.setType(spinner.getSelectedItem().toString());
					outaccount.setAddress(pay.getText().toString());
					outaccount.setMark(note.getText().toString());
					outdao.update(outaccount);
				}
				Intent intent=new Intent(EditActivity.this,ManageActivity.class);
				//intent.putExtra("tag",1);
				finish();					
				startActivity(intent);							
				ManageActivity.instance.finish();
			}
		});
}
	   private void initSpinner(Spinner mSpinner){
       	
      	mStringArray=getResources().getStringArray(R.array.intype);
       //使用自定义的ArrayAdapter
      TestArrayAdapter  mAdapter = new TestArrayAdapter(this,mStringArray);
       
       //设置下拉列表风格(这句不些也行)
       //mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       mSpinner.setAdapter(mAdapter);
       //监听Item选中事件
       mSpinner.setOnItemSelectedListener(new ItemSelectedListenerImpl());
             
       }
       
       private class ItemSelectedListenerImpl implements OnItemSelectedListener{
       @Override
       public void onItemSelected(AdapterView<?> parent, View view, int position,long arg3) {
        // System.out.println("选中了:"+mStringArray[position]);
       }

       @Override
       public void onNothingSelected(AdapterView<?> parent) {}
       	
       }
       class TestArrayAdapter extends ArrayAdapter<String> {
       	  private Context mContext;
       	    private String [] mStringArray;
       	  public TestArrayAdapter(Context context, String[] stringArray) {
       	    super(context, android.R.layout.simple_spinner_item, stringArray);
       	    mContext = context;
       	    mStringArray=stringArray;
       	  }

       	  @Override
       	  public View getDropDownView(int position, View convertView, ViewGroup parent) {
       	    //修改Spinner展开后的字体颜色
       	    if (convertView == null) {
       	      LayoutInflater inflater = LayoutInflater.from(mContext);
       	      convertView = inflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent,false);
       	    }

       	    //此处text1是Spinner默认的用来显示文字的TextView
       	    TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
       	    tv.setText(mStringArray[position]);
       	    tv.setTextSize(15f);
       	    tv.setTextColor(Color.RED);

       	    return convertView;

       	  }

       	  @Override
       	  public View getView(int position, View convertView, ViewGroup parent) {
       	    // 修改Spinner选择后结果的字体颜色
       	    if (convertView == null) {
       	      LayoutInflater inflater = LayoutInflater.from(mContext);
       	      convertView = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
       	    }

       	    //此处text1是Spinner默认的用来显示文字的TextView
       	    TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
       	    tv.setText(mStringArray[position]);
       	    tv.setTextSize(10f);
       	    tv.setGravity(Gravity.CENTER|Gravity.BOTTOM);
       	    tv.setTextColor(Color.BLUE);
       	    return convertView;
       	  }

       	}
}
