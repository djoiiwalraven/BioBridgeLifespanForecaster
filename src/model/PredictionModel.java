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
		this.fileName = path+"/"+fileName + ".csv";
		this.beta = new ArrayList<>();
		CSVRecord temp = ReadCSV.returnAsList(this.fileName, ',', 0, false).get(0);
		for(String str : temp) {
			this.beta.add(Double.valueOf(str));
		}
	}
	
	public double makePrediction(List<Double> values) {
		double prediction = 0;
		System.out.println(values.size() + "ROFL");
		System.out.println(beta.size());
		if(beta.size()-1 == values.size()) {
			for(int i = 1; i < beta.size(); i++) {
				prediction += (double)beta.get(i)*values.get(i-1);
			}
			prediction += (double)beta.get(0);
		}else {
			System.out.println("Input arrays need to have same lengths! prediction=0");
		}
		return prediction;
	}
	
	public double makePrediction(double[] values) {
		double prediction = 0;
		if(beta.size()-1 == values.length) {
			//traffic,temperature,humidity,wind speed;
			for(int i = 1; i < beta.size(); i++) {
				prediction += (double)beta.get(i)*values[i-1];
			}
			prediction += (double)beta.get(0);
		}else {
			System.out.println("Input arrays need to have same lengths! prediction=0");
		}
		return prediction;
	}
	
	public int lifeDecrease(double strain) {
		if(strain > 500d)
			return (int) (strain/250d+1);
		else
			return 0;
	}
	
}
