package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class FlightTest {

	
private Flight flight;
	
	private void setupEscenary1() {
		int numberOfFlight = 1;
		int portOfShipment = 2;
		int day = 1;
		int month = 4;
		int year = 2019;
		String destinationCity = "Bogota";
		String airline = "Avianca";
		flight = new Flight(numberOfFlight, portOfShipment,day, month, year, destinationCity,airline, 0, 0, null);

		flight.setTime(new Time(10, 20, Time.AM));
	}
	
	private void setupEscenary2() {
		setupEscenary1();
		int numberOfFlight = 20;
		int portOfShipment = 30;
		int day = 3;
		int month = 7;
		int year = 2016;
		flight.setNumberOfFlight(numberOfFlight);
		flight.setPortOfShipment(portOfShipment);
		flight.setDay(day);
		flight.setMonth(month);
		flight.setYear(year);
	}
	
	private void setupEscenary3(){
		flight = new Flight();
	}
	private void setupEscenary4() {
		flight = new Flight();
		int numberOfFlight = 20;
		int portOfShipment = 30;
		int day = 3;
		int month = 7;
		int year = 2016;
		String destinationCity = "Bogota";
		String airline = "Avianca";
		flight.setTime(new Time(10, 20, Time.AM));
		flight.setNextFlight(new Flight(numberOfFlight, portOfShipment, day, month, year, destinationCity, airline, 0, 0, null));
		
		int numberOfFlight1 = 20;
		int portOfShipment1 = 30;
		int day1 = 3;
		int month1 = 7;
		int year1 = 2016;
		String destinationCity1 = "Bogota";
		String airline1 = "Avianca";
		flight.setPreviusFlight(new Flight(numberOfFlight1, portOfShipment1, day1, month1, year1, destinationCity1, airline1, 0, 0, null));
		
		
	}
	
	@Test
	public void checkGetMethods() {
		setupEscenary1();
		int numberOfFlight = 1;
		int portOfShipment = 2;
		int day = 1;
		int month = 4;
		int year = 2019;
		String destinationCity = "Bogota";
		String airline = "Avianca";
		
		assertEquals(numberOfFlight, flight.getNumberOfFlight());
		assertEquals(portOfShipment, flight.getPortOfShipment());
		assertEquals(day, flight.getDay());
		assertEquals(month, flight.getMonth());
		assertEquals(year, flight.getYear());
		assertEquals("10:20 AM",flight.getTime().toString());
		assertEquals(destinationCity,flight.getDestinationCity());
		assertEquals(airline,flight.getAirline());
		
		// vALIDA SI EL ANTERIOR U EL SIGUIENTE 
		setupEscenary4();
	
		assertEquals(new Flight(20,30,3,7,2016,"Bogota","Avianca", 0, 0, null).toString(), flight.getNextFlight().toString());
		assertEquals(new Flight(20,30,3,7,2016,"Bogota","Avianca",0,0,null).toString(),flight.getPreviusFlight().toString());
	}
	
	@Test 
	public void checksetMethods() {
		setupEscenary2();
		int numberOfFlight = 20;
		int portOfShipment = 30;
		int day = 3;
		int month = 7;
		int year = 2016;
		
		assertEquals(numberOfFlight, flight.getNumberOfFlight());
		assertEquals(portOfShipment, flight.getPortOfShipment());
		assertEquals(day, flight.getDay());
		assertEquals(month, flight.getMonth());
		assertEquals(year, flight.getYear());
	}
	
	@Test
	public void checkComparatorDestinationCity() {
		setupEscenary1();
		Flight f1 = new Flight();
		// Cuando son iguales
		f1.setDestinationCity("Bogota");
		assertEquals(0, flight.compareTo(f1));
		// Cuando es menor
		f1.setDestinationCity("Roma");
		assertEquals(-1, flight.compareTo(f1));
		// Cuando es mayor.
		f1.setDestinationCity("Armenia");
		assertEquals(1, flight.compareTo(f1));
		
	}
	
	@Test
	public void checkCompareToDate() {
		setupEscenary3();
		Flight f1 = new Flight();
		f1.setDay(10);
		f1.setMonth(12);
		f1.setYear(2018);
		
		Flight f2 = new Flight();
		f2.setDay(10);;
		f2.setMonth(12);
		f2.setYear(2019);
		// Cuando es menor f1.
		assertEquals(-1,flight.compare(f1, f2));
		
		//Cuando es mayor f1.
		f2.setDay(10);
		f2.setMonth(11);
		f2.setYear(2018);
		assertEquals(1,flight.compare(f1, f2));
		
		//Cuando son iguales
		f2.setDay(10);;
		f2.setMonth(12);
		f2.setYear(2018);
		assertEquals(0,flight.compare(f1, f2));

	}
	
	
}
