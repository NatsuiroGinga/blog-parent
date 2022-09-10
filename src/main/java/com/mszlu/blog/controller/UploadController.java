package com.mszlu.blog.controller;

import com.mszlu.blog.utils.QiniuUtils;
import com.mszlu.blog.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @author ginga
 * @version 1.0
 * @ClassName UploadController
 * @Date 10/9/2022 上午9:48
 */
@RestController
@RequestMapping("upload")
public class UploadController {

    @Autowired
    private QiniuUtils qiniuUtils;

    @PostMapping
    public Result upload(@RequestParam("image")MultipartFile file) {
        // 原始文件名称
        final String originalFilename = file.getOriginalFilename();
        // 唯一文件名称
        final String filename = UUID.randomUUID() + "." +
                StringUtils.substringAfterLast(originalFilename, ".");
        // 上传文件
        if (qiniuUtils.upload(file, filename)) {
            return Result.success(QiniuUtils.url + filename);
        }

        return Result.fail(20001, "上传失败");
    }
}
