package pl.wit.proj;

/**
 * @author Edgar Ostrowski-Cobas & Olena Mikhieieva
 * Klasa personexception wyrzucajaca wyjatek 
 */
public class PersonException extends Exception {

	private static final long serialVersionUID = -4627364523198787094L;
	
	public PersonException(String message) {
		super(message);
	}
	
	public PersonException(String message, Exception exception) {
		super(message, exception);
	}
}
