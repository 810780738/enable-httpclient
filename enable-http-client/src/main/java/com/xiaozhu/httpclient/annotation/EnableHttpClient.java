package com.xiaozhu.httpclient.annotation;

import com.xiaozhu.httpclient.http.config.HttpRequestRetryHandler;
import com.xiaozhu.httpclient.http.config.ProxyRoutePlanner;
import com.xiaozhu.httpclient.http.config.RequestConfig;
import com.xiaozhu.httpclient.http.config.connectionpool.ConnectionKeepAliveStrategy;
import com.xiaozhu.httpclient.http.config.connectionpool.PoolHttpClientConnetcionManager;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
//将classpath下的配置自动装配
@Import({
                RequestConfig.class,
                ProxyRoutePlanner.class,
                HttpRequestRetryHandler.class,
                ConnectionKeepAliveStrategy.class,
                PoolHttpClientConnetcionManager.class
})
public @interface EnableHttpClient {
}
