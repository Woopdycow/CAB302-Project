package delivery;

import stock.*;

public abstract class Truck {

	double cost;
	int cargoCapacity;
	Stock cargo;
	
	/**
	 * Constructor Method
	 * @author Jonathon Meyer
	 */	
	public Truck() {
		
	}

	public void setCargo(Stock stock) {
		this.cargo = stock;
	}
	
	public Stock getCargo() {
		return cargo;
	}
	
	public double getCost() {
		return cost;
	}
	
	public int getCapacity() {
		return cargoCapacity;
	}
	
}
