package com.boluo.groundedsegmentanythingbackend;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.boluo.groundedsegmentanythingbackend.mapper")
public class GroundedSegmentAnythingBackendApplication {


    public static void main(String[] args) {
        SpringApplication.run(GroundedSegmentAnythingBackendApplication.class, args);
    }

}
