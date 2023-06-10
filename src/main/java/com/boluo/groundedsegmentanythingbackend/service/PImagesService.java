package com.boluo.groundedsegmentanythingbackend.service;

import cn.hutool.core.date.DateTime;
import com.boluo.groundedsegmentanythingbackend.domain.dto.SImagesDto;
import com.boluo.groundedsegmentanythingbackend.domain.entity.PImages;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author kirit
* @description 针对表【p_images(用于存放图片路径等)】的数据库操作Service
* @createDate 2023-06-10 14:23:36
*/
public interface PImagesService extends IService<PImages> {

    void insert(SImagesDto sImagesDto, String pname, String url1, String url2, DateTime date);
}
