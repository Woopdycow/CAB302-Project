package CSV;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import delivery.*;
import stock.*;

public class ManifestWriter {

	public ManifestWriter() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) throws TruckOverloadException, IOException {
		CreateCSVManifest("test.csv");
	}
	
	
	private static void CreateCSVManifest(String fileName) throws TruckOverloadException, IOException {
		
		FileWriter writer = new FileWriter("test.csv");
		
		Manifest manifest = Store.getInstance().getManifest();
		
		List<Truck> trucks = manifest.getTrucks();
		
		List<Object[]> output = new ArrayList<>();
		
		for (Truck truck : trucks) {
			String type = truck.getType();
			Stock stock = truck.getCargo();
			
			writer.write(">" + type + "\n");
			
			for (Item item : stock.getItemSet()) {
				String name = item.getName();
				int quantity = stock.getQuantity(item);
				
				Object[] nameAndQuantity = {name, quantity};
			}
		}
		writer.flush();
		writer.close();
		
	}

}
