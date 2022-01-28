package domain;

import java.util.List;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import utils.LineChartSerieHandler;

public class Graph extends BorderPane {
	
	//chart.addSeries(PrepareSeries.prepare(<name>, <dimensions y-axis>,<line function>) <color>); 
	private final String xname;
	private final String yname;
	private final String yname2;
	private final int DATA_START;
    private final int DATA_LENGTH;
    private final int DATA_STEPS;
    private NumberAxis xAxis;
    private NumberAxis yAxis;
    private LineChart<Number,Number> baseChart;
    private MultipleAxesLineChart bonusChart;


	public Graph(String xname, String yname, List<Double> data, int DATA_START, int DATA_LENGTH, int DATA_STEPS){
		
		this.xname = xname;
		this.yname = yname;
		this.yname2 = "";
		this.DATA_START = DATA_START;
		this.DATA_LENGTH = DATA_LENGTH;
		this.DATA_STEPS = DATA_STEPS;
		
	
		//RIGHT CHART
        this.xAxis = new NumberAxis(this.DATA_START, this.DATA_LENGTH, this.DATA_STEPS);
        this.yAxis = new NumberAxis();
        this.yAxis.setLabel(this.yname);
        this.xAxis.setLabel(this.xname);
        this.baseChart = new LineChart<Number, Number>(this.xAxis, this.yAxis);
        this.baseChart.setAnimated(false);
        this.baseChart.getData().clear();
        updateData(data);
        this.baseChart.setLegendVisible(false);
        this.setCenter(this.baseChart);
	}
	
public Graph(String xname, String yname, double[] data, int DATA_START, int DATA_LENGTH, int DATA_STEPS){
		
		this.xname = xname;
		this.yname = yname;
		this.yname2 = "";
		this.DATA_START = DATA_START;
		this.DATA_LENGTH = DATA_LENGTH;
		this.DATA_STEPS = DATA_STEPS;
		
	
		//RIGHT CHART
        this.xAxis = new NumberAxis(this.DATA_START, this.DATA_LENGTH, this.DATA_STEPS);
        this.yAxis = new NumberAxis();
        this.yAxis.setLabel(this.yname);
        this.xAxis.setLabel(this.xname);
        this.baseChart = new LineChart<Number, Number>(this.xAxis, this.yAxis);
        this.baseChart.setAnimated(false);
        this.baseChart.getData().clear();
        updateData(data);
        
        this.baseChart.setLegendVisible(false);
        this.setCenter(this.baseChart);
	}
	
	public Graph(String xname, String yname, String yname2, List<Double> data, List<Double> data2,int DATA_START, int DATA_LENGTH, int DATA_STEPS){
		
		this.xname = xname;
		this.yname = yname;
		this.yname2 = yname2;
		this.DATA_START = DATA_START;
		this.DATA_LENGTH = DATA_LENGTH;
		this.DATA_STEPS = DATA_STEPS;
		
        this.xAxis = new NumberAxis(this.DATA_START, this.DATA_START + this.DATA_LENGTH, this.DATA_STEPS);
        this.yAxis = new NumberAxis();
        this.yAxis.setLabel(this.yname);
        this.xAxis.setLabel(this.xname);
	
        this.baseChart = new LineChart<Number, Number>(this.xAxis, this.yAxis);
        this.baseChart.getData().clear();
        this.baseChart.setAnimated(false);
        this.bonusChart = new MultipleAxesLineChart(this.baseChart, Color.RED);
        
        updateData(data,data2);
        
        this.setCenter(bonusChart);
        this.setBottom(bonusChart.getLegend());
   
	}
	
public Graph(String xname, String yname, String yname2, double[] data, double[] data2, int DATA_START, int DATA_LENGTH, int DATA_STEPS){
		
		this.xname = xname;
		this.yname = yname;
		this.yname2 = yname2;
		this.DATA_START = DATA_START;
		this.DATA_LENGTH = DATA_LENGTH;
		this.DATA_STEPS = DATA_STEPS;
		
        this.xAxis = new NumberAxis(this.DATA_START, this.DATA_START + this.DATA_LENGTH, this.DATA_STEPS);
        this.yAxis = new NumberAxis();
        this.yAxis.setLabel(this.yname);
        this.xAxis.setLabel(this.xname);
	
        this.baseChart = new LineChart<Number, Number>(this.xAxis, this.yAxis);
        this.baseChart.setAnimated(false);
        this.baseChart.getData().clear();
        this.bonusChart = new MultipleAxesLineChart(this.baseChart, Color.RED);
        updateData(data,data2);
        
        this.setCenter(bonusChart);
        this.setBottom(bonusChart.getLegend());
   
	}
	
	public void updateData(List<Double> data){
		baseChart.getData().clear();
		baseChart.getData().add(LineChartSerieHandler.dataPoints(yname, data, DATA_LENGTH, Color.RED));
	}
	
	public void updateData(List<Double> data, List<Double> data2){
		baseChart.getData().clear();
		baseChart.getData().add(LineChartSerieHandler.dataPoints(yname, data, DATA_LENGTH, Color.RED));
        bonusChart.addSeries(LineChartSerieHandler.dataPoints(yname2, data2, DATA_LENGTH, Color.BLUE),Color.BLUE);
	}
	
	public void updateData(double[] data){
		baseChart.getData().clear();
		baseChart.getData().add(LineChartSerieHandler.dataPoints(yname, data, DATA_LENGTH, Color.RED));
	}
	
	public void updateData(double[] data, double[] data2){
		baseChart.getData().add(LineChartSerieHandler.dataPoints(yname, data, DATA_LENGTH, Color.RED));
		baseChart.getData().clear();
        bonusChart.addSeries(LineChartSerieHandler.dataPoints(yname2, data2, DATA_LENGTH, Color.BLUE),Color.BLUE);
	}
}
