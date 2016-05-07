package com.yzy.myaccount.fragment;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;




import java.util.Random;
import java.util.logging.Logger;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.yzy.myaccount.R;
import com.yzy.myaccount.dao.Tb_atypeDao;
import com.yzy.myaccount.dao.Tb_statisticDao;
import com.yzy.myaccount.dao.Tb_typeDao;
import com.yzy.myaccount.model.Tb_statistic;
import com.yzy.myaccount.util.ActivityManager;

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
	        Tb_atypeDao tdao=new Tb_atypeDao(getActivity());
	        List tlist=tdao.getAll();
	        int len=tlist.size();
	        Random random=new Random();
	        String type[]=new String[len];
	        float money[]=new float[len];
	        float min[]=new float[len];
	        float mout[]=new float[len];
	        boolean a[]=new boolean[len];
	        boolean bn[]=new boolean[len];
	        boolean cn[]=new boolean[len];
	        int color[]=new int[len];
	        for(int i=0;i<len;i++)
	        {
	        	a[i]=false;
	        	bn[i]=false;
	        	cn[i]=false;
	        	color[i]= Color.rgb(random.nextInt(256),random.nextInt(256),random.nextInt(256));
	        	type[i]=(String)tlist.get(i);
	        }
	        float in=0,out=0,tin=0,tout=0;
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
	        for(int i=0;i<len;i++)
	        {   
	        	Log.i("type",type[i]);
	        	if(statistic.getType().equals(type[i]))
	        	{
				//按类别汇总收入
					   switch (statistic.getMoney().substring(0, 2)) {
					case "收入":
						  min[i]+=Float.parseFloat(statistic.getMoney().substring(3));
						  bn[i]=true;
						break;
					case "支出":
						 mout[i]+=Float.parseFloat(statistic.getMoney().substring(3));
						 cn[i]=true;
						break;
					default:
						break;
					
					}
					money[i]+=Float.parseFloat(statistic.getMoney().substring(3));					   
					   a[i]=true;
					   Log.i("flag",""+a[i]);
	        	}
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
	    //产生随机颜色
	   
	    @Override
		public void onDestroyView() {
			super.onDestroyView();
		}
		
		
		@Override
		public void onDestroy() {
			super.onDestroy();
		}

		
		
}
