/**
 * 
 */
package com.cf.code.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cf.code.core.aop.AccessVerifier;
import com.cf.code.entity.Profile;

/**
 * @Version: 1.0
 * @Author: 丛峰
 * @Email: 3024992@qq.com
 */
@Controller
@RequestMapping("/")
public class IndexController {

	@AccessVerifier
	@RequestMapping(value={"/"})
    public Object index(@RequestParam(required = false)Profile profile,HttpSession session){
        return "index.html";
    }

}
