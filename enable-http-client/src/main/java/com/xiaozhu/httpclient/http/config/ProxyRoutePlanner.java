package com.xiaozhu.httpclient.http.config;

import org.apache.http.HttpHost;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: zhusm@bsoft.com.cn
 * @Description: httpclient 代理配置
 * @CreateTime: 12:23 2019/1/25
 * @Params:
 * @return:
 **/
@Configuration
public class ProxyRoutePlanner {

    @Autowired
    HttpClientConfigProperties clientConfigProperties;

    @Bean
    public DefaultProxyRoutePlanner defaultProxyRoutePlanner(){
        HttpHost httpHost = new HttpHost(this.clientConfigProperties.getProxyHost(), this.clientConfigProperties.getProxyPort());
        return new DefaultProxyRoutePlanner(httpHost);
    }
}
