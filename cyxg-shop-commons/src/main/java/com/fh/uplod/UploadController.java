package com.fh.uplod;

import com.fh.result.ResultUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;


public class UploadController {
    @Value("${file.user.photo}")
    private String userPhotoPath;
    @PostMapping
    public ResultUtils uploadFile(MultipartFile file, HttpServletRequest request) throws IOException {
            System.out.println("获取到的图片名称为:"+file.getOriginalFilename());
            //对图片进行重命名
            String oldName=file.getOriginalFilename();
            Long fileTimeName=System.currentTimeMillis();
            String newName=fileTimeName+oldName.substring(oldName.lastIndexOf("."));
            //这个就是准备文件上传到服务器的保存路径
            String filePath=userPhotoPath+newName;
            File dest=new File(filePath);
            if(!dest.getParentFile().exists()){
                dest.getParentFile().mkdirs();
            }
            //将临时文件保存到制定的目录中去
            file.transferTo(dest);
            //返回相对路径
            String realPath="/userPhoto/"+newName;
        return ResultUtils.success(realPath);
    }
}
