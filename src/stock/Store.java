package stock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import delivery.*;

public class Store {	
	private static final double STARTINGCAPITAL = 100000.00;
	private static String name;
	private static double capital;
	private static Stock inventory;
	

	/**
	 * Constructor Method
	 * @author Bryan Kassulke
	 */
	protected Store() {
		name = "SUPERMART";
		capital = STARTINGCAPITAL;
		inventory = new Stock();
	}

	private static class StoreHolder {
		private final static Store INSTANCE = new Store();
	}
	
	public void addItem(Item imported, int amount) {
		inventory.addItem(imported, amount);
	}
	
	public int getItemQuantity(Item item) {
		return inventory.getQuantity(item);
	}
	
	public void loadManifest(Manifest delivery) throws DeliveryException {
		double grossCost = 0.0;
		// If the manifest is not empty
		if (!delivery.getTrucks().isEmpty()) {
			// For each truck in the manifest
			for (Truck i : delivery.getTrucks()) {
				// If the truck has cargo
				if (i.getCargo() != null) {
					// For each item in truck's cargo
					for (Item j : i.getCargo().getItemSet()) {
						// Total the price of all items
						double tempCost = j.getCost() * i.getCargo().getQuantity(j);
						grossCost += Math.round(tempCost * 100.0) / 100.0;
					}
					// Unload and unpack into store
					inventory.merge(i.getCargo());
				} else {
					// Truck has no cargo!
					throw new DeliveryException();
				}
				// Add the truck expense
				grossCost += (Math.round(i.getCost() * 100.0) / 100.0);
			}
		} else {
			// Manifest contains no trucks!
			throw new DeliveryException();
		}
		capital -= grossCost;
	}
	
	public Manifest getManifest() throws TruckOverloadException {
		Manifest resupply = new Manifest();
		Stock reorder = getReorder();
		List<Item> dryGoods = new ArrayList<Item>();
		Stock coldGoods = new Stock();
		List<Item> sortedColdest = new ArrayList<Item>();
		Item coldestItem;
		//Split items based on required transport
		for (Item k : reorder.getItemSet()) {
			if (k.isDryGoods()) {
				dryGoods.add(k);
			} else {
				coldGoods.addItem(k, reorder.getQuantity(k));
			}
		}
		//Produce new Stock of sorted cold items: sortedColdest.
		coldestItem = coldGoods.getItemSet().iterator().next();
		for (int i = 0; i < coldGoods.getItemSet().size(); i++) {
			for (Item k : coldGoods.getItemSet()) {
				if (k.getTemp() < coldestItem.getTemp()) {
					coldestItem = k;
				}
			}
			sortedColdest.add(coldestItem);
		}

		int refrigeratedCapacity = new RefrigeratedTruck().getCapacity();
		int ordinaryCapacity = new OrdinaryTruck().getCapacity();
		Stock cargo;
		//While there are items left in the reorder
		while (reorder.getTotal() > 0) {
			boolean requiresCooling = false;
			//Create new stock in ArrayList
			cargo = new Stock();
			//If there are still cold goods to be loaded
			if (sortedColdest.size() > 0) {
				//Start from the first
				List<Item> removed = new ArrayList<Item>();
				for (Item k : sortedColdest) {
					//If there is room for all of it, add it all
					if ((cargo.getTotal() + reorder.getQuantity(k)) < refrigeratedCapacity) {
						//Add the reorder quantity of the item to the new cargo
						cargo.addItem(k, reorder.getQuantity(k));
						//Remove the current coldest item from the sorted list
						removed.add(k);
						//Remove all of the item from the reorder list
						reorder.removeItem(k, reorder.getQuantity(k));
						//Truck will now require cooling
						requiresCooling = true;
					//Otherwise add as much as you can
					} else if (cargo.getTotal() < refrigeratedCapacity){
						//Add as much of the item you can to the new cargo
						cargo.addItem(k, (refrigeratedCapacity - cargo.getTotal()));
						//Remove only as much as you added to the new stock
						reorder.removeItem(k, (refrigeratedCapacity - cargo.getTotal()));
						//Truck will now require cooling
						requiresCooling = true;
					}
				}
				sortedColdest.removeAll(removed);
			}
			//If there are dry goods still to be loaded after the cold items have
			if (dryGoods.size() > 0) {
				//Is there already cold items in the cargo?
				if (requiresCooling) {
					//For each of the dryGoods left, are put into cold trucks where possible
					List<Item> removed = new ArrayList<Item>();
					for (Item k : dryGoods) {
						//If there is room for all of it, add it all
						if ((cargo.getTotal() + reorder.getQuantity(k)) < refrigeratedCapacity) {
							//Add the reorder quantity of the item to the new cargo
							cargo.addItem(k, reorder.getQuantity(k));
							//Remove the current item from the sorted list
							removed.add(k);
							//Remove all of the item from the reorder list
							reorder.removeItem(k, reorder.getQuantity(k));
						//Otherwise add as much as you can
						} else if (cargo.getTotal() < refrigeratedCapacity){
							//Add as much of the item you can to the new cargo
							cargo.addItem(k, (refrigeratedCapacity - cargo.getTotal()));
							//Remove only as much as you added to the new stock
							reorder.removeItem(k, (refrigeratedCapacity - cargo.getTotal()));
						}
					}
					dryGoods.removeAll(removed);
				} else {
					//If there is no cold goods already in the cargo, ordinary trucks are used.
					List<Item> removed = new ArrayList<Item>();
					for (Item k : dryGoods) {
						//If there is room for all of it, add it all
						if ((cargo.getTotal() + reorder.getQuantity(k)) < ordinaryCapacity) {
							//Add the reorder quantity of the item to the new cargo
							cargo.addItem(k, reorder.getQuantity(k));
							//Remove the current item from the sorted list
							removed.add(k);
							//Remove all of the item from the reorder list
							reorder.removeItem(k, reorder.getQuantity(k));
						//Otherwise add as much as you can
						} else if (cargo.getTotal() < ordinaryCapacity){
							//Add as much of the item you can to the new cargo
							cargo.addItem(k, (ordinaryCapacity - cargo.getTotal()));
							//Remove only as much as you added to the new stock
							reorder.removeItem(k, (ordinaryCapacity - cargo.getTotal()));
						}
					}
					dryGoods.removeAll(removed);
				}
			}
			//cargo has been properly populated with items.
			Truck newTruck;
			if (requiresCooling) {
				newTruck = new RefrigeratedTruck();
				newTruck.setCargo(cargo);
			} else {
				newTruck = new OrdinaryTruck();
				newTruck.setCargo(cargo);
			}
			resupply.addTruck(newTruck);
		}

			
		return resupply;
	}
	
	public Stock getReorder() {
		Stock reorder = new Stock();
		for (Item k : inventory.getItemSet()) {
			if (inventory.getQuantity(k) < k.getReorderPoint()) {
				reorder.addItem(k, k.getReorderAmount());
			}
		}
		return reorder;
	}
	
	public static Store getInstance() {
		return StoreHolder.INSTANCE;
	}
	
	public String getName() {
		return name;
	}
	
	public double getCapital() {
		return capital;
	}
	
	public Stock getStock() {
		return inventory;
	}	
}
