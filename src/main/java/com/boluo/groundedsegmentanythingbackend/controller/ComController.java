package com.boluo.groundedsegmentanythingbackend.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.boluo.groundedsegmentanythingbackend.api.API;
import com.boluo.groundedsegmentanythingbackend.api.dto.APIDto;
import com.boluo.groundedsegmentanythingbackend.domain.Result;
import com.boluo.groundedsegmentanythingbackend.domain.dto.SImagesDto;
import com.boluo.groundedsegmentanythingbackend.domain.python.Python;
import com.boluo.groundedsegmentanythingbackend.service.PImagesService;
import com.boluo.groundedsegmentanythingbackend.utils.MinioUtils;
import junit.framework.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.UUID;

/**
 * @author kirit
 * @version 1.0
 * @description: TODO
 * @date 2023/6/9 20:43
 */
@RestController
@RequestMapping("/common")
@Slf4j
public class ComController {

    @Resource
    private API api;


    @Autowired
    Python python;

    @Autowired
    private MinioUtils minioUtils;

    @Autowired
    private PImagesService pImagesService;

    /**
     *
     * @param file
     * @param sImagesDto
     * @return
     */
    @PostMapping("/upload")
    public Result uplodFile(@RequestParam("file") MultipartFile file, @RequestParam("sImagesDto")SImagesDto sImagesDto){
        // 1. 生成存放的名称
        String pname = UUID.randomUUID().toString().replaceAll("-", "");
        String sname = UUID.randomUUID().toString().replaceAll("-", "");

        // 2. 上传python 返回生成图片的路径
        APIDto apiDto = new APIDto(fileSave2Local(file),sImagesDto);
        String path = api.comWithPython(apiDto);
        String url1 = null;
        String url2 = null;
        // 3. 调用minio并返回url
        try {
            // 上传父
            url1 = minioUtils.uploadFile(file, "demo", pname);
            // 上传子
            url2 = minioUtils.uploadFile(python.getOutput() + path, "demo", sname);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // 当前时间
        DateTime date = DateUtil.date();
        Assert.assertNotNull(date);

        // 4. 将数据存入数据库
        pImagesService.insert(sImagesDto, pname, url1, url2, date);
        return Result.ok();
    }


    // 将图片存储到本地并保存
    public String fileSave2Local(MultipartFile file){
        if (file.isEmpty()) {
            return "a.jpg";
        }
        String fileName = file.getOriginalFilename();
        int index = fileName.lastIndexOf(".");
        // test.jpg -> .jpg
        String fileType = fileName.substring(index);
        File dest = new File(new File(python.getInputPath()).getAbsolutePath()+ "/" + "a"+fileType);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest); // 保存文件
            return new StringBuilder().append("a").append(fileType).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "a.jpg";
        }

    }

}
