package com.xiaozhu.httpclient.http.config;

import com.xiaozhu.httpclient.configure.HttpClientConfigProperties;
import lombok.extern.java.Log;
import org.apache.http.HttpHost;
import org.apache.http.conn.SchemePortResolver;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.conn.DefaultRoutePlanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: zhusm@bsoft.com.cn
 * @Description: httpclient 代理配置
 * @CreateTime: 12:23 2019/1/25
 * @Params:
 * @return:
 **/
@Log
@Configuration
@ConditionalOnProperty(prefix = "httpclient.config",value = "enableProxy",havingValue = "true")
public class ProxyRoutePlanner {

    @Autowired
    HttpClientConfigProperties clientConfigProperties;

    @Bean
    public DefaultProxyRoutePlanner defaultProxyRoutePlanner(){
        HttpHost httpHost = null;
        if (this.clientConfigProperties.isEnableProxy()){
            httpHost = new HttpHost(this.clientConfigProperties.getProxyHost(), this.clientConfigProperties.getProxyPort());
            log.info("http client proxy isenable host:"+this.clientConfigProperties.getProxyHost()+":"+this.clientConfigProperties.getProxyPort());
            return new DefaultProxyRoutePlanner(httpHost);
        }
        log.info("http client proxy not enable");
        return null;
    }
}
