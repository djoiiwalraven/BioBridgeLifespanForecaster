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
	String file;
	List<String> temp;
	Label[] labels ;
	String[] stringLabels;
	List<CSVRecord> values;
	
	
	public CustomSlider ( String[] stringLabels) { 
		this.gridPane = new GridPane();
		this.values = ReadCSV.returnAsList(file,',',1,true);
	    gridPane.setHgap(30);
	    gridPane.setVgap(5);
	    gridPane.setPadding(new Insets(25));
	    
	    gridPane.setAlignment(Pos.BOTTOM_LEFT);
	  	this.file = "C:\\Users\\oreli\\Desktop\\KNMIPrediction2050.csv";
	  	this.temp = new ArrayList<>();
	  	this.labels = new Label[stringLabels.length];
	  	this.stringLabels = stringLabels;
	  	for(CSVRecord r:values) {
	  		for (String s:r) {
	  			System.out.print(s+";");
	  			
	  		}
	  		System.out.println("");
	  	}
	  	//createSliders(stringLabels.length);
	  	this.setBottom(gridPane);
	  	
	}
	
	
//	public void createSliders(int amount) {
//		for(int i=0;i<amount;i++) {
//			temp.add(i*2, ReadCSV.returnAsList(file,',',1,true).get(1)); //adding 0 index to temp, with colmn1, row1
//			temp.add(i*2+1, ReadCSV.returnAsList(file,',',1,true).get(2));//adding 1index to temp with colmn1 row2
//			double min = Double.parseDouble(temp.get(0+i*2));
//			double max = Double.parseDouble(temp.get(1+i*2));
//			Slider slider = new Slider();
//			slider.setMin(min);
//			slider.setMax(max);
//			slider.setShowTickLabels(true);
//	        slider.setShowTickMarks(true);
//	        slider.setBlockIncrement(20);
//			labels[i] = new Label(stringLabels[i]);
//			gridPane.add(labels[i], 0, i);
//			//gridPane.add(lbl,2,i);
//			gridPane.add(slider, 1, i);
//	    
//		}
	
}

	
	

	  
	      
	

		
	


