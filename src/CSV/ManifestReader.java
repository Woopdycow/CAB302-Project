package CSV;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import delivery.Manifest;
import delivery.OrdinaryTruck;
import delivery.RefrigeratedTruck;
import delivery.Truck;
import delivery.TruckOverloadException;
import stock.Item;
import stock.Stock;
import stock.Store;

public class ManifestReader {

	public ManifestReader() throws TruckOverloadException {
		
		Manifest manifest = ReadManifestCSV("C:/Users/Jon/Desktop/SEM 1/CAB302/CSV/manifest.csv");
		
	}
	
	private static Manifest ReadManifestCSV(String fileName) throws TruckOverloadException {
		
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
					//Create truck
					if (lines.get(i) == ">Refrigerated") {
						newTruck = new RefrigeratedTruck();
					} else if (lines.get(i) == ">Ordinary") {
						newTruck = new OrdinaryTruck();
					} else {
						newTruck = new OrdinaryTruck();
					}
					Stock cargo = new Stock();
					for (int j = i + 1; j < items + i + 1; j++) {
						String[] itemInfo = lines.get(j).split(csvSplitBy);
						int quantity = new Integer(itemInfo[1]);
						cargo.addItem(myStore.getItemByName(itemInfo[0]), quantity);
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
