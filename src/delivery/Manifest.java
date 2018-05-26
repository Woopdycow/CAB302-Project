package delivery;

import stock.*;

import java.util.ArrayList;
import java.util.List;

import delivery.*;

public class Manifest {

	public List<Truck> trucks;
	
	/**
	 * Constructor Method for Manifest
	 * @author Jonathon Meyer
	 */
	public Manifest() {
		trucks = new ArrayList<Truck>();
	}
	
	/**
	 * Adds Truck object to the manifest.
	 * @author Jonathon Meyer
	 * @param truck Truck object to be added.
	 */
	public void addTruck(Truck truck){
		trucks.add(truck);
	}
	
	/**
	 * Removes Truck object from the manifest
	 * @author Jonathon Meyer
	 * @param truck Truck object to be removed.
	 */
	public void removeTruck(Truck truck) {
		for (int i = 0; i < trucks.size(); i++) {
			if (trucks.get(i) == truck ) {
				trucks.remove(i);
			}
		}
	}
	
	/**
	 * Gets the list of all Truck in the manifest object.
	 * @author Jonathon Meyer
	 * @return Returns a list of trucks from the manifest.
	 */
	public List<Truck> getTrucks() {
		return trucks;
	}
}
