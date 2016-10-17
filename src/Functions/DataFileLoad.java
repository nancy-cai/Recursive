package functions;

import java.io.File;
import java.io.FileInputStream;
import java.util.Locale;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

public class DataFileLoad {

	public static String HA_GF_readXL(int row, String column, String strFilePath)
	// int row goes with id, not row
	{
		Cell c = null;
		int reqCol = 0;
		WorkbookSettings ws = null;
		Workbook workbook = null;
		Sheet sheet = null;
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(new File(strFilePath));
			ws = new WorkbookSettings();
			ws.setLocale(new Locale("en", "EN"));

			// opening the work book and sheet for reading data
			workbook = Workbook.getWorkbook(fs, ws);
			sheet = workbook.getSheet(0);

			// Sanitize given data
			String col = column.trim();

			// loop for going through the given row
			for (int j = 0; j < sheet.getColumns(); j++) {
				Cell cell = sheet.getCell(j, 0);
				if ((cell.getContents().trim()).equalsIgnoreCase(col)) {
					reqCol = cell.getColumn();
					// System.out.println("column No:"+reqCol);
					c = sheet.getCell(reqCol, row);
					fs.close();
					return c.getContents();
				}
			}
		} catch (BiffException be) {

			System.out.println("The given file should have .xls extension.");
		} catch (Exception e) {
			e.printStackTrace();

		}
		System.out.println("NO MATCH FOUND IN GIVEN FILE: PROBLEM IS COMING FROM DATA FILE");

		return null;
	}

	public static int HA_GF_XLRowCount(String strFilePath, String sColumn) {
		int k;
		for (k = 1; k < 999; k++) {

			String sParamVal = HA_GF_readXL(k, sColumn, strFilePath);
			if (sParamVal.equals("ENDOFROW")) {
				break;
			}

		}

		return k;

	}
}
