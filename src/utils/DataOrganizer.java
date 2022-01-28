package utils;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class DataOrganizer {
	
	public static double calcSMA(double[] input) {
		double movingAverage = 0;
		
		for(double i : input) {
			movingAverage += i;
		}
		movingAverage /= input.length;
		
		return movingAverage;
	}
	
	public static void removeNaN() {
		List<List<String>> org = new ArrayList<List<String>>();
		List<String> headers = new ArrayList<String>();
		
		// Date;Strain%;
		String[] header = {"DateTime","Strain%"};
		for(String str: header) {
			headers.add(str);
		}
		org.add(headers);
				
		for(CSVRecord strain: CSVHandler.returnAsList("/Volumes/MacOS/PAD/data/DateCombinedStrainWNaN.csv", ',', 0,  true)) {
			//System.out.println(strain);
			if(!strain.get(1).equals("NaN")) {
				List<String> temp = new ArrayList<String>();
				temp.add(strain.get(0));
				temp.add(strain.get(1));
				org.add(temp);
			}
		}
		String[][] arr = org.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
		CSVHandler.write("/Volumes/MacOS/PAD/data", "DateCombinedStrainReduced",arr);
		System.out.println("FINSIHED");
	}
	
	public static void combineAll() {
		List<List<String>> org = new ArrayList<List<String>>();
		List<String> headers = new ArrayList<String>();
		
		// Date;Traffic;Temperature;Humidity;Wind;
		String[] header = {"DateTime","Traffic","Temperature","Humidity","Windspeed","Strain%"};
		for(String str: header) {
			headers.add(str);
		}
		org.add(headers);
		
		List<CSVRecord> strains = CSVHandler.returnAsList("/Volumes/MacOS/PAD/data/DateCombinedStrainReduced.csv", ',', 0,  false);
		
		int count = 0;
		for(CSVRecord strain : strains) {
			count++;
			System.out.println(count*100/strains.size() + "%");
			String currentTime = strain.get(0);
			for(CSVRecord meta : CSVHandler.returnAsList("/Volumes/MacOS/PAD/data/organizedData.csv", ',', 0,true)) {
				if(currentTime.equals(DateCalculator.returnDateTime(meta.get(0)))){
					//System.out.println(meta.get(0));
					List<String> temp = new ArrayList<String>();
					temp.add(strain.get(0)); //date
					temp.add(meta.get(1)); //traffic
					temp.add(meta.get(2)); //temperature
					temp.add(meta.get(3)); //humidity
					temp.add(meta.get(4)); //windspeed
					temp.add(strain.get(1)); //strain%
					org.add(temp);
				}
			}
		}
		
		String[][] arr = org.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
		CSVHandler.write("/Volumes/MacOS/PAD/data", "fullyOrganized",arr);
		System.out.println("FINSIHED");
		//List<CSVRecord> rec = ReadCSV.returnAsList("/Volumes/MacOS/PAD/data/organizedData.csv",',',0,true);
	}
	
	public static void combineMeteoTraffic() {
		List<List<String>> org = new ArrayList<List<String>>();
		List<String> headers = new ArrayList<String>();
		
		// Date;Traffic;Temperature;Humidity;Wind;
		String[] header = {"DateTime","Traffic","Temperature","Humidity","Windspeed"};
		for(String str: header) {
			headers.add(str);
		}
		org.add(headers);
		
		for(CSVRecord meteo : CSVHandler.returnAsList("/Volumes/MacOS/PAD/data/meteo/reducedMeteo.csv", ',', 0,  true)) {
			String currentTime = DateCalculator.returnDateTime(meteo.get(0));
			for(CSVRecord traffic : CSVHandler.returnAsList("/Volumes/MacOS/PAD/data/traffic/verkeer.csv", ';', 0,true)) {
				if(currentTime.equals(DateCalculator.returnDateTime(traffic.get(0)))){
					System.out.println(traffic.get(0));
					List<String> temp = new ArrayList<String>();
					temp.add(traffic.get(0));
					double kg80 = 0; // kg80 = 1 traffic = 1 average person;
					kg80 += Double.parseDouble(traffic.get(1));
					kg80 += Double.parseDouble(traffic.get(2));
					kg80 += Double.parseDouble(traffic.get(3))*20;
					temp.add(String.valueOf(kg80));
					temp.add(String.valueOf(meteo.get(1)));
					temp.add(String.valueOf(meteo.get(2)));
					temp.add(String.valueOf(meteo.get(3)));
					org.add(temp);
				}
			}
		}
		
		String[][] arr = org.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
		CSVHandler.write("/Volumes/MacOS/PAD/data", "organizedData",arr);
		//List<CSVRecord> rec = CSVHandler.returnAsList("/Volumes/MacOS/PAD/data/organizedData.csv",',',0,true);
	}
	
	public static void reduceMeteoToHours(char seperator, int notAllowedEmpty, boolean hasHeader) {
		List<List<String>> tempList = new ArrayList<List<String>>();
		List<String> headers = new ArrayList<String>();
		
		String[] header = {"DateTime","Temperature","Humidity","Windspeed"};
		for(String str: header) {
			headers.add(str);
		}
		tempList.add(headers);
		
		try {
			Reader reader = Files.newBufferedReader(Paths.get("/Volumes/MacOS/PAD/data/meteo/meteo.csv"));
			Iterable<CSVRecord> records = CSVFormat.newFormat(seperator).parse(reader);
			
			int previous = -1;
			
			String currentDate = "";
			double temp = 0;
			double rain = 0;
			double wind = 0;
			int avgCounter = 0;
			
			for(CSVRecord record : (hasHeader == true) ? CSVHandler.skipFirst(records) : records) {
				if(record.get(notAllowedEmpty) != "") {
					int current = DateCalculator.returnHour(record.get(0));
					
					if(previous < 0) {
						previous = current;
						currentDate = DateCalculator.returnDateTime(record.get(0));
						System.out.println(currentDate);
					}
					if(current != previous) {
						List<String> tempRec = new ArrayList<String>();
						temp /= avgCounter;
						rain /= avgCounter;
						wind /= avgCounter;
						tempRec.add(currentDate);
						tempRec.add(String.valueOf((Double)temp));
						tempRec.add(String.valueOf((Double)rain));
						tempRec.add(String.valueOf((Double)wind));
						tempList.add(tempRec);
						previous = current;
						currentDate = DateCalculator.returnDateTime(record.get(0));
						temp = 0;
						rain = 0;
						wind = 0;
						avgCounter = 0;
						System.out.println(currentDate);
					}
					if(current == previous) {
						temp += Double.parseDouble(record.get(1).replace(",", "."));
						rain += Double.parseDouble(record.get(4).replace(",", "."));
						wind += Double.parseDouble(record.get(2).replace(",", "."));
						avgCounter++;
					}
				}
			}
			List<String> tempRec = new ArrayList<String>();
			temp /= avgCounter;
			rain /= avgCounter;
			wind /= avgCounter;
			tempRec.add(currentDate);
			tempRec.add(String.valueOf((Double)temp));
			tempRec.add(String.valueOf((Double)rain));
			tempRec.add(String.valueOf((Double)wind));
			tempList.add(tempRec);
			
			String[][] arr = tempList.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
			CSVHandler.write("/Volumes/MacOS/PAD/data/meteo", "reducedMeteo", arr);
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	//TOM's
	
	public static void combined(String file1,String file2) {
		List<List<String>> org = new ArrayList<List<String>>();
		List<String> headers = new ArrayList<String>();
		
		// Date;Traffic;Temperature;Humidity;Wind;
		String[] header = {"DateTime","Average Strain"};
		for(String str: header) {
			headers.add(str);
		}
		org.add(headers);
		for(CSVRecord strain1:CSVHandler.returnAsList(file1, ',', 0,true)) {
			String currentTime = strain1.get(0);
			for(CSVRecord strain2:CSVHandler.returnAsList(file2, ',', 0,true)) {
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
		CSVHandler.write("./data/", "vastLine5",arr);
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
				int current = DateCalculator.returnHour(r.get(0));
				
				if(previous<0) {
					previous=current;
					currentdate = DateCalculator.returnDateTime(r.get(0));
				}
				
				if(current!= previous) {
					List<String> tempRec =new ArrayList<String>();
					strain /=avgCounter;
					tempRec.add(currentdate);
					tempRec.add(String.valueOf((Double)strain));
					tempList.add(tempRec);
					previous=current;
					currentdate=DateCalculator.returnDateTime(r.get(0));
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
			CSVHandler.write("./data/","reducedStrain20714", arr);
		}catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}
}