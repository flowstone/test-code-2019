package me.xueyao.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Simon.Xue
 * @date: 2019/4/16 14:25
 */
public class ExcelHandleUtil {

    private static final String XLS = "xls";
    private static final String XLSX = "xlsx";
    private static final Logger logger = LoggerFactory.getLogger(ExcelHandleUtil.class);



    /**
     * 表格数据转为map
     * @author Simon.Xue
     * @param filePath 文件路径
     * @param fileType 文件类型
     * @param ignoreEmpty  是否忽略空值  true 忽略  false 不忽略
     * @return Map<Integer, Map<Integer, Object>>   map集合
     */
    public static Map<Integer, Map<Integer, Object>> excelToMap(String filePath, String fileType, @NotNull boolean ignoreEmpty) {
        Workbook wb = getWorkbook(filePath, fileType);

        //获得第一工作表
        Sheet sheet = wb.getSheetAt(0);
        //获得工作表的行数
        int lastRowNum = sheet.getLastRowNum();
        HashMap<Integer, Map<Integer, Object>> map = new HashMap<>(16);
        //遍历工作表的行数
        for (int rowIndex = 0; rowIndex < lastRowNum; rowIndex++) {
            Row currentRow = sheet.getRow(rowIndex);
            if (null == sheet.getRow(rowIndex)) {
                break;
            }
            //列数总和
            int lastCellNum = (int) currentRow.getLastCellNum();

            Map<Integer, Object> rowMap = new HashMap<>(16);
            //遍历每行的列数
            for (int cellIndex = 0; cellIndex < lastCellNum; cellIndex++) {
                if (null == currentRow.getCell(cellIndex)) {
                    break;
                }

                //判断当前列内容数据的类型
                switch (currentRow.getCell(cellIndex).getCellType()) {
                    case STRING:
                        rowMap.put(cellIndex, currentRow.getCell(cellIndex).getStringCellValue().trim());
                        break;
                    case BOOLEAN:
                        rowMap.put(cellIndex, currentRow.getCell(cellIndex).getBooleanCellValue());
                        break;
                    case NUMERIC:
                        rowMap.put(cellIndex, currentRow.getCell(cellIndex).getNumericCellValue());
                        break;
                    case _NONE:
                    case BLANK:
                    case ERROR:
                    case FORMULA:
                        rowMap.put(cellIndex, "");
                        break;
                    default:
                        rowMap.put(cellIndex, "");
                }

            }
            map.put(rowIndex, rowMap);
        }
        return map;
    }

    /**
     * 获得Workbook对象
     * @param filePath 文件路径
     * @param fileType  文件类型
     * @return Workbook
     */
    private static Workbook getWorkbook(String filePath, String fileType) {
        File file = new File(filePath);
        Workbook wb = null;
        try {
            if (XLS.equals(fileType)) {
                wb = new HSSFWorkbook(new FileInputStream(file));
            }
            if (XLSX.equals(fileType)) {
                wb = new XSSFWorkbook(new FileInputStream(file));
            }
        } catch (Exception e) {
            logger.error("解析Excel异常，请重试");
        }
        return wb;
    }
}
