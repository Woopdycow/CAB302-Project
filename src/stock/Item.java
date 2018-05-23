package stock;

public class Item {
	private String name;
	private double cost;
	private double price;
	private int reorderPoint;
	private int reorderAmount;
	private double temp;

	/**
	 * Constructor Method
	 * @author Bryan Kassulke
	 */
	public Item(String name, double cost, double price, int reorderPoint, int reorderAmount) {
		name = this.name;
		cost = this.cost;
		price = this.price;
		reorderPoint = this.reorderPoint;
		reorderAmount = this.reorderAmount;
	}
	
	public Item(String name, double cost, double price, int reorderPoint, int reorderAmount, double temp) {
		name = this.name;
		cost = this.cost;
		price = this.price;
		reorderPoint = this.reorderPoint;
		reorderAmount = this.reorderAmount;
		temp = this.temp;
	}
	
	public String getName() {
		return name;
	}
	
	public double getCost() {
		return cost;
	}
	
	public double getPrice() {
		return price;
	}
	
	public int getReorderPoint() {
		return reorderPoint;
	}
	
	public int getReorderAmount() {
		return reorderAmount;
	}
	
	public double getTemp() {
		return temp;
	}

}