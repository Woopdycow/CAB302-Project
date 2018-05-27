package CSV;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import GUI.ButtonPane;
import delivery.CSVFormatException;
import delivery.Manifest;
import delivery.OrdinaryTruck;
import delivery.RefrigeratedTruck;
import delivery.Truck;
import stock.Item;
import stock.Stock;
import stock.Store;

public class ManifestReader {

	public ManifestReader() {
		String fileName = "";
		try {
			Manifest manifest = ReadManifestCSV(fileName);
		} catch(CSVFormatException e) {
			ButtonPane.handleException(e.getMessage());
		}
		
		
	}
	
	public static Manifest ReadManifestCSV(String fileName) throws CSVFormatException {
		
		if (!fileName.endsWith(".csv")) {
			throw new CSVFormatException("File does not have '.csv' extension!");
		}
		
		String line = "";
		String csvSplitBy = ",";
		Store myStore = Store.getInstance();

		Manifest delivery = new Manifest();

		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			Item newItem;
			int quantity;
			String name;
			Stock newStock = new Stock();
			Truck newTruck = new OrdinaryTruck();
			List<Truck> trucks = new ArrayList<>();
			boolean first = true;
			while ((line = br.readLine()) != null) {
				if (line.startsWith(">")) {
					if (first == false) {
						newTruck.setCargo(newStock);
						trucks.add(newTruck);
					} else {
						first = false;
					}
					if (line.startsWith(">Re")) {
						System.out.println("New Cold Truck");
						newTruck = new RefrigeratedTruck();
					} else if (line.startsWith(">Or")) {
						System.out.println("New Truck");
						newTruck = new OrdinaryTruck();
					} else {
						throw new CSVFormatException("Invalid truck type.");
					}
				} else {
					String[] itemInfo = line.split(csvSplitBy);
					name = new String (itemInfo[0]);
					quantity = new Integer(itemInfo[1]);
					newItem = myStore.getItemByName(name);
					newStock.addItem(newItem, quantity);
					
				}	
			}
			for (Truck k : trucks) {
				delivery.addTruck(k);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Truck k : delivery.getTrucks()) {
			
		}
		return delivery;
	}
}
