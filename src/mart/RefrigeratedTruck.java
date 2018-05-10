package mart;

import java.util.*;

public class RefrigeratedTruck extends Truck {

	private double temperature;

	public RefrigeratedTruck(double cost, int cargoCapacity, HashMap<Item, Integer> cargo, double temperature) {
		super(cost, cargoCapacity, cargo);
		this.temperature = temperature;
	}

	public double getTemperature() {
		return temperature;
	}

}
