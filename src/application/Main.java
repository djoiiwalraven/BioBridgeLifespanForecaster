package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.StageStyle;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;

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
			primaryStage.setTitle("Bio-Bridge Lifespan Forecaster");
			
			BorderPane root = new BorderPane();
			root.setStyle("-fx-background-color:rgb(186,153,122,0.7);");
			
			
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
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			
			Text text = new Text();
			text.setText("Hello World!");
			text.setX(50);
			text.setY(50);
			
			root.getChildren().add(text);
			
			
			
			// Responsive resolution on drag
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
