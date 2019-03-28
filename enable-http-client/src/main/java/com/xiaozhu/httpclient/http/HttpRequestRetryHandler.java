package com.xiaozhu.httpclient.http;


import com.xiaozhu.httpclient.http.config.HttpClientConfigProperties;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.protocol.HttpContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;

/**
 * @Author: zhusm@bsoft.com.cn
 * @Description: HttpClient重试机制
 * @CreateTime: 12:23 2019/1/25
 * @Params:
 * @return:
 **/
@Configuration
public class HttpRequestRetryHandler {

    @Autowired
    HttpClientConfigProperties clientConfigProperties;

    @Bean
    public org.apache.http.client.HttpRequestRetryHandler httpRequestRetryHandelr(){
        final int retryFrequency = this.clientConfigProperties.getRetryFrequency();
        return new org.apache.http.client.HttpRequestRetryHandler() {
            @Override
            public boolean retryRequest(IOException exception, int executionCount, HttpContext httpContext) {
                if (executionCount >= retryFrequency) {
                    //超过重试次数
                    return false;
                }
                if (exception instanceof InterruptedIOException) {
                    //超时重试
                    return true;
                }
                if (exception instanceof NoHttpResponseException) {
                    //客户端断开重试
                    return true;
                }
                if (exception instanceof UnknownHostException) {
                    //不正确的链接不重试
                    return false;
                }
                if (exception instanceof ConnectTimeoutException) {
                    //连接超时
                    return false;
                }
                if (exception instanceof SSLException) {
                    return false;
                }
                HttpClientContext clientContext = HttpClientContext.adapt(httpContext);
                HttpRequest request = clientContext.getRequest();
                if (!(request instanceof HttpEntityEnclosingRequest)) {
                    return true;
                }
                return false;
            }
        };
    }
}
