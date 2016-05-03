package com.yzy.myaccount.pagetable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yzy.myaccount.util.DBOpenHelper;

import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceActivity.Header;
import android.provider.SyncStateContract.Helpers;
import android.util.Log;
import android.widget.Toast;
import android.database.Cursor;
import android.database.SQLException;


public class SQLiteUtility{
	
	private static final int MODE_PRIVATE = 0x0;
	private static final String DATABASE_NAME="myaccount.db";
	//private static DBOpenHelper helper;
	private static SQLiteDatabase db;
	
	
	/**
	 * 插入一些数据
	 */
	public static void insertSomething(Context context) {
		
		Cursor cursor;
		int count = 0;
		String sql = "CREATE TABLE IF NOT EXISTS tb_outaccount(_id integer primary key autoincrement,outmoney decimal,date varchar(10),time varchar(10),type varchar(10),address varchar(100),mark varchar(200))";
		SimpleDateFormat bartDateFormat =
			new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		try { 
			
//			 db = helper.getWritableDatabase();
			db=context.openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
			 String sqlCheckExsit = "select count(*) as c from Sqlite_master  where type ='table' and name ='tb_inaccount' ";   
             cursor = db.rawQuery(sqlCheckExsit, null);   
             if(cursor.moveToNext()){   
                     count = cursor.getInt(0);   
                     if(count <= 0){   
                             db.execSQL(sql);     
                     }else {
                    	 db.execSQL("drop table tb_inaccount");
                    	 db.execSQL(sql);
                     }
             }
             Calendar calendar=Calendar.getInstance();
        	 //获得当前时间的月份，月份从0开始所以结果要加1
        		int cmonth=calendar.get(Calendar.MONTH)+1;
           	 SimpleDateFormat d=new SimpleDateFormat("yyyy-'"+cmonth+"'-dd");
           	 
             for(int i = 0; i <40; i++) {
				String insertSql = "insert into tb_outaccount ("
						+ "outmoney,date,time,type) values('" +i+2000+"','"+d.format(new Date())+"','"+bartDateFormat.format(date)+"','贪污');";
				db.execSQL(insertSql);
             }
			Log.e("insertSql", "ok");
		}finally {
			db.close();
		}

	}
public static void inserttest(Context context) {
		
		Cursor cursor;
		int count = 0;
		String sql1 = "CREATE TABLE IF NOT EXISTS tb_outaccount(_id integer primary key autoincrement,outmoney decimal,date varchar(10),time varchar(10),type varchar(10),address varchar(100),mark varchar(200))";
		String sql = "CREATE TABLE IF NOT EXISTS tb_inaccount(_id integer primary key autoincrement,inmoney decimal,date varchar(10),time varchar(10),type varchar(10),handler varchar(100),mark varchar(200))";
		SimpleDateFormat bartDateFormat =
			new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		db=context.openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
		try {
			String sqlCheckExsit = "select count(*) as c from Sqlite_master  where type ='table' and name ='tb_inaccount' ";   
			String sqlCheck = "select count(*) as a from Sqlite_master  where type ='table' and name ='tb_outaccount' ";   
            cursor = db.rawQuery(sqlCheckExsit, null);   
            if(cursor.moveToNext()){   
                    count = cursor.getInt(0);   
                    if(count <= 0){   
                            db.execSQL(sql);     
                    }else {
                   	 db.execSQL("drop table tb_inaccount");
                   	 db.execSQL(sql);
                    }
            }
            cursor = db.rawQuery(sqlCheck, null);   
            if(cursor.moveToNext()){   
                    count = cursor.getInt(0);   
                    if(count <= 0){   
                            db.execSQL(sql1);     
                    }else {
                   	 db.execSQL("drop table tb_outaccount");
                   	 db.execSQL(sql1);
                    }
            }
//			 db = helper.getWritableDatabase();
			db=context.openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
             Calendar calendar=Calendar.getInstance();
        	 //获得当前时间的月份，月份从0开始所以结果要加1
        		int cmonth=calendar.get(Calendar.MONTH)+1;
           	 SimpleDateFormat d=new SimpleDateFormat("yyyy-'"+cmonth+"'-dd");
           	 
             for(int i = 0; i <20; i++) {
				String insertSql = "insert into tb_outaccount(outmoney,date,time,type)  values('" +i+2000+"','"+d.format(new Date())+"','"+bartDateFormat.format(date)+"','贪污');";
				db.execSQL(insertSql);
             }
             for(int j = 0; j <20; j++) {
 				String insertSql = "insert into tb_inaccount(inmoney,date,time,type) values('"+j+2000+"','"+d.format(new Date())+"','"+bartDateFormat.format(date)+"','腐败');";
 				db.execSQL(insertSql);
              }
			Log.e("insertSql", "ok");
		}finally {
			db.close();
		}

	}
	
