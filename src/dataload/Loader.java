package dataload;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Loader<E>  implements ILoader<E> {
	
	String fileName;
	String delimeter;
	boolean hasHeaderLine;
	int numFields;
	ArrayList<E> objCollection = new ArrayList<>();
	
	
	
	
	
	public Loader(String fileName, String delimeter, boolean hasHeaderLine, int numFields){
		this.fileName = fileName;
		this.delimeter = delimeter;
		this.hasHeaderLine = hasHeaderLine;
		this.numFields = numFields; 
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public int load(String fileName, String delimeter, boolean hasHeaderLine, int numFields, ArrayList<E> objCollection) {
		

		if (numFields < 1){
			System.out.println("Number of fields must be more than 1!");
			System.exit(0);		
		}
		Scanner inputStream = null;
		
		//Opening files for read and write, checking exception
		try
		{
			inputStream = new Scanner(new FileInputStream(fileName));
		}
		catch(FileNotFoundException e)
		{
			System.out.println(fileName + "not found");
			System.exit(0);
		}
	
		int counter = 0;//number of rows parsed 
		
		
		//process the title of the tsv file
		if(hasHeaderLine){
			@SuppressWarnings("unused")
			String titleLine = inputStream.nextLine();
			counter++;
		}
		
			
		boolean stopFlag = true;
		String line = "";
		String[] tempArray = null;
		while(inputStream.hasNextLine()) {
			
			line = inputStream.nextLine();
			tempArray = line.split(delimeter);
			
 			if(tempArray.length != numFields) {

 				System.out.println("Wrong Input format in file " + fileName +". I found " + tempArray.length +
 						" values, but I expect " + numFields + " values per row!");
 				stopFlag = false;
 			}else{
 				for(String field : tempArray) {
 					if(field == "" || field == null) {
 						System.out.print("BRHKE LATHOS REI");
 						stopFlag = false;
 					}
 				}
 			}
 			
 			if(stopFlag) {
 				//line = inputStream.nextLine();
 				objCollection.add((E)line);
 				
 			}	
			counter++;
		}		
		inputStream.close();
		System.out.println("Processed " + counter + " rows and loaded " + objCollection.size() + " objects.");//info output 
		
		return counter;
	}
	
	public ArrayList<E> getObjCollection() {
		return objCollection;
	}
	
} 
