/**
 * 
 */
package com.cf.code.web.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cf.code.core.aop.AccessVerifier;
import com.cf.code.entity.Profile;

/**
 * @author congfeng
 *
 * @email congfeng@meicai.cn
 */
@Controller
@RequestMapping("/app")
public class AppController {

    @AccessVerifier
    @RequestMapping(value = {"profile"}, method = { RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object profile(@RequestParam(required = false)Profile profile,
            @RequestParam(required = true) String username){
        return profile;
    }
    
}
