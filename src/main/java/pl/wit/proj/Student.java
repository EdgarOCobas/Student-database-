package pl.wit.proj;

import java.util.*;
import java.util.logging.Logger;

import org.apache.log4j.LogManager;
import org.javatuples.Pair;

/**
 * @author Edgar Ostrowski & Olena Mikhieieva
 *
 */


// Klasa opisujaca dane studenta
//Klasa opisujaca dane studenta
public class Student extends Person {
	
	protected static final org.apache.log4j.Logger log = LogManager.getLogger(Student.class.getName());
	
	private Map<String, Byte> mapPoints = null;

	private String groupId = null;
	
	private String nrAlbum = null;
	
	public Student(String firstName, String lastName, String groupId, String nrAlbum, Date dateOfBirth) {
		super(firstName, lastName, dateOfBirth);
		this.groupId = groupId;
		this.nrAlbum = nrAlbum;
	}
	
	public Student(String firstName, String lastName, String groupId, String nrAlbum, Date dateOfBirth, Map<String, Byte> mapPoints) {
		this(firstName, lastName, groupId, nrAlbum, dateOfBirth);
		this.mapPoints = mapPoints;
	}
	
	
	/** Metoda string wyswietlaja dane studentow
	 *
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		sb.append("Grupa: ").append(this.groupId).append("\n");
		sb.append("Numer albumu: ").append(this.nrAlbum).append("\n");
		sb.append("Punktacja: ").append("\n");
		
		for (String key : this.mapPoints.keySet()) {
			sb.append(key).append("/").append(this.mapPoints.get(key).toString()).append("\n");
		}
		return sb.toString();
	}
	
	/**
	 * Metoda zwracajaca boolean, ktory potwierdza poprawnosc danych
	 * @param firstName
	 * @param lastName
	 * @param groupId
	 * @param nrAlbum
	 * @param birthFrom
	 * @param birthTo
	 * @return true || false
	 */
	
	public boolean matches(String firstName, String lastName, String groupId, String nrAlbum, Date birthFrom, Date birthTo) {
		return super.matches(firstName, lastName, birthFrom, birthTo)
				  && matchesAlbum(nrAlbum, false) 
				  && matchesGroup(groupId, false);
	}
	
	public boolean matches(String firstName, String lastName, String groupId, String nrAlbum, Date birthFrom, Date birthTo, boolean strict) {
		return super.matches(firstName, lastName, birthFrom, birthTo, strict)
				  && matchesAlbum(nrAlbum, strict) 
				  && matchesGroup(groupId, strict);
	}
	
	/**
	 * Metoda zwracaja boolean, ktory potwierdza poprawnosci grupy
	 * @param groupId
	 * @param strict
	 * @return
	 */
	private boolean matchesGroup(String groupId, boolean strict) {
		if (groupId != null) {
			if (this.groupId != null) {
				if (!strict) {
					return this.groupId.startsWith(groupId);
				} else {
					return this.groupId.equalsIgnoreCase(groupId);
				}
			} else {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Metoda zwracaja boolean, ktory potwierdza poprawnosc albumu
	 * @param nrAlbum
	 * @param strict - parametr oznaczajacy czy wyszukiwanie musi byc doslowne
	 * @return
	 */
	private boolean matchesAlbum(String nrAlbum, boolean strict) {
		if (nrAlbum != null) {
			if (this.nrAlbum != null) {
				if (!strict) {
					return this.nrAlbum.startsWith(nrAlbum);
				} else {
					return this.nrAlbum.equalsIgnoreCase(nrAlbum);
				}
			} else {
				return false;
			}
		}
		return true;
	}
		
	/**Metoda dodajaca punkty i kryterium maksymalnych punktow dla studenta
	 * @param pointCrit
	 * @param points
	 * @throws PersonException
	 */
	public void addPoints(String pointCrit, Byte points) throws PersonException{
		if (pointCrit == null || pointCrit.isEmpty()) throw new PersonException("Puste kryterium");
		
		if (points < 0) throw new PersonException("Liczba punktow jest ujemna");
		
		if (mapPoints != null)
		{
			if (mapPoints.containsKey(pointCrit)) 
			{
				Byte newPoints = 0;
				
				if (Byte.MAX_VALUE >= (mapPoints.get(pointCrit) + points) && Byte.MIN_VALUE <= (mapPoints.get(pointCrit) + points)) 
				{
					newPoints = (byte) (mapPoints.get(pointCrit) + points);
				}
				
				log.debug(mapPoints.get(pointCrit));
				
				mapPoints.put(pointCrit,  newPoints);
			}
		}
	}
	
	/**
	 * Metoda search ktora zwraca wynik wyszukiwania dla podanego klucza typu String
	 */
	public boolean search(Map<String, Pair<Object, Byte>> conditions) throws Exception {
		boolean allTrue = true;
		if (conditions == null) 
			return true;
		for (String key : conditions.keySet()) {
			boolean result = false;
			switch (key) {
				case "firstName":
					try {
						result = matches((String)conditions.get(key).getValue0(), null, null, null);
					} catch(ClassCastException e) {
						continue;
					}
					allTrue &= result;
					break;
				case "lastName":
					try {
						result = matches(null, (String)conditions.get(key).getValue0(), null, null);
					} catch (ClassCastException e) {
						continue;
					}
					allTrue &= result;
					break;
				case "birthFrom":
					try {
						result = matches(null, null, (Date)conditions.get(key).getValue0(), null);
					} catch (ClassCastException e) {
						continue;
					}
					allTrue &= result;
					break;
				case "birthTo": 
					try {
						result = matches(null, null, null, (Date)conditions.get(key).getValue0());
					} catch (ClassCastException e) {
						continue;
					}
					allTrue &= result;
					break;
			}
		}
		return false;
	}
	
	////////////////////////
	/// Getters & Setters //
	////////////////////////
	
	
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getNrAlbum() {
		return nrAlbum;
	}

	public void setNrAlbum(String nrAlbum) {
		this.nrAlbum = nrAlbum;
	}
	
	public org.apache.log4j.Logger getLogger() {
		return log;
	}

	public Map<String, Byte> getMapPoints() {
		return mapPoints;
	}

	public void setMapPoints(Map<String, Byte> mapPoints) {
		this.mapPoints = mapPoints;
	}
}

