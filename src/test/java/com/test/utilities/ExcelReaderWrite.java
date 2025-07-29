package com.test.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.test.objectRepository.DistrictEventsPage.Event;
import com.test.resources.Activity;
import com.test.resources.Dining;
import com.test.resources.Movie;

public class ExcelReaderWrite {
	static String filepath;
	static FileInputStream fis;
	static XSSFWorkbook wb;
	static XSSFSheet sheet;
	static String sheetName;
	static FileOutputStream fos;
	static XSSFRow row;
	static File f;
	static XSSFCell cell;
	static int rows;
	static int col;
    static Object[][] loginData;

	public ExcelReaderWrite(String filepath, String sheetName) {
	    
		ExcelReaderWrite.filepath = filepath;
		f = new File(System.getProperty("user.dir") + "\\" + filepath);
		System.out.println(f);
		try {
			fis = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheet(sheetName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int getRowsCount() {
		rows = sheet.getPhysicalNumberOfRows();
		return rows;
	}

	public static int getColumnCount() {
		Row row = sheet.getRow(0);
		col = row.getLastCellNum();
		System.out.println("column count "+col);
		return col;
	}

	public static String getCellData(int rows, int column) {
		Cell cell = sheet.getRow(rows).getCell(column,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		return cell.toString();
	}

	public static void setCellData(String xlsheet,int rownum,int colnum,String data )throws IOException{
		fis=new FileInputStream(f);
		wb=new XSSFWorkbook(fis);
		sheet=wb.getSheet(xlsheet);

		row=sheet.getRow(rownum);
		if (row == null) {
			row = sheet.createRow(rownum);
		}

		row=sheet.getRow(rownum);

		cell=row.createCell(colnum);
		cell.setCellValue(data);

		fos=new FileOutputStream(f);
		wb.write(fos);
	}
	
	public static Object[][] excelDataFetch() {
		System.out.println(filepath);
		System.out.println(sheet);
        int rows = getRowsCount();
        int cols = getColumnCount();
        System.out.println("cols: " + cols);
        loginData = new Object[rows - 1][cols - 1];
        
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols-1; j++) {
                loginData[i - 1][j] = getCellData(i, j);
            }
        }
        return loginData;
    }
	
	public static void writeEvents(String filePath, String sheetName, List<Event> events) throws IOException {
        // 1) Prepare file & workbook
        File file = new File(System.getProperty("user.dir") + File.separator + filePath);
        XSSFWorkbook workbook;
        XSSFSheet sheet;

        if (file.exists() && file.length() > 0) {
            // load existing
            try (FileInputStream fis = new FileInputStream(file)) {
                workbook = new XSSFWorkbook(fis);
            }
            sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                sheet = workbook.createSheet(sheetName);
            }
        }
        else {
            // create new
            workbook = new XSSFWorkbook();
            sheet    = workbook.createSheet(sheetName);
        }

        // 2) Write headers
        XSSFRow header = sheet.getRow(0) != null
            ? sheet.getRow(0)
            : sheet.createRow(0);

        header.createCell(0).setCellValue("Date");
        header.createCell(1).setCellValue("Name");
        header.createCell(2).setCellValue("Location");
        header.createCell(3).setCellValue("Price");

        // 3) Write event rows
        for (int i = 0; i < events.size(); i++) {
            Event ev = events.get(i);
            XSSFRow row = sheet.getRow(i + 1) != null
                ? sheet.getRow(i + 1)
                : sheet.createRow(i + 1);

            row.createCell(0).setCellValue(ev.getDate());
            row.createCell(1).setCellValue(ev.getName());
            row.createCell(2).setCellValue(ev.getLocation());
            row.createCell(3).setCellValue(ev.getPrice());
        }

        // 4) Persist to disk
        try (FileOutputStream fos = new FileOutputStream(file)) {
            workbook.write(fos);
        }
        workbook.close();
    }
	
	public static void writeActivities(String filePath, String sheetName, List<Activity> activities) throws IOException {
		// 1) Prepare file & workbook
		File file = new File(System.getProperty("user.dir") + File.separator + filePath);
		XSSFWorkbook workbook;
		XSSFSheet sheet;
		
		if (file.exists() && file.length() > 0) {
			// load existing
			try (FileInputStream fis = new FileInputStream(file)) {
				workbook = new XSSFWorkbook(fis);
			}
			sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				sheet = workbook.createSheet(sheetName);
			}
		}
		else {
			// create new
			workbook = new XSSFWorkbook();
			sheet    = workbook.createSheet(sheetName);
		}
		
		// 2) Write headers
		XSSFRow header = sheet.getRow(0) != null
				? sheet.getRow(0)
						: sheet.createRow(0);
		
		header.createCell(0).setCellValue("Start Date");
		header.createCell(1).setCellValue("End Date");
		header.createCell(2).setCellValue("Activity Name");
		header.createCell(3).setCellValue("Location");
		header.createCell(4).setCellValue("Price");
		
		
		
		// 3) Write activity rows
		for (int i = 0; i < activities.size(); i++) {
			Activity ac = activities.get(i);
			XSSFRow row = sheet.getRow(i + 1) != null
					? sheet.getRow(i + 1)
							: sheet.createRow(i + 1);
			
			String startDateTime;
			String endDateTime;
			
			if(ac.getEndDateTime() == null) {
				endDateTime = "Null";
			}else {
				endDateTime = ac.getEndDateTime().format(DateTimeFormatter.ISO_DATE_TIME);
			}
			
			if(ac.getStartDateTime() == null) {
				startDateTime = "Null";
			}else {
				startDateTime = ac.getStartDateTime().format(DateTimeFormatter.ISO_DATE_TIME);
			}
			
			row.createCell(0).setCellValue(startDateTime);
			row.createCell(1).setCellValue(endDateTime);
			row.createCell(2).setCellValue(ac.getActivity());
			row.createCell(3).setCellValue(ac.getLocation());
			row.createCell(4).setCellValue(ac.getPrice());
		}
		
		// 4) Persist to disk
		try (FileOutputStream fos = new FileOutputStream(file)) {
			workbook.write(fos);
		}
		workbook.close();
	}
	
