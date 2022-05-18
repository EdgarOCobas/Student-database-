package pl.wit.proj;
import java.io.*;
import java.util.*;


/**
 * @author Edgar Ostrowski & Olena Mikhieieva
 *
 */
public class FileService 
{	
	public static void main(String[] args) throws IOException {
		
		// Dane wpisane przez uzytkownika
		
		Date dateOfBirth1 = null;
		Date dateOfBirth2 = null;
		Date dateOfBirth3 = null;
		
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
		Student st1 = new Student("Jan", "Kowalski", "ID07IO2", "12345", dateOfBirth1, st1Points);
		Map<String, Byte> st2Points = new HashMap<String, Byte>();
		st2Points.put("100", (byte) 80);
		Student st2 = new Student("Edgar", "Cobas", "ID06IO2", "19225", dateOfBirth2, st2Points);
		Map<String, Byte>st3Points = new HashMap<String, Byte>();
		st3Points.put("100", (byte) 90);
		Student st3 = new Student("Olena", "Mikhieieva", "ID06IO2", "19415", dateOfBirth3, st3Points);
		
		// Zapis studentow do listy
		
		List<Student> listOfStudents = new ArrayList<Student>();
		listOfStudents.add(st1);
		listOfStudents.add(st2);
		listOfStudents.add(st3);
		
		// Zapis danych studentow oraz nazwa pliku do ktorego dane sa zapisane
		
		String fileName = "C:\\temp\\studentOutput.txt";
		StringBuilder sb = new StringBuilder();
		for (Student el: listOfStudents) {
			sb.append(el);
		}
		
		String output = sb.toString();
		
		
		// Zapis danych do pliku oraz tworzenie pliku
		FileOutputStream fos = null;
		DataOutputStream dos = null;
		
		try {
			fos = new FileOutputStream(fileName);
			dos = new DataOutputStream(fos);
			
			dos.writeChars(output);
		} catch (FileNotFoundException fnfe) {
			System.out.println("Nie znaleziono pliku " + fnfe);
		} catch (IOException ioe) {
			System.out.println("Blad zapisu do pliku " + ioe);
		} finally {
			try {
				if (dos!= null) {
					dos.close();
				}
				if (fos != null) {
					fos.close();
				}
			}
			catch (Exception e) {
				System.out.println("Blad podczas zamykania strumieni " + e);
			}
		}
		// Odczyt danych z pliku
		
		FileInputStream inputStream = new FileInputStream(fileName);
		
		DataInputStream dis = new DataInputStream(inputStream);
		
		int count = inputStream.available();
		
		byte[] b = new byte[count];
		
		int bytes = dis.read(b);
		
		for (byte by: b) {
			System.out.print((char) by);
		}
		
		// Wyszukiwanie i wyswietlanie studentow
		
		
		
	}
}



