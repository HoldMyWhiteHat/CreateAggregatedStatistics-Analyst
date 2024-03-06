package reporting;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import datamodel.IResult;

public class ResultReporterHtml implements IResultReporter {

	String filename;
	IResult result;

	public ResultReporterHtml() {}

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
		
		
		outputWriter.println("<html> \n\t<body>");
		
		
		outputWriter.println("<h1>" + result.getDescription() + "</h1>");
		
		outputWriter.println("<p>" + result.getAggregateFunction() + " consumption (watt-hours) "
				+ "over (a) Kitchen, (b) Laundry, (c) A/C" + "</p>");
		outputWriter.println();
		outputWriter.println("<h2> Kitchen </h2>\n<ul>");
		
		for (String name: result.getAggregateMeterKitchen().keySet()){
            String key = name.toString();
            String value = result.getAggregateMeterKitchen().get(name).toString();  
            outputWriter.println("<li>" + key + "\t" + value + "\n</li>");  
		}
		
		outputWriter.println("</ul>");
		
		outputWriter.println("<h2> Laundry </h2>\n<ul>");
		
		for (String name: result.getAggregateMeterLaundry().keySet()){
            String key = name.toString();
            String value = result.getAggregateMeterLaundry().get(name).toString();  
            outputWriter.println("<li>" + key + "\t" + value + "\n</li>");  
		}
		outputWriter.println("</ul>");
		
		outputWriter.println("<h2> AC </h2>\n<ul>");

		for (String name: result.getAggregateMeterAC().keySet()){
            String key = name.toString();
            String value = result.getAggregateMeterAC().get(name).toString();  
            outputWriter.println("<li>" + key + "\t" + value + "\n</li>");  
		}
		
		outputWriter.println("</ul>");
		
		outputWriter.println("	<body> \n</html>");
		
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



