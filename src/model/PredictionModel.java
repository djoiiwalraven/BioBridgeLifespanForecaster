package model;

import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;

import utils.WriteCSV;

public class PredictionModel {
	private final static String path = "src/data";
	
	private String fileName;
	private OLSMultipleLinearRegression regression;
	
	private double[] beta; //Weights finalized
	
	public PredictionModel(String fileName){
		this.regression = new OLSMultipleLinearRegression();
		this.fileName = fileName;
	}
	
	public double makePredicttion(double[] beta, double[] values) {
		double prediction = 0;
		if(beta.length == values.length) {
			for(int i = 1; i < beta.length; i++) {
				prediction += beta[i]*values[i-1];
			}
			prediction += beta[0]; 
		}else {
			System.out.println("Input arrays need to have same lengths! prediction=0");
		}
		return prediction;
	}
	
	public double error(double realAnswer, double prediction) {
		return realAnswer - prediction;
	}
	
	
	
	
}
