package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelUtils {
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(ExcelUtils.class);
	private static HashMap<List<String>,List<List<String>>> dataFromExcel=new HashMap<>();
	private static List<String> columnsList=new ArrayList();	
	private static List<String> rowList=new ArrayList();
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;

	public static  HashMap<List<String>,List<List<String>>> readExcelData(String featureFileName){
		try {			
			String filePath = System.getProperty("user.dir") 
	                + "/src/test/resources/TestData.xlsx";
			File fs = new File(filePath);
			FileInputStream fis = new FileInputStream(fs);
			workbook=new XSSFWorkbook(fis);
			sheet=getSheetFromExcel(workbook,featureFileName);
			List<String>columnListHeaders=readColumns(sheet);
			List<List<String>>rowList=readRows(sheet,featureFileName);
			dataFromExcel.put(columnListHeaders,rowList);
			System.out.println("Excel data read successfully for feature file : "+dataFromExcel);
			return dataFromExcel;
		}catch(Exception e)
		{
			e.printStackTrace();			
			return null;

		}
	}

	private static XSSFSheet getSheetFromExcel(XSSFWorkbook workbook,String name) {
		try {
			XSSFSheet sheet = workbook.getSheet(name);
			System.out.println("Sheet name found: "+sheet.getSheetName());
			return sheet;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}
	private static List<String> readColumns(XSSFSheet sheet) {
		
		int totalRows=sheet.getLastRowNum();
		int totalColumns=sheet.getRow(0).getLastCellNum();
		for (int c = 0; c < totalColumns; c++) {
			 XSSFCell cell = sheet.getRow(0).getCell(c);  
		        String col = (cell != null) ? cell.getStringCellValue() : null;
		        columnsList.add(sheet.getRow(0).getCell(c).getStringCellValue());
            }
		return  columnsList;
	}
	private static List<List<String>> readRows(XSSFSheet sheet,String ScenarioName) {
		int index=-1;
		List<List<String>> allRows = new ArrayList<>();
		int totalRows=sheet.getLastRowNum();
		for (int r = 1; r <= totalRows; r++) {
			 XSSFRow row = sheet.getRow(r);
			 if (row == null) continue;
		        XSSFCell firstCell = row.getCell(0);
		        // ✅ Null check on first cell
		        if (firstCell == null) continue;
//			if (row.getCell(0).getStringCellValue().equalsIgnoreCase(ScenarioName)) {
				 List<String> rowList = new ArrayList<>();
				for (int c = 0; c < columnsList.size(); c++) {
					XSSFCell cell=sheet.getRow(r).getCell(c);
					 if (cell == null) {
	                        rowList.add("");
	                        continue;
	                    }
					switch (cell.getCellType().toString()) {
					case "STRING":
						rowList.add(row.getCell(c).getStringCellValue());
						break;
					case "NUMERIC":
						rowList.add(String.valueOf(row.getCell(c).getNumericCellValue()));
						break;
					case "BOOLEAN":
						rowList.add(String.valueOf(row.getCell(c).getBooleanCellValue()));
						break;
					}
				}
				 allRows.add(rowList);
//			}
			
		}
		return  allRows;

	}
	@DataProvider(name="RestAssuredPayLoads")
	public Object[][] sendDataRestAssures()
	{
        return new Object[][] {
            {1,"202","isbn123","aisle123","Reshma"},
            {2,"202","isbn223","aisle223","Dawlekar"},
            {3,"202","isbn323","aisle323","Manish"}
        };
    }
}

