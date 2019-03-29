package com.xiaozhu.httpclient.http;

import com.xiaozhu.httpclient.configure.HttpClientConfigProperties;
import com.xiaozhu.httpclient.http.config.ProxyRoutePlanner;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;


/**
 * @Author: zhusm@bsoft.com.cn
 * @Description: 客户端实例
 * @CreateTime: 12:22 2019/1/25
 * @Params: 
 * @return: 
 **/
@Service("httpClientManagerFactoryBean")
@ConditionalOnMissingBean
public class HttpClientManagerFactoryBean implements FactoryBean<CloseableHttpClient>, InitializingBean, DisposableBean {

    private CloseableHttpClient client;

    @Autowired
    HttpClientConfigProperties clientConfigProperties;

    @Autowired
    private org.apache.http.conn.ConnectionKeepAliveStrategy keepAliveStrategy;

//    @Autowired
//    private org.apache.http.client.HttpRequestRetryHandler requestRetryHandler;

    @Autowired
    private PoolingHttpClientConnectionManager clientConnetcionManager;

//    @Autowired
//    private ProxyRoutePlanner routePlanner;

    @Autowired
    private org.apache.http.client.config.RequestConfig requestConfig;


    /**
     * 销毁上下文时销毁httpclient实例
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        if (null != this.client){
            this.client.close();
        }
    }

    @Override
    public CloseableHttpClient getObject() throws Exception {
        return this.client;
    }

    @Override
    public Class<?> getObjectType() {
        return (this.client == null ? CloseableHttpClient.class : this.client.getClass());
    }

    /**
     * 初始化实例
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() {
        this.client = HttpClients.custom()
                .setConnectionManager(this.clientConnetcionManager)
                .setKeepAliveStrategy(this.keepAliveStrategy)
//                .setRoutePlanner(this.clientConfigProperties.isEnableProxy()? this.routePlanner : null)
                .setDefaultRequestConfig(this.requestConfig)
                .build();
    }

    /**
     * 构建单例实例 防止重复创建客户端
     * @return
     */
    @Override
    public boolean isSingleton() {
        return true;
    }
}
