package com.yzy.myaccount.dao;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yzy.myaccount.model.Tb_pwd;
import com.yzy.myaccount.model.Tb_type;
import com.yzy.myaccount.util.DBOpenHelper;

public class Tb_typeDao {
	private DBOpenHelper helper;
	private SQLiteDatabase db;
	public Tb_typeDao(Context context)
	{
		helper=new DBOpenHelper(context);
	}
	public void add( String  tb_type)
	{ 
		db=helper.getWritableDatabase();
		String sql="insert into tb_type (type) values(?)";
		Object []object=new Object[]{tb_type};
		db.execSQL(sql,object);
	}
	public void update(Tb_type tb_type)
	{
		db=helper.getWritableDatabase();
		String sql="update  tb_pwd set type=? where id=? ";
		Object []object=new Object[]{tb_type.getType(),tb_type.getId()};
		db.execSQL(sql, object);
	}
	public void delete(String type)
	{
		db=helper.getWritableDatabase();
		String sql="delete  from tb_type  where type=?";
		Object []object=new Object[]{type};
		db.execSQL(sql,object);
	}
	public List<String> getAll()
	{
		db=helper.getWritableDatabase();
		List<String> list=new ArrayList<String>();
		String sql="select * from tb_type ";
		Cursor cursor=db.rawQuery(sql,null);
		while(cursor.moveToNext())
		{
			list.add(cursor.getString(cursor.getColumnIndex("type")));
			
		}
		return list;
	}
	public Tb_type getType(int id)
	{
		db=helper.getWritableDatabase();
		Tb_type type=new Tb_type();
		String sql="select * from tb_type where _id= "+id;
		Cursor cursor=db.rawQuery(sql,null);
		while(cursor.moveToNext())
		{
			
			type.setId(cursor.getInt(cursor.getColumnIndex("_id")));
			type.setType(cursor.getString(cursor.getColumnIndex("type")));
			return type;
		}
		return null;
	}
}
