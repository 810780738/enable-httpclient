package com.xiaozhu.httpclient.http.config.connectionpool;


import com.xiaozhu.httpclient.configure.HttpClientConfigProperties;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @Author: zhusm@bsoft.com.cn
 * @Description:连接池管理
 * @CreateTime: 12:23 2019/1/25
 * @Params:
 * @return:
 **/
@Configuration
public class PoolHttpClientConnetcionManager {

    @Autowired
    HttpClientConfigProperties clientConfigProperties;

    @Bean
    public PoolingHttpClientConnectionManager poolingHttpClientConnetcionManager() {
        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager(this.clientConfigProperties.getTimeToLive(), TimeUnit.SECONDS);
        manager.setMaxTotal(this.clientConfigProperties.getConnectionMaxTotal());
        manager.setDefaultMaxPerRoute(this.clientConfigProperties.getMaxPerRoute());
        return manager;
    }
}
