package stock;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class ItemTests {

	private static Random random = new Random();
	private static String[] itemNames = new String[] {
			"Vegemite",
			"Peanut Butter",
			"Tomato Soup",
			"Milk",
			"Ice Cream",
		};
	
	private static double[] itemManuCost = new double[] {
			
	}
	
	private static String randItemName() {
		return itemNames[random.nextInt(itemNames.length)];
	}
	
	private static Item myItem = new Item();
	
	@Test
	public void setUpItem() {
		Item myItem;
	}

	@Test 
	public void testConstruction() {
		String name = "John Doe";
		double manuCost = 106.22;
		double sellAmount = 121.61;
		int reorderPoint = 320;
		double reorderAmount = 600;
		double temp = 13.00;
		
		myItem = new Item(name, manuCost, sellAmount, reorderPoint, reorderAmount, temp);
	}
	@Test
	public void testName() {
		String itemName = randItemName();
		//
	}
}
