package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.BooleanSupplier;

public class Airport {

	public final static String PATH_NAME_OF_AIRLINES = "data/name_of_airlines";
	public final static String PATH_DESTINATION_CITY = "data/Destination_city_data.txt";
	
	//Relaciones.
	private Flight first;
	
	
	//Atributos.
	private String name;
	private String[] airlineNames;
	private String[] destinationCity;
	private String[] period = {Time.AM, Time.PM};
	
	
	
	public Airport(String name) {
		this.first = null;
		this.name = name;
		this.airlineNames = new String[7];
		this.destinationCity = new String [8];
	}
	
	public void addFlight(Flight flight) {
		
		if (isEmpty()) {
			first = flight;
		}else {
			Flight last = addLast(first);
			last.setNextFlight(flight);
		}
	}
	private Flight addLast(Flight primero) {
		Flight current = primero;
		if(current.getNextFlight()== null) {
			return current;
		}else {
			return addLast(current.getNextFlight());
		}
	}
	
	public void generateFlights(int size) {
		
		for (int i = 0; i < size; i++) {
			
			int airlinePosition = (int) Math.floor(Math.random()*(airlineNames.length-1));
			int destinationCityPosition = (int) Math.floor(Math.random()*(destinationCity.length-1));
			int periodPosition =  (int) Math.floor(Math.random()*(period.length-1));
			
			int numberOfFlight = i;
			int portOfShipment = (int) Math.floor(Math.random()*100+1);
			int hour = (int) Math.floor(Math.random()*12+1);
			Integer minute = (int) Math.floor(Math.random()*59+00);
			int day = (int) Math.floor(Math.random()*31+1);
			int month = (int) Math.floor(Math.random()*12+1);
			int year = (int) Math.floor(Math.random()*2020+2019);
			addFlight(new Flight(numberOfFlight, portOfShipment, day, month, year, destinationCity[destinationCityPosition],
					airlineNames[airlinePosition],hour, minute, period[periodPosition]));
		
		}
		

		
	}
	
	
	
