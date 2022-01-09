package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class WriteCSV {
	public static void write(String savePath, String name,String[][] arr) {
		String outputFile = savePath +"/" + name + ".csv";
		
		 try  {
			 FileWriter writer = new FileWriter(outputFile);
			 CSVPrinter printer = new CSVPrinter(writer, CSVFormat.EXCEL);
			 
			 for(String[] x : arr) {
				 for(String str: x) {
					 printer.print(str);
				 }
				 printer.println();
			 }
		     writer.flush();
		     writer.close();
		     printer.close();
		 } catch (IOException ex) {
		     ex.printStackTrace();
		 }
	}
	
	public static void write(String savePath, String name, String[] arr) {
		String outputFile = savePath +"/" + name + ".csv";
		
		 try  {
			 FileWriter writer = new FileWriter(outputFile);
			 CSVPrinter printer = new CSVPrinter(writer, CSVFormat.EXCEL);
			 
			 for(String str : arr) {
				 printer.print(str); 
			 }
			 printer.println();
		     writer.flush();
		     writer.close();
		     printer.close();
		 } catch (IOException ex) {
		     ex.printStackTrace();
		 }
	}
	
}
