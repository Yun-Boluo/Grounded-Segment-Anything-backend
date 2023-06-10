package com.boluo.groundedsegmentanythingbackend.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boluo.groundedsegmentanythingbackend.domain.Result;
import com.boluo.groundedsegmentanythingbackend.domain.dto.SImagesDto;
import com.boluo.groundedsegmentanythingbackend.domain.entity.PImages;
import com.boluo.groundedsegmentanythingbackend.domain.entity.SImages;
import com.boluo.groundedsegmentanythingbackend.mapper.SImagesMapper;
import com.boluo.groundedsegmentanythingbackend.service.PImagesService;
import com.boluo.groundedsegmentanythingbackend.mapper.PImagesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author kirito
* @description 针对表【p_images(用于存放图片路径等)】的数据库操作Service实现
* @createDate 2023-06-10 14:23:36
*/
@Transactional
@Service
public class PImagesServiceImpl extends ServiceImpl<PImagesMapper, PImages>
    implements PImagesService{

    @Autowired
    private PImagesMapper pImagesMapper;

    @Autowired
    private SImagesMapper sImagesMapper;

    @Transactional
    @Override
    public Result insert(SImagesDto sImagesDto, String pname, String url1, String url2, DateTime date) {
        PImages pImages = new PImages();
        pImages.setImageName(pname);
        pImages.setUrl(url1);
        pImages.setUploadTime(date);
        int insert = pImagesMapper.insert(pImages);

        SImages sImages = new SImages();
        sImages.setPid(pImages.getId());
        sImages.setUrl(url2);
        sImages.setBoxTreshold(sImagesDto.getBoxTreshold());
        sImages.setTextTreshold(sImagesDto.getTextTreshold());
        sImages.setTextPrompt(sImagesDto.getTextPrompt());
        sImages.setCreateTime(date);
        int insert1 = sImagesMapper.insert(sImages);

        return Result.ok().data("pImages",pImages).data("sImages",sImages);
    }
}




