package delivery;

import stock.*;

public abstract class Truck {

	double cost;
	int cargoCapacity;
	Stock cargo;
	String type;
	
	/**
	 * Constructor Method
	 * @author Jonathon Meyer
	 */	
	public Truck() {};
	
	public abstract double getCost();
	
	public abstract void setCargo(Stock stock) throws TruckOverloadException;
	
	public Stock getCargo() {
		return cargo;
	}
	
	public int getCapacity() {
		return cargoCapacity;
	}
	
	public String getType() {
		return type;
	}
}
