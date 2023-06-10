package com.boluo.groundedsegmentanythingbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boluo.groundedsegmentanythingbackend.domain.entity.SImages;
import com.boluo.groundedsegmentanythingbackend.service.SImagesService;
import com.boluo.groundedsegmentanythingbackend.mapper.SImagesMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author kirit
* @description 针对表【s_images(用于存放风格迁移后的图片)】的数据库操作Service实现
* @createDate 2023-06-10 14:23:36
*/
@Transactional
@Service
public class SImagesServiceImpl extends ServiceImpl<SImagesMapper, SImages>
    implements SImagesService{

}




