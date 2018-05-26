package CSV;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import stock.Item;

public class ItemReader {

	public ItemReader() {
		
		List<Item> output = ReadItemCSV("C:/Users/Jon/Desktop/SEM 1/CAB302/CSV/item_properties.csv");
		
		for (Item item : output) {
			System.out.println(item.getName() + "," + item.getCost() + "," + item.getPrice() + "," + item.getReorderPoint() + "," + item.getReorderAmount() + "," + item.getTemp() + "\n");
		}
	}
	
	private static List<Item> ReadItemCSV(String fileName) {
		
		String csvFile = "C:/Users/Jon/Desktop/SEM 1/CAB302/CSV/item_properties.csv";
		String line = "";
		String csvSplitBy = ",";
		
		List<Item> items;
		
		items = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			while ((line = br.readLine()) != null) {
				String[] itemInfo = line.split(csvSplitBy);
				Item item = newItem(itemInfo);
				items.add(item);	
			}
		} catch (IOException e) {
			e.printStackTrace();
			}
		return items;
		}
	
	private static Item newItem(String[] input) {
		
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
