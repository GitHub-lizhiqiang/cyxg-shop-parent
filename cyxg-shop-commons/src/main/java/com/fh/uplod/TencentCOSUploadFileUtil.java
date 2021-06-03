package com.fh.uplod;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Random;

public class TencentCOSUploadFileUtil {
    // 存储桶名称
    private static final String BUCKET_NAME = "images-1305923660";
    //secretId 秘钥id
    private static final String SECRET_ID = "AKIDDF1YomY6PSGn6AyWpsfCUWIp67sNccNs";
    //SecretKey 秘钥
    private static final String SECRET_KEY = "GI5ctviFVXLtCF3dx1qPimZ7PREm46sP";
    // 腾讯云 自定义文件夹名称
    private static final String PREFIX = "/images/";
    // 访问域名
    public static final String URL = "https://images-1305923660.cos.ap-shanghai.myqcloud.com";
    // 创建COS 凭证
    private static final COSCredentials credentials = new BasicCOSCredentials(SECRET_ID,SECRET_KEY);
    // 配置 COS 区域 就购买时选择的区域 我这里是 广州（guangzhou）
    private static final ClientConfig clientConfig = new ClientConfig(new Region("ap-shanghai"));

    public static String uploadfile(MultipartFile file){
        // 创建 COS 客户端连接
        COSClient cosClient = new COSClient(credentials,clientConfig);
        String fileName = file.getOriginalFilename();
        try {
            String substring = fileName.substring(fileName.lastIndexOf("."));
            File localFile = File.createTempFile(String.valueOf(System.currentTimeMillis()),substring);
            file.transferTo(localFile);
            Random random = new Random();
            fileName =PREFIX+random.nextInt(10000)+System.currentTimeMillis()+substring;
            // 将 文件上传至 COS
            PutObjectRequest objectRequest = new PutObjectRequest(BUCKET_NAME,fileName,localFile);
            cosClient.putObject(objectRequest);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cosClient.shutdown();
        }
        return URL+fileName;
    }
}

