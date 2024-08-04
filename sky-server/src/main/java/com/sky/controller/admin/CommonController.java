package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.service.AWSS3Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * Common controller
 */
@RestController
@RequestMapping("/admin/common")
@Api(tags = "Common Controller")
@Slf4j
public class CommonController {

    @Autowired
    private AWSS3Service awsS3Service;

    @PostMapping("/upload")
    @ApiOperation(value = "Upload file")
    public Result<String> upload(@RequestParam("file") MultipartFile file) {
        log.info("Upload file: {}", file);
        String url = awsS3Service.upload(file);
        return Result.success(url);
    }
}
