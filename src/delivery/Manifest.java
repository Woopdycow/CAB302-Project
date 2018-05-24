package delivery;

import stock.*;

import java.util.ArrayList;
import java.util.List;

import delivery.*;

public class Manifest {

	public List<Truck> trucks;
	
	/**
	 * Constructor Method
	 * @author Jonathon Meyer
	 */
	public Manifest() {
		trucks = new ArrayList<Truck>();
	}
	
	public void addTruck(Truck truck){
		trucks.add(truck);
	}
	
	public void removeTruck(Truck truck) {
		for (int i = 0; i < trucks.size(); i++) {
			if (trucks.get(i) == truck ) {
				trucks.remove(i);
			}
		}
	}
	
	public List<Truck> getTrucks() {
		return trucks;
	}
}
