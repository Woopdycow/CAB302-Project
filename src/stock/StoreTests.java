package stock;

import static org.junit.Assert.*;
import delivery.*;
import stock.*;

import org.junit.Test;

public class StoreTests {
	
	Store myStore = Store.getInstance();
	Manifest myManifest;
	Truck truck1;
	Truck truck2;
	Item item1 = new Item("Toilet Paper", 106.22, 121.61, 320, 600);
	Item item2 = new Item("Vegemite", 13.26, 16.00, 150, 600);
	Item item3 = new Item("Ice Cream", 3.50, 5.00, 30, 120, -18);
	Item item4 = new Item("Bottled Water", 0.20, 1.50, 50, 600);
	Stock storeStock;
	Stock stock1;
	Stock stock2;
	Stock stock3;
	Stock stock4;
	
	@Test
	public void testAddItem() throws TruckOverloadException, DeliveryException {
		Item item1 = new Item("Toilet Paper", 106.22, 121.61, 320, 600);
		myStore.addItem(item1, 30);
		int amount = myStore.getItemQuantity(item1);
		assertEquals(30, amount, 0.0);
	}
	
	@Test
	public void testGetReorder() throws TruckOverloadException, DeliveryException {
		Item item1 = new Item("Toilet Paper", 106.22, 121.61, 320, 600);
		myStore.addItem(item1, 30);
		assertEquals(600, myStore.getReorder().getQuantity(item1), 0.0);
	}
	
	@Test
	public void testLoadManifest() throws TruckOverloadException, DeliveryException {		
		
		stock1.addItem(item1, 3);
		stock1.addItem(item2, 9);
		stock2.addItem(item3, 11);
		stock2.addItem(item4, 69);
		truck1 = new OrdinaryTruck();
		truck2 = new RefrigeratedTruck();
		truck1.setCargo(stock1);
		truck2.setCargo(stock2);
		
		myManifest = new Manifest();
		myManifest.addTruck(truck1);
		myManifest.addTruck(truck2);
		
		myStore.loadManifest(myManifest);
		
		double charge = 2865.28;
		
		assertEquals(100000 - charge, myStore.getCapital(), 0.0);
	}
	
	@Test
	public void testCapitalDecrease() throws TruckOverloadException, DeliveryException {
			
	}
	@Test
	public void testName() {
		myStore = Store.getInstance();
		assertEquals(myStore.getName(), "SUPERMART");
	}
}
