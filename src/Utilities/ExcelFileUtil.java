package Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil {
Workbook wb;
//constructor for reading path of ecxel
public ExcelFileUtil(String excelfile)throws Throwable
{
	FileInputStream fi= new FileInputStream(excelfile);
	wb= WorkbookFactory.create(fi);
}
//method for counting no of rows in a sheet
public int rowCount(String sheetname)
{
	return wb.getSheet(sheetname).getLastRowNum();
}
//method for counting no of columns from row
public int colCount(String sheetname)
{
	return wb.getSheet(sheetname).getRow(0).getLastCellNum();
}
//get data from cell
public String getCelldata(String sheetname,int row,int column)
{
	String data=null;
if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)	
{
int celldata=(int)wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
data=String.valueOf(celldata);
}
else
{
data=wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
}
return data;
}
//write status in cell
public void setCellData(String sheetname,int row,int column,String status,String writeexcel)
throws Throwable{
	//get sheet from wb
	Sheet ws=wb.getSheet(sheetname);
	//getrow from sheet
	Row rownum=ws.getRow(row);
	//get cell from row
	Cell cell=rownum.createCell(column);
	//set status into cell
	cell.setCellValue(status);
	if(status.equalsIgnoreCase("Pass"))
	{
		//create cell style
				CellStyle style=wb.createCellStyle();
				//create font
				Font font =wb.createFont();
				///Apply color To The Text
				font.setColor(IndexedColors.GREEN.getIndex());
				//Apply Bold To The Text
				font.setBold(true);
				font.setBoldweight(Font.BOLDWEIGHT_BOLD);
				style.setFont(font);
				rownum.getCell(column).setCellStyle(style);
	}
	else if(status.equalsIgnoreCase("Fail"))
	{
		CellStyle style=wb.createCellStyle();
		Font font=wb.createFont();
		font.setColor(IndexedColors.RED.getIndex());
		font.setBold(true);
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style.setFont(font);
		rownum.getCell(column).setCellStyle(style);
	}
	else if(status.equalsIgnoreCase("Not Executed"))
	{
		CellStyle style=wb.createCellStyle();
		Font font=wb.createFont();
		font.setColor(IndexedColors.BLUE.getIndex());
		font.setBold(true);
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style.setFont(font);
		rownum.getCell(column).setCellStyle(style);
	}
	FileOutputStream fo =new FileOutputStream(writeexcel);
	wb.write(fo);
	
}
}











