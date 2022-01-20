package domain;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import javafx.scene.layout.GridPane;

import utils.ReadCSV;


public class CustomSliders extends GridPane  {
	
	List<Label> labels;
	String[] stringLabels;
	List<CSVRecord> values;
	
	public CustomSliders (String[] stringLabels, String file) { 
	    this.setHgap(30);
	    this.setVgap(5);
	    this.setPadding(new Insets(25));
	    this.setAlignment(Pos.TOP_LEFT);
	    
	  	this.values = ReadCSV.returnAsList(file,',',1,true);
	  	this.stringLabels = stringLabels;
	  	this.labels = new ArrayList<Label>();
	  	
	  	createLabels();
	  	createSliders(this.stringLabels.length); 	
	}
	
	public void createLabels() {
		for(String str : stringLabels) {
			labels.add(new Label(str));
		}
	}
	
	public void createSliders(int amount) {
		for(int i=0 ;i < amount;i++) {
			CustomSlider slider;
			double min;
			double max;
			switch(i) {
				case 0:
					min = Double.parseDouble(values.get(0).get(amount-i));
					max = Double.parseDouble(values.get(1).get(amount-i));
					slider = new CustomSlider(min,max,0.01d);
					break;
				case 1:
					min = Double.parseDouble(values.get(0).get(amount-i));
					max = Double.parseDouble(values.get(1).get(amount-i));
					slider = new CustomSlider(min,max,0.2d);
					break;
				case 2:
					min = Double.parseDouble(values.get(0).get(amount-i));
					max = Double.parseDouble(values.get(1).get(amount-i));
					slider = new CustomSlider(min,max,0.2d);
					break;
				case 3:
					min = Double.parseDouble(values.get(0).get(amount-i));
					max = Double.parseDouble(values.get(1).get(amount-i));
					slider = new CustomSlider(min,max,250d);
					break;
				default:
					min = Double.parseDouble(values.get(0).get(amount-i));
					max = Double.parseDouble(values.get(1).get(amount-i));
					slider = new CustomSlider(min,max,1d);
					break;
			}
	
			labels.get(i).setMinWidth(100);
			this.add(labels.get(amount-1-i), 0, i);
			this.add(slider, 1, i);
		}
	}
}

	
	

	  
	      
	

		
	


