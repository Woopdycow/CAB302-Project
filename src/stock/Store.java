package stock;

import java.util.ArrayList;
import java.util.List;

import delivery.*;

public class Store {	
	private static final double STARTINGCAPITAL = 100000.00;
	private static String name;
	private static double capital;
	private static List<Item> itemIdentities;
	private static Stock inventory;
	

	/**
	 * Constructor Method
	 * @author Bryan Kassulke
	 */
	protected Store() {
		name = "SUPERMART";
		capital = STARTINGCAPITAL;
		inventory = new Stock();
		itemIdentities = new ArrayList<Item>();
	}

	/**
	 * Creates the only and only instance of the Store object.
	 * @author Bryan Kassulke
	 */
	private static class StoreHolder {
		private final static Store INSTANCE = new Store();
	}
	
	/**
	 * Global access point for the Store object.
	 * @return Returns single instance of store.
	 */
	public static Store getInstance() {
		return StoreHolder.INSTANCE;
	}
	
	/**
	 * Adds an item to the store's inventory of a given quantity.
	 * @author Bryan Kassulke
	 * @param imported Item to be added to the store's inventory.
	 * @param amount Amount of the item to be added.
	 */
	public void addItem(Item imported, int amount) {
		inventory.addItem(imported, amount);
		itemIdentities.add(imported);
	}
	
	/**
	 * Remove an item from the store's inventory.
	 * @author Bryan Kassulke
	 * @param removed Item to be removed from the inventory.
	 * @param amount Quantity of the item to be removed.
	 */
	public void removeItem(Item removed, int amount) {
		try {
			inventory.removeItem(removed, amount);
		} catch(Exception e) {
			//e.getMessage()
		}
	}
	
	/**
	 * Get the quantity of an item within the store's inventory.
	 * @author Bryan Kassulke
	 * @param item The item whose quantity is returned.
	 * @return Returns an integer equal to the amount of the item within the inventory.
	 */
	public int getItemQuantity(Item item) {
		return inventory.getQuantity(item);
	}
	
	/**
	 * Loads new items from external list into the store's inventory. Overrides existing item with shared name but preserves quantity.
	 * @author Bryan Kassulke
	 * @param newItems List of new items to be added.
	 * @throws StockException 
	 */
	public void loadItemProperties(List<Item> newItems) throws StockException {
		try {
			int temp = 0;
			for (Item k : newItems) {
				for (Item j : inventory.getItemSet()) {
					if (k.getName() == j.getName()) {
						temp = inventory.getQuantity(j);
						inventory.removeItem(j, inventory.getQuantity(j));
						inventory.addItem(k, temp);
						itemIdentities.remove(j);
						itemIdentities.add(k);
					}
				}
			}
		} catch (Exception e) {
			//e.getMessage();
		}
	}
	
	/**
	 * Loads weekly sales log to the store. Adjusts stock levels and capital accordingly.
	 * @author Bryan Kassulke
	 * @param sales List of values to be loaded as item names and quantities.
	 * @throws StockException 
	 */
	public void loadSalesLog(List<Object[]> sales) throws StockException {
		int grossProfit = 0;
		Item soldItem = new Item(" ", 0.0, 0.0, 0, 0);
		for (Object[] k : sales) {
			for (Item j : inventory.getItemSet()) {
				if (j.getName() == k[0]) {
					soldItem = j;
				}
			}
			inventory.removeItem(soldItem, (int)k[1]);
			grossProfit += (soldItem.getPrice() * (int)k[1]);
		}
		capital += grossProfit;
	}
	
