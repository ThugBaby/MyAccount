package com.yzy.myaccount.fragment;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;




import java.util.logging.Logger;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.yzy.myaccount.R;
import com.yzy.myaccount.dao.Tb_statisticDao;
import com.yzy.myaccount.model.Tb_statistic;

import android.R.integer;
import android.R.raw;
import android.graphics.Color;
import android.os.Bundle;
import android.renderscript.Type;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Toast;
public class ChartFragment extends Fragment implements OnClickListener{
	private PieChart mChart;
	View view;
	View binView ,boutView,ballView;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.mainchart,null);
		binView=view.findViewById(R.id.bin);
		binView.setOnClickListener(this);
		boutView=view.findViewById(R.id.bout);
		ballView=view.findViewById(R.id.ball);
		ballView.setOnClickListener(this);
		boutView.setOnClickListener(this);
		mChart=(PieChart)view.findViewById(R.id.chart);
		PieData mPieData=getPieData(0);
		showChart(mChart,mPieData);
		return view;
	}
	
	private void showChart(PieChart pieChart, PieData pieData) {    
	      //  pieChart.setHoleColorTransparent(true);    
	    
	        pieChart.setHoleRadius(60f);  //半径    
	        pieChart.setTransparentCircleRadius(64f); // 半透明圈    
	        //pieChart.setHoleRadius(0)  //实心圆    
	    
	        pieChart.setDescription("饼状图");    
	    
	        // mChart.setDrawYValues(true);    
	        pieChart.setDrawCenterText(true);  //饼状图中间可以添加文字    
	    
	        pieChart.setDrawHoleEnabled(true);    
	    
	        pieChart.setRotationAngle(90); // 初始旋转角度    
	    
	        // draws the corresponding description value into the slice    
	        // mChart.setDrawXValues(true);    
	    
	        // enable rotation of the chart by touch    
	        pieChart.setRotationEnabled(true); // 可以手动旋转    
	    
	        // display percentage values    
	        pieChart.setUsePercentValues(true);  //显示成百分比    
	        //mChart.setUnit(" €");    
	        // mChart.setDrawUnitsInChart(true);    
	    
	        // add a selection listener    
//	      mChart.setOnChartValueSelectedListener(this);    
	         //pieChart.setTouchEnabled(false);  
	        
	    
//	      mChart.setOnAnimationListener(this);    
	     
	        pieChart.setCenterText("年度统计%");  //饼状图中间的文字    
	    
	        //设置数据    
	        pieChart.setData(pieData);     
	            
	        // undo all highlights    
//	      pieChart.highlightValues(null);    
//	      pieChart.invalidate();    
	    
	        Legend mLegend = pieChart.getLegend();  //设置比例图    
	        mLegend.setPosition(LegendPosition.RIGHT_OF_CHART);  //最右边显示    
//	      mLegend.setForm(LegendForm.LINE);  //设置比例图的形状，默认是方形    
	        mLegend.setXEntrySpace(7f);    
	        mLegend.setYEntrySpace(5f);    
	            
	        pieChart.animateXY(1000, 1000);  //设置动画    
	        // mChart.spin(2000, 0, 360);    
	    }    
	    
	    /**  
	     *   
	     * @param count 分成几部分  
	     * @param range  
	     */    
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.bin)
		{
			 // Toast.makeText(getActivity(),"ssss",1000).show();
			mChart=(PieChart)view.findViewById(R.id.chart);
			PieData PieData=getPieData(1);
		showChart(mChart,PieData);
		}
		if(v.getId()==R.id.bout)
		{
			 // Toast.makeText(getActivity(),"ssss",1000).show();
			mChart=(PieChart)view.findViewById(R.id.chart);
			PieData PieData=getPieData(2);
		showChart(mChart,PieData);
		}
		if(v.getId()==R.id.ball)
		{
			 // Toast.makeText(getActivity(),"ssss",1000).show();
			mChart=(PieChart)view.findViewById(R.id.chart);
			PieData PieData=getPieData(0);
		showChart(mChart,PieData);
		}
	}
	    public PieData getPieData(int tag) {    
	            
	        ArrayList<String> xValues = new ArrayList<String>();  //xVals用来表示每个饼块上的内容    
	    
	        boolean a[]={false,false,false,false,false};
	        boolean bn[]={false,false,false,false,false};
	        boolean cn[]={false,false,false,false,false};
	        float in=0,out=0,tin=0,tout=0;
	        int color[]={   Color.rgb(205, 205, 205) ,  
	  	 	      Color.rgb(114, 188, 223) ,   
		 	       Color.rgb(255, 123, 124),   
		 	       Color.rgb(57, 135, 200) ,
		 	      Color.rgb(57, 100, 243) };
	        float money[]={0,0,0,0,0};
	        float min[]={0,0,0,0,0};
	        float mout[]={0,0,0,0,0};
	        String type[]={"工资","外出","贪污","腐败","其他"};
	        Tb_statisticDao dao=new Tb_statisticDao(getActivity());
	        List<Tb_statistic> list=dao.getAllData();
	        Iterator<Tb_statistic> iterator=list.iterator();
	        while(iterator.hasNext())
	        {
	        	Tb_statistic statistic=(Tb_statistic)iterator.next();
	        	switch (statistic.getMoney().substring(0,2)) {
				case "收入"://汇总收入的钱
					   in+=Float.parseFloat(statistic.getMoney().substring(3));
					break;
				case "支出"://汇总支出的钱
					out+=Float.parseFloat(statistic.getMoney().substring(3));
					break;
				default:
					break;
				}
	        	switch (statistic.getType()) {
				case "工资"://按类别汇总收入
					   switch (statistic.getMoney().substring(0, 2)) {
					case "收入":
						  min[0]+=Float.parseFloat(statistic.getMoney().substring(3));
						  bn[0]=true;
						break;
					case "支出":
						 mout[0]+=Float.parseFloat(statistic.getMoney().substring(3));
						 cn[0]=true;
						break;
					default:
						break;
					}
					money[0]+=Float.parseFloat(statistic.getMoney().substring(3));
					   
					   a[0]=true;
					break;
				case "外出":
					switch (statistic.getMoney().substring(0, 2)) {
					case "收入":
						  min[1]+=Float.parseFloat(statistic.getMoney().substring(3));
						  bn[1]=true;
						break;
					case "支出":
						 mout[1]+=Float.parseFloat(statistic.getMoney().substring(3));
						 cn[1]=true;
						break;
					default:
						break;
					}
					money[1]+=Float.parseFloat(statistic.getMoney().substring(3));
					   a[1]=true;
					break;
				case "贪污":
					switch (statistic.getMoney().substring(0, 2)) {
					case "收入":
						  min[2]+=Float.parseFloat(statistic.getMoney().substring(3));
						  bn[2]=true;
						break;
					case "支出":
						 mout[2]+=Float.parseFloat(statistic.getMoney().substring(3));
						 cn[2]=true;
						break;
					default:
						break;
					}
					money[2]+=Float.parseFloat(statistic.getMoney().substring(3));
					   a[2]=true;
					break;
				case "腐败":
					switch (statistic.getMoney().substring(0, 2)) {
					case "收入":
						  min[3]+=Float.parseFloat(statistic.getMoney().substring(3));
						  
						  bn[3]=true;
						break;
					case "支出":
						 mout[3]+=Float.parseFloat(statistic.getMoney().substring(3));
						 cn[3]=true;
						break;
					default:
						break;
					}
					money[3]+=Float.parseFloat(statistic.getMoney().substring(3));
					   a[3]=true;
					break;
				case "其他":
					switch (statistic.getMoney().substring(0, 2)) {
					case "收入":
						  min[4]+=Float.parseFloat(statistic.getMoney().substring(3));
						  bn[4]=true;
						break;
					case "支出":
						 mout[4]+=Float.parseFloat(statistic.getMoney().substring(3));
						 cn[4]=true;
						break;
					default:
						break;
					}
					money[4]+=Float.parseFloat(statistic.getMoney().substring(3));
					   a[4]=true;
					break;
				default:
					break;
				}
	        	
	        }
	        ArrayList<Entry> yValues = new ArrayList<Entry>();  //yVals用来表示封装每个饼块的实际数据    
	        
	        //y轴的集合    
	        PieDataSet pieDataSet = new PieDataSet(yValues, "年度统计"/*显示在比例图上*/);    
	        pieDataSet.setSliceSpace(0f); //设置个饼状图之间的距离    
	   	    ArrayList<Integer> colors = new ArrayList<Integer>();    
  	    
	      int c=0,b=0,b1=-1;
	      float m=0;
	         
	        /**  
	         * 将一个饼形图分成四部分， 四部分的数值比例为14:14:34:38  
	         * 所以 14代表的百分比就是14%   
	         */   
	       // DecimalFormat decimalFormat=new DecimalFormat(".0");//构造方法的字符格式这里如果小数不足2位,会以0补足.
	       switch (tag) {
		case 0:
			xValues.clear();
			yValues.clear();
			for(int i=0;i<a.length;i++)
	        {
	        	if(a[i])
	        	{
	        		
	        		xValues.add(type[i]);//饼块上显示成 工资，等等
	        		m=m+money[i];
	        		
	        	}
	        	
	        }	 
			for(int i=0;i<a.length;i++)
	        {
	        	if(a[i])
	        	{    
	        		float p=money[i]/m;
	        		
	        		 yValues.add(new Entry(p,b));//
	        		 b++;
	        		 colors.add(color[i]);
	        		
	        	}
	 	        // 饼图颜色    
	 	     
	        }	
	    
			break;
         case 1:
        	 b=0;
        	 xValues.clear();
        	 yValues.clear();
        	 for(int i=0;i<bn.length;i++)
 	        {
 	        	if(bn[i])
 	        	{
 	        		xValues.add(type[i]);//饼块上显示成 工资，等等
 	        		
 	        		
 	        	}
 	        	
 	        }	 
        	 for(int i=0;i<bn.length;i++)
 	        {
 	        	if(bn[i])
 	        	{    
 	        		float p=min[i]/in;
 	        		
 	        		 yValues.add(new Entry(p,b));//
 	        		 b++;
 	        		 colors.add(color[i]);
 	        		
 	        	}
 	 	        // 饼图颜色    
 	 	     
 	        }	
 	    
			break;
         case 2:
        	 xValues.clear();
        	 yValues.clear();
        	 b=0;
        	 for(int i=0;i<cn.length;i++)
  	        {
  	        	if(cn[i])
  	        	{
  	        		xValues.add(type[i]);//饼块上显示成 工资，等等
  	        		
  	        		
  	        	}
  	        	
  	        }	 
        	 for(int i=0;i<cn.length;i++)
 	        {
 	        	if(cn[i])
 	        	{   
 	        		float p=mout[i]/out;
 	        		
 	        		 yValues.add(new Entry(p,b));//
 	        		 b++;
 	        		 colors.add(color[i]);
 	        		
 	        	}
 	 	        // 饼图颜色    
 	 	     
 	        }	
 	    
 			break;
		default:
			break;
		}
	        
	       
	       
	        pieDataSet.setColors(colors);    
	        DisplayMetrics metrics = getResources().getDisplayMetrics();    
	        float px = 3* (metrics.densityDpi / 160f);    
	        pieDataSet.setSelectionShift(px); // 选中态多出的长度    
	        PieData pieData = new PieData(xValues, pieDataSet);              
	        return pieData;   
	    }
	    @Override
		public void onDestroyView() {
			super.onDestroyView();
		}
		
		
		@Override
		public void onDestroy() {
			super.onDestroy();
		}

		
		
}
