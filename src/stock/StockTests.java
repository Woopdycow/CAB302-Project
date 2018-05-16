package stock;

import static org.junit.Assert.*;

import org.junit.Test;

public class StockTests {

	Item myItem;
	
	@Before
	public void setUp() {
		myItem = null;
	}
	
	@Test
	public void testItem() {
		myItem = new Item("Milk", 13.22, 18.26, 350, 600, 4.0);
		assertEquals("Milk", myItem.getName());
	}

}
