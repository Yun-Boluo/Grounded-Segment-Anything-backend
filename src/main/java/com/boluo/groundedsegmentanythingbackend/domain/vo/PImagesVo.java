package com.boluo.groundedsegmentanythingbackend.domain.vo;

import com.boluo.groundedsegmentanythingbackend.domain.entity.PImages;
import com.boluo.groundedsegmentanythingbackend.domain.entity.SImages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author kirit
 * @version 1.0
 * @description: TODO
 * @date 2023/6/11 20:09
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PImagesVo {

    private PImages pImages;


    private List<SImages> sImagesList;


}
