package com.yzy.myaccount.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.R.integer;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.yzy.myaccount.model.Tb_inaccount;
import com.yzy.myaccount.model.Tb_statistic;
import com.yzy.myaccount.util.DBOpenHelper;

public class Tb_statisticDao {
	private  DBOpenHelper helper;
	private SQLiteDatabase db;
	 public Tb_statisticDao(Context context)
     {
   	  helper=new DBOpenHelper(context);
     }
     public void add(Tb_statistic tb_statistic)
     {   
   	  db=helper.getWritableDatabase();
   	  String sql="insert into tb_statistic(date,time,money,type) values(?,?,?,?)";
   	  Object []object=new Object[]{tb_statistic.getDate(),tb_statistic.getTime(),tb_statistic.getMoney(),tb_statistic.getType()};
   	  
   	  db.execSQL(sql,object);
   	  close();
     }
     public List<Tb_statistic> getAllData()
     
     {
   	  List<Tb_statistic> tb_statistic=new ArrayList<Tb_statistic>();
   	  db=helper.getWritableDatabase();
   	  Cursor cursor=db.rawQuery("select * from tb_statistic",null);
   	 
   	  while(cursor.moveToNext())
   	  {
   		tb_statistic.add(new Tb_statistic(cursor.getString(cursor.getColumnIndex("date")),cursor.getString(cursor.getColumnIndex("time")),cursor.getString(cursor.getColumnIndex("money")),cursor.getString(cursor.getColumnIndex("type"))));
   	  }
   	  close();
   	  return tb_statistic;
     }
     public List<Tb_statistic> getScrollData(int start,int count)
     {
   	  List<Tb_statistic> tb_statistics=new ArrayList<Tb_statistic>();
   	  db=helper.getWritableDatabase();
   	  Cursor cursor=db.rawQuery("select * from tb_statistic limit ?,?",new String[]{String.valueOf(start),String.valueOf(count)});
   	  while(cursor.moveToNext())
   	  {
   		tb_statistics.add(new Tb_statistic(cursor.getString(cursor.getColumnIndex("date")),cursor.getString(cursor.getColumnIndex("time")),cursor.getString(cursor.getColumnIndex("money")),cursor.getString(cursor.getColumnIndex("type"))));
   		  
   	  }
   	  close();
   	  return tb_statistics;
     }
public List<Tb_statistic> getDataByDate(int month)
     
     {  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-");
		
     String date=sdf.format(new Date());
     date=date+month;
   	  List<Tb_statistic> tb_statistic=new ArrayList<Tb_statistic>();
   	  db=helper.getWritableDatabase();
   	  Cursor cursor=db.rawQuery("select * from tb_statistic where date LIKE '%"+date+"%'",null);
   	 
   	  while(cursor.moveToNext())
   	  {
   		tb_statistic.add(new Tb_statistic(cursor.getString(cursor.getColumnIndex("date")),cursor.getString(cursor.getColumnIndex("time")),cursor.getString(cursor.getColumnIndex("money")),cursor.getString(cursor.getColumnIndex("type"))));
   	  }
   	  close();
   	  return tb_statistic;
     }
     public void delete(String time,String tag) {
		String sql="delete  from tb_inaccount where tb_inaccount.time='"+time+"'";
		String sql1="delete  from tb_outaccount where tb_outaccount.time='"+time+"'";
		String sql2="delete  from tb_statistic where tb_statistic.time='"+time+"'";
		String sql3="delete  from tb_flag where tb_flag.time='"+time+"'";
		
		  db=helper.getWritableDatabase();
		  if(tag.equals("收入"))
		  {
			  db.execSQL(sql);
			  Log.i("delete",tag+sql);
		  }
		  else if(tag.equals("支出"))
		  {
			  db.execSQL(sql1);
			  Log.i("delete",tag+sql1);
		  }
	   	  db.execSQL(sql2);
	   	  db.execSQL(sql3);
	   	  close();
	}
     public void update(Tb_statistic tb_statistic)
     {
   	  db=helper.getWritableDatabase();
   	  String sql="update tb_statistic set money=?,type=?  where time=?";
   	  Object []object=new Object[]{tb_statistic.getMoney(),tb_statistic.getType(),tb_statistic.getTime()};
   	  
   	  db.execSQL(sql,object);
   	  close();
     }
     public void close()
     {
     	  
     	  if(db!=null)
     	  {
     		  
     		  db.close();
     	  }
     }
}
