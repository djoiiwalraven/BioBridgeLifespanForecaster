package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.stage.Screen;

import utils.ReadCSVapache;
import utils.ReadFolder;

public class Main extends Application {
	
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
			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			root.setStyle("-fx-background-color:rgb(186,153,122,0.7);");
			primaryStage.setTitle("Bio-Bridge Lifespan Forecaster");
			
			// Responsive Resolution on start-up
	        int sceneWidth = 0;
	        int sceneHeight = 0;
	        
	        if (screenWidth <= 800 && screenHeight <= 600) {
	        	System.out.println("1");
	            sceneWidth = 600;
	            sceneHeight = 350;
	        } else if (screenWidth <= 1280 && screenHeight <= 768) {
	        	System.out.println("2");
	            sceneWidth = 800;
	            sceneHeight = 450;
	        } else if (screenWidth <= 1920 && screenHeight <= 1080) {
	        	System.out.println("3");
	            sceneWidth = 1800;
	            sceneHeight = 920;
	        }
			
			 // Scene
			Scene scene = new Scene(root,sceneWidth,sceneHeight);
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
		ReadFolder.read("src/data");
		
		//launch(args);
	}
}
