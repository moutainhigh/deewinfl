package com.kernal.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil
{
	public static int getIntervalDays(Date startDate, Date endDate)
	{
		if ((null == startDate) || (null == endDate))
		{
			return -1;
		}
		long intervalMilli = (endDate.getTime() - startDate.getTime());
		return (int) (intervalMilli / (24 * 60 * 60 * 1000));
	}

	public static int daysOfTwo(Date startDate, Date endDate)
	{
		Calendar aCalendar = Calendar.getInstance();
		aCalendar.setTime(startDate);
		int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
		aCalendar.setTime(endDate);
		int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
		return day2 - day1;
	}

	public static Date getEndDateByDays(Date startDate, int days)
	{
		Calendar aCalendar = Calendar.getInstance();
		aCalendar.setTime(startDate);
		aCalendar.add(Calendar.DAY_OF_YEAR, days);
		return aCalendar.getTime();
	}

	public static String getSystemDate()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}

	public static String getSystemDateTime()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}

	public static String getDateTime(Date date)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (null == date) {
			date = new Date();
		}
		return sdf.format(date);
	}

	/**
	 * @param dateFormat
	 *            输出日期格式，形如：yyyy-MM-dd HH:mm:ss
	 * @return 格式化后的日期字符串
	 */
	public static String getSystemTimeByFormat(String dateFormat)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(new Date());
	}

	public static String getSystemTimeByFormat(Date date, String dateFormat)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(date);
	}

	/**
	 * @param dateStr
	 *            日期字符串，形如：yyyy-MM-dd HH:mm:ss
	 * @param dateFormat
	 *            输出日期格式，形如：yyyy-MM-dd HH:mm:ss
	 * @return 格式化后的日期字符串
	 */
	public static Date getTimeByFormat(String dateStr, String dateFormat)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 获取 yyyy-MM-dd 日期
	 * @param dateStr 日期时间字符串
	 * @param dateFormat dateStr的字符串格式
	 * @return ' yyyy-MM-dd '格式日期
	 */
	public static String getFormateDate(String dateStr, String dateFormat){
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
		
	}
}
