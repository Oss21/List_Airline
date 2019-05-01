package model;

public class Airport {

	public final static String PATH_NAME_OF_AIRLINES = "data/name_of_airlines";
	public final static String PATH_DESTINATION_CITY = "data/Destination_city_data";
	
	//Relaciones 
	private FlightTest first;
	private FlightTest last;
	
	
	//Atributos
	private String name;
	private String[] arlineNames;
	private String[] destinationCity;
	
	
	
	
	
	
	public Airport(String name) {
		this.first = null;
		this.last = null;
		this.name = name;
		this.arlineNames = new String[7];
		this.destinationCity = new String [8];
	}
	
	
	
	public FlightTest getFirst() {
		return first;
	}
	public void setFirst(FlightTest first) {
		this.first = first;
	}
	public FlightTest getLast() {
		return last;
	}
	public void setLast(FlightTest last) {
		this.last = last;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getArlineNames() {
		return arlineNames;
	}
	public void setArlineNames(String[] arlineNames) {
		this.arlineNames = arlineNames;
	}
	public String[] getDestinationCity() {
		return destinationCity;
	}
	public void setDestinationCity(String[] destinationCity) {
		this.destinationCity = destinationCity;
	}
	
	
	
	
}
