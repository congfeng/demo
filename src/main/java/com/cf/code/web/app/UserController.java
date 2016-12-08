/**
 * 
 */
package com.cf.code.web.app;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cf.code.common.Constant;
import com.cf.code.core.aop.AccessVerifier;
import com.cf.code.core.exception.BusinessException;
import com.cf.code.dao.UserDao;
import com.cf.code.entity.Profile;
import com.cf.code.entity.User;
import com.cf.code.entity.enums.CommonStatus;

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
    public Object login(Model model,HttpSession session,
            @RequestParam(required = true) String phone,
            @RequestParam(required = true) String password) throws BusinessException{
	    phone = DigestUtils.md5Hex(phone);
	    User user = userDaoRead.findByPhone(phone);
	    if(user == null){
	        throw new BusinessException("手机号未注册");
	    }
	    if(!user.getPassword().equals(password)){
	        throw new BusinessException("手机号或密码错误");
	    }
	    Integer userId = user.getId();
	    String clientId = System.currentTimeMillis()+"";
	    if(!userDao.updateClientId(userId, clientId)){
	        throw new BusinessException("登录后更新clientId失败");
	    }
	    user.setClientId(clientId);
	    Profile profile = new Profile(userId, clientId);
	    session.setAttribute(userId+"", profile);
	    model.addAttribute(Constant.UserId, userId);
	    model.addAttribute(Constant.ClientId, clientId);
	    model.addAttribute("user", user);
        return model;
    }
	
	@RequestMapping(value = {"register"}, method = { RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public Object register(Model model,HttpSession session,
			@RequestParam(required = true) String phone,
			@RequestParam(required = true) String password) throws BusinessException{
	    phone = DigestUtils.md5Hex(phone);
        User user = userDaoRead.findByPhone(phone);
        if(user != null){
            throw new BusinessException("用户已存在，不能重复注册");
        }
		user = new User();
		user.setPhone(phone);
		user.setPassword(password);
		user.setStatus(CommonStatus.UserEnable.value);
		user.setIsTemple(false);
		user.setLevelName("白衣");
		user.setLevel(0);
		user.setScore(0);
		user.setMerits(0);
		user.setPrayNum(0L);
		user.setClientId(System.currentTimeMillis()+"");
		this.userDao.insert(user);
		Profile profile = new Profile(user.getId(), user.getClientId());
        session.setAttribute(user.getId()+"", profile);
        model.addAttribute(Constant.UserId, user.getId());
        model.addAttribute(Constant.ClientId, user.getClientId());
        model.addAttribute("user", user);
        return model;
    }
	
	@AccessVerifier
	@RequestMapping(value = {"resetpassword"}, method = { RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Object resetpassword(@RequestParam(required = false)Profile profile,
    		@RequestParam(required = true) String newpassword){
	    this.userDao.update(profile.getUserId(), newpassword, null, null, null, null);
	    return true;
    }
	
}
