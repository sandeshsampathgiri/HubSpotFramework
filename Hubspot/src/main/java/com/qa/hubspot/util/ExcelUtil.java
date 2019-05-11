package com.qa.hubspot.util;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class ExcelUtil {

	public static Workbook wb;
	public static Sheet sh;
	
	
	public static String TESTDATA_SHEET_PATH = "\\Users\\Lenovo\\git\\HubSpotFramework\\Hubspot\\src\\main\\java\\com\\qa\\hubspot\\testdata\\TestData.xlsx";

	public static Object[][] getTestData(String sheetName) {

		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(TESTDATA_SHEET_PATH);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		try {
			wb = WorkbookFactory.create(fis);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sh = wb.getSheet(sheetName);

		Object[][] data = new Object[sh.getLastRowNum()][sh.getRow(0).getLastCellNum()];

		for (int i = 0; i < sh.getLastRowNum(); i++) {
			for (int j = 0; j < sh.getRow(0).getLastCellNum(); j++) {
				data[i][j] = sh.getRow(i + 1).getCell(j).toString();
			}
		}

		return data;

	}

}
