package com.erp.service.impl;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.erp.response.BaseMessage;
import com.erp.response.MessageCode;

@Service
public class FileService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    
    /**
     * 文件上传
     * 
     * @param file
     * @return
     */
    public BaseMessage upload(final MultipartFile file,HttpServletRequest httpRequest) {
        BaseMessage baseMessage = null;
        if (!file.isEmpty()) {
            // 取当前时间戳作为文件名前缀
            String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            String path = httpRequest.getSession().getServletContext().getRealPath("/");
            LOGGER.info("-----------------------文件路径:{}",path);
            File destFile = new File(path + filename);
            try {
                // FileUtils.copyInputStreamToFile()这个方法里对IO进行了自动操作，不需要额外的再去关闭IO流
                // 保存文件目录下
                FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);
            } catch (IOException e) {
                e.printStackTrace();
                LOGGER.error(file.getOriginalFilename() + "上传失败", e);
                baseMessage = new BaseMessage(MessageCode.FAILED);
                return baseMessage;
            }
            baseMessage = new BaseMessage(MessageCode.SUCCESSED);
            baseMessage = new BaseMessage(MessageCode.SUCCESSED);
            baseMessage.setData(filename);
            return baseMessage;
        } else {
            LOGGER.info("获取不到上传文件");
            baseMessage = new BaseMessage(MessageCode.FAILED);
            return baseMessage;
        }
    }
}
