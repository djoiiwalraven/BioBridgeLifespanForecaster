package utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;





public class createCSVDataStrain{

	public static void combined(String file1,String file2) {
		List<List<String>> org = new ArrayList<List<String>>();
		List<String> headers = new ArrayList<String>();
		
		// Date;Traffic;Temperature;Humidity;Wind;
		String[] header = {"DateTime","Average Strain"};
		for(String str: header) {
			headers.add(str);
		}
		org.add(headers);
		int i =0;
		for(CSVRecord strain1:ReadCSV.returnAsList(file1, ',', 0,true)) {
			String currentTime = strain1.get(0);
			for(CSVRecord strain2:ReadCSV.returnAsList(file2, ',', 0,true)) {
				if(currentTime.equals(strain2.get(0))){
					List<String> temp = new ArrayList<String>();
					temp.add(strain2.get(0));
					double avgStrain= 0;
					avgStrain += (Double.parseDouble(strain2.get(1))+Double.parseDouble(strain1.get(1)))/2;
					
					temp.add(String.valueOf(avgStrain));
					org.add(temp);
				}
			}
		}
		String[][] arr = org.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
		WriteCSV.write("./data/", "vastLine5",arr);
	
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
	
	
	public static void reduceStrainHours(String file) {
		List<List<String>> tempList = new ArrayList<List<String>>();
		List<String> headers = new ArrayList<String>();
		
		String[] header = {"DateTime","Strain/Waarde"};
		for(String str: header) {
			headers.add(str);
		}
		tempList.add(headers);
		try {
			CSVParser parser=CSVParser.parse(new File(file), StandardCharsets.UTF_8,
			        CSVFormat.DEFAULT.withFirstRecordAsHeader().withDelimiter(';').withTrim().withNullString(""));
			int previous= -1;
			
			String currentdate="";
			double strain=0;
			int avgCounter=0;
			
			
			for(CSVRecord r:parser) {
				int current = DateCalc.returnHour(r.get(0));
				
				if(previous<0) {
					previous=current;
					currentdate = DateCalc.returnDateTime(r.get(0));
				}
				
				if(current!= previous) {
					List<String> tempRec =new ArrayList<String>();
					strain /=avgCounter;
					tempRec.add(currentdate);
					tempRec.add(String.valueOf((Double)strain));
					tempList.add(tempRec);
					previous=current;
					currentdate=DateCalc.returnDateTime(r.get(0));
					strain=0;
					avgCounter = 0;
				
				}
				if(current==previous) {
					strain += Double.parseDouble(r.get(2).replace(",","."));
					avgCounter++;
				}
			}
			List<String> tempRec = new ArrayList<String>();
			strain /=avgCounter;
			tempRec.add(currentdate);
			tempRec.add(String.valueOf((Double)strain));
			tempList.add(tempRec);
			
			
			
			
			String[][] arr = tempList.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
			WriteCSV.write("./data/","reducedStrain20714", arr);
		}catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}
}