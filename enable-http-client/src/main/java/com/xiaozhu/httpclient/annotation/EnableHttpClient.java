package com.xiaozhu.httpclient.annotation;

import com.xiaozhu.httpclient.http.HttpRequestRetryHandler;
import com.xiaozhu.httpclient.http.config.ProxyRoutePlanner;
import com.xiaozhu.httpclient.http.config.RequestConfig;
import com.xiaozhu.httpclient.http.connectionpool.ConnectionKeepAliveStrategy;
import com.xiaozhu.httpclient.http.connectionpool.PoolHttpClientConnetcionManager;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({
                RequestConfig.class,
                ProxyRoutePlanner.class,
                HttpRequestRetryHandler.class,
                ConnectionKeepAliveStrategy.class,
                PoolHttpClientConnetcionManager.class
})
public @interface EnableHttpClient {
}
