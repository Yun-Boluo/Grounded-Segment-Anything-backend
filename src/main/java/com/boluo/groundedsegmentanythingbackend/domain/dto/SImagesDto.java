package com.boluo.groundedsegmentanythingbackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kirit
 * @version 1.0
 * @description: TODO
 * @date 2023/6/10 14:55
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SImagesDto {

    /**
     * 描述语
     */
    private String textPrompt;

    /**
     * 盒子阈值
     */
    private String boxTreshold;

    /**
     * 文本的阈值
     */
    private String textTreshold;
}
