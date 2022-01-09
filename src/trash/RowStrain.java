package trash;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class RowStrain {
	private String Datum;//2019-11-27T17:34:39Z
	private LocalDate date;
	private LocalTime time;
	private String Sensor;
	private Double Waarde;
	private String Unit;
	private String Brugdee;
	private Double Kopafstand;
	private String Element;
	private String Primaire;
	private String[] arr;

	public RowStrain(List<String> arr) {
//		Datum=new Date(Date.parse(arr.get(0)));
		String[] datetime=arr.get(0).split("T");
		date=LocalDate.parse(datetime[0]);
		time=LocalTime.parse(datetime[1].split("Z")[0]);
		Datum=arr.get(0);
		Sensor=arr.get(1);
		if(arr.get(2).equals("NaN")) //Waarde=Double.MAX_VALUE;
				Waarde=Double.NaN;
		else Waarde=Double.parseDouble(arr.get(2).replace(",", "."));
		Unit=arr.get(3);
		Brugdee=arr.get(4);
		Kopafstand=Double.parseDouble(arr.get(5).replace(",", "."));
		Element=arr.get(6);
		Primaire=arr.get(7);
	}

	public String getDatum() {
		return Datum;
	}

	public String getSensor() {
		return Sensor;
	}

	public LocalDate getDate() {
		return date;
	}

	public LocalTime getTime() {
		return time;
	}

	public Double getWaarde() {
		return Waarde;
	}

	public String getUnit() {
		return Unit;
	}

	public String getBrugdee() {
		return Brugdee;
	}

	public Double getKopafstand() {
		return Kopafstand;
	}

	public String getElement() {
		return Element;
	}

	public String getPrimaire() {
		return Primaire;
	}

	public String[] getArr() {
		return arr;
	}


}
