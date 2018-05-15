package delivery;

import static org.junit.Assert.*;

import org.junit.Test;

public class TruckTests {
	
	Truck myTruck;
	
	@Before
	public void setUp() {
		myTruck = null;
	}

	@Test
	public void testConstruction() {
		myTruck = new Truck();
	}
	
	
}