	/**
	 * Receives and processes a manifest as if it were delivered. Pay's up front and replenishes stock levels.
	 * @author Bryan Kassulke
	 * @param delivery Manifest to be processed.
	 * @throws DeliveryException Throw if there are miscellaneous errors accessing information.
	 */
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
					// Manifest contains no trucks!
					throw new DeliveryException("Loaded manifest's trucks are missing cargo.");
				}
				// Add the truck expense
				grossCost += (Math.round(i.getCost() * 100.0) / 100.0);
			}
		} else {
			// Manifest contains no trucks!
			throw new DeliveryException("Loaded manifest contains no trucks.");
		}
		capital -= grossCost;
	}
	
	/**
	 * Generates a manifest to be delivered to the store based on the levels of inventory.
	 * @author Bryan Kassulke
	 * @return Returns a manifest for stock reusable.
	 * @throws StockException 
	 * @throws TruckOverloadException Trucks have been loaded with more stock than they can fit.
	 */
	public Manifest getManifest() throws StockException {
		Manifest resupply = new Manifest();
		Stock reorder = getReorder();
		List<Item> dryGoods = new ArrayList<Item>();
		List<Item> coldGoods = new ArrayList<Item>();
		List<Item> sortedColdest = new ArrayList<Item>();
		Item coldestItem;
		//Split items based on required transport
		for (Item k : reorder.getItemSet()) {
			if (k.isDryGoods()) {
				dryGoods.add(k);
			} else {
				coldGoods.add(k);
			}
		}
		//Produce new Stock of sorted cold items: sortedColdest.
		if (!coldGoods.isEmpty()) {
			coldestItem = coldGoods.get(0);
			for (int i = 0; i < coldGoods.size(); i++) {
				for (Item k : coldGoods) {
					if (k.getTemp() <= coldestItem.getTemp()) {
						coldestItem = k;
					}
				}
				sortedColdest.add(coldestItem);
			}
		}
		
		int refrigeratedCapacity = new RefrigeratedTruck().getCapacity();
		int ordinaryCapacity = new OrdinaryTruck().getCapacity();
		Stock cargo;
		//While there are items left in the reorder
		while (reorder.getTotal() > 0) {
			int temp = 0;
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
						temp = reorder.getQuantity(k);
						//Remove all of the item from the reorder list
						reorder.removeItem(k, reorder.getQuantity(k));
						//Add the reorder quantity of the item to the new cargo
						cargo.addItem(k, temp);
						//Remove the current coldest item from the sorted list
						removed.add(k);
						//Truck will now require cooling
						requiresCooling = true;
					//Otherwise add as much as you can
					} else if (cargo.getTotal() < refrigeratedCapacity){
						//Add as much of the item you can to the new cargo
						reorder.removeItem(k, refrigeratedCapacity - cargo.getTotal());
						cargo.addItem(k, refrigeratedCapacity - cargo.getTotal());
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
							temp = reorder.getQuantity(k);
							//Remove all of the item from the reorder list
							reorder.removeItem(k, reorder.getQuantity(k));
							//Add the reorder quantity of the item to the new cargo
							cargo.addItem(k, temp);
							//Remove the current item from the sorted list
							removed.add(k);
						//Otherwise add as much as you can
						} else if (cargo.getTotal() < refrigeratedCapacity) {
							//Add as much of the item you can to the new cargo
							reorder.removeItem(k, refrigeratedCapacity - cargo.getTotal());
							cargo.addItem(k, refrigeratedCapacity - cargo.getTotal());
						}
					}
					dryGoods.removeAll(removed);
				} else {
					//If there is no cold goods already in the cargo, ordinary trucks are used.
					List<Item> removed = new ArrayList<Item>();
					for (Item k : dryGoods) {
						//If there is room for all of it, add it all
						if ((cargo.getTotal() + reorder.getQuantity(k)) < ordinaryCapacity) {
							temp = reorder.getQuantity(k);
							//Remove all of the item from the reorder list
							reorder.removeItem(k, reorder.getQuantity(k));
							//Add the reorder quantity of the item to the new cargo
							cargo.addItem(k, temp);
							//Remove the current item from the sorted list
							removed.add(k);
						//Otherwise add as much as you can
						} else if (cargo.getTotal() < ordinaryCapacity){
							reorder.removeItem(k, ordinaryCapacity - cargo.getTotal());
							//Add as much of the item you can to the new cargo
							cargo.addItem(k, ordinaryCapacity - cargo.getTotal());
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
		for (Truck k : resupply.getTrucks()) {
			System.out.println("Truck");
			for (Item j : k.getCargo().getItemSet()) {
				System.out.println(j.getName() + ":" + k.getCargo().getQuantity(j));
			}
		}
		
		return resupply;
	}
	
	/**
	 * Generates Stock object of items and quantities that need to be reordered.
	 * @author Bryan Kassulke
	 * @return Returns Stock object of reorder.
	 */
	public Stock getReorder() {
		Stock reorder = new Stock();
		for (Item k : inventory.getItemSet()) {
			if (inventory.getQuantity(k) < k.getReorderPoint()) {
				reorder.addItem(k, k.getReorderAmount());
			}
		}
		return reorder;
	}
	
	/**
	 * Gets an item object when only the name is accessible.
	 * @author Bryan Kassulke
	 * @param name Name of the item to be returned.
	 * @return The item of which the name belongs to.
	 */
	public Item getItemByName(String name) {
		for (Item k : inventory.getItemSet()) {
			if (k.getName() == name) {
				return k;
			}
		}
		return null;
	}
	
	/**
	 * Returns the name of the Store.
	 * @author Bryan Kassulke
	 * @return Returns the store's name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the store's capital.
	 * @author Bryan Kassulke
	 * @return Returns the captial of the store.
	 */
	public double getCapital() {
		return capital;
	}
	
	/**
	 * Gets the Stock object that represents the store's inventory.
	 * @author Bryan Kassulke
	 * @return Returns a stock object of the store's inventory.
	 */
	public Stock getStock() {
		return inventory;
	}	
}