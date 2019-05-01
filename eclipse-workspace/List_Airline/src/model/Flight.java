package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class Flight  implements Comparable<Flight>, Comparator<Flight>{

	private int numberOfFlight;
	private int day;
	private int month;
	private int year;
	private String destinationCity;
	private int portOfShipment;
	private String airline;
	
	//Relaciones
	private Time time;
	private Flight nextFlight;
	private Flight previusFlight;
	
	public Flight() {
	}
	
	
	public Flight(int numberOfFlight, int portOfShipment,int day, int month, int year,
			String destinationCity, String airline) {
		
		this.numberOfFlight = numberOfFlight;
		this.portOfShipment = portOfShipment;
		this.day = day;
		this.month = month;
		this.year = year;
		this.destinationCity = destinationCity;
		this.airline = airline;
		
	}


	public int getNumberOfFlight() {
		return numberOfFlight;
	}


	public void setNumberOfFlight(int numberOfFlight) {
		this.numberOfFlight = numberOfFlight;
	}


	public int getDay() {
		return day;
	}


	public void setDay(int day) {
		this.day = day;
	}


	public int getMonth() {
		return month;
	}


	public void setMonth(int month) {
		this.month = month;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public String getDestinationCity() {
		return destinationCity;
	}


	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}


	public int getPortOfShipment() {
		return portOfShipment;
	}


	public void setPortOfShipment(int portOfShipment) {
		this.portOfShipment = portOfShipment;
	}


	public String getAirline() {
		return airline;
	}


	public void setAirline(String airline) {
		this.airline = airline;
	}


	public Time getTime() {
		return time;
	}


	public void setTime(Time time) {
		this.time = time;
	}


	public Flight getNextFlight() {
		return nextFlight;
	}


	public void setNextFlight(Flight nextFlight) {
		this.nextFlight = nextFlight;
	}


	public Flight getPreviusFlight() {
		return previusFlight;
	}


	public void setPreviusFlight(Flight previusFlight) {
		this.previusFlight = previusFlight;
	}


	@Override
	public int compareTo(Flight f) {
		int value = 0;
		if (this.destinationCity.compareTo(f.destinationCity) < 0) {
			value = -1;
		}else if(this.destinationCity.compareTo(f.destinationCity) > 0) {
			value = 1;
		}
		
		return value;
	}


	@Override
	public int compare(Flight f1, Flight f2) {
		int value = 0;
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
		String current = f1.getDay()+"/"+f1.getMonth()+"/"+f1.getYear();
		String toCompare = f2.getDay()+"/"+f2.getMonth()+"/"+f2.getYear();
		Date dateOne = null;
		Date dateTwo = null;
		
		try {
			dateOne = dateFormat.parse(current);
			dateTwo = dateFormat.parse(toCompare);
		} catch (ParseException e) {
			e.printStackTrace();
		}
					
		if (dateOne.compareTo(dateTwo) < 0) {
			value = -1;
		}else if (dateOne.compareTo(dateTwo) > 0) {
			value = 1;
		}
		
		return value;
	}

	
	
	
}
