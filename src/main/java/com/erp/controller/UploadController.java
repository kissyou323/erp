package com.erp.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.erp.config.Config;
import com.erp.response.BaseMessage;
import com.erp.service.impl.FileService;

/**
 * 上传文件
 * @author liuyang
 *
 */
@Controller
@RequestMapping(value = "/uploadFile")
public class UploadController {
    
    private Logger LOGGER = LoggerFactory.getLogger(UploadController.class);
    
    @Autowired
    private FileService fileService;
    
    @Autowired
    private Config config;
    
    
    @RequestMapping(value = "upload",method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage upload(@RequestParam("file") final MultipartFile file,final HttpServletRequest httpRequest) {
        return fileService.upload(file,httpRequest);
    }

    @RequestMapping(value="/uploadFlie", method=RequestMethod.POST)
    @ResponseBody  
    public String uploadFlie(HttpServletRequest request)
            throws IOException {
        LOGGER.info("开始上传文件>>>>");
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        String fileName = "";
        String newFileName = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 4)+ new Date().getTime();
        String suffixName = "";
        FileOutputStream fos = null;
        try {
            for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
                MultipartFile myfile = entity.getValue();
                fileName = myfile.getOriginalFilename();
                if (!fileName.equals("")) {
                    suffixName = fileName.substring(fileName.lastIndexOf("."));
                }
                byte[] bs = myfile.getBytes();
                File file = new File(config.getUpload_url() + newFileName + suffixName);
                fos = new FileOutputStream(file);
                fos.write(bs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fos.close();
        }
        LOGGER.info("上传文件结束>>>>" +  "{\"url\" : \"" + config.getUpload_url() + fileName + "\"}" + "newFileName:" + newFileName + suffixName);
        return newFileName + suffixName;
    }
    public static void main(String[] args) {
        int a = (int) (Math.random() * 100000);
        String str = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 4)+ new Date().getTime();
        System.out.println(new Date().getTime());
        System.out.println(Math.random() * 100000);
        System.out.println(a);
        System.out.println(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 4));
        System.out.println(str);
        String fileName = "111111.jpg";
        System.out.println(fileName.substring(fileName.lastIndexOf(".")));
    }
}
