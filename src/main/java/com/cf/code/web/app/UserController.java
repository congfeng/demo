/**
 * 
 */
package com.cf.code.web.app;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cf.code.core.aop.AccessVerifier;
import com.cf.code.core.exception.BusinessException;
import com.cf.code.dao.UserDao;
import com.cf.code.entity.Profile;
import com.cf.code.entity.User;

/**
 * @Version: 1.0
 * @Author: 丛峰
 * @Email: 3024992@qq.com
 */
@Controller
@RequestMapping("/app/user")
public class UserController {

	@Resource(name = "userDao")
	UserDao userDao;
	
	@Resource(name = "userDaoRead")
	UserDao userDaoRead;

	@RequestMapping(value = {"login"}, method = { RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object login(Model model) throws BusinessException{
	    model.addAttribute("name", "丛峰");
        return model;
    }
	
	@RequestMapping(value = {"register"}, method = { RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public void register(Model model,HttpServletResponse response,
			@RequestParam(required = true) String phone,
			@RequestParam(required = true) String password) throws BusinessException{
		if(userDaoRead.find(phone) != null){
			throw new BusinessException("用户已存在，不能重复注册");
		}
		User user = new User();
		user.setPhone(phone);
		user.setPassword(password);
		this.userDao.insert(user);
    }
	
	@AccessVerifier
	@RequestMapping(value = {"resetpassword"}, method = { RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public void resetpassword(@RequestParam(required = false)Profile profile,HttpSession session,
    		@RequestParam(required = true) String newpassword){
		this.userDao.updatePassword(profile.getRelatedId(), newpassword);
    }
	
}
