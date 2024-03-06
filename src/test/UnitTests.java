package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import dataload.Loader;
import datamodel.IResult;
import datamodel.MeasurementRecord;
import mainengine.IMainEngine;
import mainengine.MainEngineFactory;

public class UnitTests <E> {

		private static IMainEngine mainEngine;
		private static MainEngineFactory factory;
		private static ArrayList<MeasurementRecord> objCollection;
		private static IResult result;
		static private String inputFilename;
		static private String delimeter;
		static private Boolean hasHeader;
		static private int numFields;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		factory = new MainEngineFactory();
		mainEngine = factory.createMainEngine("MainEngine");
		objCollection = new ArrayList<MeasurementRecord>();

		inputFilename = "./Resources/Data/2007_clean.tsv";
		delimeter = "\t";
		hasHeader = false;
		numFields = 9;
		objCollection = new ArrayList<MeasurementRecord>(); 
	}
	
	@After
	public void tearDown() throws Exception {
		objCollection = new ArrayList<MeasurementRecord>(); 
		result = null;
	}
	/**
	 * Test method for {@link dataload.Loader#Loader(java.lang.String, java.lang.String, java.lang.Boolean, int)}.
	 */
	@Test
	public final void testLoader() {
		Loader<E> testLoad = new Loader<>("./Resources/Data/2007_clean.tsv","\t",false,9);
		int lines = testLoad.load("./Resources/Data/2007_clean.tsv","\t",false,9,(ArrayList<E>) testLoad.getObjCollection());	
		assertEquals(lines, 521668);
	}
	
	
	
	
	/**
	 * Test method for {@link mainengine.MainEngine#aggregateByTimeUnit(java.util.ArrayList, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testAggregateByTimeUnit() {

		int lines = mainEngine.loadData(inputFilename, delimeter, hasHeader, numFields, objCollection);
		System.out.println("Size to process: " + lines);
		
		result = mainEngine.aggregateByTimeUnit(objCollection, "dayofweek", "avg", "Day of week avg aggregation over 2007 sample");
		assertEquals(result.getAggregateFunction(), "avg");
		
		assertEquals(result.getAggregateMeterKitchen().size(), 7);
		assertEquals(result.getAggregateMeterLaundry().size(), 7);
		assertEquals(result.getAggregateMeterAC().size(), 7);
	}
	

	
	

	

}
