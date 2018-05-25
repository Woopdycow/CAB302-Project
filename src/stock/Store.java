package stock;

import java.util.ArrayList;
import java.util.List;

import delivery.*;

public class Store {	
	private static final double STARTINGCAPITAL = 1000000.00;
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
						grossCost += (j.getCost() * i.getCargo().getQuantity(j));
					}
					// Unload and unpack into store
					inventory.merge(i.getCargo());
				} else {
					// Truck has no cargo!
					throw new DeliveryException();
				}
				// Add the truck expense
				grossCost += i.getCost();
			}
		} else {
			// Manifest contains no trucks!
			throw new DeliveryException();
		}
		capital -= grossCost;
		
	}
	
	public Manifest getManifest() {
		Manifest resupply = new Manifest();
		Stock reorder = getReorder();
		Stock dryGoods = new Stock();
		List<Item> coldGoods = new ArrayList<Item>();
		Stock sortedColdest = new Stock();
		Item coldestItem;
		//Split items based on required transport
		for (Item k : reorder.getItemSet()) {
			if (k.isDryGoods()) {
				dryGoods.addItem(k, reorder.getQuantity(k));
			} else {
				coldGoods.add(k);
			}
		}
		//Produce new Stock of sorted cold items: sortedColdest.
		coldestItem = coldGoods.get(0);
		for (int i = 0; i < coldGoods.size(); i++) {
			for (int j = 0; j < coldGoods.size(); i++) {
				if (coldGoods.get(j).getTemp() < coldestItem.getTemp()) {
					coldestItem = coldGoods.get(j);
				}
			}
			sortedColdest.addItem(coldestItem, reorder.getQuantity(coldestItem) );
			coldGoods.remove(coldestItem);
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
