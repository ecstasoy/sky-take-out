package com.sky.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.sky.service.AWSS3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.UUID;

@Service
@Slf4j
public class AWSS3ServiceImpl implements AWSS3Service {
    @Value("${sky.aws.bucket}")
    private String bucketName;

    @Autowired
    private AmazonS3 amazonS3;

    public String upload(MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(file.getSize());
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String key = UUID.randomUUID().toString() + suffix;

            PutObjectResult putObjectResult = amazonS3.putObject(new PutObjectRequest(bucketName, key, file.getInputStream(), objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
            String url = String.format("https://s3.us-east-2.amazonaws.com/%s/%s", bucketName, key);
            log.info("Upload file success: {}", url);
            return url;

        } catch (Exception e) {
            log.info("Upload file failed: {}", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
