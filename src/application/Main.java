package application;
	
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.PredictionModel;
import javafx.stage.Screen;
import domain.CustomSlider;
import domain.CustomSliders;
import domain.Graph;

import javafx.scene.layout.BorderPane;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.event.ChangeListener;


public class Main extends Application {
	
	// Screen setup
	int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
    int screenHeight = (int) Screen.getPrimary().getBounds().getHeight();
    Stage stage;
    Scene scene;
    int initialX;
    int initialY;
    
    final int START_YEAR = 0;
    final int YEARS_INCREMENT = 1;
    final int MAX_YEARS = 50;
    
    final int START_TRAFFIC = 0;
    final int TRAFFIC_INCREMENT = 1;
    final int MAX_TRAFFIC = 100; //*50;
    
    int currentYear = START_YEAR;
    
    Slider yearSlider;
    HashMap<Slider,Double> inputYears = new HashMap<>();
    HashMap<Integer,Double> outputYears = new HashMap<>();
    
    HashMap<Slider,Double> inputTraffic = new HashMap<>();
    HashMap<Integer,Double> outputTraffic = new HashMap<>();
    
    Slider dummy = new Slider();
    
    //List<Slider> slidersYears = new ArrayList<Slider>();
    //List<Slider> slidersTraffic = new ArrayList<Slider>();
    
