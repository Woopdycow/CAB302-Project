package CSV;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import delivery.CSVFormatException;
import delivery.Manifest;



public class SalesLogReader {

	public SalesLogReader(String filename) {
		String fileName = "";
		try {
			List<Object[]> output = ReadSalesLogCSV(fileName);
		} catch(Exception e) {
			//e.getMessage()
		}
	}
	
	private static List<Object[]> ReadSalesLogCSV(String fileName) throws CSVFormatException{
		
		if (!fileName.endsWith(".csv")) {
			throw new CSVFormatException("File does not have '.csv' extension!");
		}
			
		String line = "";
		String csvSplitBy = ",";
		
		List<Object[]> salesLog;
		salesLog = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			while ((line = br.readLine()) != null) {
				String[] rawInput = line.split(csvSplitBy);
				Object[] sales = {rawInput[0], new Integer(rawInput[1])};
				salesLog.add(sales);
				if (rawInput[0] == "" || rawInput[1] == "") {
					throw new CSVFormatException("Item or quantity not valid.");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			}
		return salesLog;
	}

}
