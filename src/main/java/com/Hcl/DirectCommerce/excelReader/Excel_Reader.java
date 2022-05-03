package com.Hcl.DirectCommerce.excelReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.Hcl.DirectCommerce.testBase.TestBase;

/**
 * 
 * @author sujit jena
 *
 */
public class Excel_Reader {
	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	public FileOutputStream fileOut = null;
	public String path;
	public static FileInputStream fis;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;

	/**
	 * This Method will return 2D object Data for each record in excel sheet.
	 * 
	 * @param sheetName
	 * @param fileName
	 * @return
	 */
	@SuppressWarnings({ "deprecation" })
	public static String[][] getDataFromSheet(String path, String sheetName, String ExcelName) {
		String dataSets[][] = null;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			// get sheet from excel workbook
			XSSFSheet sheet = workbook.getSheet(sheetName);
			// count number of active tows
			int totalRow = sheet.getLastRowNum() + 1;
			// count number of active columns in row
			int totalColumn = sheet.getRow(0).getLastCellNum();
			// Create array of rows and column
			dataSets = new String[totalRow - 1][totalColumn];
			// Run for loop and store data in 2D array
			// This for loop will run on rows
			for (int i = 1; i < totalRow; i++) {
				XSSFRow rows = sheet.getRow(i);
				// This for loop will run on columns of that row
				for (int j = 0; j < totalColumn; j++) {
					// get Cell method will get cell
					XSSFCell cell = rows.getCell(j);

					// If cell of type String , then this if condition will work
					if (cell.getCellType() == Cell.CELL_TYPE_STRING)
						dataSets[i - 1][j] = cell.getStringCellValue();
					// If cell of type Number , then this if condition will work
					else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
						String cellText = String.valueOf(cell.getNumericCellValue());
						dataSets[i - 1][j] = cellText;
					} else
						// If cell of type boolean , then this if condition will work
						dataSets[i - 1][j] = String.valueOf(cell.getBooleanCellValue());
				}

			}
			return dataSets;
		} catch (Exception e) {
			System.out.println("Exception in reading Xlsx file" + e.getMessage());
			e.printStackTrace();
		}
		return dataSets;
	}

	@SuppressWarnings("deprecation")
	public static String getCellData(String sheetName, String colName, int rowNum) {
		try {
			int col_Num = 0;
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			XSSFRow row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().equals(colName)) {
					col_Num = i;
					break;
				}
			}
			row = sheet.getRow(rowNum - 1);

			XSSFCell cell = row.getCell(col_Num);
			if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				return cell.getStringCellValue();
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
				return "";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public void updateResult(String excelLocation, String sheetName, String testCaseName, String testStatus) {
		try {
			FileInputStream file = new FileInputStream(new File(excelLocation));
			// Create Workbook instance
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get sheet Name from Workbook
			XSSFSheet sheet = workbook.getSheet(sheetName);
			// count number of active rows in excel sheet
			int totalRow = sheet.getLastRowNum() + 1;
			for (int i = 1; i < totalRow; i++) {
				XSSFRow r = sheet.getRow(i);
				String ce = r.getCell(0).getStringCellValue();
				if (ce.contains(testCaseName)) {
					r.createCell(1).setCellValue(testStatus);
					file.close();
					log.info("result updated..");
					FileOutputStream out = new FileOutputStream(new File(excelLocation));
					workbook.write(out);
					out.close();
					break;
				}
			}
		} catch (Exception e) {

		}
	}

	/**
	 * This Method will return the key value pair from row by row. purpose of this
	 * method is to get the key value pair from excel user this method like
	 * name=sujit jena date=15/5/2019 String value=getvalue(String sheetName,String
	 * path).get("key");
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static Map<String, String> getvalue(String sheetName, String path) {
		Map<String, String> testData = new LinkedHashMap<String, String>();
		try {
			FileInputStream file = new FileInputStream(path);
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					cell.setCellType(Cell.CELL_TYPE_STRING);
					testData.put(cell.getStringCellValue(), cellIterator.next().getStringCellValue());																								// from Excel
				}
			}
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return testData;
	}

	/**
	 * This Method will return only single cell value parameter=sheet
	 * name,Excelpath,column name,row name 
	 * name=sujit jena 
	 * date=15/5/2019 
	 * value=getcellvalue(String sheetName,String
	 * path,columnname,rownames).get("key");
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getcellvalue(String path, String sheetName, String Columnname, String rowname)
			throws Exception {
		FileInputStream inputStream = new FileInputStream(path);
		@SuppressWarnings("resource")
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();
		Object columnNumber = null;
		Exist: while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
					String text = cell.getStringCellValue();
					if (Columnname.equalsIgnoreCase(text)) {
						columnNumber = cell.getColumnIndex();
						break Exist;
					}
				}
			}
		}
		int colnum = (Integer) columnNumber;
		Object rowNum = null;
		for (Row row : firstSheet) {
			for (Cell cell : row) {
				if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
					if (cell.getRichStringCellValue().getString().trim().equalsIgnoreCase(rowname)) {
						rowNum = row.getRowNum();
					}
				}
			}
		}
		int rownum = (Integer) rowNum;
		Cell value = workbook.getSheet(sheetName).getRow(rownum).getCell(colnum, Row.RETURN_NULL_AND_BLANK);
		List<String> l = new ArrayList<String>();
		if (value == null)
			l.add(null);
		else if ("".equals(value.getStringCellValue().trim()))
			l.add("");
		else
			l.add(value.getStringCellValue().toString());
		return (String) l.get(0);

	}

	/**
	 * This Method will return full row value as per given test casename(rowname)
	 * parameter=sheet name,Excelpath,column name,row name 
	 * name=sujit jena
	 * date=15/5/2019 String value=getrowvalue(String sheetName,String
	 * path,columnname,rownames).get("key");
	 * 
	 * @return
	 * modified=09/04/2020
	 * formula evaluator
	 */
	@SuppressWarnings("deprecation")
	public static Map<Object, Object> getrowvalue(String path, String sheetName, String testcasename) throws Exception {
		//String actual = testcasename.replaceAll("[^a-zA-Z0-9]", " ");
		FileInputStream inputStream = new FileInputStream(path);
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		DataFormatter objDefaultFormat = new DataFormatter();
		FormulaEvaluator objFormulaEvaluator = new XSSFFormulaEvaluator(workbook);
		XSSFSheet firstSheet = workbook.getSheet(sheetName);
		Row r = firstSheet.getRow(0);
		Iterator<?> itr = r.iterator();
		List<String> l = new ArrayList<String>();
		while (itr.hasNext()) {
			l.add(itr.next().toString());
			System.out.println(l);
		}
		Object rowNum = null;
		Exist: for (Row row : firstSheet) {
			for (Cell cell : row) {
				if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
					System.out.println(cell.getRichStringCellValue().getString());
					if (cell.getRichStringCellValue().getString().trim().equalsIgnoreCase(testcasename)) {
						rowNum = row.getRowNum();
						break Exist;
					}
				}
			}
		}
		int rownum = (Integer) rowNum;
		Row rowvalue = firstSheet.getRow(rownum);
		int lastColumn = r.getLastCellNum();
		List<String> vallist = new ArrayList<String>();
		for (int i = 0; i < lastColumn; i++) {
	
			Cell c = rowvalue.getCell(i);
			objFormulaEvaluator.evaluate(c); // This will evaluate the cell, And any type of cell will return string value
		    String cellValueStr = objDefaultFormat.formatCellValue(c,objFormulaEvaluator);
			if (c == null) {
				vallist.add(null);
			} else {
				vallist.add(i, cellValueStr.toString());
			}
		}
		HashMap<Object, Object> keyvalue = new HashMap<Object, Object>();
		for (int i = 0; i < l.size(); i++) {
			keyvalue.put(l.get(i), vallist.get(i));
			System.out.println(keyvalue);
		}
		return keyvalue;

	}

}
