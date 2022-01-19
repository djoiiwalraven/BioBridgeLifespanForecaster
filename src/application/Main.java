package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.stage.Stage;
import model.PredictionModel;
import javafx.stage.Screen;
import domain.CustomSlider;
import domain.Graph;

import javafx.scene.layout.BorderPane;
import javafx.scene.control.SplitPane;

import java.util.ArrayList;
import java.util.List;


public class Main extends Application {
	
	// Screen setup
	int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
    int screenHeight = (int) Screen.getPrimary().getBounds().getHeight();
    Stage stage;
    Scene scene;
    int initialX;
    int initialY;
    
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
	        List<List<Double>> one = new ArrayList<>();
	        List<List<Double>> two = new ArrayList<>();
	        
	        for(int i = 0 ; i < 20 ;i++) {
	        	List<Double> temp = new ArrayList<>();
	        	List<Double> temp2 = new ArrayList<>();
	        	temp.add(0d);
	        	temp.add(20d);
	        	temp.add(-15d+(5*i));
	        	temp.add(85.25d-(2*i));
	        	temp.add(0.99d+(10*i));
	        	
	        	//304.0,7.753805774278234,85.25301837270358,0.9976377952755906
	        	//1893.0,8.39816272965878,84.90971128608982,0.9989501312335956,-439.68162760416647
	        	//16.0,3.3568105065666067,80.79954971857391,2.2144090056285055,-616.2817361111117
	        	
	        	temp2.add(10000d);
	        	temp2.add(7.75d);
	        	temp2.add(85.25d);
	        	temp2.add(0.99d);
	        	
	        	one.add(temp);
	        	two.add(temp2);
	        }
	        
	        List<Double> oneAns = new ArrayList<>();
	        List<Double> twoAns = new ArrayList<>();
	        
	        PredictionModel m = new PredictionModel("params");
	        
	        for(List<Double> lst : one) {
	        	oneAns.add(m.makePrediction(lst));
	        }
	        
	        for(List<Double> lst : two) {
	        	twoAns.add(m.makePrediction(lst));
	        }

	        
			BorderPane leftPane = new Graph("traffic", "Strain %", oneAns, oneAns.size(),1);
			BorderPane rightPane = new Graph("traffic", "Strain %", twoAns, twoAns.size(),1);
			//BorderPane rightPane = new Graph("years", "Strain %", "Lifespan", one, two, (one.size() > two.size()) ? one.size() : two.size(),1);
	        
	       
	        
	        //CHARTS PANE
	        SplitPane charts = new SplitPane();
	        charts.getItems().addAll(leftPane, rightPane);
	        charts.setDividerPositions(0.5);
	        charts.maxWidthProperty().multiply(0.25);
	        leftPane.maxWidthProperty().bind(charts.widthProperty().multiply(0.5));
	        leftPane.minWidthProperty().bind(charts.widthProperty().multiply(0.5));
	        
	        //SET SLIDERS
	        String[] slidersArr1 = {"A","B","C"};
	        CustomSlider slider1 = new CustomSlider(slidersArr1);
	        String[] slidersArr2 = {"1","2","3"};
	        CustomSlider slider2 = new CustomSlider(slidersArr2);
	        
	        SplitPane sliders = new SplitPane();
	        sliders.getItems().addAll(slider1, slider2);
	        sliders.setDividerPositions(0.5);
	        sliders.maxWidthProperty().multiply(0.25);
	        slider1.maxWidthProperty().bind(charts.widthProperty().multiply(0.5));
	        slider2.minWidthProperty().bind(charts.widthProperty().multiply(0.5));
	        
	       
	        
	        //SET ROOT PANE
	        root.setCenter(charts);
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
