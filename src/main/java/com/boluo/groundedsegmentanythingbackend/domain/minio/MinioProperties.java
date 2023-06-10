package com.boluo.groundedsegmentanythingbackend.domain.minio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author kirito
 * @version 1.0
 * @description: TODO
 * @date 2023/6/5 22:57
 */
@ConfigurationProperties(prefix = "minio")
@Component
@Data
public class MinioProperties {
    /**
     * 连接地址
     */
    private String endpoint;

    /**
     *  用户名
     */
    private String accessKey;

    /**
     *  密码
     */
    private String secretKey;
}
