package datamodel;


public class MeasurementRecord {
	
	private String date;
	private String time;
	private double sub1;
	private double sub2;
	private double sub3;
	
	
	public MeasurementRecord() {}
	
	
	public MeasurementRecord(String date, String time, double sub1, double sub2, double sub3) {
		date = this.date;
		time = this.time;
		sub1 = this.sub1;
		sub2 = this.sub2;
		sub3 = this.sub3;
	}
	
	public String getDate() {
		return date;
	}

	public String getTime() {
		return time;
	}

	public double getSub1() {
		return sub1;
	}

	public double getSub2() {
		return sub2;
	}

	public double getSub3() {
		return sub3;
	}



	public void setDate(String date) {
		this.date = date;
	}

	public void setTime(String time) {
		this.time = time;
	}


	public void setSub1(double sub1) {
		this.sub1 = sub1;
	}


	public void setSub2(double sub2) {
		this.sub2 = sub2;
	}


	public void setSub3(double sub3) {
		this.sub3 = sub3;
	}

		
}
