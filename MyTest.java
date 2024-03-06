package client;
import java.util.ArrayList;
import mainengine.IMainEngine;
import dataload.Loader;
import datamodel.IResult;
import datamodel.MeasurementRecord;
import mainengine.MainEngineFactory;

public class MyTest {
	
	private static IMainEngine mainEngine;
	private static MainEngineFactory factory;
	private static ArrayList<MeasurementRecord> objCollection;
	private static IResult result;
	static private String inputFilename;
	static private String outputFilename;
	static private String delimeter;
	static private Boolean hasHeader;
	static private int numFields;
	
	

	public static <E> void main(String[] args) {
		
		factory = new MainEngineFactory();
		mainEngine = factory.createMainEngine("MainEngine");

		objCollection = new ArrayList<MeasurementRecord>();
		//objCollection = new ArrayList<MeasurementRecord>();
		
		
		
		

		
		//MainEngine<E> testEngine = new MainEngine<>();
		mainEngine.loadData("./Resources/Data/2007_clean.tsv","\t",false,9, objCollection);
		System.out.println("MyTest ObjCollection: " + objCollection.size());
		IResult result = mainEngine.aggregateByTimeUnit(objCollection, "month", "avg", "perigrafi");
		int printOutcome = mainEngine.reportResultInFile(result, "txt", "./Resources/TestOutput/textOutputTest.txt");
		System.out.println(printOutcome);
		
		//IResult testResult = mainEngine.aggregateByTimeUnit(inputMeasurements, aggregatorType, aggFunction, description);  
		
	}

}
;