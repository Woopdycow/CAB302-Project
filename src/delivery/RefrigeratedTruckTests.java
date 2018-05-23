package delivery;

import stock.*;
import org.junit.Assert.*;
import org.junit.*;

import static org.junit.Assert.*;

public class RefrigeratedTruckTests {
	
	Truck myTruck;
	double cost;
	int cargoCapacity;
	Stock cargo;
	
	@Before
	public void setUp() {
		myTruck = null;
	}

	@Test
	public void testConstruction() {
		myTruck = new RefrigeratedTruck(cost, cargoCapacity, cargo);
	}
	
	@Test
	public void testCargo() {
		String outputTest = "";
		myTruck = new RefrigeratedTruck(cost, cargoCapacity, cargo);
		Stock stock1 = new Stock();
		Item item1 = new Item("Vegemite", 10.0, 15.0, 320, 600);
		Item item2 = new Item("Cheese", 14.6, 16.4, 300, 600, 5.0);
		Item item3 = new Item("Grapes", 11.2, 14.3, 250, 600, 10.0);
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
	public void testGetTempUpdate() {
		myTruck = new RefrigeratedTruck(cost, cargoCapacity, cargo);
		Stock myStock = new Stock();
		Item myItem = new Item("Cheese", 14.6, 16.4, 300, 600, 5.0);
		myStock.put(myItem, 1);
		myTruck.setCargo(myStock);
		assertEquals(myTruck.getTemp(), 5.0, 0.0);
	}
	
	@Test
	public void testGetCost() {
		myTruck = new RefrigeratedTruck(cost, cargoCapacity, cargo);
		double testPrice = (900 + (200 * (java.lang.Math.pow(0.7, myTruck.getTemp() * 0.2))));
		assertEquals(myTruck.getCost(), testPrice, 0.0);
	}
	
	@Test
	public void testGetCapacity() {
		myTruck = new RefrigeratedTruck(cost, cargoCapacity, cargo);
		assertEquals(800, myTruck.getCapacity());
	}
	
	@Test(expected = Exception.TruckOverLoadException)
	public void testOverload() {
		myTruck = new RefrigeratedTruck(cost, cargoCapacity, cargo);
		Stock stock1 = new Stock();
		Item item1 = new Item("Cheese", 10.0, 15.0, 320, 600);
		stock1.put(item1, 801);
		myTruck.setCargo(stock1);
	}

}
