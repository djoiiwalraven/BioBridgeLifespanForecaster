package trash;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class DataHolder {
	Map<LocalDate,DescriptiveStatistics> map;
	Map<String,DescriptiveStatistics> sensormap;

	public DataHolder() {
		map=new TreeMap<>();
		sensormap=new TreeMap<>();
	}

	public void add(RowStrain rs) {
		//Adding for map
		DescriptiveStatistics ds;
		ds=map.get(rs.getDate());
		if(ds==null) {
			ds=new DescriptiveStatistics();
			map.put(rs.getDate(), ds );
		}
		//Adding for sensormap
		DescriptiveStatistics ds2;
		ds2=sensormap.get(rs.getSensor());
		if(ds2==null) {
			ds2=new DescriptiveStatistics();
			sensormap.put(rs.getSensor(), ds2);
		}
		if(!rs.getWaarde().isNaN()) {
			ds.addValue(rs.getWaarde());
			ds2.addValue(rs.getWaarde());
		}
		//if(v.getWaarde()!=Double.MAX_VALUE) ds.addValue(rs.getWaarde());
	}

	public Map<String, DescriptiveStatistics> getSensorMap() {
		return sensormap;
	}
	public Set<String> sensors(){
		return sensormap.keySet();
	}
	public Map<LocalDate, DescriptiveStatistics> getDateMap() {
		return map;
	}

	public Set<LocalDate> dates() {
		return map.keySet();
	}

}
