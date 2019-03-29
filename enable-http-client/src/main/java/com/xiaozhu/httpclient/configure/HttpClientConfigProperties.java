package com.xiaozhu.httpclient.configure;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: zhusm@bsoft.com.cn
 *
 * @Description: httpclient配置信息
 *
 * @Create: 2019-01-25 09:29
 **/
@Data
@ToString
@Component
@ConfigurationProperties(prefix = "httpclient.config",ignoreUnknownFields = false)
public class HttpClientConfigProperties {

    private int keepAliveSecond = 60;

    private int retryFrequency = 5;

    private int connectionMaxTotal = 2;

    private int maxPerRoute = 2;

    private int timeToLive = 10;

    private int connectionTimeOut = 5000;

    private int connectionRequestTimeOut = 5000;

    private int socketTimeOut = 5000;

    private String  proxyHost;

    private int proxyPort;

    private String hostAndPort;

    private boolean enableProxy = false;

}
