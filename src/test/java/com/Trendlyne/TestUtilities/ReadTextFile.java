package com.Trendlyne.TestUtilities;

import java.io.BufferedReader;
import java.io.FileReader;

public class ReadTextFile {
	
	String inputFilePath = System.getProperty("user.dir") + "/src/test/java/com/Trendlyne/TestData/StockList.txt";
	FileReader fileReader;
	
	public String readInput() throws Exception {
		
		fileReader = new FileReader(inputFilePath);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String data;
		String input="";
		
		while((data = bufferedReader.readLine()) != null){
			input+=data;
		}
		
		return input;
		
	}

}
