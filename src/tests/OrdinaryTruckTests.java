package tests;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Before;

import stock.*;

import org.junit.Test;

import delivery.OrdinaryTruck;
import delivery.TruckOverloadException;
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
		myTruck = new OrdinaryTruck();
	}
	
	@Test
	public void testCargo() throws TruckOverloadException {
		String outputTest = "";
		myTruck = new OrdinaryTruck();
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
		assertEquals(outputTest, "VegemiteVegemiteVegemiteVegemiteCheeseCheeseCheeseGrapesGrapesGrapesGrapesGrapesGrapesGrapes");
	}
	
	@Test
	public void testGetCost() throws TruckOverloadException {
		myTruck = new OrdinaryTruck();
		Stock stock1 = new Stock();
		Item item1 = new Item("Vegemite", 10.0, 15.0, 320, 600);
		Item item2 = new Item("Cheese", 14.6, 16.4, 300, 600, 10.0);
		Item item3 = new Item("Grapes", 11.2, 14.3, 250, 600, 5.0);
		stock1.addItem(item1, 3);
		stock1.addItem(item2, 5);
		stock1.addItem(item3, 8);
		myTruck.setCargo(stock1);
		assertEquals(myTruck.getCost(), (750 + (0.25 * 16)), 0.0);		
	}
	
	@Test
	public void testGetCapacity() {
		myTruck = new OrdinaryTruck();
		assertEquals(1000, myTruck.getCapacity(), 0.0);
	}
	
	@Test(expected = TruckOverloadException.class)
	public void testOverload() throws TruckOverloadException {
		myTruck = new OrdinaryTruck();
		Stock stock1 = new Stock();
		Item item1 = new Item("Vegemite", 10.0, 15.0, 320, 600);
		stock1.addItem(item1, 1001);
		myTruck.setCargo(stock1);
	}

}
