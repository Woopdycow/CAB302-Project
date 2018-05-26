package stock;

public class Item {
	private String name;
	private double cost;
	private double price;
	private int reorderPoint;
	private int reorderAmount;
	private double temp;
	private boolean dryGoods;

	/**
	 * Constructor Method for when no temperature is specified.
	 * @author Bryan Kassulke
	 * @param name The name of the item.
	 * @param cost Cost(Manufacturing) price of the item.
	 * @param price Sell price of the item.
	 * @param reorderPoint Threshold for items to be reordered.
	 * @param reorderAmount Quantity of items to order when below reorder threshold.
	 */
	public Item(String name, double cost, double price, int reorderPoint, int reorderAmount) {
		this.name = name;
		this.cost = cost;
		this.price = price;
		this.reorderPoint = reorderPoint;
		this.reorderAmount = reorderAmount;
		this.temp = 11.0;
		this.dryGoods = true;
	}
	
	/**
	 * Constructor Method for when temperature is specified.
	 * @author Bryan Kassulke
	 * @param name The name of the item.
	 * @param cost Cost(Manufacturing) price of the item.
	 * @param price Sell price of the item.
	 * @param reorderPoint Threshold for items to be reordered.
	 * @param reorderAmount Quantity of items to order when below reorder threshold.
	 * @param temp Temperature of which the item must be stored below.
	 */
	public Item(String name, double cost, double price, int reorderPoint, int reorderAmount, double temp) {
		this.name = name;
		this.cost = cost;
		this.price = price;
		this.reorderPoint = reorderPoint;
		this.reorderAmount = reorderAmount;
		this.temp = temp;
		this.dryGoods = false;
	}
	
	/**
	 * @author Bryan Kassulke
	 * @return Returns name of the item.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @author Bryan Kassulke
	 * @return Returns cost(manufacturing) of the item.
	 */
	public double getCost() {
		return cost;
	}
	
	/**
	 * @author Bryan Kassulke
	 * @return Returns sell price of the item.
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * @author Bryan Kassulke
	 * @return Returns reorder point of the item.
	 */
	public int getReorderPoint() {
		return reorderPoint;
	}
	
	/**
	 * @author Bryan Kassulke
	 * @return Returns reorder amount of the item.
	 */
	public int getReorderAmount() {
		return reorderAmount;
	}
	
	/**
	 * @author Bryan Kassulke
	 * @return Returns maximum transport temperature of the item.
	 */
	public double getTemp() {
		return temp;
	}
	
	/**
	 * @author Bryan Kassulke
	 * @return Returns boolean of whether the item is dry goods or not.
	 */
	public boolean isDryGoods() {
		return dryGoods;
	}

}