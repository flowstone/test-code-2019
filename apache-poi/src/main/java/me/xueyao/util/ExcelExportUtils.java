package me.xueyao.util;

import me.xueyao.entity.ExcelHead;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

/**
 * @author Simon.Xue
 * @date 2019-12-04 14:47
 **/
public class ExcelExportUtils {
    /**
     * 导出Excel
     *
     * @param fileName  文件名称
     * @param sheetName sheet名称
     * @param title     标题
     * @param values    内容
     * @return
     */
    public static OutputStream exportExcel(String fileName, String sheetName, List<ExcelHead> title, List values, HttpServletResponse response) throws Exception {
        //设置响应流
        fileName = new String(fileName.getBytes(), "utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename = " + fileName);
        OutputStream os = response.getOutputStream();
        //创建文件
        HSSFWorkbook wb = new HSSFWorkbook();
        //创建对应sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        //添加表头第0行
        HSSFRow row = sheet.createRow(0);
        //创建单元格，设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        //水平居中
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        //内容自动换行
        style.setWrapText(true);
        //声明列对象
        HSSFCell cell = null;
        //创建标题
        if (title.isEmpty()) {
            wb.write(os);
            os.flush();
            os.close();
            return os;
        }

        for (int i = 0; i < title.size(); i++) {
            sheet.setColumnWidth(i, title.get(i).getFieldDesc().getBytes().length *2* 256);
        }
        for (int i = 0; i < title.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(title.get(i).getFieldDesc());
            cell.setCellStyle(style);
        }
        if (values.isEmpty()) {
            wb.write(os);
            os.flush();
            os.close();
            return os;
        }
        //创建内容
        for (int i = 0; i < values.size(); i++) {
            row = sheet.createRow(i + 1);
            Field[] field = values.get(i).getClass().getDeclaredFields();
            for (int j = 0; j < title.size(); j++) {
                cell = row.createCell(j);
                cell.setCellStyle(style);
                for (int k = 0; k < field.length; k++) {
                    field[k].setAccessible(true);
                    if (title.get(j).getFieldName().equals(field[k].getName())) {
                        if (null != field[k].get(values.get(i))
                                && (title.get(j).getFieldName().equals("createTime") || title.get(j).getFieldName().equals("endTime"))) {
                            cell.setCellValue(DateFormatUtils.format((Date) field[k].get(values.get(i)), "yyyy-MM-dd").toString());
                        } else if (null != field[k].get(values.get(i))) {
                            cell.setCellValue(new HSSFRichTextString(field[k].get(values.get(i)).toString()));
                        }
                    }
                }
            }
        }
        wb.write(os);
        os.flush();
        os.close();
        return os;
    }
}
