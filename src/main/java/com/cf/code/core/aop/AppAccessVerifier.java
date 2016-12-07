/**
 * 
 */
package com.cf.code.core.aop;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cf.code.core.exception.AccessException;
import com.cf.code.entity.Profile;


/**
 * @author congfeng
 *
 * @email congfeng@meicai.cn
 */
public class AppAccessVerifier extends WebAdvice{

    @Override
    protected Object access(Method method,HttpServletRequest request)throws AccessException{
        AccessVerifier accessVerifier = method.getAnnotation(AccessVerifier.class);
        if(accessVerifier == null){
            return null;
        }
        String clientId = request.getParameter("clientId");
        if(StringUtils.isEmpty(clientId)){
            throw new AccessException("参数缺失");
        }
        HttpSession session = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        Profile profile = (Profile)session.getAttribute(clientId);
        if(profile == null&&accessVerifier.check()){
            throw new AccessException("未登陆");
        }
        return profile;
    }
    
    
}
