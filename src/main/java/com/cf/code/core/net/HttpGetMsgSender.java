/**
 * Copyright (c) mbaobao.com 2011 All Rights Reserved.
 */
package com.cf.code.core.net;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.cf.code.common.Constant;
import com.cf.code.common.StringUtil;
import com.cf.code.core.exception.MsgSendException;

/**
 * 
 *
 * @Version: 1.0
 * @Author: 丛峰
 * @Email: 3024992@qq.com
 *
 */
public class HttpGetMsgSender extends AbstractMsgSender<String, Map<String,String>, String> {
    
    @Override
    public String send(String targetUrl, Map<String,String> msgMap)throws MsgSendException{
        if(StringUtil.isNullOrEmpty(targetUrl)){
            throw new MsgSendException("信息发送地址错误");
        }
        HttpClient httpClient = HttpClientBuilder.create().build();
//        client.getHttpConnectionManager().getParams().setConnectionTimeout(80000);
        HttpGet getMethod = new HttpGet(targetUrl);
        String resText = null;
        try {
        	HttpResponse response = httpClient.execute(getMethod);
        	int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode >= HttpStatus.SC_BAD_REQUEST){
    			throw new MsgSendException("响应异常:"+statusCode);
            }
            resText = EntityUtils.toString(response.getEntity());
		} catch (ClientProtocolException e) {
			throw new MsgSendException("请求协议异常:"+e.getMessage());
		} catch (IOException e) {
			throw new MsgSendException("请求网络异常:"+e.getMessage());
		} finally{
			getMethod.releaseConnection();
		}
        return resText;
    }

	@Override
	public String getDefaultCharset() {
		return Constant.Charset;
	}

}