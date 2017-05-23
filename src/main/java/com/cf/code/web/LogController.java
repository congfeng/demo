/**
 * 
 */
package com.cf.code.web;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cf.code.common.Constant;
import com.cf.code.core.aop.AccessVerifier;
import com.cf.code.core.net.HttpGetMsgSender;

/**
 * @Version: 1.0
 * @Author: 丛峰
 * @Email: 3024992@qq.com
 */
@Controller("LogController")
@RequestMapping("/log")
public class LogController {
	
	private static final String IpServer = "http://ip.taobao.com/service/getIpInfo.php?ip=%s";
	
	private static final String AccessLogPath = "/usr/local/log/logs/";
	
	private static final String AccessLogPrefix = "localhost_access_log";
	
	private static final String AccessLogSuffix = "txt";
	
	private static final String AccessLogSuffixExt = "ext";
	
	private static final Map<String,String> IspMap= new HashMap<String,String>();
	
	private static final HttpGetMsgSender msgSender = new HttpGetMsgSender();
	
	@AccessVerifier
	@ResponseBody
	@RequestMapping(value = "/parse/{date}")
	public String parse(@PathVariable("date") String date) throws IOException, Exception{
		String fileName = AccessLogPrefix + "." + date + "." + AccessLogSuffix;
		File logFile = new File(AccessLogPath + fileName);
		if(!logFile.exists()){
			return "日志文件不存在";
		}
		File logFileExt = new File(AccessLogPath + fileName + "." + AccessLogSuffixExt);
		if(logFileExt.exists()){
			logFileExt.delete();
		}
		for(String line:FileUtils.readLines(logFile, Constant.Charset)){
			String[] infos = line.split(" ");
			String ip = infos[1];
			String status = infos[2];
			String address = getAddress(ip);
			line = line.replaceFirst(ip, ip+"["+address+"]") + "\r\n";
			FileUtils.writeStringToFile(logFileExt, line, Constant.Charset, true);
		}
		return "日志文件扩展完成";
	}
	
	private String getAddress(String ip) throws Exception{
		String address = IspMap.get(ip);
		if(address != null){
			return address;
		}
		String resText = msgSender.send(String.format(IpServer, ip), null);
		if(StringUtils.isEmpty(resText)){
			throw new Exception("响应空"+ip);
		}
		JSONObject resJson = JSONObject.parseObject(resText);
		if(resJson.getIntValue("code") != 0){
			return resJson.getString("data");
		}
		JSONObject dataJson = resJson.getJSONObject("data");
		String country = dataJson.getString("country");
		String region = dataJson.getString("region");
		String city = dataJson.getString("city");
		String isp = dataJson.getString("isp");
		if(!country.equals("中国")){
			address = country;
		}else if(region.equals(city)){
			address = city + isp;
		}else{
			address = region + city + isp;
		}
		IspMap.put(ip, address);
		return address;
	}
	
}
