/**
 * 
 */
package com.cf.code.core.aop;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cf.code.common.Constant.TransmitField;
import com.cf.code.core.exception.AccessException;
import com.cf.code.entity.Profile;

/**
 * @author congfeng
 *
 * @email congfeng@meicai.cn
 */
public class AdminAccessVerifier extends WebAdvice{

    @Override
    protected Object access(Method method,HttpServletRequest request)throws AccessException{
        AccessVerifier accessVerifier = method.getAnnotation(AccessVerifier.class);
        if(accessVerifier == null){
            return null;
        }
        HttpSession session = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        Profile profile = (Profile)session.getAttribute("profile");
        if(profile == null&&accessVerifier.check()){
            throw new AccessException("未登陆");
        }
        return profile;
    }
    
    protected Map<String, Object> returnException(Class<?> rt,int t,String m) throws IOException{
        HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        if(rt != Map.class&&rt != Object.class){
            response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE,m);
            return null;
        }
        response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(TransmitField.Type, t);
        result.put(TransmitField.Msg, m);
        result.put(TransmitField.Status,503);
        return result;
    }
    
}
