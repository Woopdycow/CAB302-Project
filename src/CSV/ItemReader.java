package CSV;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import GUI.InfoPane;
import delivery.CSVFormatException;
import stock.Item;

public class ItemReader {

	public ItemReader() {
		/**
		 * Reads a given CSV file and outputs a list of items.
		 * 
		 * @author Jonathon Meyer
		 */
		String fileName = "";
		try {
			List<Item> output = ReadItemCSV(fileName);
		} catch (Exception e) {
			InfoPane.handleException(e.getMessage());
		}
		
	}
	
	public static List<Item> ReadItemCSV(String fileName) throws CSVFormatException {
		
		if (!fileName.endsWith(".csv")) {
			throw new CSVFormatException("File does not have '.csv' extension!");
		}
		
		String line = "";
		String csvSplitBy = ",";
		
		List<Item> items;
		
		items = new ArrayList<>();		

		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			while ((line = br.readLine()) != null) {
				String[] itemInfo = line.split(csvSplitBy);
				Item item = newItem(itemInfo);
				items.add(item);	
			}
		} catch ( IOException e) {
			InfoPane.handleException(e.getMessage());
		}
		if (items.size() < 1) {
			throw new CSVFormatException("File produced no items.");
		}
		return items;
		}
	
	private static Item newItem(String[] input) throws CSVFormatException {
		if (input.length < 1) {
			throw new CSVFormatException("No properties discovered for item.");
		}
		String name = input[0];
		double manuCost = new Double(input[1]);
		double sellPrice = new Double(input[2]);
		int reorderPoint = new Integer(input[3]);
		int reorderAmount = new Integer(input[4]);
		if (input.length == 6) {
			double temperature = new Double(input[5]);
			Item item = new Item(name, manuCost, sellPrice, reorderPoint, reorderAmount, temperature);
			return item;
		} else {
			Item item = new Item(name, manuCost, sellPrice, reorderPoint, reorderAmount);
			return item;
		}
	}
}
