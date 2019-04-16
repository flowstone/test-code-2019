package me.xueyao.util;

import me.xueyao.common.BaseResponse;
import me.xueyao.common.BaseStatus;
import me.xueyao.entity.ExcelFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 上传文件工具类
 * @author: Simon.Xue
 * @date: 2019/4/16 11:04
 */
public class UploadFileUtil {

    private static final String XLS_FILE_TYPE = "application/vnd.ms-excel";
    private static final String XLSX_FILE_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    private static final Logger logger = LoggerFactory.getLogger(UploadFileUtil.class);

    public static BaseResponse uploadFileExcel(MultipartFile file, String filePath) throws IOException {
        BaseResponse response = new BaseResponse(BaseStatus.BADPARAM);
        if (!createDirectory(filePath)) {
            logger.error("创建目录失败，请重试 filePath= {}", filePath);
            response.setMsg("创建目录失败");
            return response;
        }

        String contentType = file.getContentType();
        String fileType = checkFileTypeExcel(file.getContentType());
        if (null == fileType) {
            logger.error("该文件不是Excel文件，文件类型 = {}", contentType);
            response.setMsg("该文件不是Excel文件");
            return response;
        }

        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = filePath + UUID.randomUUID().toString().replaceAll("-", "") + suffix;

        File newFile = new File(fileName);
        file.transferTo(newFile);

        ExcelFile excelFile = new ExcelFile();
        excelFile.setFileType(fileType);
        excelFile.setFilePath(fileName);

        response.setCode(BaseStatus.SUCCESS.getCode());
        response.setMsg("上传文件成功");
        response.setData(excelFile);
        return response;
    }

    /**
     * 判断文件类型是否是Excel文件
     * @param contentType
     * @return
     */
    private static String checkFileTypeExcel(String contentType) {
        if (XLS_FILE_TYPE.equals(contentType)) {
            return "xls";
        }
        if (XLSX_FILE_TYPE.equals(contentType)) {
            return "xlsx";
        }
        return null;
    }


    /**
     * 创建目录
     * @param folder
     * @return
     */
    private static boolean createDirectory(String folder) {
        File file = new File(folder);
        if (file.exists()) {
            return true;
        } else {
            return file.mkdirs();
        }
    }


}
