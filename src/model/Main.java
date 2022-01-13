package model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

import utils.OrganizeData;
import utils.ReadCSV;
import utils.WriteCSV;

public class Main {

	public static void main(String[] args) {
		
		/*
		 * When adding a Star to an object .. of this class.
		 * it should only really store the star itself .. in its internal data.
		 * but when retrieving a value .. as in (myPlanetCollection(myStar)
		 * it will return a List with the planets .. (generated at that time)
		 */
		
		
		/* // SMA 
		List<Double> output = new ArrayList<Double>();
		
		double[] year1 = {2,4,2,5,3,6,3,7};
		double[] year2 = {2,4,2,5,5,6,3,7};
		double[] year3 = {2,4,2,5,9,6,8,7};
		double[] year4 = {9,4,5,5,9,6,8};
		
		output.add(SMA.calcSMA(year1));
		output.add(SMA.calcSMA(year2));
		output.add(SMA.calcSMA(year3));
		output.add(SMA.calcSMA(year4));
		
		for(double o : output) {
			System.out.println(o);
		}
		*/
		//String[] arr = {"DateTime","Traffic","Temp","Rain","Wind"};
		//OrganizeData.weatherTraffic(arr);
		
		OrganizeData.combineMeteoTraffic();
		
		/* // write csv test
		String[][] arr = {
				{"temp", "rain", "wind", "traffic",},
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
		*/
		
		
		/* // write weights csv test
		// a b c y
		// 1 4 8 2
		// 2 2 4 3
		// 8 3 2 12
		// 12 12 8 35
		
		double[] y = {2,3,12,35};
		double[][] x = {
				{1,4,8},
				{2,2,4},
				{8,3,2},
				{12,12,8}
			};
		
		
		GetParams.writeWeigthsCSV(y, x);
		*/
	}

}
