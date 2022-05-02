package com.Trendlyne.TestUtilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils {

	static FileInputStream fis;
	static FileOutputStream fos;
	static XSSFWorkbook wb;
	static XSSFSheet ws;
	static XSSFRow row;
	static XSSFCell col;

	public int getLastRow(String XLFile, String XLSheet) throws Exception {
		fis = new FileInputStream(XLFile);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(XLSheet);
		int lastRowNum = ws.getLastRowNum();
		wb.close();
		fis.close();
		return lastRowNum;
	}

	public int getLastColumnValue(String XLFile, String XLSheet, int rowNum) throws Exception {
		fis = new FileInputStream(XLFile);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(XLSheet);
		row = ws.getRow(rowNum);
		int colNum = row.getLastCellNum();
		wb.close();
		fis.close();
		return colNum;
	}

	public String getCellValue(String XLFile, String XLSheet, int rowNum, int colNum) throws Exception {
		fis = new FileInputStream(XLFile);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(XLSheet);
		row = ws.getRow(rowNum);
		col = row.getCell(colNum);
		String cellData;

		try {
			DataFormatter formatter = new DataFormatter();
			cellData = formatter.formatCellValue(col);
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			cellData = "";
		}
		wb.close();
		fis.close();

		return cellData;
	}

	public void setCellValue(String XLFile, String XLSheet, int rowNum, int colNum, String cellValue) throws Exception {
		fis = new FileInputStream(XLFile);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(XLSheet);
		row = ws.getRow(rowNum);
		
		if(row.getLastCellNum() <=-1 || row == null )
			row = ws.createRow(rowNum);
		else
			col = row.createCell(colNum);
		
		col.setCellValue(cellValue);
		fos = new FileOutputStream(XLFile);
		wb.write(fos);

		wb.close();
		fis.close();
		fos.close();

	}
}
