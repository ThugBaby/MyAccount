package com.yzy.myaccount.model;

public class Tb_flag {
    
	private int _id;
	private String flag;
	private String time;
	public Tb_flag()
	{
		super();
	}
	public Tb_flag(int id,String flag,String time)
	{
		super();
		this.time=time;
		this._id=id;
		this.flag=flag;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
