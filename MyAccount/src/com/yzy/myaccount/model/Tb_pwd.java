package com.yzy.myaccount.model;

public class Tb_pwd {
    private String password;
    private int _id;
    public Tb_pwd(int _id,String password)
    {
    	super();
    	this.password=password;
    	this._id=_id;
    }
    public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public Tb_pwd(String password)
    {
    	super();
    	this.password=password;
    }
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
