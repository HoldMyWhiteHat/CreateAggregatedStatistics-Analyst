package client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import java.util.ArrayList;
import mainengine.IMainEngine;
import dataload.Loader;
import datamodel.IResult;
import datamodel.MeasurementRecord;
import mainengine.MainEngineFactory;


public class Client {
	/*
	private static IMainEngine mainEngine;
	private static MainEngineFactory factory;
	private static ArrayList<MeasurementRecord> objCollection;
	private static HashMap<String, String> filePaths = new HashMap<String , String>();
	private static IResult result;
	*/
	
	private static IMainEngine mainEngine;
	private static MainEngineFactory factory;
	private static ArrayList<MeasurementRecord> objCollection;
	private static IResult result;
	static private String inputFilename;
	static private String outputFilename;
	static private String delimeter;
	static private Boolean hasHeader;
	static private int numFields;
	public static <E> void main(String[] args){
		
		factory = new MainEngineFactory();
		mainEngine = factory.createMainEngine("MainEngine");
		objCollection = new ArrayList<MeasurementRecord>();
		
		
		
		
		Scanner input = new Scanner(System.in);
		System.out.println("(1)Load File\n(2)Aggregate\n(3)Report Result\n(4)Report History\n(5)Exit");
		int option = input.nextInt();
		boolean stopFlagExit = false;
		
		
		while(option != 5) {
			switch(option) {
				case 1:
					System.out.println("Enter file path: ");
					Scanner inputName = new Scanner(System.in);
					String filename = inputName.next();
					mainEngine.loadData(filename, "\t", false, 9, objCollection);
					break;
				
				case 2:
					Scanner inputTimeUnitType = new Scanner(System.in);
					Scanner inputFunc = new Scanner(System.in);
					Scanner inputDesc = new Scanner(System.in);

					
				
					boolean stopFlagUnitType = true;
					String timeUnitType = "";
					
					
					System.out.println("Enter time unit type (periodofday , dayofweek , month , season): ");
					loop1:
					while(stopFlagUnitType) {
						
						timeUnitType = inputTimeUnitType.next();

						if(timeUnitType.equals("periodofday") || timeUnitType.equals("dayofweek") || timeUnitType.equals("month") || 
								timeUnitType.equals("season")) 
						{
							stopFlagUnitType = false;
						}
						
						if(stopFlagUnitType)
						{
							if(!timeUnitType.equals("exit")) {
								System.out.println("Incorrect time unit type, please try again or type exit");
							}
						}
						if(timeUnitType.equals("exit")) {
							break loop1;
						}
						
					}
					if(stopFlagUnitType) {
						break;
					}
					
					boolean stopFlagAggFunc = true;
					String aggFunction = "";
					
					System.out.println("Enter time aggregation function(avg,sum): ");
					loop2:
					while(stopFlagAggFunc) {
						
						aggFunction = inputFunc.next();

						if(aggFunction.equals("avg") || aggFunction.equals("sum") ) 
						{
							stopFlagAggFunc = false;
						}
						
						if(stopFlagAggFunc)
						{
							if(!aggFunction.equals("exit")) {
								System.out.println("Incorrect time aggregation function, please try again or type exit");
							}
						}
						if(aggFunction.equals("exit")) {
							break loop2;
						}
						
					}
					if(stopFlagAggFunc) {
						break;
					}
					
					System.out.println(timeUnitType);
					System.out.println(aggFunction);

					System.out.println("Enter description: ");
					String description = inputDesc.next();
					
					IResult result = mainEngine.aggregateByTimeUnit(objCollection, timeUnitType, aggFunction, description);	
					//System.out.println("(1)Load File\n(2)Aggregate\n(3)Report Result\n(4)Report History\n(5)Exit");
					//option = input.nextInt();
					break;
				case 3:
					/*
					System.out.println("Enter file path (./Resources/Reports/textOutput.txt): ");
					Scanner inputPath = new Scanner(System.in);
					String path = inputPath.next();
					//leipei kati
					*/
					
					Scanner inputPath = new Scanner(System.in);
					Scanner inputFileType = new Scanner(System.in);
					

					
				
					
			
					
					//IResult result = mainEngine.aggregateByTimeUnit(objCollection, timeUnitType, aggFunction, description);	
					
					
				case 4:
					
					
				case 5:
					input.close();
					stopFlagExit = true;
					break;
				default:
					System.out.println("Unknown input ,please try again");
			
			}
			if(stopFlagExit) {
				break;
			}
			System.out.println("(1)Load File\n(2)Aggregate\n(3)Report Result\n(4)Report History\n(5)Exit");
			option = input.nextInt();
		
		}//while end
		System.out.println("Goodbye..");
		
		
		
	}
}//class end