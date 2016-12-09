/**
 * 
 */
package com.cf.code.web.app;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cf.code.core.aop.AccessVerifier;
import com.cf.code.dao.CommunityDao;
import com.cf.code.dao.UserDao;
import com.cf.code.entity.Profile;

/**
 * @author congfeng
 *
 */
@Controller
@RequestMapping("/app/community")
public class CommunityController {

	@Resource(name = "communityDao")
	CommunityDao communityDao;
	
	@Resource(name = "communityDaoRead")
	CommunityDao communityDaoRead;
	
	@AccessVerifier
	@RequestMapping(value = {"add"}, method = { RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Object add(@RequestParam(required = false)Profile profile,
    		@RequestParam(required = true) String newpassword){
	    return true;
    }
	
	@AccessVerifier
	@RequestMapping(value = {"list"}, method = { RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Object list(@RequestParam(required = false)Profile profile,
    		@RequestParam(required = true) String newpassword){
	    return true;
    }
	
}
