package model;

public class SMA {
	public static double calcSMA(double[] input) {
		double movingAverage = 0;
		
		for(double i : input) {
			movingAverage += i;
		}
		movingAverage /= input.length;
		
		return movingAverage;
	}
}
