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
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


public class ReadCSV {
	
	public static <T> Iterable<T> skipFirst(final Iterable<T> c) {
	    return new Iterable<T>() {
	        @Override public Iterator<T> iterator() {
	            Iterator<T> i = c.iterator();
	            i.next();
	            return i;
	        }
	    };
	}
	
	public static List<CSVRecord> returnAsList(String file, char seperator, int notAllowedEmpty, boolean header) {
		List<CSVRecord> tempList = new ArrayList<CSVRecord>();
		try {
			Reader reader = Files.newBufferedReader(Paths.get(file));// create reader to read files
			Iterable<CSVRecord> records = CSVFormat.newFormat(seperator).parse(reader);// reads the file
			for(CSVRecord record: (header == true) ? skipFirst(records) : records) {
				if(record.get(notAllowedEmpty) != "") {
					tempList.add(record);
				}
			}
			reader.close();
			return tempList;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		System.out.println("LIST IS EMPTY!");
		return tempList;
	}
	
	public static List<String> returnAsList(String file, int column, char seperator) {
		List<String> temp = new ArrayList<String>();
		try {
			Reader reader= Files.newBufferedReader(Paths.get(file));// create reader to read files
			Iterable<CSVRecord> records = CSVFormat.newFormat(seperator).parse(reader);// reads the file
			
			for(CSVRecord record:records) {
				temp.add(record.get(column));
				System.out.println(record.get(column));
			}
			reader.close();
			return temp;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		System.out.println("LIST IS EMPTY!");
		return temp;
	}
	
	public static List<String> readTraffic(String file){
		List<String> temp = new ArrayList<String>();
		try {
			Reader reader = new FileReader(file);// create reader to read files
			Iterable<CSVRecord> records = CSVFormat.newFormat(';').parse(reader);// reads the file
			
			int counter = 0;
			for(CSVRecord record : records) {
				if(counter > 0) {
					double x = Double.parseDouble(record.get(1));
					double y = Double.parseDouble(record.get(2));
					double z = Double.parseDouble(record.get(3));
					double k = Double.parseDouble(record.get(4));
					if(x <= 0 && y <= 0 && y <= 0 && k <= 0) {
						String a = record.get(0);
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
	public static Double getAverageStrain(String file , int column, List<String> values, int valColumn) {
		try {
			Reader reader= Files.newBufferedReader(Paths.get(file));// create reader to read files
			Iterable<CSVRecord> records = CSVFormat.newFormat(';').parse(reader);// reads the file
			int avg = 0;
			int avgCounter = 0;
			int counter = 0;
			for(CSVRecord record:records) {
				if(counter > 0) {
					String test = record.get(valColumn);
					String before = values.get(0);
					if(DateCalc.isWithinRange(test, before)) {
						Double x = Double.parseDouble(record.get(column).replace(",", "."));
						avg += x;
						avgCounter++;
					}
				}
				counter++;
			}
			reader.close();
			System.out.println(file);
			return (double)avg/avgCounter;
		} catch (IOException ex) {
		    ex.printStackTrace();
		}
		System.out.println("LIST IS EMPTY!");
		return 0d;
	}
	
	public static double[] getAverageOnDateTime(String file, char seperator, int timeColumn, int[] columns, boolean header, String initTime) {
		double[] averages = new double[columns.length];
		try {
			Reader reader= Files.newBufferedReader(Paths.get(file));// create reader to read files
			Iterable<CSVRecord> records = CSVFormat.newFormat(seperator).parse(reader);// reads the file
			int avgCounter = 0;
			int counter = 0;
			for(CSVRecord record: (header == true) ? skipFirst(records) : records) {
				if(record.get(columns[0]) != "") {
					String test = record.get(timeColumn);
					String before = initTime;
					if(DateCalc.isWithinRange(test, before)) {
						System.out.println(test);
						for(int i = 0; i < columns.length; i++) {
							averages[i] += Double.parseDouble(record.get(columns[i]).replace(",", "."));
						}
						avgCounter++;
					}
				}
			}
			for(int i = 0; i < columns.length; i++) {
				averages[i] /= avgCounter;
			}
			reader.close();
			return averages;
		} catch (IOException ex) {
		    ex.printStackTrace();
		}
		System.out.println("LIST IS EMPTY!");
		return averages;
	}
	
	public static void compare(String file, int column1, int column2) {
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
}


