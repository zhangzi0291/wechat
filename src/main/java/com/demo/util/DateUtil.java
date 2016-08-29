package com.demo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * @version 1.1
 * @author North
 *
 * @date 2016年3月15日
 */
public class DateUtil {
	private static Calendar c = null;
	private static final String DEFAULT_DATE = "yyyy-MM-dd";
	private static final String DEFAULT_DATETIME = "yyyy-MM-dd HH:mm:ss";

	public static void main(String[] args) {
		System.out.println(getDayOfWeek("2016-1-6", DEFAULT_DATE));
	}
	
	/**
	 * 
	 * @function：时间加秒数
	 * @author：North
	 */
	public static Date addSecond(Date date,Integer num) {    
	    Calendar calendar = Calendar.getInstance();    
	    calendar.setTime(date);    
	    calendar.add(Calendar.SECOND, num);    
	    return calendar.getTime();    
	}    
/**
 * 
 * @function：获取当前的datetime
 * @author：North
 */
	public static String getNowTime() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATETIME);
		return sdf.format(d);
	}
/**
 * @param range 正数为未来，负数为过去，0为现在
 * @function：输入数字取得date
 * @author：North
 */
	public static String getDate(int range) {
		Date d = new Date();
		c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.DATE, range);
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE);
		return sdf.format(c.getTime());
	}
	/**
	 * @param range 正数为未来，负数为过去，0为现在
	 * @function：输入数字取得datetime
	 * @author：North
	 */
	public static String getDateTime(int range) {
		Date d = new Date();
		c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.DATE, range);
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATETIME);
		return sdf.format(c.getTime());
	}
/**
 * @param date1 第一个字符串时间
 * @param date2 第二个字符串时间
 * @param format 字符串时间的格式
 * @function：输入两个字符串时间返回时间更早的那个
 * @author：North
 */
	public static String compareToDate(String date1, String date2, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Calendar c1 = Calendar.getInstance();
			Date d1 = sdf.parse(date1);
			c1.setTime(d1);
			Calendar c2 = Calendar.getInstance();
			Date d2 = sdf.parse(date2);
			c2.setTime(d2);
			if (c1.compareTo(c2) <= 0) {
				System.out.println(sdf.format(c1.getTime()));
				return sdf.format(c1.getTime());
			} else {
				return sdf.format(c2.getTime());
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
/**
 * @param d 日期Date
 * @param	format 要转换成String的格式
 * @function：转换Date变成String
 * @author：North
 */
	public static String getStrByDate(Date d, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(d);
	}
/**
 * @param date 字符串时间
 * @param format 字符串时间的格式
 * @function：转换String变成Date
 * @author：North
 */
 	public static Date getDateByStr(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
/**
 * @param date 字符串时间
 * @param format 字符串时间的格式
 * @function：转换String变成Date
 * @author：North
 */
	public static Date getDateByStr(String date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
/**
 * @param date 字符串时间
 * @param format 字符串时间的格式
 * @param dayofweek 星期几，1为星期日、2为星期一........7为星期六
 * @param weekinmonth 一个月中的第几个，正数正数，负数倒数
 * @function：取得某年某月的某个星期几的具体日期
 *  
 *  
 * @author：North
 */
	public static String getDayOfWeekInMonth(String date, String format,int dayofweek,int weekinmonth){
		try {
			SimpleDateFormat sdf=new SimpleDateFormat(format);
			c=Calendar.getInstance();
			c.setTime(sdf.parse(date));
			c.set(Calendar.DAY_OF_WEEK, dayofweek);
			c.set(Calendar.DAY_OF_WEEK_IN_MONTH,weekinmonth);
			sdf=new SimpleDateFormat(DEFAULT_DATE);
			return sdf.format(c.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
/**
 * 	@param date 字符串时间
 * 	@param format 字符串时间的格式
 * @function：输入一个字符串时间返回这一天是星期几
 * @author：North
 */
	public static String getDayOfWeek(String date, String format) {
			c = Calendar.getInstance();
			c.setTime(getDateByStr(date, format));
			int a = c.get(Calendar.DAY_OF_WEEK);
			switch (a) {
			case 1:
				return "星期日";
			case 2:
				return "星期一";
			case 3:
				return "星期二";
			case 4:
				return "星期三";
			case 5:
				return "星期四";
			case 6:
				return "星期五";
			case 7:
				return "星期六";
			default:
				return "错误";
			}
	}
	public static java.sql.Date getSqlDate(String date){
		Date utildate=getDateByStr(date);
		return new java.sql.Date(utildate.getTime());
	}
	public static java.sql.Date getSqlDate(String date,String format){
		Date utildate=getDateByStr(date,format);
		return new java.sql.Date(utildate.getTime());
	}
	
	public static String getStrByStr(String date,String format1,String format2){
		return getStrByDate(getDateByStr(date,format1), format2);
		
	}
}
