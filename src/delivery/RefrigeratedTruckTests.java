package delivery;

import static org.junit.Assert.*;

import org.junit.Test;

public class RefrigeratedTruckTests {
	
	RegrigeratedTruck myRefrigeratedTruck;
	
	@Before
	public void setUp() {
		myRefrigerationTruck = null;
	}

	@Test
	public void testConstruction() {
		myRefrigerationTruck = new RegrigeratedTruck();
	}

}
