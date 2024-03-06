package mainengine;

import java.util.ArrayList;
import datamodel.IResult;
import datamodel.MeasurementRecord;
import datamodel.Result;
import reporting.ResultReporterHtml;
import reporting.ResultReporterTxt;
import reporting.ResultReportermd;
import dataload.Loader;
import timeaggregation.Aggregator;

public class MainEngine <E> implements IMainEngine {
	
	ArrayList<MeasurementRecord> objCollection = new ArrayList<MeasurementRecord>();
	ArrayList<MeasurementRecord> inputMeasurements;
	Aggregator measurementsAggregator1 = new Aggregator();
	
	public MainEngine(){}
	
	@Override
	public int loadData(String fileName, String delimeter, Boolean hasHeaderLine, int numFields,
			ArrayList<MeasurementRecord> objCollection) {
				
		Loader<E> loadedFile = new Loader<>(fileName,delimeter,hasHeaderLine,numFields); 
		int numLines = loadedFile.load(fileName , delimeter, hasHeaderLine, numFields, loadedFile.getObjCollection());
		
		int rows = 0;
		String[] tempArray = null;//temporary holds the data from the line input 
		
		for(int i = 0; i < numLines; i++) {
			String tempString = loadedFile.getObjCollection().get(i).toString();//converting the object item to string 
			tempArray = tempString.split(delimeter);
			MeasurementRecord tempMR = new MeasurementRecord();
			
			tempMR.setDate(tempArray[0]);
			tempMR.setTime(tempArray[1]);
			tempMR.setSub1(Double.valueOf(tempArray[6]));
			tempMR.setSub2(Double.valueOf(tempArray[7]));
			tempMR.setSub3(Double.valueOf(tempArray[8]));
			
			objCollection.add(tempMR);
			
			rows++;
		}
		measurementsAggregator1.setInputMeasurements(objCollection);
		return rows;
	}

	@Override
	public IResult aggregateByTimeUnit(ArrayList<MeasurementRecord> inputMeasurements, String aggregatorType,
			String aggFunction, String description) {
		Aggregator measurementsAggregator = new Aggregator();
		
		measurementsAggregator.setInputMeasurements(inputMeasurements);
		measurementsAggregator.setTimeUnitType(aggregatorType);
		
		
		Result outputResult = (Result) measurementsAggregator.aggregateByTimeUnit(inputMeasurements, aggFunction, description);
		outputResult.setAggregateFunction(aggFunction);
		return outputResult;
	}

	@Override
	public int reportResultInFile(IResult result, String reportType, String filename) {
		if(reportType == "txt") {
			ResultReporterTxt txtOutput = new ResultReporterTxt();
			txtOutput.setFilename(filename);
			txtOutput.setResult(result);
			txtOutput.reportResultInFile(result, filename);
		}else if (reportType == "md"){
			ResultReportermd txtOutput = new ResultReportermd();
			txtOutput.setFilename(filename);
			txtOutput.setResult(result);
			txtOutput.reportResultInFile(result, filename);
		}else if(reportType == "html") {
			ResultReporterHtml txtOutput = new ResultReporterHtml();
			txtOutput.setFilename(filename);
			txtOutput.setResult(result);
			txtOutput.reportResultInFile(result, filename);
		}else {
			System.out.println("Wrong report file type!");
			System.exit(0);
		}
		return 0;
	}
	
	public ArrayList<MeasurementRecord> getObjCollection(){
		return objCollection;
		
	}
	
	public void setInputMeasurements(ArrayList<MeasurementRecord> inputMeasurements) {
		inputMeasurements = this.inputMeasurements; 
	}
}
