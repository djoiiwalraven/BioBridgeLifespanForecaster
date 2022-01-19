package domain;


import java.util.List;

import org.apache.commons.csv.CSVRecord;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import javafx.scene.layout.GridPane;

import utils.ReadCSV;


public class CustomSlider extends GridPane  {
	
	Label[] labels ;
	String[] stringLabels;
	List<CSVRecord> values;
	
	
	public CustomSlider ( String[] stringLabels, String file) { 
	    this.setHgap(30);
	    this.setVgap(5);
	    this.setPadding(new Insets(25));
	    this.setAlignment(Pos.TOP_LEFT);
	  	this.values = ReadCSV.returnAsList(file,',',1,true);
	  	this.stringLabels = stringLabels;
	  	this.labels = new Label[stringLabels.length];
	  	createSliders(stringLabels.length); 	
	}
	
	
	public void createSliders(int amount) {
		double MIN;
		double MAX;
		for(int i=0;i<amount;i++) {
			if(amount > 3 ) {
				if(i < 2) {
					int min = Integer.parseInt(values.get(0).get(1+i));
					int max = Integer.parseInt(values.get(1).get(1+i));
					MIN = min;
					MAX = max;
				}else {
					double min = Double.parseDouble(values.get(0).get(1+i));
					double max = Double.parseDouble(values.get(1).get(1+i));
					MIN = min;
					MAX = max;
				}
			}else {
				double min = Double.parseDouble(values.get(0).get(1+i));
				double max = Double.parseDouble(values.get(1).get(1+i));
				MIN = min;
				MAX = max;
			}
			
			Slider slider = new Slider();
			slider.setMin(MIN);
			slider.setMax(MAX);
			slider.setPrefWidth(250);
			slider.setShowTickLabels(true);
	        slider.setShowTickMarks(true);
	        slider.setBlockIncrement(10);
	        slider.setMajorTickUnit(10);
			labels[i] = new Label(stringLabels[i]);
			this.add(labels[i], 0, i);
			this.add(slider, 1, i);
		}
	
}
}

	
	

	  
	      
	

		
	


