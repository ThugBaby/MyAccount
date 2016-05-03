package com.yzy.myaccount.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yzy.myaccount.model.Tb_inaccount;
import com.yzy.myaccount.model.Tb_outaccount;
import com.yzy.myaccount.util.DBOpenHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;



public class Tb_outaccountDao {
    private  DBOpenHelper helper;
    private SQLiteDatabase db;
    public Tb_outaccountDao(Context context)
    {
  	  helper=new DBOpenHelper(context);
    }
    public void add(Tb_outaccount tb_outaccount)
    {
  	  db=helper.getWritableDatabase();
  	  String sql="insert into tb_outaccount(_id,outmoney,date,time,type,address,mark) values(?,?,?,?,?,?,?)";
  	  Object []object=new Object[]{tb_outaccount.get_id(),tb_outaccount.getMoney(),tb_outaccount.getDate(),tb_outaccount.getTime(),tb_outaccount.getType(),tb_outaccount.getAddress(),tb_outaccount.getMark()};
  	  
  	  db.execSQL(sql,object);
  	  db.close();
    }
    public void update(Tb_outaccount tb_outaccount)
    {
  	  db=helper.getWritableDatabase();
  	  String sql="update tb_outaccount set outmoney=?,type=?,address=?,mark=? where time=?";
  	  //Log.i("asd","update tb_outaccount set outmoney='"+tb_outaccount.getMoney()+"',type='"+tb_outaccount.getType()+"',address='"+tb_outaccount.getAddress()+"',mark='"+tb_outaccount.getMark()+"' where time='"+tb_outaccount.getTime()+"'");
  	  Object []object=new Object[]{tb_outaccount.getMoney(),tb_outaccount.getType(),tb_outaccount.getAddress(),tb_outaccount.getMark(),tb_outaccount.getTime()};

  	  db.execSQL(sql,object);
  	db.close();
    }
    public Tb_outaccount find(int id)
    {
  	  db=helper.getWritableDatabase();
  	  Cursor cursor=db.rawQuery("select _id,outmoney,time,type,address,mark from tb_outaccount where _id=?",new String[]{String.valueOf(id)} );
  	  if(cursor.moveToNext())
  	  {
  		  return new Tb_outaccount(cursor.getInt(cursor.getColumnIndex("_id")),cursor.getDouble(cursor.getColumnIndex("inmoney")),cursor.getString(cursor.getColumnIndex("date")),cursor.getString(cursor.getColumnIndex("time")),cursor.getString(cursor.getColumnIndex("type")),cursor.getString(cursor.getColumnIndex("address")),cursor.getString(cursor.getColumnIndex("mark")));
  		  
  		  
  	  }
  	db.close();
  	  return null;
    }
    public String getBytime(String time)
    
    {
   	
   	  db=helper.getWritableDatabase();
   	  Cursor cursor=db.rawQuery("select * from tb_outaccount where time='"+time+"'",null);
   	 
   	  while(cursor.moveToNext())
   	  {
   		  db.close();
   		  return  cursor.getString(cursor.getColumnIndex("address"));
   		  
   	  }
   	  db.close();
   	  return "";
    }
    public Tb_outaccount getPerBytime(String time)
    
