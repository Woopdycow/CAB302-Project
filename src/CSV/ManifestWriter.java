package CSV;

import java.io.FileWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import delivery.*;
import stock.*;
/**
 *  Creates a CSV file containing a manifest in a given format. This can be used to import into
 *   the system.
 * @author Jonathon Meyer
 *
 */

public class ManifestWriter {
	/**
	 * Creates a CSV file containing a manifest in a given format. This can be used to import into
	 * the system.
	 * @param fileName The name and directory of the file to be analysed
	 * @throws IOException
	 * @throws StockException 
	 * @author Jonathon Meyer
	 */
	public static void CreateCSVManifest(String fileName) throws IOException, StockException {
		FileWriter writer = new FileWriter("manifest.csv");
		
		Manifest manifest = Store.getInstance().getManifest();
		
		System.out.println(Store.getInstance().getManifest().toString());
		
		List<Truck> trucks = manifest.getTrucks();
		
		List<Object[]> output = new ArrayList<>();
		
		for (Truck truck : trucks) {
			String type = truck.getType();
			Stock stock = truck.getCargo();
			System.out.print(type);
			
			writer.write(">" + type + "\n");
			
			for (Item item : stock.getItemSet()) {
				String name = item.getName();
				int quantity = stock.getQuantity(item);
				
				Object[] nameAndQuantity = {name, quantity};
				writer.write(nameAndQuantity[0] + "," + nameAndQuantity[1]);
			}
		}
		writer.flush();
		writer.close();
		
	}

}