	@Override
	public void start(Stage primaryStage) {
		
		try {
			//FXML
			BorderPane root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			root.setStyle("-fx-background-color:rgb(186,153,122,0.7);");
			primaryStage.setTitle("Bio-Bridge Lifespan Forecaster");
			
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
	        
	        
	        PredictionModel m = new PredictionModel("params");
	        
	        //SET SLIDERS
	        String fileSliderKNMI = "src/data/KNMIPrediction2050.csv";
	        String fileSliderData = "src/data/DataMinMaxValues.csv"; //DataMinMaxValues.csv;
	        String[] slidersArr1 = {"Time (yrs)","Traffic Weigth","Temperature (C)", "Humidity (%)", "Wind (m/s)"};
	        CustomSliders slidersLeft = new CustomSliders(slidersArr1,fileSliderKNMI);
	        String[] slidersArr2 = {"Temperature (C)", "Humidity (%)","Wind (m/s)"};
	        CustomSliders slidersRight = new CustomSliders(slidersArr2,fileSliderData);
	        
	        SplitPane sliders = new SplitPane();
	        sliders.getItems().addAll(slidersLeft, slidersRight);
	        sliders.setDividerPositions(0.5);
	        sliders.maxWidthProperty().multiply(0.25);
	        slidersLeft.maxWidthProperty().bind(sliders.widthProperty().multiply(0.5));
	        slidersRight.minWidthProperty().bind(sliders.widthProperty().multiply(0.5));
	        
	        // INITIALIZE DATA IN GRAPHS
	        
	        //YEARS
	        for(int i = 0; i < slidersLeft.getChildren().size()-1; i++) {
	        	if(slidersLeft.getChildren().get(i) instanceof Slider) {
	        		Slider s = (Slider)slidersLeft.getChildren().get(i);
	        		inputYears.put(s, s.getValue());
	        	}
	        }
	        for(int year = START_YEAR; year < START_YEAR+MAX_YEARS; year+=YEARS_INCREMENT) {
	        	List<Double> temp = new ArrayList<Double>(inputYears.values());
	        	outputYears.put(year, m.makePrediction(temp));
	        }
	        List<Double> initYears = new ArrayList<Double>(outputYears.values());
	       
	        
	        //TRAFFIC
	        for(int i = 0; i < slidersRight.getChildren().size(); i++) {
	        	if(slidersRight.getChildren().get(i) instanceof Slider) {
	        		Slider s = (Slider)slidersRight.getChildren().get(i);
	        		inputTraffic.put(s, s.getValue());
	        	}
	        }
	        for(int traffic = START_TRAFFIC; traffic < START_TRAFFIC+MAX_TRAFFIC; traffic+=TRAFFIC_INCREMENT) {
	        	System.out.println(traffic);
	        	List<Double> temp = new ArrayList<Double>(inputTraffic.values());
	        	
	        	List<Double> temp2 = new ArrayList<Double>();
	        	temp2.add((double)traffic*500);
	        	for(double t : temp) {
	        		temp2.add(t);
	        	}
	        	System.out.println(temp2.size() + "SIZE");
	        	outputTraffic.put(traffic, m.makePrediction(temp2));
	        }
	        List<Double> initTraffic = new ArrayList<Double>(outputTraffic.values());
	        
	        
	        
	        //SET GRAPHS WITH DATA
	        
	        Graph leftPane = new Graph("Years Passed", "Strain %", "Lifespan", initYears, initYears, START_YEAR,MAX_YEARS,YEARS_INCREMENT);
	     	Graph rightPane = new Graph("Traffic Weigth x50", "Strain %", initYears, START_TRAFFIC, MAX_TRAFFIC,TRAFFIC_INCREMENT);
	     	
	     	// SET EVENTS
	     	
	     	yearSlider = (Slider)slidersLeft.getChildren().get(slidersLeft.getChildren().size()-1);
	     	yearSlider.setOnMouseReleased( e -> {
	     		currentYear = (int) yearSlider.getValue();
	     		System.out.println(currentYear);
        	});
	     	
	     	for(Slider s : inputYears.keySet()) {
	        	s.setOnMouseReleased( new GraphEventHandler(leftPane) {
	        		@Override
	        	    public void handle(Event event) {
	        			inputYears.put(s,s.getValue());
		        		List<Double> temp = new ArrayList<Double>(inputYears.values());
			        	outputYears.put(currentYear, m.makePrediction(temp));
			        	List<Double> temp2 = new ArrayList<Double>(outputYears.values());
			        	System.out.println(temp2);
			        	Graph a = getGraph();
			        	a.updateData(temp2);
	        	    }
	        	});
	        }
	     	
	     	for(Slider s : inputTraffic.keySet()) {
	        	s.setOnMouseReleased( new GraphEventHandler(rightPane) {
	        		@Override
	        	    public void handle(Event event) {
	        			inputTraffic.put(s,s.getValue());
		        		List<Double> temp = new ArrayList<Double>(inputTraffic.values());
		        		for(int traffic = START_TRAFFIC; traffic < START_TRAFFIC+MAX_TRAFFIC; traffic+=TRAFFIC_INCREMENT) {
		        			List<Double> temp2 = new ArrayList<Double>();
		    	        	temp2.add((double)traffic*50);
		    	        	for(double t : temp) {
		    	        		temp2.add(t);
		    	        	}
		        			outputYears.put(traffic, m.makePrediction(temp2));
		        		}
			        	List<Double> temp3 = new ArrayList<Double>(outputTraffic.values());
			        	System.out.println(temp3);
			        	Graph a = getGraph();
			        	a.updateData(temp3);
	        	    }
	        	});
	        }
	     	
	     	        //CHARTS PANE
	     	SplitPane charts = new SplitPane();
	     	charts.getItems().addAll(leftPane, rightPane);
	     	charts.setDividerPositions(0.5);
	     	charts.maxWidthProperty().multiply(0.25);
	     	leftPane.maxWidthProperty().bind(charts.widthProperty().multiply(0.5));
	     	leftPane.minWidthProperty().bind(charts.widthProperty().multiply(0.5));
	        
	        //SET ROOT PANE
	        root.setCenter(charts);
	        root.setCenter(sliders);
	        
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

abstract class GraphEventHandler implements EventHandler<Event>
{
    private Graph g;

    public GraphEventHandler(Graph g) {
        this.g = g;
    }

    public Graph getGraph() {
        return g;
    }
}
