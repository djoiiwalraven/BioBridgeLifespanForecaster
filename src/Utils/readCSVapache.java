package Utils;


import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;



public class readCSVapache {
	public static void main(String[] args) {
		try {
			Reader reader= Files.newBufferedReader(Paths.get("./data/diabetes .csv"));// create reader to read files
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);// reads the file
			

			
			Scanner in = new Scanner(System.in);
			System.out.println("which first column do you want:");
			int column1=in.nextInt();
			System.out.println("which first column do you want:");
			int column2=in.nextInt();
			
			
			for(CSVRecord record:records) {
				System.out.println(record.get(column1-1)+"\t"+record.get(column2-1));
			}
			reader.close();
			
			}
			   
		catch (IOException ex) {
		    ex.printStackTrace();
		}
		
		
		
 }
}


