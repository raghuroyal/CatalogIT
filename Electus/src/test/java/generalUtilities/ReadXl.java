package generalUtilities;

import java.io.FileInputStream;
import jxl.Sheet;
import jxl.Workbook;

public class ReadXl {
	FileInputStream myFile;
	Workbook myBook;
	Sheet mySheet;
	String xlFilePath;

	public ReadXl(String xlFilePath) {
		this.xlFilePath = xlFilePath;
	}

	public Sheet getMySheet(String sheetName) {
		try {
			myFile = new FileInputStream(xlFilePath);
			myBook = Workbook.getWorkbook(myFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mySheet = myBook.getSheet(sheetName);
		return mySheet;
	}

	public int getRowCount(String sheetName) {
		mySheet = getMySheet(sheetName);
		return mySheet.getRows();
	}

	public int getColumnCount(String sheetName) {
		mySheet = getMySheet(sheetName);
		return mySheet.getColumns();
	}

	public String getCellData(String sheetName, int col, int row) {
		mySheet = getMySheet(sheetName);
		return mySheet.getCell(col, row).getContents();
	}

}
