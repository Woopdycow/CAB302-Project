package mart;

import java.util.*;

public abstract class Truck {

	double cost;
	int cargoCapacity;
	HashMap<Item, Integer> cargo;
	
	public Truck(double cost, int cargoCapacity, HashMap<Item, Integer> cargo) {
		this.cost = cost;
		this.cargoCapacity = cargoCapacity;
		this.cargo = cargo;
	}

}
