package com.revature.util;

import java.sql.Timestamp;
import java.util.Calendar;

public class Time {
	public Timestamp currentTime(){
		Calendar calendar = Calendar.getInstance();
		Timestamp time = new Timestamp(calendar.getTime().getTime());
		return time;
	}
}
