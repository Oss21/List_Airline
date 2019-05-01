package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TimeTest {


	private Time time;
	
	public void setupEscenary1() {
		int hour = 12;
		int minute = 38;
		String period = "PM";
		time = new Time(hour,minute,period);
	}
	
	
	@Test
	public void checkMethodsGet() {
		setupEscenary1();
		assertEquals(12,time.getHour(),"La hora no se guardo correctamente" );
		assertEquals(38, time.getMinute(),"El minuto no se agrego correctamente");
		assertEquals(Time.PM, time.getPeriod(),"La zona horaria es incorrecta");
	}
	
	@Test
	public void checkToString() {
		setupEscenary1();
		assertEquals("12:38 PM", time.toString());
	}
	
	@Test
	public void checkCompareTo() {
		setupEscenary1();
		Time tm= new Time(12, 38,Time.PM);
		// Cuando las horas son iguales.
		assertEquals(0, time.compareTo(tm));
		//Cuando la hora es menor.
		Time tmDos = new Time(11, 00,Time.PM);
		assertEquals(-1, time.compareTo(tmDos));
		//Cuando la hora es mayor.
		Time tmTres = new Time(12, 37,Time.PM);
		assertEquals(1, time.compareTo(tmTres));
		
	}

}
