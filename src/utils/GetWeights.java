package utils;

import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;

public class GetWeights {
	
public static void writeCSV(double[] y, double[][] x, String path, String fileName) {
		OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();
		regression.newSampleData(y, x);
		double[] beta = regression.estimateRegressionParameters();
		WriteCSV.write(path, fileName, beta );
	}
	
}
