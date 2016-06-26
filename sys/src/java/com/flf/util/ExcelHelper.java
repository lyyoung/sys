package com.flf.util;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.flf.entity.Accident;

public class ExcelHelper {

	public static void main(String[] args){
			String filePath = "E:/test.xlsx";
			if(isExcel(filePath)){
				if(isExcel2003(filePath)){
					parserExcelXLS(filePath);
				}else{
					parserExcel1(filePath);
				}
			}
	}
	public static List<Accident> parserExcel(String filePath){
		if(isExcel(filePath)){
			if(isExcel2003(filePath)){
				return parserExcelXLS(filePath);
			}else{
				return parserExcel1(filePath);
			}
		}
		return null;
	}
	/*
	public static void parserExcel(String filePath){
		try{
			FileInputStream fis = new FileInputStream(filePath);  
			XSSFWorkbook wb = new XSSFWorkbook(fis);  
			XSSFSheet sheet = wb.getSheetAt(0); 
			for (int i = 3; i < sheet.getPhysicalNumberOfRows(); i++) {  
			    String cellNovalue = "";  
			    XSSFRow row = sheet.getRow(i);  
			    Iterator it = row.cellIterator();  
			    while (it.hasNext()) {  
			        XSSFCell cell = (XSSFCell) it.next();  
			        cellNovalue = getXSSCellFormatValue(cell);  
			
			        System.out.print("\t"+cellNovalue+"\t");  
			        }
			        System.out.println();
			    } 
			}catch(Exception e){
				e.printStackTrace();
			} 
	}*/
	public static List<Accident> parserExcel1(String filePath){
		List<Accident> list = new ArrayList<Accident>();
		try{
			FileInputStream fis = new FileInputStream(filePath);  
			XSSFWorkbook wb = new XSSFWorkbook(fis);  
			XSSFSheet sheet = wb.getSheetAt(0); 
			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {  
			    String cellNovalue = "";  
			    XSSFRow row = sheet.getRow(i); 
			    Accident a = new Accident();
			    for(int j = 0;j<row.getPhysicalNumberOfCells();j++){
			    	String id = getXSSCellFormatValue(row.getCell(j++));
					System.out.println("id:"+id);
					String time = getXSSCellFormatValue(row.getCell(j++));
					String province = getXSSCellFormatValue(row.getCell(j++));
					System.out.println(id+time+province);
					String city = getXSSCellFormatValue(row.getCell(j++));
					String category_big = getXSSCellFormatValue(row.getCell(j++));
					String category_small = getXSSCellFormatValue(row.getCell(j++));
					String material = getXSSCellFormatValue(row.getCell(j++));
					String reason_type = getXSSCellFormatValue(row.getCell(j++));
					String result_type = getXSSCellFormatValue(row.getCell(j++));
					System.out.println(j+":"+row.getCell(j));
					String accident_des = getXSSCellFormatValue(row.getCell(j++));
					String accident_reason = getXSSCellFormatValue(row.getCell(j++));
					String accident_reason_des = getXSSCellFormatValue(row.getCell(j++));
					String casualties = getXSSCellFormatValue(row.getCell(j++));
					String outages = getXSSCellFormatValue(row.getCell(j++));
					String other_results = getXSSCellFormatValue(row.getCell(j++));
					String loss_des = getXSSCellFormatValue(row.getCell(j++));
					String information_sources = getXSSCellFormatValue(row.getCell(j++));
					String image_url = getXSSCellFormatValue(row.getCell(j++));
					String note = getXSSCellFormatValue(row.getCell(j++));
					System.out.println(id+time+province);
					a.setAccident_des(accident_des);
					a.setAccident_reason(accident_reason);
					a.setAccident_reason_des(accident_reason_des);
					a.setCasualties(casualties);
					a.setCategory_big(category_big);
					a.setCategory_small(category_small);
					a.setCity(city);
					a.setId(id);
					a.setImage_url(image_url);
					a.setInformation_sources(information_sources);
					a.setLoss_des(loss_des);
					a.setMaterial(material);
					a.setOther_results(other_results);
					a.setOutages(outages);
					a.setProvince(province);
					a.setReason_type(reason_type);
					a.setResult_type(result_type);
					a.setTime(Tools.str2Date1(time));
					a.setNote(note);
			    }
			    list.add(a);
			 } 
			}catch(Exception e){
				e.printStackTrace();
			} 
		return list;
	}
	private static boolean isExcel(String filePath){
		return isExcel2003(filePath)||isExcel2007(filePath);
	}
	private static boolean isExcel2003(String filePath){
		return filePath.matches("^.+\\.(?i)(xls)$");
	}
    private static boolean isExcel2007(String filePath){
    	return filePath.matches("^.+\\.(?i)(xlsx)$");
	}
	private static String getXSSCellFormatValue(XSSFCell cell) {
		String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
            // 如果当前Cell的Type为NUMERIC
            case XSSFCell.CELL_TYPE_NUMERIC:
            case XSSFCell.CELL_TYPE_FORMULA: {
                // 判断当前的cell是否为Date
                if (DateUtil.isCellDateFormatted(cell)) {
                    // 如果是Date类型则，转化为Data格式
                    
                    //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
                    //cellvalue = cell.getDateCellValue().toLocaleString();
                    
                    //方法2：这样子的data格式是不带带时分秒的：2011-10-12
                    Date date = cell.getDateCellValue();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    cellvalue = sdf.format(date);
                    
                }
                // 如果是纯数字
                else {
                    // 取得当前Cell的数值
                    cellvalue = String.valueOf(cell.getNumericCellValue());
                }
                break;
            }
            // 如果当前Cell的Type为STRIN
            case XSSFCell.CELL_TYPE_STRING:
                // 取得当前的Cell字符串
                cellvalue = cell.getRichStringCellValue().getString();
                break;
            // 默认的Cell值
            default:
                cellvalue = " ";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;
	}

