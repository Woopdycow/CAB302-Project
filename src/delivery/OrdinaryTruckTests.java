package delivery;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import stock.*;

import org.junit.Test;

import stock.Item;
import stock.Stock;

public class OrdinaryTruckTests {

	OrdinaryTruck myTruck;
	double cost;
	Stock cargo;
	double temp;
	int cargoCapacity;
	
	@Before
	public void setUp() {
		myTruck = null;
	}

	@Test
	public void testConstruction() {
		myTruck = new OrdinaryTruck(cost, cargoCapacity, cargo);
	}
	
	@Test
	public void testCargo() {
		String outputTest = "";
		myTruck = new OrdinaryTruck(cost, cargoCapacity, cargo);
		Stock stock1 = new Stock();
		Item item1 = new Item("Vegemite", 10.0, 15.0, 320, 600);
		Item item2 = new Item("Cheese", 14.6, 16.4, 300, 600);
		Item item3 = new Item("Grapes", 11.2, 14.3, 250, 600);
		stock1.Add(item1, 4);
		stock1.Add(item2, 3);
		stock1.Add(item3, 7);
		myTruck.setCargo(stock1);
		for (Item getItem : myTruck.getCargo().getItem()) {
			for (int i = 0; i <= stock1.get(getItem); i++)
				outputTest += getItem.getName();
		}
		assertEquals(outputTest, "VegemiteVegemiteVegemiteVegemiteCheeseCheeseCheeseGrapesGrapesGrapesGrapesGrapesGrapesGrapes");
	}
	
	@Test
	public void testGetCost() {
		myTruck = new OrdinaryTruck(cost, cargoCapacity, cargo);
		Stock stock1 = new Stock();
		Item item1 = new Item("Vegemite", 10.0, 15.0, 320, 600);
		Item item2 = new Item("Cheese", 14.6, 16.4, 300, 600, 10.0);
		Item item3 = new Item("Grapes", 11.2, 14.3, 250, 600, 5.0);
		stock1.Add(item1, 3);
		stock1.Add(item2, 5);
		stock1.Add(item3, 8);
		myTruck.setCargo(stock1);
		assertEquals(myTruck.getCost(), (750 + (0.25 * 192.6), 0.0);		
	}
	
	@Test
	public void testGetCapacity() {
		myTruck = new OrdinaryTruck(cost, cargoCapacity, cargo);
		assertEquals(1000, myTruck.getCapacity(), 0.0);
	}
	
	@Test(expected = Exception.TruckOverLoadException)
	public void testOverload() {
		myTruck = new OrdinaryTruck(cost, cargoCapacity, cargo);
		Stock stock1 = new Stock();
		Item item1 = new Item("Vegemite", 10.0, 15.0, 320, 600);
		stock1.Add(item1, 1001);
		myTruck.setCargo(stock1);
	}

}