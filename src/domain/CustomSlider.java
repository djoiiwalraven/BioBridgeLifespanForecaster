package domain;

import javafx.scene.control.Slider;

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
