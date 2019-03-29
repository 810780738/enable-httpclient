package com.xiaozhu.httpclient.http.config;

import com.xiaozhu.httpclient.configure.HttpClientConfigProperties;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 * @Author: zhusm@bsoft.com.cn
 * @Description: 请求配置类
 * @CreateTime: 12:22 2019/1/25
 * @Params:
 * @return:
 **/
@Configuration
@Log
public class RequestConfig {

    @Autowired
    HttpClientConfigProperties clientConfigProperties;

    @Bean("xzRequestConfig")
    public org.apache.http.client.config.RequestConfig requestConfig(){
        log.info("http client xzRequestConfigBean注册成功");
        return org.apache.http.client.config.RequestConfig.custom()
                .setConnectTimeout(this.clientConfigProperties.getConnectionTimeOut())
                .setConnectionRequestTimeout(this.clientConfigProperties.getConnectionRequestTimeOut())
                .setSocketTimeout(this.clientConfigProperties.getSocketTimeOut())
                .build();
    }


}
