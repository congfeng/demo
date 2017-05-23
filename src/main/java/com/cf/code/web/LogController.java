/**
 * 
 */
package com.cf.code.web;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cf.code.common.Constant;
import com.cf.code.core.aop.AccessVerifier;
import com.cf.code.core.net.HttpGetMsgSender;
import com.cf.code.dao.IpAddressDao;
import com.cf.code.entity.IpAddress;

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
//	private static final String AccessLogPath = "/Users/congfeng/workspace_mc/_resources/";
	
	private static final String AccessLogPrefix = "localhost_access_log";
	
	private static final String AccessLogSuffix = "txt";
	
	private static final String AccessLogSuffixExt = "html";
	
	private static final int Limit = 20;
	
	HttpGetMsgSender msgSender = new HttpGetMsgSender();
	
	@Resource(name = "ipAddressDaoRead")
	IpAddressDao ipAddressDaoRead;
	
	@Resource(name = "ipAddressDao")
	IpAddressDao ipAddressDao;
	
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
		int cursor = 0;
		if(logFileExt.exists()){
			cursor = FileUtils.readLines(logFileExt).size();
		}
		List<String> lines = FileUtils.readLines(logFile, Constant.Charset);
		int maxlines = lines.size();
		Map<String,String> ipAddressCache = new HashMap<String,String>();
		for(int i = 0;i < Limit;i++){
			int index = cursor + i;
			if(index >= maxlines){
				return "日志文件已扩展完成";
			}
			String line = lines.get(index);
			String[] infos = line.split(" ");
			String ip = infos[1];
			Integer status = Integer.valueOf(infos[2]);
			String address = ipAddressCache.get(ip);
			if(address == null){
				address = getAddress(ip);
				ipAddressCache.put(ip, address);
			}
			line = line.replaceFirst(ip, ip+"["+address+"]");
			if(status >= HttpStatus.SC_BAD_REQUEST){
				line = line.replaceFirst(" "+status+" ", "<font color='red'> "+ status +" </font>") ;
			}
			FileUtils.writeStringToFile(logFileExt, line+"<br/>\r\n", Constant.Charset, true);
		}
		return "日志文件已扩展至" + (cursor+Limit) + "行";
	}
	
	private String getAddress(String ip) throws Exception{
		String address = this.ipAddressDaoRead.findAddress(ip);
		if(address != null){
			return address;
		}
		String resText = msgSender.send(String.format(IpServer, ip), null);
		if(StringUtils.isEmpty(resText)){
//			throw new Exception(ip+"响应空");
			return "";
		}
		JSONObject resJson = JSONObject.parseObject(resText);
		if(resJson.getIntValue("code") != 0){
//			throw new Exception(ip+"查询失败"+resJson.getString("data"));
			return resJson.getString("data");
		}
		JSONObject dataJson = resJson.getJSONObject("data");
		String countryId = dataJson.getString("country_id");
		String country = dataJson.getString("country");
		String regionId = dataJson.getString("region_id");
		String region = dataJson.getString("region");
		String cityId = dataJson.getString("city_id");
		String city = dataJson.getString("city");
		String ispId = dataJson.getString("isp_id");
		String isp = dataJson.getString("isp");
		String areaId = dataJson.getString("area_id");
		String area = dataJson.getString("area");
		if(!country.equals("中国")){
			address = country;
		}else if(region.equals(city)){
			address = city + isp;
		}else{
			address = region + city + isp;
		}
		IpAddress ipAddress = new IpAddress();
		ipAddress.setIp(ip);
		ipAddress.setAddress(address);
		ipAddress.setCountryId(countryId);
		ipAddress.setCountry(country);
		ipAddress.setRegionId(regionId);
		ipAddress.setRegion(region);
		ipAddress.setCityId(cityId);
		ipAddress.setCity(city);
		ipAddress.setIspId(ispId);
		ipAddress.setIsp(isp);
		ipAddress.setAreaId(areaId);
		ipAddress.setArea(area);
		this.ipAddressDao.insert(ipAddress);
		return address;
	}
	
}
