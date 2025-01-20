package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/*
 * 文件上传,保存在本地方式(不推荐)
 */
/*@Slf4j
@RestController
public class UploadController {
    // 上传文件保存的目录
    private static final String UPLOAD_DIR = "E:/image/";

    @PostMapping("/upload")
    public Result upload(@RequestParam String name, @RequestParam Integer age, @RequestParam MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error("文件为空");
        }
        try {
            // 创建目标目录（如果不存在）
            File directory = new File(UPLOAD_DIR);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // 生成唯一的文件名
            // 获取原始文件名
            String originalFilename = file.getOriginalFilename();
            // 获取文件扩展名
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            // 生成唯一的文件名
            String uniqueFileName = UUID.randomUUID().toString() + extension;
            // 创建目标文件
            File destinationFile = new File(UPLOAD_DIR, uniqueFileName);

            // 保存文件
            file.transferTo(destinationFile);

            log.info("文件已保存到: {}", destinationFile.getAbsolutePath());
            return Result.success("文件上传成功");
        } catch (IOException e) {
            log.error("文件上传失败", e);
            return Result.error("文件上传失败");
        }
    }
}

 */
@Slf4j
@RestController
public class UploadController {
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;
    @PostMapping("/upload")
    public Result upload( MultipartFile file) throws Exception {
        log.info("文件上传开始,文件原始名称:{}",file.getOriginalFilename());
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        return Result.success(url);
    }
}
