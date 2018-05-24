package delivery;

import stock.Stock;

public class OrdinaryTruck extends Truck {

	public static final int CARGOCAPACITY = 1000;
	
	public OrdinaryTruck(double cost, int cargoCapacity, Stock cargo) {
		super();
		this.cargoCapacity = CARGOCAPACITY;
	}
	
	public void setCargo(Stock stock) {
		this.cargo = stock;
	}
	
	public void checkCapacity() throws TruckOverloadException {
		int total = cargo.getTotal();
		if (total > cargoCapacity) {
			throw new TruckOverloadException();
		}
	}
	
	public double getCost() {
		this.cost = 750 + (0.25 * cargo.getTotal());
		return cost;
	}
}
