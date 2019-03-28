package com.xiaozhu.httpclient.util;

/**
 * @Author: zhusm@bsoft.com.cn
 *
 * @Description: 字符串工具
 *
 * @Create: 2019-01-24 14:48
 **/
public class StringUtil {

    /**
     * @Author: zhusm@bsoft.com.cn
     * @Description: 判断字符串是否为空 为空返回true
     * @CreateTime: 14:49 2019/1/24
     * @Params: [string]
     * @return: boolean
     **/
    public static final boolean isEmpty(String string){
        return string == null || "".equals(string) || string.length()<=0;
    }
}
