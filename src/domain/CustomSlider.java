package domain;

import java.util.List;

import org.apache.commons.csv.CSVRecord;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import javafx.scene.layout.GridPane;

import utils.ReadCSV;

public class CustomSlider extends Slider{
	
	public CustomSlider(double min, double max, double ticks) {
		
		this.setMin(min);
		this.setMax(max);
		
		
        this.setShowTickLabels(true);
        this.setShowTickMarks(true);
        this.setBlockIncrement(1);
        this.setSnapToTicks(true);
        this.setMajorTickUnit(ticks);
        this.setMinorTickCount(0);
        
        this.setPrefWidth(500);
        
        this.setOnMouseReleased(getOnDragDetected());
        
	}
}
