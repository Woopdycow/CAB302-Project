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
		this.name = name;
		this.cost = cost;
		this.price = price;
		this.reorderPoint = reorderPoint;
		this.reorderAmount = reorderAmount;
	}
	
	public Item(String name, double cost, double price, int reorderPoint, int reorderAmount, double temp) {
		this.name = name;
		this.cost = cost;
		this.price = price;
		this.reorderPoint = reorderPoint;
		this.reorderAmount = reorderAmount;
		this.temp = temp;
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