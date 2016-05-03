package com.yzy.myaccount.dao;

import java.util.ArrayList;
import java.util.List;

import com.yzy.myaccount.model.Tb_flag;
import com.yzy.myaccount.util.DBOpenHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
public class Tb_flagDao {
    
	private DBOpenHelper helper;
	private SQLiteDatabase db;
	public Tb_flagDao(Context context)
	{
		helper=new DBOpenHelper(context);
	}
	public void add(Tb_flag tb_flag)
	{ 
		db=helper.getWritableDatabase();
		String sql="insert into tb_flag (time,flag) values(?,?)";
		Object []object=new Object[]{tb_flag.getTime(),tb_flag.getFlag()};
		db.execSQL(sql,object);
		db.close();
	}
	public void update(Tb_flag tb_flag)
	{
		db=helper.getWritableDatabase();
		String sql="update  tb_flag set flag=? where time=?";
		Object []object=new Object[]{tb_flag.getFlag(),tb_flag.getTime()};
		db.execSQL(sql, object);
		db.close();
	}
	public void delete(Integer...ids)
	{
		db=helper.getWritableDatabase();
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<ids.length;i++)
		{
			sb.append("?").append(",");
			
		}
		sb.deleteCharAt(sb.length()-1);
		db.execSQL("delete from tb_flag where _id in("+sb+")",(Object[])ids);
		db.close();
	}
   public  List<Tb_flag> getScrollData(int start,int count)
   
   {
List<Tb_flag> tb_flag=new ArrayList<Tb_flag>();	   
	  db=helper.getWritableDatabase();
	  String sql="select * from tb_flag limit ?,?";
	  String []string =new String[]{String.valueOf(start),String.valueOf(count)};
	  Cursor cursor=db.rawQuery(sql, string);
	  if(cursor.moveToNext())
	  {
		  tb_flag.add(new Tb_flag(cursor.getInt(cursor.getColumnIndex("_id")),cursor.getString(cursor.getColumnIndex("time")),cursor.getString(cursor.getColumnIndex("flag"))));
	  }
	  db.close();
	  return tb_flag;
   }
   public Tb_flag find(int id)
   {
 	  db=helper.getWritableDatabase();
 	  Cursor cursor=db.rawQuery("select _id,flag from tb_flag where _id=?",new String[]{String.valueOf(id)} );
 	  if(cursor.moveToNext())
 	  {
 		 db.close();
 		  return new Tb_flag(cursor.getInt(cursor.getColumnIndex("_id")),cursor.getString(cursor.getColumnIndex("time")),cursor.getString(cursor.getColumnIndex("flag")));
 		  
 		  
 		  
 	  }
 	 db.close();
 	  return null;
   }
   public Tb_flag findbytime(String time)
   {
 	  db=helper.getWritableDatabase();
 	  Cursor cursor=db.rawQuery("select * from tb_flag where time='"+time+"'",null);
 	
 	  if(cursor.moveToNext())
 	  {
 		 db.close();
 		  return new Tb_flag(cursor.getInt(cursor.getColumnIndex("_id")),cursor.getString(cursor.getColumnIndex("flag")),cursor.getString(cursor.getColumnIndex("time")));
 		  
 		  
 		  
 	  }
 	  
 	 db.close();
 	  return null;
   }
   public long getCount()
   {
 	  
 	 db=helper.getWritableDatabase();
 	 Cursor cursor=db.rawQuery("select count(_id) from tb_flag",null);
 	 if(cursor.moveToNext())
 	 {
 		db.close();
 		 return cursor.getLong(0);
 	 }
 	db.close();
 	 return 0;
   }
   public int getMaxId()
   {
 	  db=helper.getWritableDatabase();
 	  Cursor cursor=db.rawQuery("select max(_id) from tb_flag",null);
 	  if(cursor.moveToNext())
 	  {
 		  db.close(); 
 		  return cursor.getInt(0);
 	  }
 	 db.close();
 	  return 0;
 	  
   }
}
