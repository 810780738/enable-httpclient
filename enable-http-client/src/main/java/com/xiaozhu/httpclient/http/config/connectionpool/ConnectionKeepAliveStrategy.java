package com.xiaozhu.httpclient.http.connectionpool;

import com.xiaozhu.httpclient.configure.HttpClientConfigProperties;
import org.apache.http.HeaderElement;
import org.apache.http.HttpResponse;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: zhusm@bsoft.com.cn
 * @Description: 连接池策略
 * @CreateTime: 12:22 2019/1/25
 * @Params: 
 * @return: 
 **/
@Configuration
public class ConnectionKeepAliveStrategy {

    @Autowired
    HttpClientConfigProperties clientConfigProperties;

    @Bean("bsoftConnectionKeepAliveStrategy")
    public org.apache.http.conn.ConnectionKeepAliveStrategy connectionKeepAliveStrategy(){
        return new org.apache.http.conn.ConnectionKeepAliveStrategy() {
            @Override
            public long getKeepAliveDuration(HttpResponse httpResponse, HttpContext httpContext) {
                BasicHeaderElementIterator iterator = new BasicHeaderElementIterator(httpResponse.headerIterator(HTTP.CONN_KEEP_ALIVE));
                while (iterator.hasNext()) {
                    HeaderElement headerElement = iterator.nextElement();
                    String param = headerElement.getName();
                    String value = headerElement.getValue();
                    if (null != value && param.equalsIgnoreCase("timeout")) {
                        return Long.parseLong(value) * 1000;
                    }
                }
                return ConnectionKeepAliveStrategy.this.clientConfigProperties.getKeepAliveSecond() * 1000;
            }
        };
    }
}
