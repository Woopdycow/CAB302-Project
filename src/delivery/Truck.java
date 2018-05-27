package delivery;

import stock.*;

public abstract class Truck {

	double cost;
	int cargoCapacity;
	Stock cargo;
	String type;
	
	/**
	 * Constructor Method for Truck
	 * @author Jonathon Meyer
	 */	
	public Truck() {};
	
	public abstract double getCost();
	
	public abstract void setCargo(Stock stock) ;
	
	/**
	 * Gets the Truck's cargo Stock object.
	 * @author Jonathon Meyer
	 * @return Returns truck cargo as Stock Object.
	 */
	public Stock getCargo() {
		return cargo;
	}
	
	/**
	 * Gets the maximum capacity of the truck.
	 * @author Jonathon Meyer
	 * @return Returns integer value of the trucks capacity.
	 */
	public int getCapacity() {
		return cargoCapacity;
	}
	
	/**
	 * Gets the type of truck. Refrigerated or Ordinary.
	 * @author Jonathon Meyer
	 * @return Returns the type of truck as a string.
	 */
	public String getType() {
		return type;
	}
}
