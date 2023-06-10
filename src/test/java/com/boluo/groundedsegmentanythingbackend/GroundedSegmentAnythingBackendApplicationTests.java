package com.boluo.groundedsegmentanythingbackend;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import com.boluo.groundedsegmentanythingbackend.api.API;
import com.boluo.groundedsegmentanythingbackend.api.dto.APIDto;
import com.boluo.groundedsegmentanythingbackend.domain.dto.SImagesDto;
import com.boluo.groundedsegmentanythingbackend.domain.python.Python;
import io.swagger.annotations.Api;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@SpringBootTest
class GroundedSegmentAnythingBackendApplicationTests {
    @Autowired
    private Python python;
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private API api;

    @Test
    void contextLoads() {
        System.out.println("python = " + python);
    }

    @Test
    public void testHttps() throws Exception {
        String result = HttpRequest.get("127.0.0.1:5000//hello")
                .execute().body();
        System.out.println(result);
    }


    @Test
    private void restTemplate1(){
        String s = restTemplate.getForObject("127.0.0.1:5000//hello", String.class);
        System.out.println("s = " + s);

    }

    @Test
    void test2() {
        // 当前时间
        DateTime date = DateUtil.date();
        System.out.println("date = " + date);
        Assert.assertNotNull(date);
    }

    @Test
    void test3() {
        String s = api.comWithPython(new APIDto("111", new SImagesDto("hello", "sjfajk", "sdfa")));
        System.out.println("s = " + s);
    }
}
