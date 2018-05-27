package stock;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Stock {
	public Map<Item, Integer> contents;

	/**
	 * Constructor Method Stock object.
	 * @author Bryan Kassulke
	 */
	public Stock() {
		contents = new LinkedHashMap<Item, Integer>();
	}
	
	/**
	 * Adds new item/s to the stock object with a particular quantity. Increments quantity of existing item if it exists.
	 * @author Bryan Kassulke
	 * @param newItem Item to be added.
	 * @param quantity Amount of the added to add.
	 */
	public void addItem(Item newItem, int quantity) {
		int amount = quantity;
		if (contents.containsKey(newItem)) {
			amount += contents.get(newItem);
		}	
		contents.put(newItem, amount);
	}
	
	/**
	 * Removes item/s from the stock object with a particular quantity. If the amount is more than the Stock's quantity, then it is removed.
	 * @author Bryan Kassulke
	 * @param item Item to be removed.
	 * @param amount Amount of the added to removed.
	 * @throws StockException 
	 */
	public void removeItem(Item item, int amount) throws StockException {
		if (contents.get(item) > amount) {
			contents.put(item, contents.get(item) - amount);
		} else if (contents.get(item) == amount) {
			contents.remove(item);
		} else {
			throw new StockException("Attempted to remove more items than were available.");
		}
	}

	/**
	 * Adds all item entries from another Stock object to this one.
	 * @author Bryan Kassulke
	 * @param copiedStock Stock item to be duplicated.
	 */
	public void merge(Stock copiedStock) {		
		for (Item key : copiedStock.getItemSet()) {
			this.addItem(key, copiedStock.getQuantity(key));
		}
	}
	
	/**
	 * Returns a Set of all items in the Stock. (No particular order)
	 * @author Bryan Kassulke
	 * @return The set of items in stock object.
	 */
	public Set<Item> getItemSet(){
		return contents.keySet();
	}
	
	/**
	 * Returns the quantity of the item in the Stock object.
	 * @author Bryan Kassulke
	 * @param thisItem The item whose quantity is being queried.
	 * @return Returns the amount of the item within the Stock.
	 */
	public int getQuantity(Item thisItem) {
		if (contents.containsKey(thisItem)) {
			return contents.get(thisItem);
		}	
		return 0;
	}
	
	/**
	 * Checks if an item is within the Stock.
	 * @author Bryan Kassulke
	 * @param thisItem The item whose existence is to be confirmed.
	 * @return Returns a boolean as to whether or not the item exists in the Stock.
	 */
	public boolean itemExists(Item thisItem) {
		return contents.containsKey(thisItem);
	}
	
	/**
	 * Calculates the amount of individual items within the Stock.
	 * @author Bryan Kassulke
	 * @return Returns the number of items within stock.
	 */
	public int getTotal(){
		int total = 0;
		for (int value : contents.values()) {
			total += value;
		}
		return total;
	}
}
