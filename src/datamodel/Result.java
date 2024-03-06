package datamodel;


import java.util.ArrayList;
import java.util.HashMap;

public class Result implements IResult {
	
	String description;
	String aggFunction;
	String timeUnit;
	
	HashMap<String, ArrayList<MeasurementRecord>> detailedResults = new HashMap<String, ArrayList<MeasurementRecord>>();
	HashMap<String,	Double> aggregateMeterKitchen = new HashMap<String, Double>();
	HashMap<String, Double> aggregateMeterLaundry = new HashMap<String,Double>();
	HashMap<String, Double> aggregateMeterAC = new HashMap<String, Double>();
	
	public Result(){}
	
	
	@Override
	public int add(String timeUnit, MeasurementRecord record) {
		 ArrayList<MeasurementRecord> inputList = detailedResults.get(timeUnit);
		    // if list does not exist create it
		    if(inputList == null) {
		         inputList = new ArrayList<MeasurementRecord>();
		         inputList.add(record);
		         detailedResults.put(timeUnit, inputList);
		    } else {
		        // add if item is not already in list
		       if(!inputList.contains(record)) inputList.add(record);
		    }
		
		return 0;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public HashMap<String, ArrayList<MeasurementRecord>> getDetailedResults() {
			
		return detailedResults;
	}

	@Override
	public HashMap<String, Double> getAggregateMeterKitchen() {
		return aggregateMeterKitchen;
	}

	@Override
	public HashMap<String, Double> getAggregateMeterLaundry() {
		return aggregateMeterLaundry;
	}

	@Override
	public HashMap<String, Double> getAggregateMeterAC() {
		return aggregateMeterAC;
	}

	@Override
	public String getAggregateFunction() {
		return aggFunction;
	}
	
	public void setAggregateFunction(String aggFunction) {
		this.aggFunction = aggFunction;
	}
	

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getTimeUnit() {
		return timeUnit;
	}


	public void setTimeUnit(String timeUnit) {
		this.timeUnit = timeUnit;
	}
	

}
