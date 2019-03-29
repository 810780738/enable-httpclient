package com.xiaozhu.httpclient.configure;

import com.xiaozhu.httpclient.http.HttpClientDoPost;
import com.xiaozhu.httpclient.http.HttpClientManagerFactoryBean;
import com.xiaozhu.httpclient.http.clientinterface.HttpCientPost;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: zhusm@bsoft.com.cn
 *
 * @Description: 自动配置
 *
 * @Create: 2019-03-29 10:01
 **/
@Log
@ComponentScan
//在classpath下发现该服务自动注入IOC(对外暴露的服务接口)
@ConditionalOnClass({HttpClientDoPost.class,
        HttpClientManagerFactoryBean.class})
public class HttpClientAutoConfigure {

    @Autowired
    private HttpClientConfigProperties properties;


    @Bean
    HttpClientDoPost doPost(){
        log.info("properties -------------->"+properties);
        return new HttpClientDoPost();
    }

    @Bean
    HttpClientManagerFactoryBean httpClientManagerFactoryBean(){
        return new HttpClientManagerFactoryBean();
    }
}
