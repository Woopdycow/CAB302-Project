package stock;

/**
 * Thrown when delivery manifest is unable to be received properly.
 * @author Bryan Kassulke
 */
public class DeliveryException extends Exception {

	/**
	 * Constructs a DeliveryException with null as its error message string.
	 */
	public DeliveryException() {
		super();
	}

	/**
	 * Constructs a DeliveryException with a error message string.
	 * 
	 * @param messsage A description of the exception's cause.
	 */
	public DeliveryException(String message) {
		super(message);
	}
}
