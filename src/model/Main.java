package model;

import java.util.List;

import org.apache.commons.csv.CSVRecord;

import utils.ReadCSV;
import utils.WriteCSV;

public class Main {

	public static void main(String[] args) {
		
		String[][] arr = {
				{"A", "B", "C", "D"},
				{"1","2"}
		};
		
		WriteCSV.write("/Users/ddwalraven/Downloads", "arr",arr);
		
		List<CSVRecord> rec = ReadCSV.returnAsList("/Users/ddwalraven/Downloads/arr.csv",',',0,true);
		
		for(CSVRecord record: rec) {
			for(String str: record) {
				System.out.print(str + " ; ");
			}
			System.out.println();
		}
	}

}
