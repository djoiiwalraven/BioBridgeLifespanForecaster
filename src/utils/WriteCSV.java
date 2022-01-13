package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

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
	
	public static void write(String savePath, String name,double[][] arr) {
		String outputFile = savePath +"/" + name + ".csv";
		
		 try  {
			 FileWriter writer = new FileWriter(outputFile);
			 CSVPrinter printer = new CSVPrinter(writer, CSVFormat.EXCEL);
			 
			 for(double[] x : arr) {
				 for(double d: x) {
					 printer.print(d);
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
	
	public static void write(String savePath, String name, double[] arr) {
		String outputFile = savePath +"/" + name + ".csv";
		
		 try  {
			 FileWriter writer = new FileWriter(outputFile);
			 CSVPrinter printer = new CSVPrinter(writer, CSVFormat.EXCEL);
			 
			 for(double d : arr) {
				 printer.print(d); 
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
