package com.boluo.groundedsegmentanythingbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.boluo.groundedsegmentanythingbackend.domain.Result;
import com.boluo.groundedsegmentanythingbackend.domain.entity.PImages;
import com.boluo.groundedsegmentanythingbackend.domain.entity.SImages;
import com.boluo.groundedsegmentanythingbackend.domain.vo.PImagesVo;
import com.boluo.groundedsegmentanythingbackend.mapper.PImagesMapper;
import com.boluo.groundedsegmentanythingbackend.mapper.SImagesMapper;
import com.boluo.groundedsegmentanythingbackend.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kirit
 * @version 1.0
 * @description: TODO
 * @date 2023/6/11 16:29
 */

@Transactional
@Service
public class CommonServiceImpl implements CommonService {


    @Autowired
    private PImagesMapper pImagesMapper;


    @Autowired
    private SImagesMapper sImagesMapper;

    @Override
    public Result searchAll() {
        LambdaQueryWrapper<PImages> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<SImages> lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
        //  查询所有的父
        List<PImages> pImages = pImagesMapper.selectList(lambdaQueryWrapper);
        // 查询所有的子
        List<SImages> sImages = sImagesMapper.selectList(lambdaQueryWrapper1);

        List<PImagesVo> pImagesVoList = new ArrayList<>();
        // 封装返回
        pImages.forEach(pImages1 -> {
            Long pId = pImages1.getId();
            List<SImages> collect = sImages.stream()
                    .filter(sImages1 -> sImages1.getPid() != pId)
                    .collect(Collectors.toList());
            PImagesVo pImagesVo = new PImagesVo(pImages1,collect);
            pImagesVoList.add(pImagesVo);
        });

        return Result.ok().data("vo",pImagesVoList);
    }
}
