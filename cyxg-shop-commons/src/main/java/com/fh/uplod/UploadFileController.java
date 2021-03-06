package com.fh.uplod;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@RestController
@RequestMapping("/upload")
public class UploadFileController {
    @PostMapping
    public String upload(@RequestParam("file") MultipartFile file){
        if (null == file) {
            return "文件为空";
        }
        String filePath = TencentCOSUploadFileUtil.uploadfile(file);
        return "上传成功，访问地址为:"+filePath;
    }
}
