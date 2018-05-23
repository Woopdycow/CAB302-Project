package stock;

import static org.junit.Assert.*;
import delivery.*;
import stock.*;

import org.junit.Test;

public class StoreTests {
	
	Store myStore;
	Manifest myManifest;
	Truck truck1;
	Truck truck2;
	Item item1;
	Item item2;
	Item item3;
	Item item4;
	Stock storeStock;
	Stock stock1;
	Stock stock2;
	Stock stock3;
	Stock stock4;
	double capital = 100000;
	double charge;
	
	@Test
	public void testManifestLoad1() {
		//List<Truck> myManifest = new ArrayList<Truck>;
		// for each line in the csv
		// if line == >Refrigerated then
		// create new ColdTruck
		// else if line == >Ordinary then
		// create new NormalTruck
		// else add item and value to most recently created truck
		myStore = new Store();
		
		item1 = new Item("Toilet Paper", 106.22, 121.61, 320, 600);
		item2 = new Item("Vegemite", 13.26, 16.00, 150, 600);
		item3 = new Item("Ice Cream", 3.50, 5.00, 30, 120, -18);
		item4 = new Item("Bottled Water", 0.20, 1.50, 50, 600);
		
		storeStock = new Stock();
		stock1 = new Stock();
		stock2 = new Stock();
		
		stock1.add(item1, 3);
		stock1.add(item2, 9);
		
		stock2.add(item3, 11);
		stock2.add(item4, 69);
		
		truck1 = new Truck();
		truck2 = new RefrigeratedTruck();
		
		truck1.setCargo(stock1);
		
		myManifest = new Manifest();
		
		myManifest.add(truck1);
		myManifest.add(truck2);
		
		myStore.loadManifest(myManifest);
		assertEquals(new Integer(storeStock.getQuantity(item1) + 3), storeStock.getQuantity(item1), 0.0);
	}
	
	@Test
	public void testManifestLoad2() {
		//List<Truck> myManifest = new ArrayList<Truck>;
		// for each line in the csv
		// if line == >Refrigerated then
		// create new ColdTruck
		// else if line == >Ordinary then
		// create new NormalTruck
		// else add item and value to most recently created truck
		myStore = new Store();
		
		item1 = new Item("Toilet Paper", 106.22, 121.61, 320, 600);
		item2 = new Item("Vegemite", 13.26, 16.00, 150, 600);
		item3 = new Item("Ice Cream", 3.50, 5.00, 30, 120, -18);
		item4 = new Item("Bottled Water", 0.20, 1.50, 50, 600);
		
		storeStock = new Stock();
		stock1 = new Stock();
		stock2 = new Stock();
		
		stock1.add(item1, 3);
		stock1.add(item2, 9);
		
		stock2.add(item3, 11);
		stock2.add(item4, 69);
		
		truck1 = new Truck();
		truck2 = new RefrigeratedTruck();
		
		truck1.setCargo(stock1);
		
		myManifest = new Manifest();
		
		myManifest.add(truck1);
		myManifest.add(truck2);
		
		myStore.loadManifest(myManifest);
		
		assertEquals(new Integer(storeStock.getQuantity(item2) + 9), storeStock.getQuantity(item2), 0.0);
	}
	
	@Test
	public void testManifestLoad3() {
		//List<Truck> myManifest = new ArrayList<Truck>;
		// for each line in the csv
		// if line == >Refrigerated then
		// create new ColdTruck
		// else if line == >Ordinary then
		// create new NormalTruck
		// else add item and value to most recently created truck
		myStore = new Store();
		
		item1 = new Item("Toilet Paper", 106.22, 121.61, 320, 600);
		item2 = new Item("Vegemite", 13.26, 16.00, 150, 600);
		item3 = new Item("Ice Cream", 3.50, 5.00, 30, 120, -18);
		item4 = new Item("Bottled Water", 0.20, 1.50, 50, 600);
		
		storeStock = new Stock();
		stock1 = new Stock();
		stock2 = new Stock();
		
		stock1.add(item1, 3);
		stock1.add(item2, 9);
		
		stock2.add(item3, 11);
		stock2.add(item4, 69);
		
		truck1 = new Truck();
		truck2 = new RefrigeratedTruck();
		
		truck1.setCargo(stock1);
		
		myManifest = new Manifest();
		
		myManifest.add(truck1);
		myManifest.add(truck2);
		
		myStore.loadManifest(myManifest);
		
		assertEquals(new Integer(storeStock.getQuantity(item3) + 11), storeStock.getQuantity(item3), 0.0);
	}
	
	@Test
	public void testManifestLoad4() {
		//List<Truck> myManifest = new ArrayList<Truck>;
		// for each line in the csv
		// if line == >Refrigerated then
		// create new ColdTruck
		// else if line == >Ordinary then
		// create new NormalTruck
		// else add item and value to most recently created truck
		myStore = new Store();
		
		item1 = new Item("Toilet Paper", 106.22, 121.61, 320, 600);
		item2 = new Item("Vegemite", 13.26, 16.00, 150, 600);
		item3 = new Item("Ice Cream", 3.50, 5.00, 30, 120, -18);
		item4 = new Item("Bottled Water", 0.20, 1.50, 50, 600);
		
		storeStock = new Stock();
		stock1 = new Stock();
		stock2 = new Stock();
		
		stock1.add(item1, 3);
		stock1.add(item2, 9);
		
		stock2.add(item3, 11);
		stock2.add(item4, 69);
		
		truck1 = new Truck();
		truck2 = new RefrigeratedTruck();
		
		truck1.setCargo(stock1);
		
		myManifest = new Manifest();
		
		myManifest.add(truck1);
		myManifest.add(truck2);
		
		myStore.loadManifest(myManifest);
		
		assertEquals(new Integer(storeStock.getQuantity(item4) + 69), storeStock.getQuantity(item4), 0.0);
	}
	
	@Test
	public void testCapitalDecrease() {
			//List<Truck> myManifest = new ArrayList<Truck>;
			// for each line in the csv
			// if line == >Refrigerated then
			// create new ColdTruck
			// else if line == >Ordinary then
			// create new NormalTruck
			// else add item and value to most recently created truck
			myStore = new Store();
			
			item1 = new Item("Toilet Paper", 106.22, 121.61, 320, 600);
			item2 = new Item("Vegemite", 13.26, 16.00, 150, 600);
			item3 = new Item("Ice Cream", 3.50, 5.00, 30, 120, -18);
			item4 = new Item("Bottled Water", 0.20, 1.50, 50, 600);
			
			storeStock = new Stock();
			stock1 = new Stock();
			stock2 = new Stock();
			
			stock1.add(item1, 3);
			stock1.add(item2, 9);
			
			stock2.add(item3, 11);
			stock2.add(item4, 69);
			
			truck1 = new Truck();
			truck2 = new RefrigeratedTruck();
			
			truck1.setCargo(stock1);
			
			myManifest = new Manifest();
			
			myManifest.add(truck1);
			myManifest.add(truck2);
			
			myStore.loadManifest(myManifest);
			
			assertEquals(100000 - charge, capital, 0.0);
	}
	@Test
	public void testName() {
		myStore = new Store();
		myStore.name = "SupaMart";
	}
}
