package stock;

public class Store {	
	private static final double STARTINGCAPITAL = 1000000.00;
	private static String name;
	private static double capital;
	private static Stock inventory;
	

	/**
	 * Constructor Method
	 * @author Bryan Kassulke
	 */
	// Protected constructor is sufficient to suppress unauthorized
	// calls to the constructor
	protected Store() {
		name = "SUPERMART";
		capital = STARTINGCAPITAL;
		inventory = new Stock();
	}

	private static class StoreHolder {
		private final static Store INSTANCE = new Store();
	}
	public static Store getInstance() {
		return StoreHolder.INSTANCE;
	}
	
	public static double getCapital() {
		return capital;
	}
	
}
