package com.boluo.groundedsegmentanythingbackend.api.dto;

import com.boluo.groundedsegmentanythingbackend.domain.dto.SImagesDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kirit
 * @version 1.0
 * @description: TODO
 * @date 2023/6/10 14:42
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class APIDto {

    private String imagePath;

    private SImagesDto sImagesDto;

}
