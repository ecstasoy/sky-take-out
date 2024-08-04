package com.sky.service;


import org.springframework.web.multipart.MultipartFile;

public interface AWSS3Service {
    /**
     * Upload file to S3
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}
