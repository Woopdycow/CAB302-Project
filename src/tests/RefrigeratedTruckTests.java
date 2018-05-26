package tests;

import stock.*;
import org.junit.Assert.*;

import delivery.RefrigeratedTruck;
import delivery.TruckOverloadException;

import org.junit.*;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Set;

public class RefrigeratedTruckTests {
	
	RefrigeratedTruck myTruck;
	double cost;
	int cargoCapacity;
	Stock cargo;
	double temp;
	
	@Before
	public void setUp() {
		myTruck = null;
	}

	@Test
	public void testConstruction() {
		myTruck = new RefrigeratedTruck();
	}
	
	@Test
	public void testCargo() throws TruckOverloadException {
		String outputTest = "";
		myTruck = new RefrigeratedTruck();
		Stock stock1 = new Stock();
		Item item1 = new Item("Vegemite", 10.0, 15.0, 320, 600);
		Item item2 = new Item("Cheese", 14.6, 16.4, 300, 600);
		Item item3 = new Item("Grapes", 11.2, 14.3, 250, 600);
		stock1.addItem(item1, 4);
		stock1.addItem(item2, 3);
		stock1.addItem(item3, 7);
		myTruck.setCargo(stock1);

		for (Item getItem : myTruck.getCargo().getItemSet()) {
			for (int i = 0; i < myTruck.getCargo().getQuantity(getItem); i++)
				outputTest += getItem.getName();
		}
		assertEquals("VegemiteVegemiteVegemiteVegemiteCheeseCheeseCheeseGrapesGrapesGrapesGrapesGrapesGrapesGrapes", outputTest);
	}
	
	@Test
	public void testGetTempUpdate() throws TruckOverloadException {
		myTruck = new RefrigeratedTruck();
		Stock myStock = new Stock();
		Item myItem = new Item("Cheese", 14.6, 16.4, 300, 600, 5.0);
		myStock.addItem(myItem, 1);
		myTruck.setCargo(myStock);
		assertEquals(myTruck.getTemp(), 5.0, 0.0);
	}
	
	@Test
	public void testGetCost() {
		myTruck = new RefrigeratedTruck();
		double testPrice = (900 + (200 * (java.lang.Math.pow(0.7, myTruck.getTemp() * 0.2))));
		assertEquals(myTruck.getCost(), testPrice, 0.0);
	}
	
	@Test
	public void testGetCapacity() {
		myTruck = new RefrigeratedTruck();
		assertEquals(800, myTruck.getCapacity());
	}
	
	@Test(expected = TruckOverloadException.class)
	public void testOverload() throws TruckOverloadException {
		myTruck = new RefrigeratedTruck();
		Stock stock1 = new Stock();
		Item item1 = new Item("Cheese", 10.0, 15.0, 320, 600);
		stock1.addItem(item1, 801);
		myTruck.setCargo(stock1);
	}

}
