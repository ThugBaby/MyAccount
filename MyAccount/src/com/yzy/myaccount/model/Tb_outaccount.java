package com.yzy.myaccount.model;

import java.io.Serializable;

public class Tb_outaccount  implements Serializable{
	private int _id;
	private double money;
	private String date;
	private  String time;
	private String type ;
	private String address;
	private String mark;
	public  Tb_outaccount()
	{
		super();
	}
	public Tb_outaccount(int id,double money,String date,String time,String type,String address,String mark)
	{
		super();
		this._id=id;
		this.money=money;
		this.setDate(date);
		this.time=time;
		this.type=type;
		this.address=address;
		this.mark=mark;
		
	}
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
