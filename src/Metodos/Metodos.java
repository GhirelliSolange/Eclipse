
package Metodos;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Metodos {

	static WebDriver driver;
	private static XSSFSheet ExcelWSheet;
	private static XSSFCell Cell;

	public static WebDriver setup() {
		System.setProperty("webdriver.chrome.driver", "Chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		return driver;
	}

	public static void sleep(int seconds) {
		int miliseconds = seconds * 1000;
		try {
			Thread.sleep(miliseconds);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	public int retornaFilas(String filename) throws IOException {
		int count = 0;
		try (FileInputStream fis = new FileInputStream(filename)) {
			XSSFWorkbook ExcelWBook = new XSSFWorkbook(fis);
			XSSFSheet ExcelWSheet = ExcelWBook.getSheetAt(0);
			count = ExcelWSheet.getPhysicalNumberOfRows();
			ExcelWBook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return count - 1;
	}

	public String retornaDato(int fila, int columna, String filename) throws IOException {
		String CellData = null;

		try (FileInputStream fis = new FileInputStream(filename)) {
			XSSFWorkbook ExcelWBook = new XSSFWorkbook(fis);
			ExcelWSheet = ExcelWBook.getSheetAt(0);
			Cell = (ExcelWSheet).getRow(fila).getCell(columna);
			if (Cell == null) {
				CellData = "";
			} else {
				switch ((Cell).getCellTypeEnum()) {
				case NUMERIC:
					CellData = Long.toString((long) (Cell).getNumericCellValue());
					break;
				case STRING:
					CellData = (Cell).getStringCellValue();
					break;
				default:
					CellData = "";
					break;
				}
			}

			ExcelWBook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return CellData;
	}

	public void writeCellValue(String filepath, String sheetName, int rowNumber, int cellNumber, String resultText)
			throws IOException {

		File file = new File(filepath);

		FileInputStream inputStream = new FileInputStream(file);

		try (XSSFWorkbook newWorkbook = new XSSFWorkbook(inputStream)) {
			XSSFSheet newSheet = newWorkbook.getSheet(sheetName);

			XSSFRow row = newSheet.getRow(rowNumber);

			XSSFCell firstCell = row.getCell(cellNumber - 1);

			System.out.println("first cell value is:" + firstCell.getStringCellValue());

			XSSFCell nextCell = row.createCell(cellNumber);
			nextCell.setCellValue(resultText);

			System.out.println("nextcell value:" + nextCell.getStringCellValue());

			inputStream.close();

			FileOutputStream outputStream = new FileOutputStream(file);

			newWorkbook.write(outputStream);

			outputStream.close();
		}

	}

}
