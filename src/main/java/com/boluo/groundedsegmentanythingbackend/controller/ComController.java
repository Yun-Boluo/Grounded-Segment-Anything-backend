package com.boluo.groundedsegmentanythingbackend.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.boluo.groundedsegmentanythingbackend.api.API;
import com.boluo.groundedsegmentanythingbackend.api.dto.APIDto;
import com.boluo.groundedsegmentanythingbackend.domain.Result;
import com.boluo.groundedsegmentanythingbackend.domain.dto.SImagesDto;
import com.boluo.groundedsegmentanythingbackend.domain.python.Python;
import com.boluo.groundedsegmentanythingbackend.service.CommonService;
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
@CrossOrigin
public class ComController {

    @Resource
    private API api;


    @Autowired
    Python python;

    @Autowired
    private MinioUtils minioUtils;

    @Autowired
    private PImagesService pImagesService;


    @Autowired
    private CommonService commonService;

    /**
     * 上传
     * @param file
     * @return
     */
    @CrossOrigin
    @PostMapping("/upload")
    public Result uplodFile(@RequestParam("file") MultipartFile file, @RequestParam("textPrompt") String textPrompt,
                            @RequestParam("boxTreshold") String boxTreshold,@RequestParam("textTreshold") String textTreshold){
        SImagesDto  sImagesDto = new SImagesDto(textPrompt,boxTreshold, textTreshold);
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        int index = fileName.lastIndexOf(".");
        // test.jpg -> .jpg
        String fileType = fileName.substring(index);

        String pPath = fileSave2Local(file,fileType);

        // 1. 生成存放的名称
        String pname = UUID.randomUUID().toString().replaceAll("-", "")+fileType;
        String sname = UUID.randomUUID().toString().replaceAll("-", "")+fileType;


        // 2. 上传python 返回生成图片的路径
        APIDto apiDto = new APIDto(pPath,sImagesDto);
        String path = api.comWithPython(apiDto);
        String url1 = null;
        String url2 = null;
        // 3. 调用minio并返回url
        try {
            // 上传父
            url1 = minioUtils.uploadFile(python.getInputPath()+"\\"+ pPath, "demo", pname);
            // 上传子
            url2 = minioUtils.uploadFile(python.getOutput() +"\\"+ path, "demo", sname);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // 当前时间
        DateTime date = DateUtil.date();
        Assert.assertNotNull(date);

        // 4. 将数据存入数据库

        return pImagesService.insert(sImagesDto, pname, url1, url2, date);
    }

    /**
     *  查询所有样例返回
     * @return
     */
    @PostMapping("/searchAll")
    public Result searchAll(){
        return commonService.searchAll();
    }


    // 将图片存储到本地并保存
    public String fileSave2Local(MultipartFile file,String fileType){
        if (file.isEmpty()) {
            return "a.jpg";
        }

        File dest = new File(new File(python.getInputPath()).getAbsolutePath()+ "/" + "a"+fileType);

        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        if (dest.exists()) {
            dest.delete();
        }
        try {
            file.transferTo(dest); // 保存文件
            return "a" + fileType;
        } catch (Exception e) {
            e.printStackTrace();
            return "a" + fileType;
        }

    }


}
