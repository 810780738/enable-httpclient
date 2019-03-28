package com.xiaozhu.httpclient.http.clientinterface;

import com.xiaozhu.httpclient.exception.HttpClientException;

/**
 * @program: healthword
 *
 * @description: post提交接口
 *
 * @author: zhusm@bsoft.com.cn
 *
 * @create: 2019-01-25 15:46
 **/
public interface HttpCientPost {

    String doPost(String json, String request_suffixUri) throws HttpClientException, HttpClientException;

    <T> T doPost(String request_suffixUri, Object json, Class<T> clazz) throws HttpClientException;
}
