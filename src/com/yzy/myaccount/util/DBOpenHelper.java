package com.yzy.myaccount.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBOpenHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "myaccount.db";
	private static final int DATABASE_VERSION = 1;
	private static final String TEMP1 = "alter table  tb_flag rename to temp_flag";
	private static final String TEMP2 = "alter table  tb_inaccount rename to temp_inaccount";
	private static final String TEMP3 = "alter table  tb_outaccount rename to temp_outaccount";
	private static final String TEMP4 = "alter table  tb_pwd rename to temp_pwd";
	private static final String TEMP5 = "alter table  tb_statistic rename to temp_statistic";
	// 然后把备份表temp_A中的数据copy到新创建的数据库表A中，这个表A没发生结构上的变化
	private static final String cTEMP1 = "insert into tb_flag select * from temp_flag";
	private static final String cTEMP2 = "insert into tb_inaccount select * from temp_inaccount";
	private static final String cTEMP3 = "insert into tb_outaccount select * from temp_outaccount";
	private static final String cTEMP4 = "insert into tb_pwd select * from temp_pwd";
	private static final String cTEMP5 = "insert into tb_statistic select * from temp_statistic";
	// 删除备份表
	public static final String dTEMP1 = "drop table if exists temp_flag";
	public static final String dTEMP2 = "drop table if exists temp_inaccount";
	public static final String dTEMP3 = "drop table if exists temp_outaccount";
	public static final String dTEMP4 = "drop table if exists temp_pwd";
	public static final String dTEMP5 = "drop table if exists temp_statistic";

	public DBOpenHelper(Context context) {

		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS tb_pwd(_id integer primary key autoincrement,password varchar(20))");
		db.execSQL("CREATE TABLE IF NOT EXISTS tb_outaccount(_id integer primary key autoincrement,outmoney decimal,date varchar(10),time varchar(10),type varchar(10),address varchar(100),mark varchar(200))");
		db.execSQL("CREATE TABLE IF NOT EXISTS tb_inaccount(_id integer primary key autoincrement,inmoney decimal,date varchar(10),time varchar(10),type varchar(10),handler varchar(100),mark varchar(200))");
		// 创建统计表
		db.execSQL("CREATE TABLE IF NOT EXISTS tb_statistic(_id integer primary key autoincrement,date varchar(10),time varchar(10),money varchar(20),type varchar(10))");
		db.execSQL("CREATE TABLE IF NOT EXISTS tb_flag(_id integer primary key autoincrement,time varchar(20),flag varchar(200))");

	}// 出现错误(1) no such table: tb_inaccount，原因:
		// manageChartfragment错误调用insersomthing删除了tb_inaccount
		// 升级时数据库会删除，注意更正

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i("oldversion:", "" + oldVersion);
		// 创建临时表
		db.execSQL(TEMP1);
		db.execSQL(TEMP2);
		db.execSQL(TEMP3);
		db.execSQL(TEMP4);
		db.execSQL(TEMP5);
		onCreate(db);
		// 将临时表中的数据放入新表
		db.execSQL(cTEMP1);
		db.execSQL(cTEMP2);
		db.execSQL(cTEMP3);
		db.execSQL(cTEMP4);
		db.execSQL(cTEMP5);
		// 将临时表删除掉
		db.execSQL(dTEMP1);
		db.execSQL(dTEMP2);
		db.execSQL(dTEMP3);
		db.execSQL(dTEMP4);
		db.execSQL(dTEMP5);
		// db.execSQL("drop table if exists tb_pwd");
		// db.execSQL("drop table if exists tb_outaccount");
		// db.execSQL("drop table if exists tb_inaccount");
		// db.execSQL("drop table if exists tb_flag");
		// db.execSQL("drop table if exists tb_statistic");

	}
}
