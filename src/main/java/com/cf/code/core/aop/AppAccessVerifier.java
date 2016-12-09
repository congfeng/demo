/**
 * 
 */
package com.cf.code.core.aop;

import java.lang.reflect.Method;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cf.code.common.Constant;
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
    
    @Override
    protected Object access(Method method,HttpServletRequest request)throws AccessException{
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
    
    
}
