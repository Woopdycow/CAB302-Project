package delivery;

/**
 * Thrown by file readers when format is not recognised.
 * @author Bryan Kassulke
 */
public class CSVFormatException extends Exception {

	/**
	 * Constructs a CSVException with null as its error message string.
	 */
	public CSVFormatException() {
		super();
	}

	/**
	 * Constructs a CSVException with a error message string.
	 * 
	 * @param messsage A description of the exception's cause.
	 */
	public CSVFormatException(String message) {
		super(message);
	}
}
