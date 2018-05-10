package mart;

public class Item {
	private String name;
	private double manufacturingCost;
	private double sellPrice;
	private int reorderPoint;
	private int reorderAmount;
	private double tempMaximum;
	

	public Item(String name, double manufacturingCost, double sellPrice, int reorderPoint, int reorderAmount, double tempMaximum) {
		this.name = name;
		this.manufacturingCost = manufacturingCost;
		this.sellPrice = sellPrice;
		this.reorderPoint = reorderPoint;
		this.reorderAmount = reorderAmount;
		this.tempMaximum = tempMaximum;
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getManufacturingCost() {
		return this.manufacturingCost;
	}
	
	public double getSellPrice() {
		return this.sellPrice;
	}
	
	
	public int getReorderPoint() {
		return this.reorderPoint;
	}
	
	public int getReorderAmount() {
		return this.reorderAmount;
	}
	
	public double getTempMaximum() {
		return this.tempMaximum;
	}

}
