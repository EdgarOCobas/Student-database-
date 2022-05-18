package pl.wit.proj.test;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Before;
import org.junit.Test;

import pl.wit.proj.Student;
import pl.wit.proj.Person;

/**
 * @author Edgar Ostrowski & Olena Mikhieieva
 *
 */

public class StudentMatchesTest {
	private Date dateOfBirth1 = null;
	private Date dateOfBirth2 = null;	
	private Date dateOfBirth3 = null;
	private Map<String, Byte> st1Points = null;
	
	@Before
	public void setUp() throws Exception {
		Calendar cal = Calendar.getInstance();
		
		cal.set(Calendar.YEAR, 1990);
		cal.set(Calendar.MONTH, 5);
		cal.set(Calendar.DAY_OF_MONTH, 8);
		dateOfBirth1 = cal.getTime();
		
		cal.set(Calendar.YEAR, 1980);
		cal.set(Calendar.MONTH, 5);
		cal.set(Calendar.DAY_OF_MONTH, 8);
		dateOfBirth2 = cal.getTime();
		
		cal.set(Calendar.YEAR, 2000);
		cal.set(Calendar.MONTH, 5);
		cal.set(Calendar.DAY_OF_MONTH, 8);
		dateOfBirth3 = cal.getTime();
		
		Map<String, Byte> st1Points = new HashMap<String, Byte>();
		st1Points.put("100", (byte) 40);
		}
	
	@Test
	public void matchProgrammerTest() {
		Student st1 = new Student("Jan", "Kowalski", "ID07IO2", "12345", dateOfBirth1, st1Points);
		//Brak warunkow zatem true
		assertTrue(st1.matches(null, null, null, null, null, null));
		//Bledne imie
		assertFalse(st1.matches("Janina", null, null, null, null, null));
		//Bledne nazwisko
		assertFalse(st1.matches(null, "Nowak", null, null, null, null));
		//Bledny numer grupy
		assertFalse(st1.matches(null, null, "ID07IO1", null, null, null));
		//Bledny numer albumu
		assertFalse(st1.matches(null, null, null, "23456", null, null));
		//Zgodnosc daty urodzenia
		assertTrue(st1.matches(null, null, null, null, dateOfBirth1, dateOfBirth1));
		//Data mieszczaca sie w przedziale
		assertTrue(st1.matches(null, null, null, null, dateOfBirth2, dateOfBirth3));
		//Data niezgodna
		assertFalse(st1.matches(null, null, null, null, dateOfBirth2, dateOfBirth2));
		//Zgodnosc częsciowa
		assertTrue(st1.matches("Jan", "Kow", null, null, null, null));
		//Brak zgodności pełnej
		assertFalse(st1.matches("Jan", "Kow", null, null, null, null, true));
	}
}