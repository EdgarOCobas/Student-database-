package pl.wit.proj;

import java.util.*;
import org.javatuples.*;



/**
 * @author Edgar Ostrowski & Olena Mikhieieva
 *
 */


/**
 * Klasa zachowujaca studentow oraz ich danych w postaci bazy danych
 *
 */
public class StudentDatabase {
	
	// Tablica zawieraja klasa studentow
	private Person students[] = null;
	
	public StudentDatabase() {
		this.students = new Person[0];
	}
	
	/** Metoda dodajaca studenta do tablicy
	 * @param student
	 */
	public void addPerson(Person student) {
		students = Arrays.copyOf(students, students.length+1);
		students[students.length-1]=student;
	}
	

	/** Metoda dla przeszukiwania studenta w tablicy
	 * @param conditions
	 * @return
	 * @throws Exception
	 */
	public List<Person> search(Map<String, Pair<Object, Byte>> conditions) throws Exception {
		List<Person> results = new ArrayList<Person>();
		for (Person p:this.students) {
			if (p.search(conditions))
				results.add(p);
		}
		return results;
	}
}
