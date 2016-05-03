package com.yzy.myaccount.dao;
import com.yzy.myaccount.model.Tb_pwd;
import com.yzy.myaccount.util.DBOpenHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
public class Tb_pwdDao {
    
	private DBOpenHelper helper;
	private SQLiteDatabase db;
	public Tb_pwdDao(Context context)
	{
		helper=new DBOpenHelper(context);
	}
	public void add(Tb_pwd tb_pwd)
	{ 
		db=helper.getWritableDatabase();
		String sql="insert into tb_pwd (password) values(?)";
		Object []object=new Object[]{tb_pwd.getPassword()};
		db.execSQL(sql,object);
	}
	public void update(Tb_pwd tb_pwd)
	{
		db=helper.getWritableDatabase();
		String sql="update  tb_pwd set _id=?,password=? ";
		Object []object=new Object[]{tb_pwd.get_id(),tb_pwd.getPassword()};
		db.execSQL(sql, object);
	}
	public void delete(Integer...ids)
	{
		
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<ids.length;i++)
		{
			sb.append("?").append(",");
			
		}
		sb.deleteCharAt(sb.length()-1);
		db.execSQL("delete from tb_pwd where _id in("+sb+")",(Object[])ids);
	}
   public void delete()
   {db=helper.getWritableDatabase();
   db.execSQL("delete from tb_pwd ");
   }
   public Tb_pwd find()
   {
 	  db=helper.getWritableDatabase();
 	  Cursor cursor=db.rawQuery("select * from tb_pwd ",null );
 	  if(cursor.moveToNext())
 	  {
 		  int a=cursor.getInt(cursor.getColumnIndex("_id"));
 		//Log.e ("SSS",String.valueOf(a));
 		  return new Tb_pwd(cursor.getInt(cursor.getColumnIndex("_id")),cursor.getString(cursor.getColumnIndex("password")));
 	  }
 	  return null;
   }
   public long getCount()
   {
 	  /*
 	   * ��ȡ�ܼ�¼��
 	   */
 	 db=helper.getWritableDatabase();
 	 Cursor cursor=db.rawQuery("select count(_id) from tb_pwd",null);
 	 if(cursor.moveToNext())
 	 {
 		 return cursor.getLong(0);
 	 }
 	 return 0;
   }
   public int getMaxId()
   {
 	  db=helper.getWritableDatabase();
 	  Cursor cursor=db.rawQuery("select max(_id) from tb_pwd",null);
 	  if(cursor.moveToNext())
 	  {
 		  return cursor.getInt(0);
 	  }
 	  return 0;
   }
}