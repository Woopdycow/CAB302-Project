package delivery;

import stock.*;
import delivery.*;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ManifestTests {

	Manifest myManifest;
	
	OrdinaryTruck truck1 = new OrdinaryTruck();
	RefrigeratedTruck truck2 = new RefrigeratedTruck();
	OrdinaryTruck truck3 = new OrdinaryTruck();
	
	
	@Before
	public void setUp() {
		myManifest = null;
	}

	@Test
	public void testConstruction() {
		myManifest = new Manifest();
	}
	
	@Test
	public void testAdd() throws TruckOverloadException {
		myManifest = new Manifest();
		Item item1 = new Item("Vegemite", 2.60, 3.50, 30, 70);
		Item item2 = new Item("Ice Cream", 4.26, 8.30, 100, 200, -18.00);
		Item item3 = new Item("Beans", 3.18, 4.99, 20, 80);
		Stock stock1 = new Stock();
		Stock stock2 = new Stock();
		Stock stock3 = new Stock();
		stock1.addItem(item1, 6);
		stock2.addItem(item2, 11);
		stock3.addItem(item3, 64);
		truck1.setCargo(stock1);
		truck2.setCargo(stock2);
		truck3.setCargo(stock3);
		//System.out.println(truck1.cargo.getTotal());
		System.out.println(truck2);
		System.out.println(truck1.getCost());
		myManifest.addTruck(truck1);
		myManifest.addTruck(truck2);
		myManifest.addTruck(truck3);
		
		//System.out.println(truck1.getCost());
		assertEquals(10.0, 10.0, 0.0);
	}
	
	@Test
	public void testGetTruck() throws TruckOverloadException {
		myManifest = new Manifest();
		Item item1 = new Item("Vegemite", 2.60, 3.50, 30, 70);
		Item item2 = new Item("Ice Cream", 4.26, 8.30, 100, 200, -18.00);
		Item item3 = new Item("Beans", 3.18, 4.99, 20, 80);
		Stock stock1 = new Stock();
		Stock stock2 = new Stock();
		Stock stock3 = new Stock();
		stock1.addItem(item1, 6);
		stock2.addItem(item2, 11);
		stock3.addItem(item3, 64);
		truck1.setCargo(stock1);
		myManifest.addTruck(truck1);
		myManifest.addTruck(truck2);
		myManifest.addTruck(truck3);
		
		String tester = "[delivery.OrdinaryTruck@255316f2, delivery.RefrigeratedTruck@41906a77, delivery.OrdinaryTruck@4b9af9a9]";
		
		assertEquals(tester, myManifest.getTrucks().toString());
	}
	
	@Test
	public void testRemove() throws TruckOverloadException {
		myManifest = new Manifest();
		Item item1 = new Item("Vegemite", 2.60, 3.50, 30, 70);
		Item item2 = new Item("Ice Cream", 4.26, 8.30, 100, 200, -18.00);
		Item item3 = new Item("Beans", 3.18, 4.99, 20, 80);
		Stock stock1 = new Stock();
		Stock stock2 = new Stock();
		Stock stock3 = new Stock();
		stock1.addItem(item1, 6);
		stock2.addItem(item2, 11);
		stock3.addItem(item3, 64);
		truck1.setCargo(stock1);
		myManifest.addTruck(truck1);
		
		myManifest.removeTruck(truck1);
		
		if (myManifest.trucks.contains(truck1)) {
			fail("Truck not removed.");
		}
	}
}
