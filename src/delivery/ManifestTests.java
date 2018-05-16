package delivery;

import stock.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ManifestTests {

	Manifest myManifest;
	
	Truck truck1 = new Truck();
	Truck truck2 = new RefrigeratedTruck();
	Truck truck3 = new Truck();
	
	
	@Before
	public void setUp() {
		myManifest = null;
	}

	@Test
	public void testConstruction() {
		myManifest = new Manifest();
	}
	
	@Test
	public void testAdd() {
		myManifest.addTruck(truck1);
		myManifest.addTruck(truck2);
		myManifest.addTruck(truck3);
		assertEquals(truck2.getTemp(), 10.0);
	}
	
	@public void testRemove() {
		myManifest.addTruck(truck1);
		
	}
}
