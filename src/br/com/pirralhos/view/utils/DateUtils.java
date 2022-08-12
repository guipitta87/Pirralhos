package br.com.pirralhos.view.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
	public static int monthsBetween(Date minuend, Date subtrahend) {
		Calendar cal = Calendar.getInstance(new Locale("pt","BR"));
		cal.setTime(minuend);
		int minuendDay = cal.get(Calendar.DAY_OF_MONTH);
		int minuendMonth = cal.get(Calendar.MONTH);
		int minuendYear = cal.get(Calendar.YEAR);
		cal.setTime(subtrahend);
		
		int subtrahendDay = cal.get(Calendar.DAY_OF_MONTH);
		int subtrahendMonth = cal.get(Calendar.MONTH);
		int subtrahendYear = cal.get(Calendar.YEAR);

		// the following will work okay for Gregorian but will not
		// work correctly in a Calendar where the number of months
		// in a year is not constant
		int monthBetween =  ((minuendYear - subtrahendYear) * cal.getMaximum(Calendar.MONTH))
				+ (minuendMonth - subtrahendMonth);
		if(minuendDay <= subtrahendDay)
			monthBetween++;
		return monthBetween;
	}

}
