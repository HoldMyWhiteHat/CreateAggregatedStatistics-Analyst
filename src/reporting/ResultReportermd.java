package reporting;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import datamodel.IResult;

public class ResultReportermd implements IResultReporter {
	
	String filename;
	IResult result;

	public ResultReportermd() {	
	}

	@Override
	public int reportResultInFile(IResult result, String filename) {
	
		FileOutputStream outputStream = null;

		//Opening files for read and write, checking exception
		try {
			outputStream = new FileOutputStream(filename);	
		} catch (FileNotFoundException e) {
			System.out.println("Error opening the file " + filename);
			System.exit(0);
		}
		
		PrintWriter outputWriter = new PrintWriter(outputStream);
		System.out.println("Writing to file");
				
		outputWriter.println("#" + result.getDescription());
		
		outputWriter.println(result.getAggregateFunction() + " consumption (watt-hours) "
				+ "over (a) Kitchen, (b) Laundry, (c) A/C");
		outputWriter.println();
		outputWriter.println("## Kitchen");
		
		for (String name: result.getAggregateMeterKitchen().keySet()){
            String key = name.toString();
            String value = result.getAggregateMeterKitchen().get(name).toString();  
            outputWriter.println("* " + key + "\t" + value);  
		}
		outputWriter.println();
		outputWriter.println("## Laundry");
		
		for (String name: result.getAggregateMeterLaundry().keySet()){
            String key = name.toString();
            String value = result.getAggregateMeterLaundry().get(name).toString();  
            outputWriter.println("* " + key + "\t" + value);  
		}
		outputWriter.println();
		outputWriter.println("## AC");

		for (String name: result.getAggregateMeterAC().keySet()){
            String key = name.toString();
            String value = result.getAggregateMeterAC().get(name).toString();  
            outputWriter.println("* " + key + "\t" + value);  
		}
		
		
		outputWriter.close();
		System.out.println("DONE");
		
		
		return 0;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void setResult(IResult result) {
		this.result = result;
	}
	

}
