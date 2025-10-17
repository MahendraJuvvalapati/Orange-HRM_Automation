package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    private static final Logger log = LogManager.getLogger(ExcelUtils.class);

    public static String[][] loginData(String filePath, String sheetName) throws IOException {
        log.info("📂 Opening Excel file: {}", filePath);

        try (FileInputStream fis = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                log.error("❌ Sheet '{}' not found in file: {}", sheetName, filePath);
                throw new IOException("Sheet '" + sheetName + "' not found in file: " + filePath);
            }

            int noOfRows = sheet.getLastRowNum();
            int noOfColumns = sheet.getRow(0).getLastCellNum();
            log.info("📊 Sheet '{}' has {} rows and {} columns", sheetName, noOfRows, noOfColumns);

            String[][] data = new String[noOfRows][noOfColumns];

            for (int i = 1; i <= noOfRows; i++) {
                XSSFRow row = sheet.getRow(i);
                for (int j = 0; j < noOfColumns; j++) {
                    XSSFCell cell = row.getCell(j);
                    data[i - 1][j] = (cell == null) ? "" : cell.toString();
                    log.debug("Row {}, Column {} value: {}", i, j, data[i - 1][j]);
                }
            }

            log.info("✅ Successfully read data from Excel sheet '{}'", sheetName);
            return data;
        } catch (IOException e) {
            log.error("❌ Failed to read Excel file: {}", filePath, e);
            throw e;
        }
    }
}
