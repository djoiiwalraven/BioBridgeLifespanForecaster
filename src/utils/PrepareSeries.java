package utils;

import java.util.List;
import java.util.function.Function;

import javafx.scene.chart.XYChart;

public class PrepareSeries {
	 public static XYChart.Series<Number, Number> prepare(String name, int DATA_COUNT, Function<Integer, Double> function) {
		 XYChart.Series<Number, Number> series = new XYChart.Series<>();
		 series.setName(name);
		 for (int i = 0; i < DATA_COUNT; i++) {
			 series.getData().add(new XYChart.Data<>(i, function.apply(i)));
		 }
		 return series;
	 }
	 
	 public static XYChart.Series<Number, Number> dataPoints(String name, List<Double> data, int limit){
		 XYChart.Series<Number, Number> series = new XYChart.Series<>();
		 
		 series.setName(name);
		 for (int i = 0; i < data.size() && i < limit; i++) {
			 series.getData().add(new XYChart.Data<>(i,data.get(i)));
		 }
		 
		 return series;
	 }
}
