package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import trash.ReadFolder;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.stage.Screen;
import domain.CustomSlider;
//MALC
import domain.MultipleAxesLineChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.SplitPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import utils.PrepareSeries;

import utils.ReadCSV;

public class Main extends Application {
	
	// Screen setup
	int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
    int screenHeight = (int) Screen.getPrimary().getBounds().getHeight();
    Stage stage;
    Scene scene;
    int initialX;
    int initialY;
    
    static final int YEARS = 10;
    static final int TRAFFIC = 50;
    
    //MALC
    //static final int X_DATA_COUNT = 8;
    
	@Override
	public void start(Stage primaryStage) {
		
		try {
			//FXML
			BorderPane root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			root.setStyle("-fx-background-color:rgb(186,153,122,0.7);");
			primaryStage.setTitle("Bio-Bridge Lifespan Forecaster");
			
			System.out.println();
			// Responsive Resolution on start-up
	        int sceneWidth = 0;
	        int sceneHeight = 0;
	        
	        if( screenWidth >= 1920 && screenHeight >= 1080) {
	        	System.out.println("BIG");
	        	sceneWidth = 1812;
	            sceneHeight = 1020;
	        } else if ( screenWidth >= 1280 && screenHeight >= 768) {
	        	System.out.println("MEDIUM");
	        	sceneWidth = 1200;
	            sceneHeight = 675;
	        } else if (screenWidth >= 800 && screenHeight >= 600) {
	        	System.out.println("SMALL");
	            sceneWidth = 720;
	            sceneHeight = 405;
	        } else {
	        	sceneWidth = 480;
	            sceneHeight = 270;
	        }
	        
	        //MALC DATA
	        //chart.addSeries(PrepareSeries.prepare(<name>, <dimensions y-axis>,<line function>) <color>); 
	        List<String> lowTraffic = new ArrayList<String>();
	        List<Double> strainData = new ArrayList<Double>();
	        List<Double> lifespanData = new ArrayList<Double>();
	        //lowTraffic = ReadCSVapache.readTraffic("/Volumes/MacOS/PAD/data/traffic/verkeer.csv");
			//strains.add(ReadCSVapache.getAverageStrain("/Volumes/MacOS/PAD/data/strain-group1/strain#10106.csv", 2, lowTraffic, 0));
			//strains.add(ReadCSVapache.getAverageStrain("/Volumes/MacOS/PAD/data/strain-group2/strain#10201.csv", 2, lowTraffic, 0));
			//strains.add(ReadCSVapache.getAverageStrain("/Volumes/MacOS/PAD/data/strain-group3/strain#10301.csv", 2, lowTraffic, 0));
			//strains.add(ReadCSVapache.getAverageStrain("/Volumes/MacOS/PAD/data/strain-group4/strain#20401.csv", 2, lowTraffic, 0));
			//strains.add(ReadCSVapache.getAverageStrain("/Volumes/MacOS/PAD/data/strain-group5/strain#20501.csv", 2, lowTraffic, 0));
			//strains.add(ReadCSVapache.getAverageStrain("/Volumes/MacOS/PAD/data/strain-group6/strain#20601.csv", 2, lowTraffic, 0));
			//strains.add(ReadCSVapache.getAverageStrain("/Volumes/MacOS/PAD/data/strain-group7/strain#20704.csv", 2, lowTraffic, 0));
			//strains.add(ReadCSVapache.getAverageStrain("/Volumes/MacOS/PAD/data/strain-group8/strain#20801.csv", 2, lowTraffic, 0));
	        //random values
			strainData.add(-200d);
	        strainData.add(500d);
	        strainData.add(0d);
	        strainData.add(180d);
	        strainData.add(-73d);
	        strainData.add(-400d);
	        strainData.add(-300d);
	        strainData.add(89d);
			
	        lifespanData.add(100d);
	        lifespanData.add(80d);
	        lifespanData.add(40d);
	        lifespanData.add(20d);
	        lifespanData.add(18d);
	        lifespanData.add(12d);
	        lifespanData.add(5d);
	        lifespanData.add(2d);
			
			//MALC LEFT
	        NumberAxis lxAxis = new NumberAxis(0, YEARS, 1);
	        NumberAxis lyAxis = new NumberAxis();
	        lyAxis.setLabel("Strain %");
	        lxAxis.setLabel("years");
			
	        
	        LineChart baseLeftChart = new LineChart(lxAxis, lyAxis);
	        baseLeftChart.getData().add(PrepareSeries.dataPoints("Strain %", strainData, YEARS, Color.RED));
	        MultipleAxesLineChart leftChart = new MultipleAxesLineChart(baseLeftChart, Color.RED);
	        leftChart.addSeries(PrepareSeries.dataPoints("Lifespan", lifespanData, YEARS, Color.BLUE),Color.BLUE);
	        
	        
	        //CHART RIGHT
	        NumberAxis rxAxis = new NumberAxis(0, TRAFFIC, 10);
	        NumberAxis ryAxis = new NumberAxis();
	        ryAxis.setLabel("Strain %");
	        rxAxis.setLabel("Traffic");
	        LineChart rightChart = new LineChart(rxAxis, ryAxis);
	        rightChart.getData().add(PrepareSeries.dataPoints("Strain %", strainData, TRAFFIC, Color.ORANGE));
	        rightChart.setLegendVisible(false);
	        
	        //CHART PANES
	        BorderPane leftPane = new BorderPane();
	        BorderPane rightPane = new BorderPane();
	        leftPane.setCenter(leftChart);
	        leftPane.setBottom(leftChart.getLegend());
	        rightPane.setCenter(rightChart);
	        
	        //CHARTS PANE
	        SplitPane charts = new SplitPane();
	        charts.getItems().addAll(leftPane, rightPane);
	        charts.setDividerPositions(0.5);
	        charts.maxWidthProperty().multiply(0.25);
	        leftPane.maxWidthProperty().bind(charts.widthProperty().multiply(0.5));
	        leftPane.minWidthProperty().bind(charts.widthProperty().multiply(0.5));
	        
	        //SET ROOT PANE
	        root.setCenter(charts);
	        String[] slidersArr = {"A","B","C"};
	        CustomSlider sliders = new CustomSlider(slidersArr);
	        root.setBottom(sliders);
	        
	        //SET SCENE
			Scene scene = new Scene(root,sceneWidth,sceneHeight);
			scene.getStylesheets().add(getClass().getResource("Main.css").toExternalForm());
	        
	        // Stage
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