	/**
	 * 查询
	 * @param context 上下文
	 * @param sql 	  SQl查询语句
	 * @param fields  字段名集合
	 * @return	查询结果集 List<String>类型
	 */
	public static List<String> query(Context context, String sql, String[] fields) {
		List<String> dataList = new ArrayList<String>();
		Cursor cursor;

		try {
			//db =helper.getWritableDatabase();
			db=context.openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
			cursor = db.rawQuery(sql, null);
			if (cursor != null) {
				while (cursor.moveToNext()) {
					for (int i = 0; i < fields.length; i++) {
						String temp = cursor.getString(cursor
								.getColumnIndex(fields[i]));
						dataList.add(temp);
					}
				}
			}
		}catch(Exception e) {
			new AlertDialog.Builder(context)	
			.setIcon(android.R.drawable.ic_dialog_alert)		
			.setTitle("数据库连接错误：")				
			.setMessage("数据访问异常。")						
			.show();											
		} finally {
			db.close();
		}
		return dataList;
	}
	
	/**
	 * 数据记录总条数
	 * @param context 上下文
	 * @param sql	  SQL查询语句
	 * @return		  记录条数
	 */
	public static int getCount(Context context, String sql) {
		
		int totalCounty = 0;
		try {
			
			//db =helper.getWritableDatabase();
			db=context.openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
			Cursor cursor = db.rawQuery(sql, null);
			cursor.moveToFirst();
			totalCounty = cursor.getInt(0);
		} catch (Exception e) {
			new AlertDialog.Builder(context)
					.setIcon(android.R.drawable.ic_dialog_alert)
					.setTitle("数据库连接错误：").setMessage("数据访问异常。").show();
		} finally {
			db.close();
		}
		return totalCounty;
	}


	/**
	 * 查询
	 * @param context 上下文
	 * @param sql 	  SQl查询语句
	 * @param fields  字段名集合
	 * @return	查询结果集 ArrayList<HashMap<String,String>>类型
	 */
	public static  ArrayList<HashMap<String,String>>  query2(Context context, String sql, String[] fields) {
		
		ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
		Cursor cursor;	//游标
		try{	
			//db = helper.getWritableDatabase();	//连接数据库
			db=context.openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
			cursor = db.rawQuery(sql, null);	//获取数据集游标

			if (cursor != null) {
				while (cursor.moveToNext()) {	//游标递增，访问数据集

					for (int i = 0; i < fields.length; i++) {
						String temp = cursor.getString(cursor//获取对应数据项
								.getColumnIndex(fields[i]));	
						HashMap<String, String> map = new HashMap<String, String>();
						map.put("item", temp);
						dataList.add(map);

					}
				}
			}
		}catch(Exception e) {
			new AlertDialog.Builder(context)	
			.setIcon(android.R.drawable.ic_dialog_alert)		
			.setTitle("数据库连接错误：")				
			.setMessage("数据访问异常。")						
			.show();											
		}finally {
			cursor = null;
			db.close();
		}
		return dataList;
	}
	
	
	

	
}