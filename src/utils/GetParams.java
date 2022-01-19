package utils;

import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;

public class GetParams {
	
public static void writeParamsCSV(double[] y, double[][] x, String path, String fileName) {
		OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();
		regression.newSampleData(y, x);
		System.out.println(regression.calculateRSquared());
		System.out.println(regression.calculateAdjustedRSquared());
		double[] beta = regression.estimateRegressionParameters();
		WriteCSV.write(path, fileName, beta );
	}
	
}