	public static void writeMovies(String filePath, String sheetName, List<Movie> movies) throws IOException {
		// 1) Prepare file & workbook
		File file = new File(System.getProperty("user.dir") + File.separator + filePath);
		XSSFWorkbook workbook;
		XSSFSheet sheet;
		
		if (file.exists() && file.length() > 0) {
			// load existing
			try (FileInputStream fis = new FileInputStream(file)) {
				workbook = new XSSFWorkbook(fis);
			}
			sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				sheet = workbook.createSheet(sheetName);
			}
		}
		else {
			// create new
			workbook = new XSSFWorkbook();
			sheet    = workbook.createSheet(sheetName);
		}
		
		// 2) Write headers
		XSSFRow header = sheet.getRow(0) != null
				? sheet.getRow(0)
						: sheet.createRow(0);
		
		header.createCell(0).setCellValue("Movie Name");
		header.createCell(1).setCellValue("Language");
		header.createCell(2).setCellValue("Rating");
		
		// 3) Write movie rows
		for (int i = 0; i < movies.size(); i++) {
			Movie mv = movies.get(i);
			XSSFRow row = sheet.getRow(i + 1) != null
					? sheet.getRow(i + 1)
							: sheet.createRow(i + 1);
			
			row.createCell(0).setCellValue(mv.getMovieName());
			row.createCell(1).setCellValue(mv.getMovieLanguage());
			row.createCell(2).setCellValue(mv.getRating());
		}
		
		// 4) Persist to disk
		try (FileOutputStream fos = new FileOutputStream(file)) {
			workbook.write(fos);
		}
		workbook.close();
	}
	
	public static void writeDining(String filePath, String sheetName, List<Dining> dining) throws IOException {
		// 1) Prepare file & workbook
		File file = new File(System.getProperty("user.dir") + File.separator + filePath);
		XSSFWorkbook workbook;
		XSSFSheet sheet;
		
		if (file.exists() && file.length() > 0) {
			// load existing
			try (FileInputStream fis = new FileInputStream(file)) {
				workbook = new XSSFWorkbook(fis);
			}
			sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				sheet = workbook.createSheet(sheetName);
			}
		}
		else {
			// create new
			workbook = new XSSFWorkbook();
			sheet    = workbook.createSheet(sheetName);
		}
		
		// 2) Write headers
		XSSFRow header = sheet.getRow(0) != null
				? sheet.getRow(0)
						: sheet.createRow(0);
		
		header.createCell(0).setCellValue("Name");
		header.createCell(1).setCellValue("Rate");
		header.createCell(2).setCellValue("Price");
		header.createCell(3).setCellValue("Time");
		header.createCell(4).setCellValue("Address");
		
		// 3) Write dining rows
		for (int i = 0; i < dining.size(); i++) {
			Dining dn = dining.get(i);
			XSSFRow row = sheet.getRow(i + 1) != null
					? sheet.getRow(i + 1)
							: sheet.createRow(i + 1);
			
			row.createCell(0).setCellValue(dn.getName());
			row.createCell(1).setCellValue(dn.getRate());
			row.createCell(2).setCellValue(dn.getPrice());
			row.createCell(3).setCellValue(dn.getTime());
			row.createCell(4).setCellValue(dn.getAddress());
		}
		
		// 4) Persist to disk
		try (FileOutputStream fos = new FileOutputStream(file)) {
			workbook.write(fos);
		}
		workbook.close();
	}
	
	public static void writeLetterCity(String filePath, String sheetName, List<String> cities) throws IOException {
		// 1) Prepare file & workbook
		File file = new File(System.getProperty("user.dir") + File.separator + filePath);
		XSSFWorkbook workbook;
		XSSFSheet sheet;
		
		if (file.exists() && file.length() > 0) {
			// load existing
			try (FileInputStream fis = new FileInputStream(file)) {
				workbook = new XSSFWorkbook(fis);
			}
			sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				sheet = workbook.createSheet(sheetName);
			}
		}
		else {
			// create new
			workbook = new XSSFWorkbook();
			sheet    = workbook.createSheet(sheetName);
		}
		
		// 2) Write headers
		XSSFRow header = sheet.getRow(0) != null
				? sheet.getRow(0)
						: sheet.createRow(0);
		
		header.createCell(0).setCellValue("Letter A");
		
		// 3) Write city rows
		for (int i = 0; i < cities.size(); i++) {
			String ct = cities.get(i);
			XSSFRow row = sheet.getRow(i + 1) != null
					? sheet.getRow(i + 1)
							: sheet.createRow(i + 1);
			
			row.createCell(0).setCellValue(ct);
		}
		
		// 4) Persist to disk
		try (FileOutputStream fos = new FileOutputStream(file)) {
			workbook.write(fos);
		}
		workbook.close();
	}
}
