package tests;

import org.junit.*;

import stock.Item;
import stock.Stock;
import stock.StockException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
		assertEquals(new Integer(quantity), myStock.getQuantity(myItem), 0.0);
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
		
		int expected = quantity1 + quantity2;
		
		assertEquals(expected, myStock.getQuantity(myItem));
	}
	
	/**
	 * Check if multiple items with the same names are added, sum their quantities.
	 */
	@Test
	public void testDuplicateAdd() {
		myStock = new Stock();
		
		Item myItem1 = new Item("Vegemite", 4.5, 6.0, 12, 30);
		
		int quantity1 = 10;
		int quantity2 = 13;
		
		myStock.addItem(myItem1, quantity1);
		myStock.addItem(myItem1, quantity2);
		
		assertEquals(new Integer(quantity1 + quantity2), myStock.getQuantity(myItem1), 0.0);
	}
	
	@Test 
	public void testAddTogether1() {
		Stock stock1 = new Stock();
		Stock stock2 = new Stock();
		
		Item myItem1 = new Item("Beans", 3.50, 6.00, 15, 60);
		Item myItem2 = new Item("Ice Cream", 0.82, 1.50, 40, 80, -18);
		Item myItem3 = new Item("Potato Chips", 3.20, 4.50, 30, 70);
		
		stock1.addItem(myItem1, 50);
		stock1.addItem(myItem2, 80);
		stock2.addItem(myItem1, 52);
		stock2.addItem(myItem3, 70);
		
		int expected = 50+52;
		stock1.merge(stock2);
		
		assertEquals(expected, stock1.getQuantity(myItem1), 0.0);
	}
	
	@Test
	public void testAddTogether2() {
	Stock stock1 = new Stock();
	Stock stock2 = new Stock();
	
	Item myItem1 = new Item("Beans", 3.50, 6.00, 15, 60);
	Item myItem2 = new Item("Ice Cream", 0.82, 1.50, 40, 80, -18);
	Item myItem3 = new Item("Potato Chips", 3.20, 4.50, 30, 70);
	
	stock1.addItem(myItem1, 50);
	stock1.addItem(myItem2, 80);
	stock2.addItem(myItem1, 52);
	stock2.addItem(myItem3, 70);
	
	stock1.merge(stock2);
	
	assertEquals(80, stock1.getQuantity(myItem2), 0.0);
	}
	
	@Test
	public void testAddTogether3() {
	Stock stock1 = new Stock();
	Stock stock2 = new Stock();
	
	Item myItem1 = new Item("Beans", 3.50, 6.00, 15, 60);
	Item myItem2 = new Item("Ice Cream", 0.82, 1.50, 40, 80, -18);
	Item myItem3 = new Item("Potato Chips", 3.20, 4.50, 30, 70);
	
	stock1.addItem(myItem1, 50);
	stock1.addItem(myItem2, 80);
	stock2.addItem(myItem1, 52);
	stock2.addItem(myItem3, 70);
	
	stock1.merge(stock2);
	
	assertEquals(70, stock1.getQuantity(myItem3), 0.0);
	}
	
	@Test
	public void testRemoveItem1() throws StockException {
		Stock stock1 = new Stock();
		Item myItem1 = new Item("Beans", 3.50, 6.00, 15, 60);
		stock1.addItem(myItem1, 10);
		stock1.removeItem(myItem1, 5);
		assertEquals(5, stock1.getQuantity(myItem1));
	}
	
	@Test
	public void testRemoveItem2() throws StockException {
		Stock stock1 = new Stock();
		Item myItem1 = new Item("Beans", 3.50, 6.00, 15, 60);
		stock1.addItem(myItem1, 10);
		stock1.removeItem(myItem1, 10);
		assertEquals(false, stock1.itemExists(myItem));
	}
	
	@Test
	public void testRemoveItem3() throws StockException {
		try {
			Stock stock1 = new Stock();
			Item myItem1 = new Item("Beans", 3.50, 6.00, 15, 60);
			stock1.addItem(myItem1, 10);
			stock1.removeItem(myItem1, 15);
			fail("Expected StockException to be thrown.");
		} catch (StockException e) {
			assertEquals(e.getMessage(), "Attempted to remove more items than were available.");
		}
	}
	
	@Test
	public void testGetQuantityNull() {
		Stock stock1 = new Stock();
		Item myItem1 = new Item("Beans", 3.50, 6.00, 15, 60);

		assertEquals(0, stock1.getQuantity(myItem1), 0.0);
	}
	
	@Test
	public void testGetTotal() {
		Stock stock1 = new Stock();
		Item myItem1 = new Item("Beans", 3.50, 6.00, 15, 60);
		stock1.addItem(myItem1, 64);
		stock1.addItem(myItem1, 32);
		stock1.addItem(myItem1, 16);

		assertEquals(112, stock1.getTotal(), 0.0);
	}
}
