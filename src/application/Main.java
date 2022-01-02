package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.stage.Screen;

//MALC
import domain.MultipleAxesLineChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import utils.PrepareSeries;

import utils.ReadCSVapache;
import utils.ReadFolder;

public class Main extends Application {
	
	// Screen setup
	int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
    int screenHeight = (int) Screen.getPrimary().getBounds().getHeight();
    Stage stage;
    Scene scene;
    int initialX;
    int initialY;
    
    //MALC
    public static final int X_DATA_COUNT = 10;
    
	@Override
	public void start(Stage primaryStage) {
		
		try {
			//FXML
			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			root.setStyle("-fx-background-color:rgb(186,153,122,0.7);");
			primaryStage.setTitle("Bio-Bridge Lifespan Forecaster");
			
			// Responsive Resolution on start-up
	        int sceneWidth = 0;
	        int sceneHeight = 0;
	        
	        if (screenWidth <= 800 && screenHeight <= 600) {
	            sceneWidth = 600;
	            sceneHeight = 350;
	        } else if (screenWidth <= 1280 && screenHeight <= 768) {
	            sceneWidth = 800;
	            sceneHeight = 450;
	        } else if (screenWidth <= 1920 && screenHeight <= 1080) {
	            sceneWidth = 1800;
	            sceneHeight = 920;
	        }
	        
	        //MALC
	        NumberAxis xAxis = new NumberAxis(0, X_DATA_COUNT, 1);
	        NumberAxis yAxis = new NumberAxis();
	        xAxis.setLabel("years");
	        
	        
	        //chart.addSeries(PrepareSeries.prepare(<name>, <dimensions y-axis>,<line function>) <color>); 
	        List<String> lowTraffic = new ArrayList<String>();
	        List<Double> strains = new ArrayList<Double>();
	        lowTraffic = ReadCSVapache.readTraffic("/Volumes/MacOS/PAD/data/traffic/verkeer.csv", 0,1,2,3);
			strains = ReadCSVapache.read("/Volumes/MacOS/PAD/data/strain-group5/strain#20501.csv", 2, lowTraffic, 0);
	        
			for(Double s : strains) {
				System.out.println(s);
			}
			
	        LineChart baseChart = new LineChart(xAxis, yAxis);
	        baseChart.getData().add(PrepareSeries.dataPoints("Strain %", strains, 10));
	        MultipleAxesLineChart chart = new MultipleAxesLineChart(baseChart, Color.RED);
	        chart.addSeries(PrepareSeries.dataPoints("Lifespan", strains, 10),Color.BLUE);
	        
	        
	        BorderPane bp = new BorderPane();
	        bp.setCenter(chart);
	        bp.setBottom(chart.getLegend());
	        
			
			 // Scene
			Scene scene = new Scene(bp,sceneWidth,sceneHeight);
			scene.getStylesheets().add(getClass().getResource("Main.css").toExternalForm());
			
			// Responsive scene resolution on drag  (replace primaryStage w/ stage)?
	        scene.setOnMousePressed(m -> {
	            if (m.getButton() == MouseButton.PRIMARY) {
	                scene.setCursor(Cursor.MOVE);
	                initialX = (int) (stage.getX() - m.getScreenX());
	                initialY = (int) (stage.getY() - m.getScreenY());
	            }
	        });

	        scene.setOnMouseDragged(m -> {
	            if (m.getButton() == MouseButton.PRIMARY) {
	                stage.setX(m.getScreenX() + initialX);
	                stage.setY(m.getScreenY() + initialY);
	            }
	        });

	        scene.setOnMouseReleased(m -> {
	            scene.setCursor(Cursor.DEFAULT);
	        });
	        
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
