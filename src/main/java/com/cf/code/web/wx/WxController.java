/**
 * 
 */
package com.cf.code.web.wx;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cf.code.common.WebUtil;

/**
 * @author congfeng
 */
@Controller("WxController")
@RequestMapping("/wx")
public class WxController {
	
	private static Logger log = LogManager.getLogger(WxController.class);
	
	private static final String AuthorizationURL = "https://api.weixin.qq.com/sns/jscode2session";
	
	@Value("#{sys.AppId}")
	String AppID;
	
	@Value("#{sys.AppSecret}")
	String AppSecret;
	
	@RequestMapping(value = {""}, method = { RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object access(Model model,HttpServletRequest request,
    		@RequestParam(required = false) String signature,
    		@RequestParam(required = false) String timestamp,
            @RequestParam(required = false) String nonce,
            @RequestParam(required = false) String echostr){
		Map<String,String> requestData = WebUtil.readJsonFromRequestBodyHTTP(request);
		log.info("wx_access_data:" + requestData);
        return echostr;
    }
	
}
