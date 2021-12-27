package utils;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class ReadFolder {

	public static void read(String file) {
		// TODO Auto-generated method stub
		DataHolder dh=new DataHolder();
		File directory=new File(file);
		if(directory.isDirectory()) {
			System.out.println(directory);
			for(File f:directory.listFiles()) {
				if(f.isFile())//We will read only files
				try {
					Reader reader = Files.newBufferedReader(Paths.get(f.toURI()));
					Iterable<CSVRecord> records = CSVFormat.newFormat(';').parse(reader);
					int i=0;
					for(CSVRecord o:records) {
						if(i++==0) continue;//Skip first line
						dh.add(new RowStrain(o.toList()));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		for(LocalDate x:dh.dates()) {
			System.out.println(x+" -> Average: "+dh.getDateMap().get(x).getMean()+" StDev: "+dh.getDateMap().get(x).getStandardDeviation());
		}
		System.out.println("<============Sensor Data==================>");
		for(String s:dh.sensors()) {
			System.out.println(s+" -> Average: "+dh.getSensorMap().get(s).getMean()+" StDev: "+dh.getSensorMap().get(s).getStandardDeviation());
		}
	}

}
