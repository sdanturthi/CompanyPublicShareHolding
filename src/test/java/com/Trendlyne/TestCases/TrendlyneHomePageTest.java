package com.Trendlyne.TestCases;

import org.testng.annotations.Test;

import com.Trendlyne.PageObjects.TrendlyneHomePage;
import com.Trendlyne.TestUtilities.ReadTextFile;
import com.Trendlyne.TestUtilities.XLUtils;

public class TrendlyneHomePageTest extends BaseClass {
	
	XLUtils xlutil = new XLUtils();
	
	@Test
	public void TrendlyneHomePageTest_TC001() throws Exception {
		String url = rc.getUrl();
		driver.get(url);		
		driver.manage().window().maximize();
		String filePath = System.getProperty("user.dir") + "/src/test/java/com/Trendlyne/TestData/Stock-List.xlsx";
		int lastRowValue = xlutil.getLastRow(filePath, "Sheet1");
		int lastColValue = xlutil.getLastColumnValue(filePath, "Sheet1", 0);
		System.out.println(lastRowValue);
		System.out.println(lastColValue);
		
		ReadTextFile textFile = new ReadTextFile();
		String[] inputData = textFile.readInput().split(",");
		
		for(int i =0; i<inputData.length; i++) {
			String stockName = inputData[i].trim();
			System.out.println(stockName);
			TrendlyneHomePage tlHomePage = new TrendlyneHomePage(driver);
			tlHomePage.inputSearch(stockName);
			Thread.sleep(5000);
			tlHomePage.stockShareHoldingBtn();
			Thread.sleep(5000);	
		}
		
		
	/*for(int j=0; j<=lastRowValue; j++) {
			String cellValue = xlutil.getCellValue(filePath, "Sheet1", j, 0);
			System.out.println(cellValue);
			TrendlyneHomePage tlHomePage = new TrendlyneHomePage(driver);
			tlHomePage.inputSearch(cellValue);
			Thread.sleep(5000);
			tlHomePage.stockShareHoldingBtn();
			Thread.sleep(5000);			
		} */
		
	}
}
