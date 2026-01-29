package com.demo.utility;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;

public class DataProviders {
	private static final String File_Path = System.getProperty("user.dir")+"/src/test/resources/testData/testData.xlsx";
	
	@DataProvider(name = "validLoginData")
	public static Object[][] validLoginData(){
		return getSheetData("ValidLoginData");
	}
	@DataProvider(name = "inValidLoginData")
	public static Object[][] inValidLoginData(){
		return getSheetData("InvalidLoginData");
	}
	
	private static Object[][] getSheetData(String sheetName){
		List<String[]> sheetData = ExcelReaderUtility.getSheetData(File_Path, sheetName);
		
		Object[][] data = new Object[sheetData.size()][sheetData.get(0).length];
		
		for(int i =0; i<sheetData.size(); i++) {
			data[i] = sheetData.get(i);
		}
		
		return data;
	}
}
