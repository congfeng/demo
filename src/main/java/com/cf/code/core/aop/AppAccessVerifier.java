/**
 * 
 */
package com.cf.code.core.aop;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cf.code.common.Constant;
import com.cf.code.common.Constant.TransmitField;
import com.cf.code.core.exception.AccessException;
import com.cf.code.dao.UserDao;
import com.cf.code.entity.Profile;
import com.cf.code.entity.User;


/**
 * @author congfeng
 *
 * @email congfeng@meicai.cn
 */
public class AppAccessVerifier extends WebAdvice{

    @Resource(name = "userDaoRead")
    UserDao userDaoRead;
    
    @Value("#{sys.permit}")
	private String permit;
    
    @Override
    protected Object access(Method method,HttpServletRequest request)throws AccessException{
    	String key = request.getParameter(Constant.KEY);
        if(StringUtils.isEmpty(key)||!key.equals(permit)){
    		log.warn("illegal permit");
    		throw new AccessException("- -");
    	}
        AccessVerifier accessVerifier = method.getAnnotation(AccessVerifier.class);
        if(accessVerifier == null){
            return null;
        }
        String userId = request.getParameter(Constant.UserId);
        String clientId = request.getParameter(Constant.ClientId);
        if(StringUtils.isEmpty(userId)){
            throw new AccessException("参数缺失["+Constant.UserId+"]");
        }
        Integer uId = null;
        try {
            uId = Integer.valueOf(userId);
        } catch (Exception e) {
            throw new AccessException("参数类型错误["+Constant.UserId+"]");
        }
        if(StringUtils.isEmpty(clientId)){
            throw new AccessException("参数缺失["+Constant.ClientId+"]");
        }
        HttpSession session = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        Profile profile = (Profile)session.getAttribute(userId);
        if(profile == null){
            User user = userDaoRead.find(uId);
            if(user == null){
                throw new AccessException("用户不存在");
            }
            if(!clientId.equals(user.getClientId())){
                throw new AccessException("未登陆");
            }
            profile = new Profile(uId, clientId);
            session.setAttribute(userId, profile);
        }else{
            if(!clientId.equals(profile.getClientId())){
                throw new AccessException("未登陆");
            }
        }
        return profile;
    }
    
    protected Map<String, Object> returnException(Class<?> rt,int t,String m) throws IOException{
        HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        if(rt != Map.class&&rt != Object.class){
            response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE,m);
            return null;
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(TransmitField.Type, t);
        result.put(TransmitField.Msg, m);
        result.put(TransmitField.Status,503);
        return result;
    }
    
}
