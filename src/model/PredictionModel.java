package model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;

import utils.CSVHandler;

public class PredictionModel {
	private final static String path = "src/data";
	
	private String fileName;
	
	private List<Double> beta; //Weights finalized
	
	public static void writeParamsCSV(double[] y, double[][] x, String path, String fileName) {
		OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();
		
		
		regression.newSampleData(y, x);
		System.out.println(regression.calculateRSquared());
		System.out.println(regression.calculateAdjustedRSquared());
		
		double[] beta = regression.estimateRegressionParameters();
		double[] residuals = regression.estimateResiduals();
		double[][] parametersVariance = regression.estimateRegressionParametersVariance();
		double regressandVarience = regression.estimateRegressandVariance();
		double stadnardErrors[] = regression.estimateRegressionParametersStandardErrors();
		
		System.out.println(residuals);
		System.out.println(parametersVariance);
		System.out.println(regressandVarience);
		System.out.println(stadnardErrors);
				
		for(double d : beta) {
			System.out.print(d + " - ");
		}
		System.out.println();
		CSVHandler.write(path, fileName, beta );
	}
	
	public PredictionModel(String fileName){
		this.fileName = path+"/"+fileName + ".csv";
		this.beta = new ArrayList<>();
		CSVRecord temp = CSVHandler.returnAsList(this.fileName, ',', 0, false).get(0);
		for(String str : temp) {
			this.beta.add(Double.valueOf(str));
		}
	}
	
	public double makePrediction(List<Double> values) {
		double prediction = 0;
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
