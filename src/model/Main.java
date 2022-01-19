package model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

import utils.GetParams;
import utils.OrganizeData;
import utils.ReadCSV;
import utils.WriteCSV;

public class Main {

	public static void main(String[] args) {

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
		
		List<CSVRecord> data = ReadCSV.returnAsList("/Volumes/MacOS/PAD/data/fullyOrganized.csv", ',', 0, true);
		
		double[] y = new double[data.size()];
		double[][] x = new double[data.size()][data.get(0).size()-2];
		
		for(int i = 0; i < data.size(); i++) {
			y[i] = Double.parseDouble(data.get(i).get(5));
			double[] temp = new double[data.get(0).size()-2];
			for(int j = 0; j < temp.length; j++) {
				x[i][j] = Double.parseDouble(data.get(i).get(j+1));
			}
		}
		
		for(double[] i : x) {
			for(double j : i) {
				System.out.print(j + ";");
			}
			System.out.println();
		}
		
		GetParams.writeParamsCSV(y, x,"src/data","params");
		
	}

}
