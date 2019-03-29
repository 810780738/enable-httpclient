package com.xiaozhu.httpclient.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaozhu.httpclient.http.clientinterface.HttpCientPost;
import com.xiaozhu.httpclient.exception.HttpClientException;
import com.xiaozhu.httpclient.configure.HttpClientConfigProperties;
import com.xiaozhu.httpclient.util.StringUtil;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @Author: zhusm@bsoft.com.cn
 *
 * @Description: post
 *
 * @Create: 2019-01-25 12:59
 **/
@Service
@ConditionalOnMissingBean
public class HttpClientDoPost implements HttpCientPost {

    @Resource(name = "httpClientManagerFactoryBean")
    private CloseableHttpClient client;

    @Autowired
    HttpClientConfigProperties clientConfigProperties;


    /**
     * @Author: zhusm@bsoft.com.cn
     * @Description: json方式提交数据返回字符串
     * @CreateTime: 15:21 2019/1/25
     * @Params: [json]
     * @return: java.lang.String
     **/
    @Override
    public String doPost(String json,String request_suffixUri) throws HttpClientException {
        if (StringUtil.isEmpty(json)){
            throw new HttpClientException("json not be null");
        }
        try {
            BaseHttp.PostBuilder post = new BaseHttp().postcustomjson(request_suffixUri,clientConfigProperties);
            post.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
            CloseableHttpResponse response = this.client.execute(post);
            HttpEntity entity = response.getEntity();
            String respons =  EntityUtils.toString(response.getEntity(), Consts.UTF_8);
            EntityUtils.consume(entity);
            return respons;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> T doPost(String request_suffixUri,Object json,Class<T> clazz) throws HttpClientException {
        if (null == json){
            throw new HttpClientException("Object not be null");
        }
        try {
            String requestJson = JSON.toJavaObject((JSON) json, String.class);
//            String requestJson = JSONUtils.toString(json);
            BaseHttp.PostBuilder post = new BaseHttp().postcustomjson(request_suffixUri,clientConfigProperties);
            post.setEntity(new StringEntity(requestJson, ContentType.APPLICATION_JSON));
            CloseableHttpResponse response = this.client.execute(post);
            HttpEntity entity = response.getEntity();
            String respons =  EntityUtils.toString(response.getEntity(), Consts.UTF_8);
            T t = JSONObject.parseObject(respons, clazz);
            EntityUtils.consume(entity);
            return t;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }




}
