package stock;

import static org.junit.Assert.*;
import static org.junit.Test.*;

import java.util.*;


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
		
		myItem = new Item("Vegemite", 4.5, 6.00, 12, 30);
		
		int quantity1 = 10;
		int quantity2 = 13;
		
		myStock.addItem(myItem, quantity1);
		myStock.addItem(myItem, quantity2);
		
		assertEquals(new Integer(quantity1 + quantity2), myStock.getQuantity(myItem));
	}
	
	@Test
	public void testQuantityAddition() {
		myStock = new Stock();
		
		myItem = new Item("Vegemite", 4.5, 6.0, 12, 30);
		
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
		
		Item myItem1 = new Item("Vegemite", 4.5, 6.0, 12, 30);
		Item myItem2 = new Item("Vegemite", 3.2, 4.5, 13, 30);
		
		int quantity1 = 10;
		int quantity2 = 13;
		
		myStock.add(myItem1, quantity1);
		myStock.add(myItem2, quantity2);
		
		assertEquals(new Integer(quantity1 + quantity2), myStock.getQuantity(myItem1));
	}
	
	@Test 
	public void testAddTogether1() {
		Stock stock1 = new Stock();
		Stock stock2 = new Stock();
		
		Item myItem1 = new Item("Beans", 3.50, 6.00, 15, 60);
		Item myItem2 = new Item("Ice Cream", 0.82, 1.50, 40, 80, -18);
		Item myItem3 = new Item("Potato Chips", 3.20, 4.50, 30, 70);
		
		stock1.add(myItem1, 50);
		stock1.add(myItem2, 80);
		stock2.add(myItem1, 52);
		stock2.add(myItem3, 70);
		
		assertEquals(new Integer(50 + 52), stock1.getQuantity(myItem1), 0.0);
	}
	
	@Test
	public void testAddTogether2() {
	Stock stock1 = new Stock();
	Stock stock2 = new Stock();
	
	Item myItem1 = new Item("Beans", 3.50, 6.00, 15, 60);
	Item myItem2 = new Item("Ice Cream", 0.82, 1.50, 40, 80, -18);
	Item myItem3 = new Item("Potato Chips", 3.20, 4.50, 30, 70);
	
	stock1.add(myItem1, 50);
	stock1.add(myItem2, 80);
	stock2.add(myItem1, 52);
	stock2.add(myItem3, 70);
	
	assertEquals(80, stock1.getQuantity(myItem2), 0.0);
	}
	
	@Test
	public void testAddTogether3() {
	Stock stock1 = new Stock();
	Stock stock2 = new Stock();
	
	Item myItem1 = new Item("Beans", 3.50, 6.00, 15, 60);
	Item myItem2 = new Item("Ice Cream", 0.82, 1.50, 40, 80, -18);
	Item myItem3 = new Item("Potato Chips", 3.20, 4.50, 30, 70);
	
	stock1.add(myItem1, 50);
	stock1.add(myItem2, 80);
	stock2.add(myItem1, 52);
	stock2.add(myItem3, 70);
	
	assertEquals(70, stock1.getQuantity(myItem3, 0.0);
	}
}
