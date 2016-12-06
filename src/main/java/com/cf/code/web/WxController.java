/**
 * 
 */
package com.cf.code.web;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author congfeng
 *
 * @email congfeng@meicai.cn
 */
@Controller
@RequestMapping("/wx")
public class WxController {

    private static Logger log = LogManager.getLogger(WxController.class);
    
    @RequestMapping(value = {""}, method = { RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object init(@RequestParam(required = false) String timeStr) {
        log.info("wx---------");
        return "ddddd";
    }
    
}
