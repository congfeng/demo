/**
 * Copyright (c) mbaobao.com 2011 All Rights Reserved.
 */
package com.cf.code.core.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

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
public class HttpPostMsgSender extends AbstractMsgSender<String, Map<String,String>, String> {
    
    @Override
    public String send(String targetUrl, Map<String,String> msgMap)throws MsgSendException{
        if(StringUtil.isNullOrEmpty(targetUrl)){
            throw new MsgSendException("信息发送地址错误");
        }
        HttpClient client = new HttpClient();
        client.getHttpConnectionManager().getParams().setConnectionTimeout(80000);
        PostMethod postMethod = new PostMethod(targetUrl){};
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, getDefaultCharset());
//        postMethod.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset="+getDefaultCharset());
        if(msgMap != null&&msgMap.size() > 0){
        	for(String key:msgMap.keySet()){
            	postMethod.setParameter(key,msgMap.get(key));
            }
        }
//        try {
//        	postMethod.setRequestEntity(new StringRequestEntity("{dddddd}", "application/json" , Charset));
//        } catch (UnsupportedEncodingException e) {
//        	throw new MsgSendException("传递信息的编码错误:"+e.getMessage());
//        }
        try {
             client.executeMethod(postMethod);
        } catch (HttpException e) {
            postMethod.releaseConnection();
            throw new MsgSendException("请求协议异常:"+e.getMessage());
        } catch (IOException e) {
            postMethod.releaseConnection();
            throw new MsgSendException("请求网络异常:"+e.getMessage());
        }
        int statusCode = postMethod.getStatusCode();
        String resText = null;
        InputStream is = null;
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        try {
            is = postMethod.getResponseBodyAsStream();
            byte[] buffer = new byte[1024];  
            int len = -1;  
            while ((len = is.read(buffer)) != -1) {  
                outSteam.write(buffer, 0, len);  
            }
            resText = new String(outSteam.toByteArray(), getDefaultCharset());
        } catch (IOException e) {
            throw new MsgSendException("读取响应信息异常:"+e.getMessage());
        } finally{
            postMethod.releaseConnection();
            try {
                outSteam.close();
            } catch (IOException e) {
                outSteam = null;
            }  
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    is = null;
                }
            }
        }
        if(statusCode != 200){
            throw new MsgSendException("返回状态错误:"+statusCode+",msg:"+resText);
        }
        return resText;
    }
    
    @Override
	public String getDefaultCharset() {
		return Constant.Charset;
	}
    
}
