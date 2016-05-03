package com.yzy.myaccount.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.Legend.LegendPosition;
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
import com.yzy.myaccount.model.Tb_inaccount;
import com.yzy.myaccount.model.Tb_outaccount;

import android.R.integer;
import android.graphics.Color;
import android.graphics.Path.Direction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.EventLogTags.Description;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class BarChartFragment extends Fragment {
	private BarChart mBarChart;
	private BarData mBarData;
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
		 
	View view = inflater.inflate(R.layout.chart,null);
	mBarChart = (BarChart)view.findViewById(R.id.bar);
		mBarData = getBarData(3,100);
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
		barChart.setDescription("");// 数据描述 
		barChart.setDescriptionPosition(400f,600f);
		barChart.fitScreen();
        // 如果没有数据的时候，会显示这个，类似ListView的EmptyView    
		barChart.setNoDataTextDescription("You need to provide data for the chart.");    
          
		barChart.setDrawGridBackground(false); // 是否显示表格颜色    
		barChart.setGridBackgroundColor(Color.WHITE & 0x70FFFFFF); // 表格的的颜色，在这里是是给颜色设置一个透明度    
       
		barChart.setTouchEnabled(false); // 设置是否可以触摸    
		barChart.setDragEnabled(true);// 是否可以拖拽    
		barChart.setScaleEnabled(false);// 是否可以缩放    
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
        mLegend.setPosition(LegendPosition.ABOVE_CHART_CENTER);
        YAxis yAxis=barChart.getAxisLeft();
        yAxis.setDrawGridLines(false);
        yAxis.setEnabled(false);
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
       
        barChart.animateX(2500); // 立即执行的动画,x轴 	
	}

	private BarData getBarData(int count, float range) {
		
		ArrayList<String> xValues = new ArrayList<String>();
		Calendar calendar=Calendar.getInstance();
		 int cmonth=calendar.get(Calendar.MONTH)+1;
		 SimpleDateFormat matter=new SimpleDateFormat("MM");
		 String mString;
		for (int i =(count-1); i>=0;i--) {
			 calendar = Calendar.getInstance();
			    calendar.add(Calendar.MONTH,-i);
			    mString=matter.format(calendar.getTime());
			xValues.add( mString+"月");
		}
		
		ArrayList<BarEntry> yValues = new ArrayList<BarEntry>();
		ArrayList<BarEntry> Values = new ArrayList<BarEntry>();
		
		  
		for (int i = 0; i<count; i++) { 
				int j=2-i;
				calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH,-j);
			int c=calendar.get(Calendar.MONTH)+1;
			Log.i("日历",""+c);
			 int inaccount=0;
			 int outaccount=0;
            Tb_inaccountDao indao=new Tb_inaccountDao(getActivity());
            Tb_outaccountDao oDao=new Tb_outaccountDao(getActivity());
           List list=indao.getLTData(c);
           List list2=oDao.getLTData(c);
           java.util.Iterator iterator=list.iterator();
           java.util.Iterator iterator1=list2.iterator();
          
           while(iterator1.hasNext())
           {
        	   Tb_outaccount out=(Tb_outaccount)iterator1.next();
        	   outaccount+=out.getMoney();
        	   Log.i("支出",""+outaccount);
           }
           while(iterator.hasNext())
           {
        	   Tb_inaccount in=(Tb_inaccount)iterator.next();
        	   inaccount+=in.getMoney();
           }
            Log.i("收入",""+inaccount);
            yValues.add(new BarEntry(inaccount, i));
            Values.add(new BarEntry(outaccount, i));
            
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
}
