package CSV;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class SalesLogReader {

	public SalesLogReader(String filename) {
		ReadSalesLogCSV(filename);
	}

	public static void main(String[] args) {
		
		List<Object[]> output = ReadSalesLogCSV("C:/Users/Jon/Desktop/SEM 1/CAB302/CSV/sales_log_0.csv");

		for (Object[] sales : output) {
			System.out.println(sales[0].toString() + ", " + sales[1].toString());
		}
	}
	
	private static List<Object[]> ReadSalesLogCSV(String fileName) {
		
		String line = "";
		String csvSplitBy = ",";
		
		List<Object[]> salesLog;
		salesLog = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			while ((line = br.readLine()) != null) {
				String[] rawInput = line.split(csvSplitBy);
				Object[] sales = {rawInput[0], new Integer(rawInput[1])};
				salesLog.add(sales);
			}
		} catch (IOException e) {
			e.printStackTrace();
			}
		return salesLog;
	}

}
