package CSV;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import delivery.CSVFormatException;
import delivery.Manifest;
import delivery.OrdinaryTruck;
import delivery.RefrigeratedTruck;
import delivery.Truck;
import stock.Stock;
import stock.Store;

public class ManifestReader {

	public ManifestReader() {
		String fileName = "";
		try {
			Manifest manifest = ReadManifestCSV(fileName);
		} catch(Exception e) {
			//e.getMessage()
		}
		
		
	}
	
	private static Manifest ReadManifestCSV(String fileName) throws CSVFormatException {
		
		if (!fileName.endsWith(".csv")) {
			throw new CSVFormatException("File does not have '.csv' extension!");
		}
		
		String csvFile = "C:/Users/Jon/Desktop/SEM 1/CAB302/CSV/manifest.csv";
		String line = "";
		String csvSplitBy = ",";
		Store myStore = Store.getInstance();
		
		
		Manifest delivery = new Manifest();
		
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			//Print each line to an array
			List<String> lines = new ArrayList<>();
			for (int i = 0; (line = br.readLine()) != null; i++) {
				lines.set(i, line);
			}

			int items = 0;
			if (lines.get(0).startsWith(">")) {
				Truck newTruck;
				for (int i = 0; i < lines.size(); i+= items + 1) {
					items = 0;
					//Count how many items are on the truck
					for (int j = i + 1; !lines.get(j).startsWith(">"); j++) {
						items++;
					}
					if (items == 0) {
						throw new CSVFormatException("No items detected for truck.");
					}
					//Create truck
					if (lines.get(i) == ">Refrigerated") {
						newTruck = new RefrigeratedTruck();
					} else if (lines.get(i) == ">Ordinary") {
						newTruck = new OrdinaryTruck();
					} else {
						throw new CSVFormatException("Invalid truck type.");
					}
					Stock cargo = new Stock();
					for (int j = i + 1; j < items + i + 1; j++) {
						String[] itemInfo = lines.get(j).split(csvSplitBy);
						int quantity = new Integer(itemInfo[1]);
						cargo.addItem(myStore.getItemByName(itemInfo[0]), quantity);
					}
					if (cargo.getTotal() == 0) {
						throw new CSVFormatException("No items for truck cargo.");
					}
					newTruck.setCargo(cargo);
					delivery.addTruck(newTruck);
				}
			} else {
				//Throw read error
			}
			

		} catch (IOException e) {
			e.printStackTrace();
			}
		return delivery;
	}
}
