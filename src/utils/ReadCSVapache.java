package utils;


import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


public class ReadCSVapache {
	
	public static List<String> read(String file, int column) {
		List<String> temp = new ArrayList<String>();
		try {
			Reader reader= Files.newBufferedReader(Paths.get(file));// create reader to read files
			Iterable<CSVRecord> records = CSVFormat.newFormat(';').parse(reader);// reads the file
			
			for(CSVRecord record:records) {
				temp.add(record.get(column-1));
				//System.out.println(record.get(column-1));
			}
			reader.close();
			return temp;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		System.out.println("LIST IS EMPTY!");
		return temp;
	}
	
	public static void read(String file, int column1, int column2) {
		try {
			Reader reader = new FileReader(file);// create reader to read files
			Iterable<CSVRecord> records = CSVFormat.newFormat(';').parse(reader);// reads the file
			
			
			for(CSVRecord record:records) {
				System.out.println(record.get(column1) + " : " + record.get(column2));
			}
			reader.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static List<String> readTraffic(String file, int column, int persons, int bikes, int cars){
		List<String> temp = new ArrayList<String>();
		try {
			Reader reader = new FileReader(file);// create reader to read files
			Iterable<CSVRecord> records = CSVFormat.newFormat(';').parse(reader);// reads the file
			
			int counter = 0;
			for(CSVRecord record : records) {
				if(counter > 0) {
					double x = Double.parseDouble(record.get(persons));
					double y = Double.parseDouble(record.get(bikes));
					double z = Double.parseDouble(record.get(cars));
					if(x <= 15 && y <= 5 && y <= 0) {
						String a = record.get(column);
						//System.out.println(a);
						temp.add(a);
					}
				}
				counter++;
			}
			reader.close();
			return temp;
		} catch (IOException ex) {
		    ex.printStackTrace();
		}
		System.out.println("LIST IS EMPTY!");
		return temp;
	}
	
	//read(<file>,<string>,<list of test values>,<column  corresponding test values>)
	public static List<Double> read(String file , int column, List<String> values, int valColumn) {
		List<Double> temp = new ArrayList<Double>();
		try {
			Reader reader= Files.newBufferedReader(Paths.get(file));// create reader to read files
			Iterable<CSVRecord> records = CSVFormat.newFormat(';').parse(reader);// reads the file
			
			int counter = 0;
			for(CSVRecord record:records) {
				if(counter > 900000) {
					for(String val: values) {
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
						LocalDateTime test = LocalDateTime.parse(record.get(valColumn).replace("Z", ""),formatter);
						LocalDateTime before = LocalDateTime.parse(val.replace("Z", ""),formatter);
						LocalDateTime after = before.plus(1,ChronoUnit.HOURS);
						if(DateCalc.isWithinRange(test, before, after)) {
							Double x = Double.parseDouble(record.get(column).replace(",", "."));
							temp.add(x);
						}
					}
				}
				counter++;
			}
			reader.close();
			return temp;
		} catch (IOException ex) {
		    ex.printStackTrace();
		}
		System.out.println("LIST IS EMPTY!");
		return temp;
	}
}


