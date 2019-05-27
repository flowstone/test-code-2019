package me.xueyao.controller;

import com.alibaba.fastjson.JSONObject;
import me.xueyao.common.BaseResponse;
import me.xueyao.common.BaseStatus;
import me.xueyao.entity.ExcelFile;
import me.xueyao.entity.UserInfo;
import me.xueyao.util.ExcelHandleUtil;
import me.xueyao.util.UploadFileUtil;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author: Simon.Xue
 * @date: 2019/4/16 11:15
 */
@RestController
@RequestMapping("/test")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @PostMapping("/uploadFile")
    public BaseResponse uploadFile(MultipartFile file) {
        BaseResponse response = new BaseResponse();
        String filePath = "F:\\data\\java\\excel\\";
        try {
            response = UploadFileUtil.uploadFileExcel(file, filePath);
            if (BaseStatus.SUCCESS.getCode() == response.getCode()) {
                ExcelFile excelFile = (ExcelFile) response.getData();
                importExcel(excelFile.getFilePath(), excelFile.getFileType());
            }
        } catch (IOException e) {
            logger.warn("上传文件异常，请重试 {}", e);
            response.setCode(BaseStatus.BADPARAM.getCode());
            response.setMsg("上传文件异常，请重试");
        }
        return response;
    }

    public BaseResponse importExcel(String filePath, String fileType) {
        BaseResponse response = new BaseResponse();
        Map<Integer, Map<Integer, Object>> map = ExcelHandleUtil.excelToMap(filePath, fileType, true);
        logger.info("map = {}", JSONObject.toJSONString(map));
        return response;
    }

    @GetMapping("/export")
    public BaseResponse exportExcel() throws ClassNotFoundException {

        HSSFWorkbook sheets = new HSSFWorkbook();
        HSSFSheet xlsSheet = sheets.createSheet("成绩表");
        List<UserInfo> userInfos = new ArrayList<>();
        UserInfo userInfo = new UserInfo();

        for (int i = 0; i < 10; i++) {
            userInfo.setUsername("小明" + i);
            userInfo.setEmail("xueyao.me" + i + "@gmail.com");
            userInfo.setAge(i);
            userInfo.setSex(1);
            userInfo.setMobile("");
            userInfos.add(userInfo);
        }

        HSSFRow row = xlsSheet.createRow(0);

        HSSFCell cell = row.createCell(0);
        cell.setCellValue("用户信息表");
        HSSFRow row1 = xlsSheet.createRow(1);
        HSSFCell cell1 = row1.createCell(0);
        cell1.setCellValue("用户名");
        row1.createCell(1).setCellValue("手机号");
        row1.createCell(2).setCellValue("年龄");
        row1.createCell(3).setCellValue("性别");
        row1.createCell(4).setCellValue("邮箱");
        int lastRowNum = xlsSheet.getLastRowNum();
        Class<?> aClass = Class.forName("me.xueyao.entity.UserInfo");
        int length = aClass.getDeclaredFields().length;

        for (int i = lastRowNum+1; i < length + lastRowNum; i++) {
            int j = 0;
            HSSFRow row2 = xlsSheet.createRow(i);
            row2.createCell(0).setCellValue(userInfos.get(j).getUsername());
            row2.createCell(1).setCellValue(userInfos.get(j).getMobile());
            row2.createCell(2).setCellValue(userInfos.get(j).getAge());
            row2.createCell(3).setCellValue(userInfos.get(j).getSex());
            row2.createCell(4).setCellValue(userInfos.get(j).getEmail());
            j++;
        }

        File file = new File("a.xls");
        try {
            sheets.write(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}
