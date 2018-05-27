package stock;

/**
 * Received manifest contains items that don't exist within the store's contents.
 * @author Bryan Kassulke
 */
public class StockException extends Exception {

	/**
	 * Constructs a StockException with null as its error message string.
	 */
	public StockException() {
		super();
	}

	/**
	 * Constructs a StockException with a error message string.
	 * 
	 * @param messsage A description of the exception's cause.
	 */
	public StockException(String message) {
		super(message);
	}
}
