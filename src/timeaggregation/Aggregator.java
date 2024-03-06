package timeaggregation;

import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDate;

import datamodel.IResult;
import datamodel.Result;
import datamodel.MeasurementRecord;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;



public class Aggregator implements IAggregator {
	
	String timeUnitType;
	ArrayList<MeasurementRecord> inputMeasurements = new ArrayList<MeasurementRecord>() ;
	
	public Aggregator(){}
	
	
	@Override
	public IResult aggregateByTimeUnit(ArrayList<MeasurementRecord> inputMeasurements, String aggFunction,
			String description) {
		
		Result outputResult = new Result();
		outputResult.setDescription(description);
		outputResult.setAggregateFunction(aggFunction);
		
		//process the input measurements and categorise them
		MeasurementRecord measurementObj = null;
		for(int i = 0; i < inputMeasurements.size() ; i++) {
			measurementObj = inputMeasurements.get(i);
			String strTime = measurementObj.getTime().split(":")[0];
			if(strTime == "00") {
				strTime = "0";
			}else if(strTime.split("")[0] == "0") {
				strTime.replace("0", "");
			}
			int time = Integer.valueOf(strTime);
			
			String[] dateArray = measurementObj.getDate().split("/");
			String strDate = dateArray[1];
			int date = Integer.valueOf(strDate);
			
			LocalDate localDate = LocalDate.of(Integer.valueOf(dateArray[2]), Integer.valueOf(dateArray[1]), Integer.valueOf(dateArray[0]));
			String day = localDate.getDayOfWeek().name();
			
			//time unit aggregation
			if(timeUnitType == "periodofday") {	
				if(time >= 5 && time < 9){
				outputResult.add("EARLY MORNING", measurementObj);
				outputResult.setTimeUnit("EARLY MORNING");
				}else if(time >= 9 && time < 13){
					outputResult.add("MORNING", measurementObj);
					outputResult.setTimeUnit("MORNING");
				}else if(time >= 13 && time < 17){
					outputResult.add("AFTERNOON", measurementObj);
					outputResult.setTimeUnit("AFTERNOON");
				}else if(time >= 17 && time < 21){
					outputResult.add("EVENING", measurementObj);
					outputResult.setTimeUnit("EVENING");
				}else{
					outputResult.add("NIGHT  ", measurementObj);
					outputResult.setTimeUnit("NIGHT");
				}
				
			}else if (timeUnitType == "dayofweek"){
				if (day == "MONDAY") {
					outputResult.add("MONDAY", measurementObj);
					outputResult.setTimeUnit("MONDAY");
				}else if (day == "TUESDAY") {
					outputResult.add("TUESDAY", measurementObj);
					outputResult.setTimeUnit("TUESDAY");
				}else if (day == "WEDNESDAY") {
					outputResult.add("WEDNESDAY", measurementObj);
					outputResult.setTimeUnit("WEDNESDAY");
				}else if (day == "THURSDAY") {
					outputResult.add("THURSDAY", measurementObj);
					outputResult.setTimeUnit("THURSDAY");
				}else if (day == "FRIDAY") {
					outputResult.add("FRIDAY", measurementObj);
					outputResult.setTimeUnit("FRIDAY");
				}else if (day == "SATURDAY") {
					outputResult.add("SATURDAY", measurementObj);
					outputResult.setTimeUnit("SATURDAY");
				}else if (day == "SUNDAY") {
					outputResult.add("SUNDAY", measurementObj);
					outputResult.setTimeUnit("SUNDAY");
				}
			}else if (timeUnitType == "month") {
				if(date == 1) {
					outputResult.add("January  ", measurementObj);
					outputResult.setTimeUnit("January");
				}else if (date == 2) {
					outputResult.add("February ", measurementObj);
					outputResult.setTimeUnit("February");
				}else if (date == 3) {
					outputResult.add("March    ", measurementObj);
					outputResult.setTimeUnit("March");
				}else if (date == 4) {
					outputResult.add("April    ", measurementObj);
					outputResult.setTimeUnit("April");
				}else if (date == 5) {
					outputResult.add("May      ", measurementObj);
					outputResult.setTimeUnit("May");
				}else if (date == 6) {
					outputResult.add("June     ", measurementObj);
					outputResult.setTimeUnit("June");
				}else if (date == 7) {
					outputResult.add("July     ", measurementObj);
					outputResult.setTimeUnit("July");
				}else if (date == 8){
					outputResult.add("August   ", measurementObj);
					outputResult.setTimeUnit("August");
				}else if (date == 9) {
					outputResult.add("September", measurementObj);
					outputResult.setTimeUnit("September");
				}else if (date == 10) {
					outputResult.add("October  ", measurementObj);
					outputResult.setTimeUnit("October");
				}else if (date == 11) {
					outputResult.add("November", measurementObj);
					outputResult.setTimeUnit("November");
				}else if (date == 12) {
					outputResult.add("December", measurementObj);
					outputResult.setTimeUnit("December");
				}
			}else if(timeUnitType == "season"){
				if(date >= 3 && date <= 5) {
					outputResult.add("SPRING", measurementObj);
					outputResult.setTimeUnit("SPRING");
				}else if(date >= 6 && date <= 8) {
					outputResult.add("SUMMER", measurementObj);
					outputResult.setTimeUnit("SUMMER");
				}else if (date >= 9 && date <= 11) {
					outputResult.add("AUTUMN", measurementObj);
					outputResult.setTimeUnit("AUTUMN");
				}else {
					outputResult.add("WINTER", measurementObj);
					outputResult.setTimeUnit("WINTER");
				}
			}else {
				System.out.println("Invalid time unit type");
			}
		}
		
		ArrayList<String> keys = new ArrayList<String>(); 
		for(String name: outputResult.getDetailedResults().keySet()) {
			keys.add(name);
		}
			
		
		for(int j = 0; j < outputResult.getDetailedResults().size(); j ++) {
		
			DescriptiveStatistics statsSub1 = new DescriptiveStatistics();
			DescriptiveStatistics statsSub2 = new DescriptiveStatistics();
			DescriptiveStatistics statsSub3 = new DescriptiveStatistics();
			
			//getting the size of the ArrayLists from the detailedResults
			HashMap<String , ArrayList<MeasurementRecord>> sizeArray = outputResult.getDetailedResults();
			String key = keys.get(j);
			ArrayList<MeasurementRecord> sizeArrayList = sizeArray.get(key);	
			int size = sizeArrayList.size();
			
			
			
			//Creating statistics for Kitchen,Laundry and AC  
			for( int i = 0; i < size; i++) {
				statsSub1.addValue(outputResult.getDetailedResults().get(key).get(i).getSub1());
		        statsSub2.addValue(outputResult.getDetailedResults().get(key).get(i).getSub2());
		        statsSub3.addValue(outputResult.getDetailedResults().get(key).get(i).getSub3());
		        
			}
			
			if(aggFunction == "avg") {
				
				double meanSub1 = statsSub1.getMean();
				double meanSub2 = statsSub2.getMean();
				double meanSub3 = statsSub3.getMean();
				outputResult.getAggregateMeterKitchen().put(key, meanSub1);
				outputResult.getAggregateMeterLaundry().put(key, meanSub2);
				outputResult.getAggregateMeterAC().put(key, meanSub3);
				
			}else if(aggFunction == "sum"){
				
				double sumSub1 = statsSub1.getSum();
				double sumSub2 = statsSub2.getSum();
				double sumSub3 = statsSub3.getSum();
				
				outputResult.getAggregateMeterKitchen().put(key, sumSub1);
				outputResult.getAggregateMeterLaundry().put(key, sumSub2);
				outputResult.getAggregateMeterAC().put(key, sumSub3);
			}
		
		}
		
		return outputResult;
	}

	@Override
	public String getTimeUnitType() {
		return timeUnitType;
	}
	
	public void setTimeUnitType(String timeUnitType) {
		this.timeUnitType = timeUnitType;
	}
	
	public void setInputMeasurements(ArrayList<MeasurementRecord> inputMeasurements) {
		inputMeasurements = this.inputMeasurements;
	}
	
	public ArrayList<MeasurementRecord> getInputMeasurements() {
		return inputMeasurements;
	}
	

}
