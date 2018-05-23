package stock;
import java.util.HashMap;
import java.util.Map;

public class Stock {
	private HashMap<Item, Integer> contents;

	/**
	 * Constructor Method
	 * @author Bryan Kassulke
	 */
	public Stock() {
		contents = new HashMap<Item, Integer>();
	}
	
	public void addItem(Item newItem, int quantity) {
		int amount = quantity;
		if (contents.containsKey(newItem)) {
			amount += contents.get(newItem);
		}	
		contents.put(newItem, amount);
	}
	
	public void merge(Stock copiedStock) {		
		for (Item key : copiedStock.getContents().keySet()) {
			this.addItem(key, copiedStock.getContents().get(key));
		}
	}
	
	public HashMap<Item, Integer> getContents(){
		return contents;
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
