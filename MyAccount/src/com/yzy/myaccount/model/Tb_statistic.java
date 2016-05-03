package com.yzy.myaccount.model;

import android.R.string;

public class Tb_statistic {
	private int _id;
	private String money;
	private String date;
	private  String time;
	private String type;
	public Tb_statistic(  String date, String time,String money,String type) {
		super();
		
	
		this.date = date;
		this.time = time;
		this.money = money;
		this.type=type;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Tb_statistic( )
	{
		super();
	}
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
