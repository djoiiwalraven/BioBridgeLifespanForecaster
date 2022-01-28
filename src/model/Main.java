package model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

import utils.DataOrganizer;
import utils.CSVHandler;

import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

public class Main {

	public static void main(String[] args) {

		List<Double> output = new ArrayList<Double>();
		
		double[] year1 = {2,4,2,5,3,6,3,7};
		double[] year2 = {2,4,2,5,5,6,3,7};
		double[] year3 = {2,4,2,5,9,6,8,7};
		double[] year4 = {9,4,5,5,9,6,8};
		
		output.add(DataOrganizer.calcSMA(year1));
		output.add(DataOrganizer.calcSMA(year2));
		output.add(DataOrganizer.calcSMA(year3));
		output.add(DataOrganizer.calcSMA(year4));
		
		for(double o : output) {
			System.out.println(o);
		}
	
		
		List<CSVRecord> data = CSVHandler.returnAsList("src/data/fullyOrganized.csv", ',', 0, true);
		
		PearsonsCorrelation pc = new PearsonsCorrelation();
		
		
		
		double[][] matrix = new double[data.size()][data.get(0).size()-1];
		
		for(int i = 0; i < data.size(); i++) {
			for(int j = 0; j < data.get(0).size()-1; j++) {
				matrix[i][j] = Double.parseDouble(data.get(i).get(j+1));
			}
		}
		
		RealMatrix correlationMatrix = pc.computeCorrelationMatrix(matrix);
		
		String[] headers = {"Traffic","Temperature","Humidity","Windspeed", "Strain%"};
		
		System.out.print(":      ");
		for(String txt : headers) {
			System.out.print(txt + " : ");
		}
		System.out.println();
		
		double[][] cm = correlationMatrix.getData();
		
		for(int i  = 0; i <  cm.length; i++) {
			System.out.print(headers[i] + ": ");
			for(int j = 0; j < cm[i].length; j++) {
				System.out.print(cm[i][j] + " ; ");
			}
			System.out.println();
		}
		
		
		double[] y = new double[data.size()];
		double[][] x = new double[data.size()][data.get(0).size()-2];
		
		for(int i = 0; i < data.size(); i++) {
			y[i] = Double.parseDouble(data.get(i).get(5));
			//double[] temp = new double[data.get(0).size()-2];
			for(int j = 0; j < data.get(0).size()-2; j++) {
				x[i][j] = Double.parseDouble(data.get(i).get(j+1));
			}
		}
		
		/*
		for(double[] i : x) {
			for(double j : i) {
				System.out.print(j + ";");
			}
			System.out.println();
		}*/
		
		PredictionModel.writeParamsCSV(y, x,"src/data","params2");
		
	}

}
