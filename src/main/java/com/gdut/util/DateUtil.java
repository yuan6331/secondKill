package com.gdut.util;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil extends Date{
	
	public static DateUtil date() {
		return new DateUtil();
	}
	
	public String getFormateDate() {
		Date date = new Date();
		DateFormat dFormat=DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.CHINA);
		return dFormat.format(date);
	}
}
