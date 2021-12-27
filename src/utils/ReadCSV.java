package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import utils.Struct;

public class ReadCSV {
	
	public static void read(String file) {
		// TODO Auto-generated method stub
		try {  
			
			Scanner in = new Scanner(new File(file));// check the path full doesn t work and path from data either
			ArrayList<Struct> multiString=new ArrayList<Struct>();// ask for tree set
			while(in.hasNext()) {//Reading file and storing into RAM
					multiString.add( new Struct(in.nextLine().split(";")) );
					//System.out.println(multiString.get(multiString.size()-1).getArr()[3]+"   "+multiString.get(multiString.size()-1).getArr()[2]);
			}
			System.out.println("Choose the right column by number to show:");
			int i=0;
			for(String col:multiString.get(0).getArr()) {
				System.out.println("Option "+(i++)+": "+col);
			}
			System.out.println("Choose the option that you want: ");
			Scanner input=new Scanner(System.in);
			int colIndex1=input.nextInt();
			for(Struct st:multiString)
				System.out.println(st.getArr()[colIndex1]);
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
