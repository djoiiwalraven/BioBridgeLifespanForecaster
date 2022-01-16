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
	        	for(int j = 0; j < 4; j++) {
		        	temp.add((double)i+j);
		        	temp2.add((double)i*j);
	        	}
	        	one.add(temp);
	        	two.add(temp2);
	        }
	        
	        List<Double> oneAns = new ArrayList<>();
	        List<Double> twoAns = new ArrayList<>();
	        
	        PredictionModel m = new PredictionModel("weigths");
	        
	        for(List<Double> lst : one) {
	        	oneAns.add(m.makePrediction(lst));
	        }
	        
	        for(List<Double> lst : two) {
	        	twoAns.add(m.makePrediction(lst));
	        }

	        
			BorderPane leftPane = new Graph("years", "Strain %", oneAns, oneAns.size(),1);
			BorderPane rightPane = new Graph("years", "Strain %", twoAns, twoAns.size(),1);
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
