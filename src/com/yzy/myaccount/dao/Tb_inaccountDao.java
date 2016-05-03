package com.yzy.myaccount.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.yzy.myaccount.model.Tb_inaccount;
import com.yzy.myaccount.util.DBOpenHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Tb_inaccountDao {
      private  DBOpenHelper helper;
      private SQLiteDatabase db;
      public Tb_inaccountDao(Context context)
      {
    	  helper=new DBOpenHelper(context);
      }
      public void add(Tb_inaccount tb_inaccount)
      {   
    	  db=helper.getWritableDatabase();
    	  String sql="insert into tb_inaccount(inmoney,date,time,type,handler,mark) values(?,?,?,?,?,?)";
    	  Object []object=new Object[]{tb_inaccount.getMoney(),tb_inaccount.getDate(),tb_inaccount.getTime(),tb_inaccount.getType(),tb_inaccount.getHandler(),tb_inaccount.getMark()};
    	  String s="insert into tb_inaccount(inmoney,date,time,type,handler,mark) values('"+tb_inaccount.getMoney()+"','"+tb_inaccount.getDate()+"','"+tb_inaccount.getTime()+"','"+tb_inaccount.getType()+"','"+tb_inaccount.getHandler()+"','"+tb_inaccount.getMark()+"')";
    	 // Log.i("asd",s);
    	  db.execSQL(sql,object);
    	  close();
      }
      public void update(Tb_inaccount tb_inaccount)
      {
    	  db=helper.getWritableDatabase();
    	  String sql="update tb_inaccount set inmoney=?,type=?,handler=?,mark=? where time=?";
    	  Object []object=new Object[]{tb_inaccount.getMoney(),tb_inaccount.getType(),tb_inaccount.getHandler(),tb_inaccount.getMark(),tb_inaccount.getTime()};
    	  
    	  db.execSQL(sql,object);
    	  close();
      }
      public Tb_inaccount find(int id)
      {
    	  db=helper.getWritableDatabase();
    	  Cursor cursor=db.rawQuery("select _id,inmoney,date,time,type,handler,mark from tb_inaccount where _id=?",new String[]{String.valueOf(id)} );
    	  if(cursor.moveToNext())
    	  {
    		  return new Tb_inaccount(cursor.getInt(cursor.getColumnIndex("_id")),cursor.getDouble(cursor.getColumnIndex("inmoney")),cursor.getString(cursor.getColumnIndex("date")),cursor.getString(cursor.getColumnIndex("time")),cursor.getString(cursor.getColumnIndex("type")),cursor.getString(cursor.getColumnIndex("handler")),cursor.getString(cursor.getColumnIndex("mark")));
    		  
    		  
    	  }
    	  close();
    	  return null;
      }
      public void delete(Integer... id)
      {
    	  if(id.length>0)
    	  {
    		  StringBuffer s=new StringBuffer();
    		  for(int i=0;i<id.length;i++)
    		  {
    			  s.append("?").append(",");
    			  
    		  }
    		  s.deleteCharAt(s.length()-1);
    		  db=helper.getWritableDatabase();
    		  db.execSQL("delete from tb_inaccount where _id in ("+s+")", (Object[])id);
    		  close();
    	  }
      }
      
      public List<Tb_inaccount> getScrollData(int start,int count)
      
      {
    	  List<Tb_inaccount> tb_inaccount=new ArrayList<Tb_inaccount>();
    	  db=helper.getWritableDatabase();
    	  Cursor cursor=db.rawQuery("select * from tb_inaccount limit ?,?",new String[]{String.valueOf(start),String.valueOf(count)});
    	  while(cursor.moveToNext())
    	  {
    		  tb_inaccount.add(new Tb_inaccount(cursor.getInt(cursor.getColumnIndex("_id")),cursor.getDouble(cursor.getColumnIndex("inmoney")),cursor.getString(cursor.getColumnIndex("date")),cursor.getString(cursor.getColumnIndex("time")),cursor.getString(cursor.getColumnIndex("type")),cursor.getString(cursor.getColumnIndex("handler")),cursor.getString(cursor.getColumnIndex("mark"))));
    		  
    	  }
    	  close();
    	  return tb_inaccount;
      }
 public List<Tb_inaccount> getAllData()
      
      {
    	  List<Tb_inaccount> tb_inaccount=new ArrayList<Tb_inaccount>();
    	  db=helper.getWritableDatabase();
    	  Cursor cursor=db.rawQuery("select * from tb_inaccount",null);
    	 
    	  while(cursor.moveToNext())
    	  {
    		  tb_inaccount.add(new Tb_inaccount(cursor.getInt(cursor.getColumnIndex("_id")),cursor.getDouble(cursor.getColumnIndex("inmoney")),cursor.getString(cursor.getColumnIndex("date")),cursor.getString(cursor.getColumnIndex("time")),cursor.getString(cursor.getColumnIndex("type")),cursor.getString(cursor.getColumnIndex("handler")),cursor.getString(cursor.getColumnIndex("mark"))));
    		  
    	  }
    	  close();
    	  return tb_inaccount;
      }
 public String getBytime(String time)
 
 {
	
	  db=helper.getWritableDatabase();
	  Cursor cursor=db.rawQuery("select * from tb_inaccount where time='"+time+"'",null);
	 
	  while(cursor.moveToNext())
	  {
		  close();
		  return  cursor.getString(cursor.getColumnIndex("handler"));
		  
	  }
	  close();
	  return "";
 }
public Tb_inaccount getPerBytime(String time)
 
 {
	
	  db=helper.getWritableDatabase();
	  Cursor cursor=db.rawQuery("select * from tb_inaccount where time='"+time+"'",null);
	  //Log.i("q1","select * from tb_inaccount where time='"+time+"'");
	  while(cursor.moveToNext())
	  {
		  close();
		  return    new Tb_inaccount(cursor.getInt(cursor.getColumnIndex("_id")),cursor.getDouble(cursor.getColumnIndex("inmoney")),cursor.getString(cursor.getColumnIndex("date")),cursor.getString(cursor.getColumnIndex("time")),cursor.getString(cursor.getColumnIndex("type")),cursor.getString(cursor.getColumnIndex("handler")),cursor.getString(cursor.getColumnIndex("mark")));
		  
	  }
	  close();
	  return null;
 }
      public long getCount()
      {
    	  /*
    	   * ��ȡ�ܼ�¼��
    	   */
    	 db=helper.getWritableDatabase();
    	 Cursor cursor=db.rawQuery("select count(_id) from tb_inaccount",null);
    	 if(cursor.moveToNext())
    	 {
    		 return cursor.getLong(0);
    	 }
    	 close();
    	 return 0;
      }
      public int getMaxId()
      {
    	  db=helper.getWritableDatabase();
    	  Cursor cursor=db.rawQuery("select max(_id) from tb_inaccount",null);
    	  if(cursor.moveToNext())
    	  {
    		  return cursor.getInt(0);
    	  }
    	  close();
    	  return 0;
      }
      
      
public List<Tb_inaccount> getMonthData(int month)
      
      {   
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-'"+month+"'-dd");
			
	      String date=sdf.format(new Date());
	     // Log.i("d",date);
    	  List<Tb_inaccount> tb_inaccount=new ArrayList<Tb_inaccount>();
    	  db=helper.getWritableDatabase();
    	  Cursor cursor=db.rawQuery("select * from tb_inaccount where date='"+date+"'",null);
    	  
    	  while(cursor.moveToNext())
    	  {
    		  tb_inaccount.add(new Tb_inaccount(cursor.getInt(cursor.getColumnIndex("_id")),cursor.getDouble(cursor.getColumnIndex("inmoney")),cursor.getString(cursor.getColumnIndex("date")),cursor.getString(cursor.getColumnIndex("time")),cursor.getString(cursor.getColumnIndex("type")),cursor.getString(cursor.getColumnIndex("handler")),cursor.getString(cursor.getColumnIndex("mark"))));
    		  
    	  }
    	  close();
    	  return tb_inaccount;
      }
public List<Tb_inaccount> getLTData(int month)

{   
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-");
		
       String date=sdf.format(new Date());
       date=date+month;
       //Log.i("d",date);
	  List<Tb_inaccount> tb_inaccount=new ArrayList<Tb_inaccount>();
	  db=helper.getWritableDatabase();
	  Cursor cursor=db.rawQuery("select * from tb_inaccount where date LIKE '%"+date+"%'",null);
	  String sqlString="select * from tb_inaccount where date LIKE '%"+date+"%'";
	  Log.i("sql语句",sqlString);
	  while(cursor.moveToNext())
	  {
		  tb_inaccount.add(new Tb_inaccount(cursor.getInt(cursor.getColumnIndex("_id")),cursor.getDouble(cursor.getColumnIndex("inmoney")),cursor.getString(cursor.getColumnIndex("date")),cursor.getString(cursor.getColumnIndex("time")),cursor.getString(cursor.getColumnIndex("type")),cursor.getString(cursor.getColumnIndex("handler")),cursor.getString(cursor.getColumnIndex("mark"))));
		  
	  }
	  close();
	  return tb_inaccount;
}
public void close()
{
	  
	  if(db!=null)
	  {
		  
		  db.close();
	  }
}
}
