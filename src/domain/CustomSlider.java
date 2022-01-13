package domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
//import javafx.scene.paint.Color;
import utils.ReadCSV;


public class CustomSlider extends BorderPane  {
	GridPane gridPane;
	Label[] labels ;
	String[] stringLabels;
	List<String> temp;
	List<CSVRecord> values;
	
	
	public CustomSlider ( String[] stringLabels) { 
		this.gridPane = new GridPane();
	    gridPane.setHgap(30);
	    gridPane.setVgap(5);
	    gridPane.setPadding(new Insets(25));
	    gridPane.setAlignment(Pos.BOTTOM_LEFT);
	    
	
	  	//this.temp = new ArrayList<>();
	  	
	  	this.labels = new Label[stringLabels.length];
	  	this.stringLabels = stringLabels;
	  	
	  	this.values = ReadCSV.returnAsList("/Volumes/MacOS/PAD/data/KNMIPrediction2050.csv",',',1,true);
	  	
	  	for(CSVRecord r:this.values) {
	  		for (String s:r) {
	  			System.out.print(s+";");
	  			
	  		}
	  		System.out.println("");
	  	}
	  	createSliders(stringLabels.length);
	  	this.setBottom(gridPane);
	  	
	}
	
	
	public void createSliders(int amount) {
		for(int i=0;i<amount;i++) {
			//temp.add(i*2, ReadCSV.returnAsList(file,',',1,true).get(1)); //adding 0 index to temp, with colmn1, row1
			//temp.add(i*2+1, ReadCSV.returnAsList(file,',',1,true).get(2));//adding 1index to temp with colmn1 row2
			double min = Double.parseDouble(values.get(0).get(1+i));
			double max = Double.parseDouble(values.get(1).get(1+i));
			Slider slider = new Slider();
			slider.setMin(min);
			slider.setMax(max);
			slider.setShowTickLabels(true);
	        slider.setShowTickMarks(true);
	        slider.setBlockIncrement(20);
			labels[i] = new Label(stringLabels[i]);
			gridPane.add(labels[i], 0, i);
			//gridPane.add(lbl,2,i);
			gridPane.add(slider, 1, i);
	    
		}
	}
	
}

	
	

	  
	      
	

		
	


