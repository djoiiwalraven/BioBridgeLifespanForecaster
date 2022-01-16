package domain;

import java.util.List;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import utils.PrepareSeries;

public class Graph extends BorderPane {
	
	//chart.addSeries(PrepareSeries.prepare(<name>, <dimensions y-axis>,<line function>) <color>); 
	private final String xname;
	private final String yname;
	private final String yname2;
    private final int DATA_LENGTH;
    private final int DATA_STEPS;
    private NumberAxis xAxis;
    private NumberAxis yAxis;
    private LineChart<Number,Number> baseChart;
    private MultipleAxesLineChart bonusChart;


	public Graph(String xname, String yname, List<Double> data, int DATA_LENGTH, int DATA_STEPS){
		
		this.xname = xname;
		this.yname = yname;
		this.yname2 = "";
		this.DATA_LENGTH = DATA_LENGTH;
		this.DATA_STEPS = DATA_STEPS;
	
		//RIGHT CHART
        this.xAxis = new NumberAxis(0, this.DATA_LENGTH, this.DATA_STEPS);
        this.yAxis = new NumberAxis();
        this.yAxis.setLabel(this.yname);
        this.xAxis.setLabel(this.xname);
        this.baseChart = new LineChart<Number, Number>(this.xAxis, this.yAxis);
        updateData(this.yname,data);
        this.baseChart.setLegendVisible(false);
        this.setCenter(this.baseChart);
	}
	
	public Graph(String xname, String yname, String yname2, List<Double> data, List<Double> data2, int DATA_LENGTH, int DATA_STEPS){
		
		this.xname = xname;
		this.yname = yname;
		this.yname2 = yname2;
		this.DATA_LENGTH = DATA_LENGTH;
		this.DATA_STEPS = DATA_STEPS;
		
        this.xAxis = new NumberAxis(0, this.DATA_LENGTH, this.DATA_STEPS);
        this.yAxis = new NumberAxis();
        this.yAxis.setLabel(this.yname);
        this.xAxis.setLabel(this.xname);
	
        this.baseChart = new LineChart<Number, Number>(this.xAxis, this.yAxis);
        this.bonusChart = new MultipleAxesLineChart(this.baseChart, Color.RED);
        updateData(this.yname,data,this.yname2,data2);
        
        this.setCenter(bonusChart);
        this.setBottom(bonusChart.getLegend());
   
	}
	
	public void updateData(String name, List<Double> data){
		baseChart.getData().add(PrepareSeries.dataPoints(name, data, DATA_LENGTH, Color.RED));
	}
	
	public void updateData(String name, List<Double> data, String name2, List<Double> data2){
		baseChart.getData().add(PrepareSeries.dataPoints(name, data, DATA_LENGTH, Color.RED));
        bonusChart.addSeries(PrepareSeries.dataPoints(name2, data2, DATA_LENGTH, Color.BLUE),Color.BLUE);
	}
}