	public Flight getFirst() {
		return first;
	}
	public void setFirst(Flight first) {
		this.first = first;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getAirlineNames() {
		return airlineNames;
	}
	public void setAirlineNames(String[] arlineNames) {
		this.airlineNames = arlineNames;
	}
	public String[] getDestinationCity() {
		return destinationCity;
	}
	public void setDestinationCity(String[] destinationCity) {
		this.destinationCity = destinationCity;
	}



	public Boolean isEmpty() {
		return (first == null)? true:false;
	}

	public void loadAirlinesNames() throws IOException {
		File file = new File(PATH_NAME_OF_AIRLINES);
		FileReader fr = new FileReader(file);
		BufferedReader lector = new BufferedReader(fr);
		String line = lector.readLine();
		int i = 0;
		while (line != null) {
			airlineNames[i] = line;
			i++;
			line = lector.readLine();
		}
		lector.close();
		fr.close();
	}
	
	public void loadDestinationCity() throws IOException {
		File file = new File(PATH_DESTINATION_CITY);
		FileReader fr = new FileReader(file);
		BufferedReader lector = new BufferedReader(fr);
		String line = lector.readLine();
		int i = 0;
		while(line != null) {
			destinationCity[i] = line;
			i++;
			line = lector.readLine();
		}
		lector.close();
		fr.close();
	
	}
	
	public Flight searchByAirline(String name) {
		return searchByAirlineAux(first, name);
	}
	
	private Flight searchByAirlineAux(Flight flight, String name) {
		
		if (flight.getAirline().equals(name)) {
			return flight;
		}else {
			return searchByAirlineAux(flight.getNextFlight(), name);
		}
	}
	
	
	
	public Flight searchDestinationCity(String destination) {
		boolean foundFlight = false;
		Flight current = first;
		Flight toCompare = new Flight();
		Flight found = null;
		toCompare.setDestinationCity(destination);
		while(current != null && !foundFlight) {
			if (current.compareTo(toCompare) == 0) {
				found = current;
				foundFlight = true;
			}
			current = current.getNextFlight();
		}
		
		return found;
	}
	
	public Flight searchByDate(int day, int month, int year) {
		boolean foundFlight = false;
		Flight current = first;
		Flight aux = new Flight();
		Flight found = null;
		aux.setDay(day);
		aux.setMonth(month);
		aux.setYear(year);
		while(current != null) {
			if (current.compare(current,aux) == 0 && !foundFlight) {
				found = current;
				foundFlight = true;
			}
			current = current.getNextFlight();
		}
		
		return found;
	}
	
	
	public Flight searchByTime(int hour, int minute, String period) {
		boolean foundFlight = false;
		Flight found = null;
		Flight current = first;
		Time time = new Time(hour, minute, period);
		
		while(current != null) {
			if (current.getTime().compareTo(time)== 0 && !foundFlight) {
				found = current;
				foundFlight = true; 
			}
			current = current.getNextFlight();
		}
		
		return found;
	}
	
	public Flight searchByNumberOfFLight(int num) {
		boolean foundFlight = false;
		Flight current = first;
		Flight found = null;
		
		while(current != null) {
			if (current.getNumberOfFlight() == num && !foundFlight) {
				found = current;
				foundFlight = true;
			}
			current = current.getNextFlight();
		}
		
		return found;
	}
	
	
	public Flight searchByPortOfShipment(int num) {
		boolean foundFlight = false;
		Flight current = first;
		Flight found = null;
		
		while(current != null) {
			if (current.getNumberOfFlight() == num && !foundFlight) {
				found = current;
				foundFlight = true;
			}
			current = current.getNextFlight();
		}
		
		return found;
	}
	
	//burble
	public void sortByDate() {
		for (int i = 0; i < listSize(); i++) {
			Flight current = first;
			Flight previus = null;
			Flight next = first.getNextFlight();
			while (next != null) {
				if (current.compare(current, next) > 0) {
					if(previus != null) {
						Flight sig = next.getNextFlight();
						
						previus.setNextFlight(next);
						next.setNextFlight(current);
						current.setNextFlight(sig);
					}else {
						Flight sig = next.getNextFlight();
						
						first = next;
						next.setNextFlight(current);
						current.setNextFlight(sig);
					}
					previus = next;
					next = next.getNextFlight();
				}else {
					previus = current;
					current = next;
					next = next.getNextFlight();
				}
			}
		}
	}
	
	//Selection
	
	public  void sortByDestinationCity(){
		 Flight n1 = null;
		 Flight n2 = null;
		 
		 if (first == null || first.getNextFlight()== null)
		 {
				 return;
		 }
		 for (n1 = first; n1.getNextFlight() != null; n1 = n1.getNextFlight()){
			for (n2 = n1.getNextFlight(); n2 != null; n2 = n2.getNextFlight()) {
					Flight bn2 = before(n2);
					Flight bn1 = before(n1);
					if (n1.compareTo(n2) > 0){
						if (n1 == first){
							if (n1.getNextFlight() == n2){
								n1.setNextFlight(n2.getNextFlight());;
								n2.setNextFlight(n1);
								first = n2;
							 }
							else{
								 Flight temp = n2.getNextFlight();
								 n2.setNextFlight(n1.getNextFlight()); 
								 n1.setNextFlight(temp); 
								 bn2.setNextFlight(n1);
								 first = n2;
							}
						}else{
							   if (n1.getNextFlight() == n2)
							   {
									 bn1.setNextFlight(n2);
									 Flight temp = n2.getNextFlight();
									 n2.setNextFlight(n1);
									 n1.setNextFlight(temp); 
							   }
							   else
							   {
								   bn1.setNextFlight(n2);
								   Flight temp = n2.getNextFlight();
								   n2.setNextFlight(n1.getNextFlight());
								   n1.setNextFlight(temp);
								   bn2.setNextFlight(n1); 
							   }
						   }

							Flight temp = n1;
							n1 = n2;
							n2 = temp;

					   }
			}
		 }

	}
	
	

	
	private Flight before( Flight s)
   {
       Flight prev = null;
       Flight current = first;
       while( current != null && current != s )
       {
           prev = current;
           current = current.getNextFlight();
       }
       return current != null ? prev : null;
   }
	
	
	
	
	
	private int listSize() {
		Flight current = first;
		int size = 0;
		while (current != null) {
			size++;
			current = current.getNextFlight();
		}
		return size;
	}
}
