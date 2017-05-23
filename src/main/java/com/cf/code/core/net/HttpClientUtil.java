/**
 * 
 */
package com.cf.code.core.net;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * @author congfeng
 *
 */
public class HttpClientUtil {

	private static PoolingHttpClientConnectionManager connectionManager;
	
	private static TrustManager manager = new X509TrustManager() {
		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			
		}
		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			
		}
	};
	
	static{
		try {
			SSLContext context = SSLContext.getInstance("TLS");
			context.init(null,new TrustManager[]{manager},null);
			SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(context,NoopHostnameVerifier.INSTANCE);
			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
					.register("http", PlainConnectionSocketFactory.INSTANCE)
					.register("https", socketFactory).build();
			connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String httpGetRequest(String url,Map<String,String> params) throws Exception {
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager).build();
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
		if(params != null){
			for(String key:params.keySet()){
				nvps.add(new BasicNameValuePair(key, params.get(key)));  
			}
		}
        httpPost.setEntity(new UrlEncodedFormEntity(nvps));  
		CloseableHttpResponse response = httpClient.execute(httpPost);
		return EntityUtils.toString(response.getEntity());
	}  
	
	public static void main(String[] args) throws Exception {
		String url = "https://api.weixin.qq.com/sns/jscode2session";
		Map<String,String> params = new HashMap<String,String>();
		params.put("grant_type", "authorization_code");
		params.put("appid", "wx307aed19f515584b");
		params.put("secret", "2d28e84b363162e6faefb3ee5d578bc7");
		params.put("js_code", "011u8jt12X32lZ0oXuv12pqBt12u8jtZ");
//		{"session_key":"axKyjYsfMJi8Kh4FsYoRtw==","expires_in":2592000,"openid":"ot1QK0ZPo5rv6ZHzAt_01EM1v6mY"}
		String resText = httpGetRequest(url,params);
		String session_key = StringUtils.substringBetween(resText, "\"session_key\":\"","\"");
		String openid = StringUtils.substringBetween(resText, "\"openid\":\"","\"");
		System.out.println(resText);
		System.out.println(session_key);
		System.out.println(openid);
		
	}
	
}
