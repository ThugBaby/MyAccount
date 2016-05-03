package com.yzy.myaccount.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class daytime {
   
	 Calendar calendar=Calendar.getInstance();
	 int cmonth=calendar.get(Calendar.MONTH)+1;
	      SimpleDateFormat d=new SimpleDateFormat("yyyy-'"+cmonth+"'-dd");
	     String date=d.format(new Date());
	
}
