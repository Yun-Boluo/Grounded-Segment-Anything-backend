package com.boluo.groundedsegmentanythingbackend.domain.python;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author kirit
 * @version 1.0
 * @description: TODO
 * @date 2023/6/9 20:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Python {

    @Value("${python.evn}")
    private String evn;

    @Value("${python.workPath}")
    private String workPath;

    @Value("${python.inputPath}")
    private String inputPath;

    @Value("${python.outputPath}")
    private String output;
}
