
package pl.wit.proj.test;
import static org.junit.Assert.*;

import java.util.*;


import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pl.wit.proj.Student;
import pl.wit.proj.PersonException;
import pl.wit.proj.Person;
import pl.wit.proj.StudentDatabase;

import org.javatuples.*;

/**
 * @author Edgar Ostrowski & Olena Mikhieieva
 *
 */

public class StudentDatabaseTest {
	private Date dateOfBirth1 = null;
	private Date dateOfBirth2 = null;
	private Date dateOfBirth3 = null;
	
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
	}
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void searchTest() {
		StudentDatabase sd = new StudentDatabase();
		//konstruktor Student - String firstName, String lastName, String groupId, String nrAlbum, Date dateOfBirth, Map<String, Byte> mapPoints) {
		Student st1 = new Student("Jan", "Kowalski", "ID07IO2", "12345", dateOfBirth1); 
		Student st2 = new Student("Edgar", "Cobas", "ID06IO2", "19225", dateOfBirth2);
		Student st3 = new Student("Olena", "Mikhieieva", "ID06IO2", "19415", dateOfBirth3);
		
		sd.addPerson(st1);
		sd.addPerson(st2);
		sd.addPerson(st3);
		
		try {
			st1.addPoints("100", (byte) 40);
		} catch (PersonException e1) {
			e1.printStackTrace();
		}
		try {
			st2.addPoints("100", (byte) 80);
		} catch (PersonException e1) {
			e1.printStackTrace();
		}
		try {
			st3.addPoints("100", (byte) 90);
		} catch (PersonException e1) {
			e1.printStackTrace();
		}
		
		
		
		Map<String, Pair<Object, Byte>> conditions = new HashMap<String, Pair<Object, Byte>>();
		
		try {
			conditions.put("firstName", Pair.with((Object)"Jan",(byte)0));
			List<Person> result1 = new ArrayList<Person>();
			List<Student> res = new ArrayList<Student>();
			res.add(st1);
			res.add(st2);
			res.add(st3);
			result1.addAll(sd.search(conditions));

			assertEquals(3,res.size());
			
			List<Person> result2 = sd.search(conditions);
			assertEquals(1, result2.size());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
