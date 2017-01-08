/**
 * 
 */
package com.cf.code.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cf.code.core.MyContextLoader;
import com.cf.code.core.aop.AccessVerifier;
import com.cf.code.core.exception.BusinessException;
import com.cf.code.dao.MsgDao;
import com.cf.code.entity.Profile;

/**
 * @Version: 1.0
 * @Author: 丛峰
 * @Email: 3024992@qq.com
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {
	
	@Resource(name = "msgDaoRead")
	MsgDao msgDaoRead;
	
	@RequestMapping(value = {"login"}, method = { RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public Object login(Model model,HttpSession session,
			@RequestParam(required = true) String username,
			@RequestParam(required = true) String password) throws BusinessException{
		if(!username.equals("admin")){
			throw new BusinessException("用户不存在");
		}
		if(!password.equals("admin")){
			throw new BusinessException("用户或密码错误");
		}
		Profile profile = new Profile(0,"admin");
		session.setAttribute("profile", profile);
        return profile;
    }
	
	@RequestMapping(value = {"logout"}, method = { RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public Object logout(HttpSession session){
		session.removeAttribute("profile");
		return null;
	}
	
	@AccessVerifier
	@RequestMapping(value = {""}, method = { RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public Object init(@RequestParam(required = false)Profile profile,HttpSession session,Model model) {
		model.addAttribute("profile",profile);
		model.addAttribute("imAddress",MyContextLoader.getImAddress());
		model.addAttribute("msgCount",10);
        return model;
    }
	
}
