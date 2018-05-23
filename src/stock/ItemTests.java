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
			"Ice Cream"
		};
	
	private static double[] itemManuCost = new double[] {
			105.46,
			58.4, 
			58.05,
			52.4,
			124.08,
			163.92,
			125.99,
			113.07,
			136.67,
			9.61
	};
	
	private static double[] itemSellPrice = new double[] {
			116.29,
			176.45,
			55.46,
			101.08,
			153.73,
			157.52,
			187.61,
			125.39,
			153.86,
			77.7
	};
	
	private static int[] itemReorderPoint = new int[] {
		783,
		684,
		785,
		156,
		568,
		294,
		688,
		939,
		239,
		838
	};
	
	private static int[] itemReorderAmount = new int[] {
		774,
		921,
		1548,
		197,
		1887,
		909,
		1502,
		515,
		1262,
		341
	};
	
	private static double[] itemTemp = new double[] {
		-13.17,
		4.84,
		-17.37,
		-5.44,
		-18.34,
		-1.13,
		-12.1,
		-16.77,
		4.89,
		-3
	};
	
	private static String randItemName() {
		return itemNames[random.nextInt(itemNames.length)];
	}
	
	private static double randManuCost() {
		return itemManuCost[random.nextInt(itemManuCost.length)];
	}
	
	private static double randSellPrice() {
		return itemSellPrice[random.nextInt(itemSellPrice.length)];
	}
	
	private static int randReorderPoint() {
		return itemReorderPoint[random.nextInt(itemReorderPoint.length)];
	}
	
	private static int randReorderAmount() {
		return itemReorderAmount[random.nextInt(itemReorderAmount.length)];
	}
	
	private static double randTemp() {
		return itemTemp[random.nextInt(itemTemp.length)];
	}
	
	Item myItem;
	
	@Before
	public void setUp() {
		myItem = null;
	}

	@Test 
	public void testConstruction() {
		String name = "Milk";
		double manuCost = 106.22;
		double sellPrice = 121.61;
		int reorderPoint = 320;
		int reorderAmount = 600;
		double temp = 13.00;
		
		myItem = new Item(name, manuCost, sellPrice, reorderPoint, reorderAmount, temp);
	}
	@Test
	public void testName() {
		String itemName = randItemName();
		myItem = new Item(itemName, 106.22, 121.61, 320, 600);
		assertEquals(itemName, myItem.getName());
	}
	
	@Test
	public void testCost() {
		double manuCost = randManuCost();
		myItem = new Item("Vegemite", manuCost, 121.61, 320, 600);
		assertEquals(manuCost, myItem.getCost(), 0.0);
	}
	
	@Test
	public void testSell() {
		double sellPrice = randSellPrice();
		myItem = new Item("Vegemite", 106.22, sellPrice, 320, 600);
		assertEquals(sellPrice, myItem.getPrice(), 0.0);
	}
	
	@Test
	public void testReorderPoint() {
		int reorderPoint = randReorderPoint();
		myItem = new Item("Vegemite", 106.22, 121.61, reorderPoint, 600);
		assertEquals(reorderPoint, myItem.getReorderPoint(), 0.0);
	}
	
	@Test
	public void testReorderAmount() {
		int reorderAmount = randReorderAmount();
		myItem = new Item("Vegemite", 106.22, 121.61, 320, reorderAmount);
		assertEquals(reorderAmount, myItem.getReorderAmount(), 0.0);
	}
	
	@Test
	public void testTemperature() {
		double temp = randTemp();
		myItem = new Item("Milk", 32.26, 13.82, 680, 1282, temp);
		assertEquals(temp, myItem.getTemp(), 0.0);
	}
}
