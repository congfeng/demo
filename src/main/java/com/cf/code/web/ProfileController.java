/**
 * 
 */
package com.cf.code.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.jfinal.core.Controller;

/**
 * @Version: 1.0
 * @Author: 丛峰
 * @Email: 3024992@qq.com
 */
public class ProfileController extends Controller{
	
	public void login(){
		String username = this.getPara("username");
		String password = this.getPara("password");
		Map<String,Object> ret = new HashMap<String,Object>();
		if(StringUtils.isEmpty(username)){
			ret.put("s", 0);
			ret.put("m", "用户名 不能为空");
			this.renderJson(ret);
			return ;
		}
		if(StringUtils.isEmpty(password)){
			ret.put("s", 0);
			ret.put("m", "密码不能为空");
			this.renderJson(ret);
			return ;
		}
		if(!(username.equals("admin")&&password.equals("admin"))){
			ret.put("s", 0);
			ret.put("m", "用户名或密码错误");
			this.renderJson(ret);
			return ;
		}
		this.renderJson("s", 1);
	}
	
}
