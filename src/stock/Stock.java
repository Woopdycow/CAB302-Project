package stock;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Stock {
	public Map<Item, Integer> contents;

	/**
	 * Constructor Method
	 * @author Bryan Kassulke
	 */
	public Stock() {
		contents = new LinkedHashMap<Item, Integer>();
	}
	
	public void addItem(Item newItem, int quantity) {
		int amount = quantity;
		if (contents.containsKey(newItem)) {
			amount += contents.get(newItem);
		}	
		contents.put(newItem, amount);
	}
	
	public void removeItem(Item item, int amount) {
		if (contents.get(item) > amount) {
			contents.put(item, contents.get(item) - amount);
		} else {
			contents.remove(item);
		}
	}

	public void merge(Stock copiedStock) {		
		for (Item key : copiedStock.getItemSet()) {
			this.addItem(key, copiedStock.getQuantity(key));
		}
	}
		
	public Set<Item> getItemSet(){
		return contents.keySet();
	}
	
	public int getQuantity(Item thisItem) {
		if (contents.containsKey(thisItem)) {
			return contents.get(thisItem);
		}	
		return 0;
	}
	
	public boolean itemExists(Item thisItem) {
		return contents.containsKey(thisItem);
	}
	
	public int getTotal(){
		int total = 0;
		for (int value : contents.values()) {
			total += value;
		}
		return total;
	}
}
