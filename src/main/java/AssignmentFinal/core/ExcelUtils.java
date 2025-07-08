package AssignmentFinal.core;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelUtils {
    public static Object[][] getTableArray(String filePath, String sheetName, int startCol, int totalCol) {
        String[][] table = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = workbook.getSheet(sheetName);

            int startRow = 1;
            int tableRow = 0, tableCol = 0;
            int totalRow = sheet.getLastRowNum();

            table = new String[totalRow][totalCol];
            for (int row = startRow; row <= totalRow; row++, tableRow++) {
                for (int col = startCol; col <= totalCol; col++, tableCol++) {
                    XSSFCell cell = sheet.getRow(row).getCell(col);
                    table[tableRow][tableCol] = cell.getStringCellValue().trim();
                }
                tableCol = 0;
            }
        } catch (Exception e) {
            System.out.println("Error reading file " + filePath);
        }
        return table;
    }
}
