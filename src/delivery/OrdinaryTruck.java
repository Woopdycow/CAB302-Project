package delivery;

import stock.*;

public class OrdinaryTruck extends Truck {

	private static final int ORDINARYCAPACITY = 1000;
	private static final String TRUCKTYPE = "Ordinary";
	
	
	/**
	 * Constructor Method
	 * @author Jonathon Meyer
	 */
	public OrdinaryTruck() {
		this.cargoCapacity = ORDINARYCAPACITY;
	}
	
	public void setCargo(Stock stock) throws TruckOverloadException {
		if (stock.getTotal() > cargoCapacity) {
			throw new TruckOverloadException();
		} else {
			cargo = stock;
		}
	}
	
	public double getCost() {
		this.cost = 750.00 + (0.25 * cargo.getTotal());
		return cost;
	}
	public String getType() {
		return TRUCKTYPE;
	}
}
