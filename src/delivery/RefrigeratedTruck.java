package delivery;

import stock.*;

public class RefrigeratedTruck extends Truck {
	
	private double temp = 10.00;
	private static final int REFRIGERATEDCAPACITY = 800;
	private static final String TRUCKTYPE = "Refrigerated";
	
	/**
	 * Constructor Method
	 * @author Jonathon Meyer
	 */
	public RefrigeratedTruck() {
		this.cargoCapacity = REFRIGERATEDCAPACITY;
	}
	
	public void setCargo(Stock stock) throws TruckOverloadException {
		if (stock.getTotal() > cargoCapacity) {
			throw new TruckOverloadException();
		} else {
			cargo = stock;
		}
		getTemp();
	}
	
	public double getTemp() {
		if (cargo != null) {
			for (Item key : cargo.getItemSet()){ 
				if (key.getTemp() < temp) {
					temp = key.getTemp();
				}
			}
			return temp;
		} else {
			return 10.0;
		}
		
	}
	
	public double getCost() {
		this.cost = 900 + (200 * Math.pow(0.7, (temp / 5)));
		return cost;
	}
	
	public String getType() {
		return TRUCKTYPE;
	}
}
