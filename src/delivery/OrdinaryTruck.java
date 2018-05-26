package delivery;

import stock.*;

public class OrdinaryTruck extends Truck {

	private static final int ORDINARYCAPACITY = 1000;
	private static final String TRUCKTYPE = "Ordinary";
	
	/**
	 * Constructor Method for Ordinary Truck.
	 * @author Jonathon Meyer
	 */
	public OrdinaryTruck() {
		this.cargoCapacity = ORDINARYCAPACITY;
	}
	
	/**
	 * Sets the truck's cargo to a Stock object.
	 * @author Jonathon Meyer
	 * @param stock The stock object to become the truck's cargo.
	 */
	public void setCargo(Stock stock) throws TruckOverloadException {
		if (stock.getTotal() > cargoCapacity) {
			throw new TruckOverloadException();
		} else {
			cargo = stock;
		}
	}
	
	/**
	 * Gets the cost of the truck.
	 * @author Jonathon Meyer
	 * @return Returns the calculated cost of the truck.
	 */
	public double getCost() {
		this.cost = 750.00 + (0.25 * cargo.getTotal());
		return cost;
	}
	
	/**
	 * Gets the truck's type.
	 * @author Jonathon Meyer
	 * @return Returns the truck type expressed as a string.
	 */
	public String getType() {
		return TRUCKTYPE;
	}
}
