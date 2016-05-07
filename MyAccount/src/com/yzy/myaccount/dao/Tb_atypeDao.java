package com.yzy.myaccount.dao;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Flags.Flag;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yzy.myaccount.model.Tb_type;
import com.yzy.myaccount.util.DBOpenHelper;

public class Tb_atypeDao {
	private DBOpenHelper helper;
	private SQLiteDatabase db;
	public Tb_atypeDao(Context context)
	{
		helper=new DBOpenHelper(context);
	}
	public void add( String  tb_type)
	{ 
		db=helper.getWritableDatabase();
		String sql="insert into tb_atype (type) values(?)";
		Object []object=new Object[]{tb_type};
		db.execSQL(sql,object);
	}
	public List<String> getAll()
	{
		db=helper.getWritableDatabase();
		List<String> list=new ArrayList<String>();
		String sql="select * from tb_atype ";
		Cursor cursor=db.rawQuery(sql,null);
		while(cursor.moveToNext())
		{
			list.add(cursor.getString(cursor.getColumnIndex("type")));
			
		}
		return list;
	}
	public boolean getType(int id)
	{
		db=helper.getWritableDatabase();
	   boolean   flag=false;
		String sql="select * from tb_atype where _id= "+id;
		Cursor cursor=db.rawQuery(sql,null);
		while(cursor.moveToNext())
		{
			flag=true;
			
			return flag;
		}
		return flag;
	}
}
