package utils;

import java.util.List;
import java.util.function.Function;

import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PrepareSeries {
	 public static XYChart.Series<Number, Number> prepare(String name, int DATA_COUNT, Function<Integer, Double> function) {
		 XYChart.Series<Number, Number> series = new XYChart.Series<>();
		 series.setName(name);
		 for (int i = 0; i < DATA_COUNT; i++) {
			 series.getData().add(new XYChart.Data<>(i, function.apply(i)));
		 }
		 return series;
	 }
	 
	 public static XYChart.Series<Number, Number> dataPoints(String name, List<Double> data, int limit, Color cl){
		 XYChart.Series<Number, Number> series = new XYChart.Series<>();
		 series.setName(name);
		 for (int i = 0; i < data.size() && i < limit; i++) {
			 XYChart.Data<Number,Number> dataPoint = new XYChart.Data<Number,Number>(i,data.get(i));
			 //dataPoint.setNode(new Circle(5, cl));
			 
			 series.getData().add(dataPoint);
		 }
		 return series;
	 }
	 
	 public static XYChart.Series<Number, Number> dataPoints(String name, double[] data, int limit, Color cl){
		 XYChart.Series<Number, Number> series = new XYChart.Series<>();
		 series.setName(name);
		 for (int i = 0; i < data.length && i < limit; i++) {
			 XYChart.Data<Number,Number> dataPoint = new XYChart.Data<Number,Number>(i,data[i]);
			 //dataPoint.setNode(new Circle(5, cl));
			 series.getData().add(dataPoint);
		 }
		 return series;
	 }
}
