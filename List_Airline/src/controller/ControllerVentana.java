package controller;

import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Airport;
import model.Flight;

public class ControllerVentana {

	private Airport airport;
	
	@FXML
	private TableView<Flight> tableView;
    @FXML
    private TableColumn<Flight,String> time;

    @FXML
    private TableColumn<Flight, String> date;

    @FXML
    private TableColumn<Flight,String> airline;

    @FXML
    private TableColumn<Flight, Integer> flight;

    @FXML
    private TableColumn<Flight, String> destinationCity;

    @FXML
    private TableColumn<Flight, Integer> portOfShipment;																																																																																																																																																																																																																																																																

    @FXML
    private TextField numberOfFlight;

    @FXML
    private TextField dataToSearch;
    
    @FXML
    private ChoiceBox<String> selectSort;
    @FXML
    private ChoiceBox<String> selectSearch;

    
    @FXML
    public void clearData(ActionEvent event) {
    	tableView.getItems().clear();
    }

   
	@FXML
    public void generateData(ActionEvent event) {
		try {
    	int data = Integer.parseInt(numberOfFlight.getText());
			airport.loadAirlinesNames();
			airport.loadDestinationCity();
			airport.generateFlights(data);
		
    	Flight current = airport.getFirst();
    	while (current != null) {
       		tableView.getItems().addAll(current);	
			current = current.getNextFlight();
		}
    	
    	numberOfFlight.clear();
    	}catch (NumberFormatException e) {
    		JOptionPane.showMessageDialog(null, "Debe ingresar un valir numerico y luego presionar generar");
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	
    }

    @FXML
    public void search(ActionEvent event) {
    	try {
    	String current = selectSearch.getSelectionModel().getSelectedItem();
    	String name = dataToSearch.getText();
    	if (current.equals("Airline")) {
    		tableView.getItems().clear();
			tableView.getItems().addAll(airport.searchByAirline(name));
    	}else if (current.equals("Destination City")) {
    		tableView.getItems().clear();
			tableView.getItems().addAll(airport.searchDestinationCity(name));

    	}else if (current.equals("Date")) {
    		tableView.getItems().clear();
    		String date[] = name.split("/");
    		int day = Integer.parseInt(date[0]);
    		int month = Integer.parseInt(date[1]);
    		int year= Integer.parseInt(date[2]);
    		tableView.getItems().addAll(airport.searchByDate(day, month, year));
    		
    	}else if (current.equals("Time")) {
    		tableView.getItems().clear();
    		String []  time =  name.split(":");
    		int hour = Integer.parseInt(time[0]);
    		String [] time2= time[1].split(" ");
    		int minute = Integer.parseInt(time2[0]);
    		String period = time2[1];
      		tableView.getItems().addAll(airport.searchByTime(hour, minute, period));    		
    	}else if (current.equals("Number Of Flight")) {
    		tableView.getItems().clear();
      		tableView.getItems().addAll(airport.searchByNumberOfFLight(Integer.parseInt(name)));
      	}else if(current.equals("Port Of Shipment")){
      		tableView.getItems().clear();
      		tableView.getItems().addAll(airport.searchByPortOfShipment(Integer.parseInt(name)));
      		
      	}else {
      		System.out.println("dd");
      	}
    
    	selectSearch.getSelectionModel().clearSelection();
    	dataToSearch.clear();
    	
    	}catch (NullPointerException e) {
    		JOptionPane.showMessageDialog(null, "Debe selecionar primero que desea buscar");
    	}
    	
    	
    }
    
    @FXML
    public void sortBy(ActionEvent event) {
    	String current = selectSort.getSelectionModel().getSelectedItem();
    	if (current.equals("Date")) {
    		tableView.getItems().clear();
    		airport.sortByDate();
    		Flight c = airport.getFirst();
        	while (c != null) {
           		tableView.getItems().addAll(c);	
    			c = c.getNextFlight();
    		}

    	}else if (current.equals("Destination City")) {
    		tableView.getItems().clear();
    		airport.sortByDestinationCity();
    		Flight c = airport.getFirst();
        	while (c != null) {
           		tableView.getItems().addAll(c);	
    			c = c.getNextFlight();
    		}
    	}
    }

    @FXML
    public void initialize() {
    	tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	airport = new Airport("CLO");
    	airline.setCellValueFactory(new PropertyValueFactory<Flight, String>("airline"));
    	flight.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("numberOfFlight"));
    	destinationCity.setCellValueFactory(new PropertyValueFactory<Flight, String>("destinationCity"));
    	portOfShipment.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("portOfShipment"));
    	time.setCellValueFactory(new PropertyValueFactory<Flight, String>("time"));
    	date.setCellValueFactory(new PropertyValueFactory<Flight, String>("date"));
    	
    	//ChoiceBox
    	//chDireccion.setItems(FXCollections.observableArrayList("Nororiente", "Noroeste", "Suroriente", "Suroeste"));
    	selectSort.setItems(FXCollections.observableArrayList("Destination City","Airline","Date","Time","Number Of Flight","Port Of Shipment"));
    	selectSearch.setItems(FXCollections.observableArrayList("Destination City","Airline","Date","Time","Number Of Flight","Port Of Shipment"));

    
    }
}

