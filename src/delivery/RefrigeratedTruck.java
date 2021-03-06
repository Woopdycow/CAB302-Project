package delivery;

import stock.*;

public class RefrigeratedTruck extends Truck {
	
	private double temp = 11.00;
	private static final int REFRIGERATEDCAPACITY = 800;
	private static final String TRUCKTYPE = "Refrigerated";
	
	/**
	 * Constructor Method for RefrigeratedTruck
	 * @author Jonathon Meyer
	 */
	public RefrigeratedTruck() {
		this.cargoCapacity = REFRIGERATEDCAPACITY;
	}
	
	/**
	 * Sets the truck's cargo to a Stock object.
	 * @author Jonathon Meyer
	 * @param stock The stock object to become the truck's cargo.
	 */
	public void setCargo(Stock stock) {
		cargo = stock;
	}
	
	/**
	 * Gets the temperature of the truck. 
	 * @author Jonathon Meyer
	 * @return Returns the temperature expressed as a double.
	 */
	public double getTemp() {
		if (cargo != null) {
			temp = 10.0;
			for (Item key : cargo.getItemSet()){
				if (key.getTemp() < temp) {
					temp = key.getTemp();
				}
			}
		}
		return temp;
	}
	
	/**
	 * Gets the cost of the truck.
	 * @author Jonathon Meyer
	 * @return Returns the calculated cost of the truck.
	 */
	public double getCost() {
		this.cost = 900 + (200 * Math.pow(0.7, (temp / 5.0)));
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
