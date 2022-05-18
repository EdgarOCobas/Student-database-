package pl.wit.proj;

import java.util.*;

import org.javatuples.Pair;



/**
 * @author Edgar Ostrowski & Olena Mikhieieva
 * Klasa abstrakcyjna person
 *
 */
public abstract class Person {
	protected String firstName;
	
	protected String lastName;
	
	private Date dateOfBirth;
	
	//private Map<String, Pair<Object, Byte>> conditions = null;
	
	public abstract boolean search(Map<String, Pair<Object, Byte>> conditions) throws Exception;
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Imie: ").append(this.firstName).append("\n");
		sb.append("Nazwisko: ").append(this.lastName).append("\n");
		sb.append("Data urodzenia: ").append(dateOfBirth.toString()).append("\n");
		return sb.toString();
	}
	
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Person(String firstName, String lastName, Date dateOfBirth) {
		this(firstName, lastName);
		this.dateOfBirth = dateOfBirth;
	}
	
	
	/** Metoda matches ktora potwierdza poprawnosc danych nie strict'owej pomocnicza dla metody search
	 * @param firstName
	 * @param lastName
	 * @param birthFrom
	 * @param birthTo
	 * @return
	 */
	public boolean matches(String firstName, String lastName, Date birthFrom, Date birthTo) {
		return matches(firstName, lastName, birthFrom, birthTo, false);

	}
	
	/**Metoda matches ktora potwierdza poprawnosc danych pomocnicza dla metody search
	 * @param firstName
	 * @param lastName
	 * @param birthFrom
	 * @param birthTo
	 * @param strict
	 * @return
	 */
	public boolean matches(String firstName, String lastName, Date birthFrom, Date birthTo, boolean strict) {
		return matchesFirstName(firstName, strict) && matchesLastName(lastName, strict)
				&& matchesDateOfBirth(birthFrom, birthTo);

	}
	
	/** Metoda potwierdzajaca poprawnosc imienia pomocnicza dla metody search
	 * @param firstName
	 * @param strict
	 * @return
	 */
	private boolean matchesFirstName(String firstName, boolean strict) {
		if (firstName != null) {
			if (this.firstName != null) {
				if (!strict)
					return this.firstName.startsWith(firstName);
				else
					return this.firstName.equalsIgnoreCase(firstName);
			} else
				return false;
		}
		return true;
	}
	
	/**Metoda potwierdzajaca poprawnosc nazwiska pomocnicza dla metody search
	 * @param lastName
	 * @param strict
	 * @return
	 */
	private boolean matchesLastName(String lastName, boolean strict) {
		if (lastName != null) {
			if (this.lastName != null) {
				if (!strict)
					return this.lastName.startsWith(lastName);
				else
					return this.lastName.equalsIgnoreCase(lastName);
			} else
				return false;
		}
		return true;
	}

	/** Metoda potwierdzajaca date urodzenia pomocnicza dla metody search
	 * @param birthFrom
	 * @param birthTo
	 * @return
	 */
	private boolean matchesDateOfBirth(Date birthFrom, Date birthTo) {

		if (birthFrom != null) {
			if (this.dateOfBirth != null) {
				if (!(this.dateOfBirth.after(birthFrom) || this.dateOfBirth.equals(birthFrom)))
					return false;
			} else
				return false;
		}
		if (birthTo != null) {
			if (this.dateOfBirth != null) {
				if (!(this.dateOfBirth.before(birthTo) || this.dateOfBirth.equals(birthTo)))
					return false;
			} else
				return false;
		}

		return true;
	}

}
