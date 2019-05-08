package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class AirportTest {
	private Airport airport;
	
	private void setupEscenary1() {
		airport = new Airport("COO");
		
	}
	
	@Test
	public void checkIsEmpty() {
		setupEscenary1();
		// Verifica que el primero no existe.
		assertTrue(airport.isEmpty());
		// Verifica que el primero existe.
		airport.setFirst(new Flight(20, 10, 2, 3, 2000, "Barcelona", "Latam", 0, 0, null));
		assertTrue(airport.isEmpty() == false);
	}
	
	@Test 
	public void checkAddFlight() {
		//preguntar
//		assertEquals(f2.toString(),airport.getFirst().getNextFlight().toString());
		//Cuando se agrega el primero.
		setupEscenary1();
		Flight f = new Flight(10, 20, 2, 12, 2013, "Barcelona", "Avianca", 0, 0, null);
		airport.addFlight(f);
		assertTrue(f.getNextFlight() == null, "No deberia existir el siguiente.");
		assertTrue(f.getPreviusFlight() == null,"No debiria existir el anterior");
		
		// Añadio correctament al primero.
		assertTrue(f.equals(airport.getFirst()));
		// Verificar si se agrega al final y conoce al anterior.
		Flight f2 = new Flight(20, 30, 2, 12, 2014, "Barcelona", "Avianca", 0, 0, null);
		airport.addFlight(f2);
	
		// Cuando se agrega despues de ultimo
		assertTrue(f2.equals(airport.getFirst().getNextFlight()), "Los objetos de tipo FLight no contienen los mismos valores");
		
	}
	

	@Test	
	public void checkLoadAirlineNames() {
		setupEscenary1();
		try {
			airport.loadAirlinesNames();
			assertTrue(CompareFileWithArray.assertEqualsCompareFileWithArrangement(airport.getAirlineNames(),Airport.PATH_NAME_OF_AIRLINES));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DifferentSizesException e) {
			e.printStackTrace();
		}
	 // Probar si la excepcion se lanza correctamente.
		try {
			assertTrue(CompareFileWithArray.assertEqualsCompareFileWithArrangement(airport.getAirlineNames(),"/"+Airport.PATH_DESTINATION_CITY));
			fail("La excepcion no se lanzo correctamente");
		} catch (IOException e) {
		} catch (DifferentSizesException e) {
		}
		
		
	}
	
	@Test
	public void checkLoadDestinationCity() throws IOException {
		setupEscenary1();
		airport.loadDestinationCity();
		
		try {
			assertTrue(CompareFileWithArray.assertEqualsCompareFileWithArrangement(airport.getDestinationCity(),Airport.PATH_DESTINATION_CITY));
		} catch (IOException | DifferentSizesException e) {
			e.printStackTrace();
		}
		
		 // Probar si la excepcion se lanza correctamente.

		try {
			assertTrue(CompareFileWithArray.assertEqualsCompareFileWithArrangement(airport.getDestinationCity(),"/"+Airport.PATH_DESTINATION_CITY));
			fail("La excepcion no se lanzo correctamente");
		} catch (IOException e) {
			
		} catch (DifferentSizesException e) {
			
		}
	}
	
	
	

}
