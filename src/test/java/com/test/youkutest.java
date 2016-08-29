package com.test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class youkutest {
	
	public static void main(String[] args) {
		String s = "a/s<d";
		System.out.println(Arrays.toString(s.split("/|<")));
	}
	
	/**
	 * 
	  * 获取当天时间，时分秒自定
	  *@param hour 小时
	  *@param minute 分钟
	  *@param second 秒
	  *@return 
	  *@date 2016年8月11日 下午3:48:54
	  *@author zxn
	 */
	public static String getTime(Integer hour,Integer minute,Integer second){
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		return sdf.format(calendar.getTime());
	}
}
