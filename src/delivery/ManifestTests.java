package delivery;

import stock.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ManifestTests {

	Manifest myManifest;
	
	Truck truck1 = new Truck();
	Truck truck2 = new RefrigeratedTruck(-10.0);
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
		myManifest.add(truck1);
		myManifest.add(truck2);
		myManifest.add(truck3);
	}
	
}