    {
    	  db=helper.getWritableDatabase();
       	  Cursor cursor=db.rawQuery("select * from tb_outaccount where time='"+time+"'",null);
       	 
       	  while(cursor.moveToNext())
       	  {
       		  db.close();
       		 return new Tb_outaccount(cursor.getInt(cursor.getColumnIndex("_id")),cursor.getDouble(cursor.getColumnIndex("outmoney")),cursor.getString(cursor.getColumnIndex("date")),cursor.getString(cursor.getColumnIndex("time")),cursor.getString(cursor.getColumnIndex("type")),cursor.getString(cursor.getColumnIndex("address")),cursor.getString(cursor.getColumnIndex("mark")));
       		  
       	  }
       	  db.close();
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
  		  db.execSQL("delete from tb_outaccount where _id in ("+s+")", (Object[])id);
  	  }
  	db.close();
    }
    
    public List<Tb_outaccount> getScrollData(int start,int count)
    
    {
  	  List<Tb_outaccount> tb_outaccount=new ArrayList<Tb_outaccount>();
  	  db=helper.getWritableDatabase();
  	  Cursor cursor=db.rawQuery("select * from tb_outaccount limit ?,?",new String[]{String.valueOf(start),String.valueOf(count)});
  	  while(cursor.moveToNext())
  	  {
  		  tb_outaccount.add(new Tb_outaccount(cursor.getInt(cursor.getColumnIndex("_id")),cursor.getDouble(cursor.getColumnIndex("money")),cursor.getString(cursor.getColumnIndex("date")),cursor.getString(cursor.getColumnIndex("time")),cursor.getString(cursor.getColumnIndex("type")),cursor.getString(cursor.getColumnIndex("address")),cursor.getString(cursor.getColumnIndex("mark"))));
  		  
  	  }
  	db.close();
  	  return tb_outaccount;
    }
    
    public long getCount()
    {
  	  /*
  	   * ��ȡ�ܼ�¼��
  	   */
  	 db=helper.getWritableDatabase();
  	 Cursor cursor=db.rawQuery("select count(_id) from tb_outaccount",null);
  	 if(cursor.moveToNext())
  	 {
  		 return cursor.getLong(0);
  	 }
  	db.close();
  	 return 0;
    }
    public int getMaxId()
    {
  	  db=helper.getWritableDatabase();
  	  Cursor cursor=db.rawQuery("select max(_id) from tb_outaccount",null);
  	  if(cursor.moveToNext())
  	  {
  		  return cursor.getInt(0);
  	  }
  	db.close();
  	  return 0;
    }
    public List<Tb_outaccount> getAllData()
    
    {
  	  List<Tb_outaccount> tb_outaccount=new ArrayList<Tb_outaccount>();
  	  db=helper.getWritableDatabase();
  	  Cursor cursor=db.rawQuery("select * from tb_outaccount",null);
  	 
  	  while(cursor.moveToNext())
  	  {
  		  tb_outaccount.add(new Tb_outaccount(cursor.getInt(cursor.getColumnIndex("_id")),cursor.getDouble(cursor.getColumnIndex("outmoney")),cursor.getString(cursor.getColumnIndex("date")),cursor.getString(cursor.getColumnIndex("time")),cursor.getString(cursor.getColumnIndex("type")),cursor.getString(cursor.getColumnIndex("handler")),cursor.getString(cursor.getColumnIndex("mark"))));
  		  
  	  }
  	  db.close();
  	  return tb_outaccount;
    }
    public List<Tb_outaccount> getMonthData(int month)
    
    {   
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-'"+month+"'-dd");
			
	      String date=sdf.format(new Date());
  	  List<Tb_outaccount> tb_outaccount=new ArrayList<Tb_outaccount>();
  	  db=helper.getWritableDatabase();
  	  Cursor cursor=db.rawQuery("select * from tb_outaccount where date="+date,null);
  	 
  	  while(cursor.moveToNext())
  	  {
  		 tb_outaccount.add(new Tb_outaccount(cursor.getInt(cursor.getColumnIndex("_id")),cursor.getDouble(cursor.getColumnIndex("outmoney")),cursor.getString(cursor.getColumnIndex("date")),cursor.getString(cursor.getColumnIndex("time")),cursor.getString(cursor.getColumnIndex("type")),cursor.getString(cursor.getColumnIndex("handler")),cursor.getString(cursor.getColumnIndex("mark"))));
  		  
  	  }
  	  db.close();
  	  return tb_outaccount;
    }
    public List<Tb_outaccount> getLTData(int month)

    {   
    		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-");
    		
           String date=sdf.format(new Date());
           date=date+month;
           //Log.i("d",date);
    	  List<Tb_outaccount> tb_outaccount=new ArrayList<Tb_outaccount>();
    	  db=helper.getWritableDatabase();
    	  Cursor cursor=db.rawQuery("select * from tb_outaccount where date LIKE '%"+date+"%'",null);
    	  String sql="select * from tb_outaccount where date LIKE '%"+date+"%'";
    	  //Log.i("out",sql);
    	  while(cursor.moveToNext())
    	  {
    		  tb_outaccount.add(new Tb_outaccount(cursor.getInt(cursor.getColumnIndex("_id")),cursor.getDouble(cursor.getColumnIndex("outmoney")),cursor.getString(cursor.getColumnIndex("date")),cursor.getString(cursor.getColumnIndex("time")),cursor.getString(cursor.getColumnIndex("type")),cursor.getString(cursor.getColumnIndex("address")),cursor.getString(cursor.getColumnIndex("mark"))));
    		  
    	  }
    	  db.close();
    	  return tb_outaccount;
    }
}
