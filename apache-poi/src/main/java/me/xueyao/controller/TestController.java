package me.xueyao.controller;

import com.alibaba.fastjson.JSONObject;
import me.xueyao.common.BaseResponse;
import me.xueyao.common.BaseStatus;
import me.xueyao.entity.ExcelFile;
import me.xueyao.util.ExcelHandleUtil;
import me.xueyao.util.UploadFileUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

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
}
