package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class Time implements Comparable<Time>{

	public final static String AM = "AM";
	public final static String PM = "PM";
	
	//Atributos
	private int hour;
	private int minute;
	private String period;
	
	
	public Time(int hour, int minute, String period){
		this.hour = hour;
		this.minute = minute;
		this.period = period;
	}


	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
		
	}


	@Override
	public String toString() {
		return hour+":"+minute+" "+period;
	}


	@Override
	public int compareTo(Time time) {
		int value = 0;
		DateFormat dateFormat = new SimpleDateFormat("h:mm a");
		String current = this.hour+":"+this.minute+" "+this.period;
		String toCompare = time.getHour()+":"+time.getMinute()+" "+time.getPeriod();
		
		Date dateOne = null;
		Date dateTwo = null;
		try {
		dateOne = dateFormat.parse(current);
		dateTwo = dateFormat.parse(toCompare);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if (dateOne.compareTo(dateTwo)< 0) {
			value =-1;
		}else if (dateOne.compareTo(dateTwo) > 0) {
			value = 1;
		}else {
			value = 0;
		}
				
		return value;
	}
	


	
//	if (this.toString().compareTo(time.toString()) < 0) {
//	if (this.period.compareTo(time.getPeriod()) < 0) {
//		value = -1;      
//	}else {
//		value = 1 ;
//	}
//	
//}else if(this.toString().compareTo(time.toString()) > 0 ) {
//	if (this.period.compareTo(time.getPeriod()) == 0) {
//		value = -1;
//	}else {
//		value = 1; 
//	}
//}else {
//	value = 0;
//}	
	





	
	
	
}

