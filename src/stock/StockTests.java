package stock;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class StockTests {

	Item myItem;
	Stock myStock;
	
	@Before
	public void setUp() {
		myItem = null;
	}
	/**
	 * Tests to see if the item has been added to Stock, and asks for the quantity to confirm.
	 */
	@Test
	public void testItem() {
		myItem = new Item("Milk", 13.22, 18.26, 350, 600, 4.0);
		myStock = new Stock();
		int quantity = 6;
		myStock.addItem(myItem, quantity);
		assertEquals(new Integer(quantity), myStock.getQuantity(myItem));
	}
/**
 * Test if multiple item objects are added, sum their quantities.
 */
	@Test
	public void testMultiAdd() {
		myStock = new Stock();
		
		myItem = new Item("Vegemite", 4.5, 6.00, 12);
		
		int quantity1 = 10;
		int quantity2 = 13;
		
		myStock.addItem(myItem, quantity1);
		myStock.addItem(myItem, quantity2);
		
		assertEquals(new Integer(quantity1 + quantity2), myStock.getQuantity(myItem));
	}
	
	@Test
	public void testQuantityAddition() {
		myStock = new Stock();
		
		myItem = new Item("Vegemite", 4.5, 6.0, 12);
		
		int quantity1 = 10;
		int quantity2 = 13;
		
		myStock.addItem(myItem, quantity1);
		myStock.addItem(myItem, quantity2);
		
		if(myStock.itemExists == true) {
			fail("Second item with the same name is meant to be combined with the first.");
		}
	}
	/**
	 * Check if multiple items with the same names are added, sum their quantities.
	 */
	@Test
	public void testDuplicateAdd() {
		myStock = new Stock();
		
		myItem1 = new Item("Vegemite", 4.5, 6.0, 12);
		myItem2 = new Item("Vegemite", 3.2, 4.5, 13);
		
		int quantity1 = 10;
		int quantity2 = 13;
		
		myStock.addItem(myItem1, quantity1);
		myStock.addItem(myItem2, quantity2);
		
		assertEquals(new Integer(quantity1 + quantity2), myStock.getQuantity(myItem1));
	}
}
