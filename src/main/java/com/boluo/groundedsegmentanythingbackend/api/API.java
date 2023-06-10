package com.boluo.groundedsegmentanythingbackend.api;

import com.boluo.groundedsegmentanythingbackend.api.dto.APIDto;
import com.boluo.groundedsegmentanythingbackend.domain.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kirit
 * @version 1.0
 * @description: TODO
 * @date 2023/6/10 14:34
 */
@Component
public class API {

    @Resource
    private RestTemplate restTemplate;

    private static final String PYTHON_URL = "http://127.0.0.1:5000";

    public String comWithPython(APIDto apiDto){
        Map<String,APIDto> postParams  = new HashMap<>();
        postParams .put("apiDto",apiDto);
        String param = apiDto.toString();
        return restTemplate
                .postForObject(PYTHON_URL + "//hello", postParams , String.class);
    }
}
