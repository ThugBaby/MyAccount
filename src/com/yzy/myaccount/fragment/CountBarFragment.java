package com.yzy.myaccount.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.yzy.myaccount.R;
import com.yzy.myaccount.dao.Tb_inaccountDao;
import com.yzy.myaccount.dao.Tb_outaccountDao;
import com.yzy.myaccount.dao.Tb_statisticDao;
import com.yzy.myaccount.model.Tb_inaccount;
import com.yzy.myaccount.model.Tb_outaccount;
import com.yzy.myaccount.model.Tb_statistic;

public class CountBarFragment extends Fragment implements OnClickListener {
	private BarChart mBarChart;
	private BarData mBarData;
	View view;
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
		 
	 view = inflater.inflate(R.layout.countbar,container,false);
	 View barallView=view.findViewById(R.id.barall); 
	 View barinView=view.findViewById(R.id.barin); 
	 View baroutView=view.findViewById(R.id.barout); 
	 barallView.setOnClickListener(this);
	 barinView.setOnClickListener(this);
	 baroutView.setOnClickListener(this);
	mBarChart = (BarChart)view.findViewById(R.id.countbar);
		mBarData = getBarData(12,0);//0表示全部显示，1表示只显示收入，2表示只显示支出
		showBarChart(mBarChart, mBarData);
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
	private void showBarChart(BarChart barChart, BarData barData) {
		barChart.setDrawBorders(false);  ////是否在折线图上添加边框 
		barChart.animateXY(3000, 2000);
		barChart.setDescription("年度统计:元");// 数据描述 
		barChart.setDescriptionPosition(320f,80f);
		barChart.fitScreen();
        // 如果没有数据的时候，会显示这个，类似ListView的EmptyView    
		barChart.setNoDataTextDescription("You need to provide data for the chart.");              
		barChart.setDrawGridBackground(false); // 是否显示表格颜色    
		barChart.setGridBackgroundColor(Color.WHITE & 0x70FFFFFF); // 表格的的颜色，在这里是是给颜色设置一个透明度           
		barChart.setTouchEnabled(true); // 设置是否可以触摸    
		barChart.setDragEnabled(true);// 是否可以拖拽    
		barChart.setScaleEnabled(true);// 是否可以缩放    
		barChart.setDescriptionTextSize(16);
		barChart.setPinchZoom(false);//     
		
//		barChart.setBackgroundColor();// 设置背景    
		
		//barChart.isDrawMarkerViewEnabled();
		barChart.setDrawBarShadow(false);
         barChart.setDrawGridBackground(false);
		barChart.setData(barData); // 设置数据    
		Legend mLegend = barChart.getLegend(); // 设置比例图标示
        mLegend.setForm(LegendForm.CIRCLE);// 样式    
        mLegend.setFormSize(6f);// 字体    
        mLegend.setTextColor(Color.RED);// 颜色 
        YAxis yAxis=barChart.getAxisLeft();
        yAxis.setDrawGridLines(false);
        yAxis.setEnabled(true);
        yAxis.setStartAtZero(true);
        
        YAxis rYAxis=barChart.getAxisRight();
        rYAxis.setEnabled(false);
//      X轴设定
       XAxis xAxis = barChart.getXAxis();
    xAxis.setPosition(XAxisPosition.BOTTOM); 
       xAxis.setDrawAxisLine(true);
        xAxis.setDrawLabels(true);
        xAxis.setTextSize(10); //X轴上的刻度的字的大小 单位dp  
       xAxis.setDrawGridLines(false);//不显示x坐标轴刻度竖线
       //xAxis.setGridLineWidth(1);
       //xAxis.setLabelsToSkip(20);    //设置坐标相隔多少，参数是int类型  
       xAxis.resetLabelsToSkip();   //将自动计算坐标相隔多少  
       xAxis.setSpaceBetweenLabels(2);
       
        barChart.animateX(1500); // 立即执行的动画,x轴 	
	}

private BarData getBarData(int count, int tag) {
		
		ArrayList<String> xValues = new ArrayList<String>();
		Calendar calendar=Calendar.getInstance();
		 int cmonth=calendar.get(Calendar.MONTH)+1;
		 SimpleDateFormat matter=new SimpleDateFormat("MM");
		 String mString;
		for (int i =1; i<=12;i++) {
			 
			xValues.add( i+"月");
		}
		
		ArrayList<BarEntry> yValues = new ArrayList<BarEntry>();//收入
		ArrayList<BarEntry> Values = new ArrayList<BarEntry>();//支出
		yValues.clear();
		 Values.clear();
		for (int i = 1; i<=count; i++) { 
			 int inaccount=0;
			 int outaccount=0;
           Tb_statisticDao dao=new Tb_statisticDao(getActivity());
            List list=dao.getDataByDate(i);
           java.util.Iterator iterator=list.iterator();      
           while(iterator.hasNext())
           {
        	   
        	   Tb_statistic out=( Tb_statistic)iterator.next();
        	   switch (out.getMoney().substring(0,2)) {
			case "收入":
				  inaccount+=Float.parseFloat(out.getMoney().substring(3));
				break;
			case "支出":
				outaccount+=Float.parseFloat(out.getMoney().substring(3));
				break;
			default:
				break;
			}
        	    
           }
           switch (tag) {
		case 0://全部显示
			
			 yValues.add(new BarEntry(inaccount, i-1));
	            Values.add(new BarEntry(outaccount, i-1));
			break;
		case 1://显示收入
		
			 yValues.add(new BarEntry(inaccount, i-1));
			 break;
		case 2://显示支出
			
			 Values.add(new BarEntry(outaccount, i-1));
			break;
		default:
			break;
		}
         
        }
		
		// y轴的数据集合    
        BarDataSet barDataSet = new BarDataSet(yValues, "收入"); 
        BarDataSet tbarDataSet = new BarDataSet(Values, "支出"); 
        barDataSet.setColor(Color.rgb(114, 188, 223));
        tbarDataSet.setColor(Color.rgb(255, 255,0));
        List<IBarDataSet> barDataSets = new ArrayList<IBarDataSet>();    
        barDataSets.add(barDataSet);//add the datasets    
         barDataSets.add(tbarDataSet);
        BarData barData = new BarData(xValues, barDataSets);
		return barData;
	}

@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	if(v.getId()==R.id.barall)
	{
		 // Toast.makeText(getActivity(),"ssss",1000).show();
		mBarChart=(BarChart)view.findViewById(R.id.countbar);
		mBarData=getBarData(12,0);
		showBarChart(mBarChart,mBarData);
	}
	if(v.getId()==R.id.barin)
	{
		 // Toast.makeText(getActivity(),"ssss",1000).show();
		mBarChart=(BarChart)view.findViewById(R.id.countbar);
		mBarData=getBarData(12,1);
		showBarChart(mBarChart,mBarData);
	}
	if(v.getId()==R.id.barout)
	{
		 // Toast.makeText(getActivity(),"ssss",1000).show();
		mBarChart=(BarChart)view.findViewById(R.id.countbar);
		mBarData=getBarData(12,2);
		showBarChart(mBarChart,mBarData);
	}
}
}

