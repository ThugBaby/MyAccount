package com.yzy.myaccount.fragment;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yzy.myaccount.R;






import com.yzy.myaccount.dao.Tb_statisticDao;
import com.yzy.myaccount.model.Tb_statistic;

import android.R.anim;
import android.R.integer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class CountListFragment  extends Fragment{
	ListView lview;
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
		  View view=inflater.inflate(R.layout.countlist,null);
		  View  view2=inflater.inflate(R.layout.countlistitem,null);
		  lview=(ListView)view.findViewById(R.id.countlist);
		 
		  List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		   // Map<String, Object> map=new HashMap<String, Object>();
		    for(int i=1;i<=12;i++)
		    {
		    	 int inaccount=0,outaccount=0;
		    	 Map<String, Object> map=new HashMap<String, Object>();
		    	 Tb_statisticDao dao=new Tb_statisticDao(getActivity());
		            List list2=dao.getDataByDate(i);
		           java.util.Iterator iterator=list2.iterator();      
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
		    	 
		    	 
		    	 
		    map.put("m",i+"月");
		    map.put("in","收入:"+inaccount+"元");
		    map.put("out","支出:"+outaccount+"元");
		    map.put("rest","盈余:"+(inaccount-outaccount)+"元");
		    list.add(map);
		    }
		    SimpleAdapter simpleAdapter=new SimpleAdapter(getActivity(),list,R.layout.countlistitem,new String[]{"m","in","out","rest"},new int[]{R.id.month,R.id.in,R.id.out,R.id.rest} );		  
		    String [] aStrings={"ss","dd","ff"};
		    //ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,aStrings);
		    
		    lview.setAdapter(simpleAdapter);
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
}
