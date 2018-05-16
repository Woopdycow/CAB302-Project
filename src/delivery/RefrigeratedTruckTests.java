package delivery;

import stock.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class RefrigeratedTruckTests {
	
	Truck myTruck;
	
	@Before
	public void setUp() {
		myTruck = null;
	}

	@Test
	public void testConstruction() {
		myTruck = new RefrigeratedTruck();
	}
	
	@Test
	public void testCargo() {
		String outputTest = "";
		myTruck = new RefrigeratedTruck();
		Stock stock1 = new Stock();
		Item item1 = new Item("Vegemite", 10.0, 15.0, 320, 600);
		Item item2 = new Item("Cheese", 14.6, 16.4, 300, 600);
		Item item3 = new Item("Grapes", 11.2, 14.3, 250, 600);
		stock1.put(item1, 4);
		stock1.put(item2, 3);
		stock1.put(item3, 7);
		myTruck.setCargo(stock1);
		for (Item getItem : myTruck.getCargo()) {
			for (int i = 0; i <= stock1.get(getItem); i++)
				outputTest += getItem.getName();
		}
		assertEquals(outputTest, "VegemiteVegemiteVegemiteVegemiteCheeseCheeseCheeseGrapesGrapesGrapesGrapesGrapesGrapesGrapes");
	}
	
	@Test
	public void testGetTemp() {
		myTruck = new RefrigeratedTruck(-2.0);
		assertEquals(myTruck.getTemp(), -2.0);
		pow(1,1);
	}
	
	@Test
	public void testGetCost() {
		myTruck = new RefrigeratedTruck();
		double testPrice = (900 + (200 * (java.lang.Math.pow(0.7, myTruck.getTemp() * 0.2))));
		assertEquals(myTruck.getCost, testPrice);
	}
	
	@Test
	public void testGetCapacity() {
		myTruck = new RefrigeratedTruck();
		assertEquals(myTruck.getCapacity(), 800);
	}
	
	@Test(expected = Exception.TruckOverLoadException)
	public void testOverload() {
		myTruck = new RefrigeratedTruck();
		Stock stock1 = new Stock();
		Item item1 = new Item("Cheese", 10.0, 15.0, 320, 600);
		stock1.put(item1, 801);
		myTruck.setCargo(stock1);
	}

}
