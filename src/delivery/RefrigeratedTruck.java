package delivery;

import stock.*;

public class RefrigeratedTruck extends Truck {
	
	double cost;
	int cargoCapacity;
	Stock cargo;
	double temp = 10.00;
	public static final int CARGOCAPACITY = 800;
	
	/**
	 * Constructor Method
	 * @author Jonathon Meyer
	 */
	public RefrigeratedTruck() {
		this.cargoCapacity = CARGOCAPACITY;
	}
	
	public int getCapacity() {
		return this.cargoCapacity;
	}
	
	public void setCargo(Stock stock){
		this.cargo = stock;
	}
	
	public double getTemp() {
		for (Item key : cargo.getContents().keySet()){ 
			if (key.getTemp() < temp) {
				temp = key.getTemp();
			}
		}
		return temp;
	}
	
	public double getCost() {
		this.cost = 900 + 200 * Math.pow(0.7, (temp / 5));
		return cost;
	}

}
