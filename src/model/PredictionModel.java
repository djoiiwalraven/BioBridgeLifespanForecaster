package model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

import utils.ReadCSV;

public class PredictionModel {
	private final static String path = "src/data";
	
	private String fileName;
	
	private List<Double> beta; //Weights finalized
	
	public PredictionModel(String fileName){
		this.fileName = fileName + ".csv";
		beta = new ArrayList<>();
		CSVRecord temp = ReadCSV.returnAsList(this.fileName, ',', 0, false).get(0);
		System.out.println(temp);
		for(String str : temp) {
			beta.add(Double.valueOf(str));
		}
	}
	
	public double makePrediction(List<Double> values) {
		double prediction = 0;
		if(beta.size() == values.size()) {
			for(int i = 1; i < beta.size(); i++) {
				prediction += beta.get(i)*values.get(i-1);
			}
			prediction += beta.get(0); 
		}else {
			System.out.println("Input arrays need to have same lengths! prediction=0");
		}
		return prediction;
	}
	
	public double error(double realAnswer, double prediction) {
		return realAnswer - prediction;
	}
	public static int lifespanWeather(double temperature,double humidity,double wind,double strain) {
		int lifespanYear=2120;
		int lifespanDecrease=0;
		if(Math.abs(strain)>500) {
			lifespanDecrease+=(strain*temperature+strain*humidity+strain*wind)/10000;
		}
		else lifespanDecrease+= 0 ;
		return lifespanYear-lifespanDecrease;
		
	}
	public static int lifespanStrain(double strain) {
		int lifespanYear=2120;
		int lifespanDecrease=0;
		if(Math.abs(strain)>500) {
			lifespanDecrease-=Math.sqrt((strain)/50);
		}
		else lifespanDecrease-= 0 ;
		return lifespanYear-lifespanDecrease;
		
	}
	
	
	
}
