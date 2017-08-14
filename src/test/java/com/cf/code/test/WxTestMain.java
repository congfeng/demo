/**
 * 
 */
package com.cf.code.test;

/**
 * @author congfeng
 *
 */
public class WxTestMain {
	
	public static String AppId = "wx003e224bd47fc59f";

	public static String AuthorizeUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
	
	public static void main(String[] args) {
		String url = String.format(AuthorizeUrl, AppId,"ddddd");
		System.out.println(url);
	}
	
}
