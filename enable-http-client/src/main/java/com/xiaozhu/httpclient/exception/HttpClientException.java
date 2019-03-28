package com.xiaozhu.httpclient.exception;

/**
 * @Author: zhusm@bsoft.com.cn
 *
 * @Description: httpclient异常
 *
 * @Create: 2019-01-25 15:33
 **/
public class HttpClientException extends Exception {
    public HttpClientException(String msg) {
        super(msg);
    }

    public HttpClientException() {
        super("");
    }
}