	/*public static void parserExcelXLS(String filePath){
		try{
			FileInputStream fis = new FileInputStream(filePath);  
	        HSSFWorkbook wb = new HSSFWorkbook(fis);  
	        HSSFSheet sheet = wb.getSheetAt(0); 
	        for (int i = 3; i < sheet.getPhysicalNumberOfRows(); i++) {  
	            String cellNovalue = "";  
	            HSSFRow row = sheet.getRow(i);  
	            Iterator it = row.cellIterator();  
	            while (it.hasNext()) {  
	                HSSFCell cell = (HSSFCell) it.next();  
	                cellNovalue = getCellFormatValue(cell);  

	                System.out.print("\t"+cellNovalue+"\t");  
	            }
	            System.out.println();
	        }
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	*/
	public static List<Accident> parserExcelXLS(String filePath){
		List<Accident> list = new ArrayList<Accident>();
		try{
			FileInputStream fis = new FileInputStream(filePath);  
	        HSSFWorkbook wb = new HSSFWorkbook(fis);  
	        HSSFSheet sheet = wb.getSheetAt(0); 
	        for (int i = 3; i < sheet.getPhysicalNumberOfRows(); i++) {  
	            String cellNovalue = "";  
	            HSSFRow row = sheet.getRow(i);  
	            Accident a = new Accident();
			    for(short j = 0;j<row.getPhysicalNumberOfCells();j++){
			    	String id = getCellFormatValue(row.getCell(j++));
					System.out.println("id:"+id);
					String time = getCellFormatValue(row.getCell(j++));
					String province = getCellFormatValue(row.getCell(j++));
					System.out.println(id+time+province);
					String city = getCellFormatValue(row.getCell(j++));
					String category_big = getCellFormatValue(row.getCell(j++));
					String category_small = getCellFormatValue(row.getCell(j++));
					String material = getCellFormatValue(row.getCell(j++));
					String reason_type = getCellFormatValue(row.getCell(j++));
					String result_type = getCellFormatValue(row.getCell(j++));
					System.out.println(j+":"+row.getCell(j));
					String accident_des = getCellFormatValue(row.getCell(j++));
					String accident_reason = getCellFormatValue(row.getCell(j++));
					String accident_reason_des = getCellFormatValue(row.getCell(j++));
					String casualties = getCellFormatValue(row.getCell(j++));
					String outages = getCellFormatValue(row.getCell(j++));
					String other_results = getCellFormatValue(row.getCell(j++));
					String loss_des = getCellFormatValue(row.getCell(j++));
					String information_sources = getCellFormatValue(row.getCell(j++));
					String image_url = getCellFormatValue(row.getCell(j++));
					String note = getCellFormatValue(row.getCell(j++));
					System.out.println(id+time+province);
					a.setAccident_des(accident_des);
					a.setAccident_reason(accident_reason);
					a.setAccident_reason_des(accident_reason_des);
					a.setCasualties(casualties);
					a.setCategory_big(category_big);
					a.setCategory_small(category_small);
					a.setCity(city);
					a.setId(id);
					a.setImage_url(image_url);
					a.setInformation_sources(information_sources);
					a.setLoss_des(loss_des);
					a.setMaterial(material);
					a.setOther_results(other_results);
					a.setOutages(outages);
					a.setProvince(province);
					a.setReason_type(reason_type);
					a.setResult_type(result_type);
					a.setTime(Tools.str2Date1(time));
					a.setNote(note);
			    }
			    list.add(a);
	        }
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
    /**
     * 获取单元格数据内容为字符串类型的数据
     * 
     * @param cell Excel单元格
     * @return String 单元格数据内容
     */
    private static String getStringCellValue(HSSFCell cell) {
        String strCell = "";
        switch (cell.getCellType()) {
        case HSSFCell.CELL_TYPE_STRING:
            strCell = cell.getStringCellValue();
            break;
        case HSSFCell.CELL_TYPE_NUMERIC:
            strCell = String.valueOf(cell.getNumericCellValue());
            break;
        case HSSFCell.CELL_TYPE_BOOLEAN:
            strCell = String.valueOf(cell.getBooleanCellValue());
            break;
        case HSSFCell.CELL_TYPE_BLANK:
            strCell = "";
            break;
        default:
            strCell = "";
            break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }
        if (cell == null) {
            return "";
        }
        return strCell;
    }

    /**
     * 获取单元格数据内容为日期类型的数据
     * 
     * @param cell
     *            Excel单元格
     * @return String 单元格数据内容
     */
    private static String getDateCellValue(HSSFCell cell) {
        String result = "";
        try {
            int cellType = cell.getCellType();
            if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
                Date date = cell.getDateCellValue();
                result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)
                        + "-" + date.getDate();
            } else if (cellType == HSSFCell.CELL_TYPE_STRING) {
                String date = getStringCellValue(cell);
                result = date.replaceAll("[年月]", "-").replace("日", "").trim();
            } else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
                result = "";
            }
        } catch (Exception e) {
            System.out.println("日期格式不正确!");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据HSSFCell类型设置数据
     * @param cell
     * @return
     */
    private static String getCellFormatValue(HSSFCell cell) {
        String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
            // 如果当前Cell的Type为NUMERIC
            case HSSFCell.CELL_TYPE_NUMERIC:
            case HSSFCell.CELL_TYPE_FORMULA: {
                // 判断当前的cell是否为Date
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // 如果是Date类型则，转化为Data格式
                    
                    //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
                    //cellvalue = cell.getDateCellValue().toLocaleString();
                    
                    //方法2：这样子的data格式是不带带时分秒的：2011-10-12
                    Date date = cell.getDateCellValue();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    cellvalue = sdf.format(date);
                    
                }
                // 如果是纯数字
                else {
                    // 取得当前Cell的数值
                    cellvalue = String.valueOf(cell.getNumericCellValue());
                }
                break;
            }
            // 如果当前Cell的Type为STRIN
            case HSSFCell.CELL_TYPE_STRING:
                // 取得当前的Cell字符串
                cellvalue = cell.getRichStringCellValue().getString();
                break;
            // 默认的Cell值
            default:
                cellvalue = " ";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;

    }
	public static List<Accident> parserExcel(String filename,File file) {
		System.out.println(filename);
		if(isExcel(filename)){
			if(isExcel2003(filename)){
				return parserExcelXLS(file);
			}else{
				return parserExcel1(file);
			}
		}
		return null;
	}
	private static List<Accident> parserExcel1(File file) {
		List<Accident> list = new ArrayList<Accident>();
		try{
			FileInputStream fis = new FileInputStream(file);  
			XSSFWorkbook wb = new XSSFWorkbook(fis);  
			XSSFSheet sheet = wb.getSheetAt(0); 
			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {  
			    String cellNovalue = "";  
			    XSSFRow row = sheet.getRow(i); 
			    Accident a = new Accident();
			    for(int j = 0;j<row.getPhysicalNumberOfCells();j++){
			    	String id = getXSSCellFormatValue(row.getCell(j++));
					System.out.println("id:"+id);
					String time = getXSSCellFormatValue(row.getCell(j++));
					String province = getXSSCellFormatValue(row.getCell(j++));
					System.out.println(id+time+province);
					String city = getXSSCellFormatValue(row.getCell(j++));
					String category_big = getXSSCellFormatValue(row.getCell(j++));
					String category_small = getXSSCellFormatValue(row.getCell(j++));
					String material = getXSSCellFormatValue(row.getCell(j++));
					String reason_type = getXSSCellFormatValue(row.getCell(j++));
					String result_type = getXSSCellFormatValue(row.getCell(j++));
					System.out.println(j+":"+row.getCell(j));
					String accident_des = getXSSCellFormatValue(row.getCell(j++));
					String accident_reason = getXSSCellFormatValue(row.getCell(j++));
					String accident_reason_des = getXSSCellFormatValue(row.getCell(j++));
					String casualties = getXSSCellFormatValue(row.getCell(j++));
					String outages = getXSSCellFormatValue(row.getCell(j++));
					String other_results = getXSSCellFormatValue(row.getCell(j++));
					String loss_des = getXSSCellFormatValue(row.getCell(j++));
					String information_sources = getXSSCellFormatValue(row.getCell(j++));
					String image_url = getXSSCellFormatValue(row.getCell(j++));
					String note = getXSSCellFormatValue(row.getCell(j++));
					System.out.println(id+time+province);
					a.setAccident_des(accident_des);
					a.setAccident_reason(accident_reason);
					a.setAccident_reason_des(accident_reason_des);
					a.setCasualties(casualties);
					a.setCategory_big(category_big);
					a.setCategory_small(category_small);
					a.setCity(city);
					a.setId(id);
					a.setImage_url(image_url);
					a.setInformation_sources(information_sources);
					a.setLoss_des(loss_des);
					a.setMaterial(material);
					a.setOther_results(other_results);
					a.setOutages(outages);
					a.setProvince(province);
					a.setReason_type(reason_type);
					a.setResult_type(result_type);
					a.setTime(Tools.str2Date1(time));
					a.setNote(note);
			    }
			    list.add(a);
			 } 
			}catch(Exception e){
				e.printStackTrace();
			} 
		return list;
	}
	private static List<Accident> parserExcelXLS(File file) {
		List<Accident> list = new ArrayList<Accident>();
		try{
			FileInputStream fis = new FileInputStream(file);  
	        HSSFWorkbook wb = new HSSFWorkbook(fis);  
	        HSSFSheet sheet = wb.getSheetAt(0); 
	        for (int i = 3; i < sheet.getPhysicalNumberOfRows(); i++) {  
	            String cellNovalue = "";  
	            HSSFRow row = sheet.getRow(i);  
	            Accident a = new Accident();
			    for(short j = 0;j<row.getPhysicalNumberOfCells();j++){
			    	String id = getCellFormatValue(row.getCell(j++));
					System.out.println("id:"+id);
					String time = getCellFormatValue(row.getCell(j++));
					String province = getCellFormatValue(row.getCell(j++));
					System.out.println(id+time+province);
					String city = getCellFormatValue(row.getCell(j++));
					String category_big = getCellFormatValue(row.getCell(j++));
					String category_small = getCellFormatValue(row.getCell(j++));
					String material = getCellFormatValue(row.getCell(j++));
					String reason_type = getCellFormatValue(row.getCell(j++));
					String result_type = getCellFormatValue(row.getCell(j++));
					System.out.println(j+":"+row.getCell(j));
					String accident_des = getCellFormatValue(row.getCell(j++));
					String accident_reason = getCellFormatValue(row.getCell(j++));
					String accident_reason_des = getCellFormatValue(row.getCell(j++));
					String casualties = getCellFormatValue(row.getCell(j++));
					String outages = getCellFormatValue(row.getCell(j++));
					String other_results = getCellFormatValue(row.getCell(j++));
					String loss_des = getCellFormatValue(row.getCell(j++));
					String information_sources = getCellFormatValue(row.getCell(j++));
					String image_url = getCellFormatValue(row.getCell(j++));
					String note = getCellFormatValue(row.getCell(j++));
					System.out.println(id+time+province);
					a.setAccident_des(accident_des);
					a.setAccident_reason(accident_reason);
					a.setAccident_reason_des(accident_reason_des);
					a.setCasualties(casualties);
					a.setCategory_big(category_big);
					a.setCategory_small(category_small);
					a.setCity(city);
					a.setId(id);
					a.setImage_url(image_url);
					a.setInformation_sources(information_sources);
					a.setLoss_des(loss_des);
					a.setMaterial(material);
					a.setOther_results(other_results);
					a.setOutages(outages);
					a.setProvince(province);
					a.setReason_type(reason_type);
					a.setResult_type(result_type);
					a.setTime(Tools.str2Date1(time));
					a.setNote(note);
			    }
			    list.add(a);
	        }
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
}
