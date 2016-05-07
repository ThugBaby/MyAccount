package com.yzy.myaccount.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import android.R.integer;
import android.app.DatePickerDialog;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.yzy.myaccount.R;
import com.yzy.myaccount.R.string;
import com.yzy.myaccount.activity.AddAccount;
import com.yzy.myaccount.activity.MainActivity;
import com.yzy.myaccount.activity.TypeActivity;
import com.yzy.myaccount.dao.Tb_flagDao;
import com.yzy.myaccount.dao.Tb_inaccountDao;
import com.yzy.myaccount.dao.Tb_outaccountDao;
import com.yzy.myaccount.dao.Tb_statisticDao;
import com.yzy.myaccount.dao.Tb_typeDao;
import com.yzy.myaccount.fragment.menuFragment.MyOnClickListener;
import com.yzy.myaccount.fragment.menuFragment.MyOnPageChangeListener;
import com.yzy.myaccount.fragment.menuFragment.MyPagerAdapter;
import com.yzy.myaccount.model.Tb_flag;
import com.yzy.myaccount.model.Tb_inaccount;
import com.yzy.myaccount.model.Tb_outaccount;
import com.yzy.myaccount.model.Tb_statistic;
import com.yzy.myaccount.model.Tb_type;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class addAccountFrag extends Fragment {
	private List<View> listViews; // Tab页面列表
	private ImageView cursor;// 动画图片
	private TextView t1, t2, t3;// 页卡头标
	private int offset = 0;// 动画图片偏移量
	private int currIndex = 0;// 当前页卡编号
	private int bmpW;// 动画图片宽度
	private ViewPager viewPage;
	View view ;
	AddAccount   fca;
	protected static final int DATE_DIALOG_ID=0;
	EditText editMoney ,editPay,editNote,address;
	TextView  editTime;
	Spinner spinner;
	Button btSave,btCancel;
	private int mYear;
	private int mMonth;
	private int mDay;
	private ArrayAdapter<String> mAdapter ;
	  private String [] mStringArray;
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		fca = (AddAccount) getActivity();
	}

	@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
		 
		view = inflater.inflate(R.layout.addaccount, null);
		InitImageView();
		InitTextView();
		InitViewPager();
		return view;

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
	/**
     * 初始化头标
*/
	 private void InitTextView() {
		          t1 = (TextView)view.findViewById(R.id.tw1);
		         t2 = (TextView)view.findViewById(R.id.tw2);
		        t1.setOnClickListener(new MyOnClickListener(0));
		       t2.setOnClickListener(new MyOnClickListener(1));
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
	    	   
	    	   viewPage = (ViewPager)view.findViewById(R.id.addpager);
	             listViews = new ArrayList<View>();
	             
	             LayoutInflater mInflater = fca.getLayoutInflater();
	             listViews.add(mInflater.inflate(R.layout.addinaccount, null));
	            listViews.add(mInflater.inflate(R.layout.addoutaccount, null));
	            viewPage.setAdapter(new MyPagerAdapter(listViews));
	            viewPage.setCurrentItem(0);
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
	            	  
	            	   //问题解决：加判断 判断当前是在哪个页面
	            	   switch(position)
	            	   {
	            	   case 0:
	            		   
	            	   editMoney=(EditText)mListViews.get(position).findViewById(R.id.editMoney);
	           	    editTime=(TextView)mListViews.get(position).findViewById(R.id.editTime);
	           	    editPay=(EditText)mListViews.get(position).findViewById(R.id.editPay);
	           	    editNote=(EditText)mListViews.get(position).findViewById(R.id.editNote);
	           	    spinner=(Spinner)mListViews.get(position).findViewById(R.id.spinner1);
	           	    btSave=(Button)mListViews.get(position).findViewById(R.id.btSave);
	              	    btCancel=(Button)mListViews.get(position).findViewById(R.id.btCancel);
	              	  initSpinner(spinner);
	              	    SimpleDateFormat dFormat=new SimpleDateFormat("HH:mm:ss");
	              	 Calendar calendar=Calendar.getInstance();
	           	 //获得当前时间的月份，月份从0开始所以结果要加1
	           		int cmonth=calendar.get(Calendar.MONTH)+1;
	              	     SimpleDateFormat d=new SimpleDateFormat("yyyy-'"+cmonth+"'-dd");
	              	     final String date=d.format(new Date());
	              	    editTime.setText(dFormat.format(new Date()));
	              	    btSave.setOnClickListener(new OnClickListener() {
	           			
	           			@Override
	           			public void onClick(View arg0) {
	           				// TODO Auto-generated method stub
	           				String  InMoney=null;
	           				InMoney=editMoney.getText().toString();
	           				if(!InMoney.isEmpty())
	           				{
	           					
	           					Tb_inaccountDao inaccountDao=new Tb_inaccountDao(getActivity());
	           					Tb_flagDao flagDao=new Tb_flagDao(getActivity());
	           					Tb_statisticDao statisticDao=new Tb_statisticDao(getActivity());
	           					Tb_inaccount inaccount=new Tb_inaccount(inaccountDao.getMaxId()+1,Double.parseDouble(InMoney),date,editTime.getText().toString(),spinner.getSelectedItem().toString(),editPay.getText().toString(),editNote.getText().toString());
	           					Tb_statistic statistic=new Tb_statistic(date, editTime.getText().toString(),"收入:"+InMoney,spinner.getSelectedItem().toString());
	           					Tb_flag flag=new Tb_flag(0,editNote.getText().toString(),editTime.getText().toString());
	           					flagDao.add(flag);
	           					inaccountDao.add(inaccount);
	           					statisticDao.add(statistic);
	           					Intent intent=new Intent((AddAccount)getActivity(),MainActivity.class);
	           					startActivity(intent);
	           					getActivity().finish();
	           				}
	           				else
	           				{
	           					Toast.makeText(getActivity(),"请输入金额！", Toast.LENGTH_SHORT).show();
	           					
	           				}
	           			}
	           		});
	              	    btCancel.setOnClickListener(new OnClickListener() {
	           			
	           			@Override
	           			public void onClick(View arg0) {
	           				// TODO Auto-generated method stub
	           				
	           				editMoney.setText("");
	           				spinner.setSelection(0);
	           				editPay.setText("");
	           				editNote.setText("");
	           				
	           			}
	           		});
	              	    editTime.setOnClickListener(new OnClickListener() {
	           			
	           		
	           			@Override
	           			public void onClick(View arg0) {
	           				// TODO Auto-generated method stub
	           				//showDialog(DATE_DIALOG_ID);
	           			}
	           		});
	           	    final Calendar c=Calendar.getInstance();
	           	    mYear=c.get(Calendar.YEAR);
	           	    mMonth=c.get(Calendar.MONTH);
	           	    mDay=c.get(Calendar.DAY_OF_MONTH);
	           	    //updateDisplay();
	               
	                  break;
	            	   case 1:
	            		   
		            	  final EditText oeditMoney=(EditText)mListViews.get(position).findViewById(R.id.oeditPay);
		            	  final TextView  oeditTime=(TextView)mListViews.get(position).findViewById(R.id.oeditTime);
		            	  final EditText  oaddress=(EditText)mListViews.get(position).findViewById(R.id.oadress);
		            	  final  EditText  oeditNote=(EditText)mListViews.get(position).findViewById(R.id.oeditNote);
		            	  final Spinner ospinner=(Spinner)mListViews.get(position).findViewById(R.id.ospinner1);
		            	  final Button  obtSave=(Button)mListViews.get(position).findViewById(R.id.obtSave);
		            	  final Button  obtCancel=(Button)mListViews.get(position).findViewById(R.id.obtCancel);
		            	   initSpinner(ospinner);
		              	    SimpleDateFormat dFormat1=new SimpleDateFormat("HH:mm:ss");
		              	 Calendar calendar1=Calendar.getInstance();
		           	 //获得当前时间的月份，月份从0开始所以结果要加1
		           		int cmonth1=calendar1.get(Calendar.MONTH)+1;
		              	     SimpleDateFormat d1=new SimpleDateFormat("yyyy-'"+cmonth1+"'-dd");
		              	     final String date1=d1.format(new Date());
		              	    oeditTime.setText(dFormat1.format(new Date()));
		              	    obtSave.setOnClickListener(new OnClickListener() {
		           			
		           			@Override
		           			public void onClick(View arg0) {
		           				// TODO Auto-generated method stub
		           				String  outMoney=null;
		           				outMoney=oeditMoney.getText().toString();
		           				if(!outMoney.isEmpty())
		           				{
		           					
		           					Tb_outaccountDao outaccountDao=new Tb_outaccountDao(getActivity());
		           					Tb_statisticDao statisticDao=new Tb_statisticDao(getActivity());
		           					Tb_outaccount outaccount=new Tb_outaccount(outaccountDao.getMaxId()+1,Double.parseDouble(outMoney),date1,oeditTime.getText().toString(),ospinner.getSelectedItem().toString(),oaddress.getText().toString(),oeditNote.getText().toString());
		           					Tb_statistic statistic=new Tb_statistic(date1,oeditTime.getText().toString(),"支出:"+outMoney,ospinner.getSelectedItem().toString());
		           					outaccountDao.add(outaccount);
		           					statisticDao.add(statistic);
		           					Intent intent=new Intent((AddAccount)getActivity(),MainActivity.class);
		           					startActivity(intent);
		           					getActivity().finish();
		           				}
		           				else
		           				{
		           					Toast.makeText(getActivity(),"请输入金额！", Toast.LENGTH_SHORT).show();
		           					
		           				}
		           			}
		           		});
		              	    obtCancel.setOnClickListener(new OnClickListener() {
		           			
		           			@Override
		           			public void onClick(View arg0) {
		           				// TODO Auto-generated method stub
		           				oeditMoney.setText("");
		           				ospinner.setSelection(0);
		           				oaddress.setText("");
		           				oeditNote.setText("");
		           				
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
	                cursor = (ImageView)view.findViewById(R.id.cursor1);
	                bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.a)
	                        .getWidth();// 获取图片宽度
	                DisplayMetrics dm = new DisplayMetrics();
	                fca.getWindowManager().getDefaultDisplay().getMetrics(dm);
	                int screenW = dm.widthPixels;// 获取分辨率宽度
	                offset = (screenW / 2 - bmpW) / 2;// 计算偏移量
	                Matrix matrix = new Matrix();
	                matrix.postTranslate(0, 0);
	                cursor.setImageMatrix(matrix);// 设置动画初始位置
	            }
	            /**
	             * 页卡切换监听
	        */
	            public class MyOnPageChangeListener implements OnPageChangeListener {

	                int one = offset+ bmpW;// 页卡1 -> 页卡2 偏移量
	                //int two = one * 2;// 页卡1 -> 页卡3 偏移量

	                @Override
	                public void onPageSelected(int arg0) {
	                    Animation animation = null;
	                    switch (arg0) {
	                    case 0:
			                    cColor(0);
			                
	                        if (currIndex == 1) {
	                            animation = new TranslateAnimation(one, 0, 0, 0);}
	                        break;
	                    case 1:
	                    	cColor(1);
	                        if (currIndex == 0) {
	                            animation = new TranslateAnimation(offset, one, 0, 0);
	                        } 
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
	            public void cColor(int index)
	            {
	            	switch(index)
		            {
		            case 0: t1.setTextColor(R.color.slide);
		            		t2.setTextColor(Color.BLACK);
		            		
		            break;
		            case 1: 
		            	t1.setTextColor(Color.BLACK);
	            		t2.setTextColor(R.color.slide);
	            	break;
	            	default:
	            		t1.setTextColor(Color.BLACK);
	            		t2.setTextColor(Color.BLACK);
	            		break;
		            }
	            }
//	        	private void updateDisplay()
//	           	{
//	           		
//	           		editTime.setText(new StringBuilder().append(mYear).append("-").append(mMonth+1).append("-").append(mDay));
//	           		
//	           	}
	           	protected Dialog onCreateDialog(int id)
	           	{
	           		switch(id)
	           		{
	           		case DATE_DIALOG_ID:
	           			return new DatePickerDialog(getActivity(),mDateListener,mYear,mMonth,mDay);
	           		
	           		
	           		}
	           		return null;
	           		
	           	}
	           	private DatePickerDialog.OnDateSetListener mDateListener=new DatePickerDialog.OnDateSetListener() {
	           		
	           		@Override
	           		public void onDateSet(DatePicker arg0, int year, int month, int day) {
	           			// TODO Auto-generated method stub
	           			mYear=year;
	           			mMonth=month;
	           			mDay=day;
	           			//updateDisplay();
	           		}
	           	};
	            private void initSpinner(Spinner mSpinner){
	            	 Tb_typeDao dao=new Tb_typeDao(getActivity());
	                List<String> list=dao.getAll();
	                String[] arrayString=new String[list.size()+1];
	                for(int i=0;i<list.size();i++)
	                {
	                	arrayString[i]=list.get(i);
	                }
	                 arrayString[arrayString.length-1]="类别管理";
	                	mStringArray=arrayString;
	            //使用自定义的ArrayAdapter
	            mAdapter = new TestArrayAdapter(getActivity(),mStringArray);
	            
	            //设置下拉列表风格(这句不些也行)
	            //mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	            mSpinner.setAdapter(mAdapter);
	            //监听Item选中事件
	            mSpinner.setOnItemSelectedListener(new ItemSelectedListenerImpl());
	                  
	            }	            
	            private class ItemSelectedListenerImpl implements OnItemSelectedListener{
	            @Override
	            public void onItemSelected(AdapterView<?> parent, View view, int position,long arg3) {
	           
	              if(position==mStringArray.length-1)
	              {
	            	  Log.i("选择了",""+mStringArray[position]);
	            	  AddAccount.instance.finish();
	            	  Intent intent=new Intent(getActivity(),TypeActivity.class);
	            	  startActivity(intent);
	              }
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
