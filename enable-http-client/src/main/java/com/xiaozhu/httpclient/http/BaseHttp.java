package com.xiaozhu.httpclient.http;

import com.xiaozhu.httpclient.configure.HttpClientConfigProperties;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @Author: zhusm@bsoft.com.cn
 *
 * @Description: post/get请求实例
 *
 * @Create: 2019-01-25 13:31
 **/
public class BaseHttp {

    public static final String REQUEST_POST = "POST";
    public static final String REQUEST_GET = "GET";


//    private HttpClientConfigProperties clientConfigProperties = ApplicationContextProvider.getBean(HttpClientConfigProperties.class);
    @Autowired
    private HttpClientConfigProperties clientConfigProperties;

    public PostBuilder postcustom() throws URISyntaxException {
        return new PostBuilder();
    }
    /***
     * @Author: zhusm@bsoft.com.cn
     * @Description: json提交方式
     * @CreateTime: 15:18 2019/1/25
     * @Params: [suffixUri]
     * @return: hcn.health.controller.httpclient.BaseHttp.PostBuilder
     **/
    public PostBuilder postcustomjson(String suffixUri) throws URISyntaxException {
        return new PostBuilder(suffixUri,true);
    }

    public PostBuilder postcustom(String suffixUri) throws URISyntaxException {
        return new PostBuilder(suffixUri,false);
    }

    public GetBuilder getcustom() throws URISyntaxException {
        return new GetBuilder();
    }
    public GetBuilder getcustom(String suffixUri) throws URISyntaxException {
        return new GetBuilder(suffixUri);
    }

    public class PostBuilder extends HttpEntityEnclosingRequestBase {

        @Override
        public String getMethod() {
            return REQUEST_POST;
        }

        public PostBuilder(String suffixUri,boolean isJsonType) throws URISyntaxException {
            URI uri = new URI(BaseHttp.this.clientConfigProperties.getHostAndPort()+suffixUri);
            this.setURI(uri);
            if (isJsonType) addHeader("Content-Type", "application/json;charset=utf-8");

        }

        public PostBuilder() {
            super();
        }
    }

    public class GetBuilder extends HttpEntityEnclosingRequestBase{

        @Override
        public String getMethod() {
            return REQUEST_GET;
        }

        public GetBuilder(String suffixUri) throws URISyntaxException {
            URI uri = new URI(BaseHttp.this.clientConfigProperties.getHostAndPort()+suffixUri);
            this.setURI(uri);
        }

        public GetBuilder() {
            super();
        }
    }
}
