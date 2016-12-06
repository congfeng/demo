/**
 * 
 */
package com.cf.code.core.aop;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

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
        String userNo = request.getParameter("userNo");
        String clientId = request.getParameter("clientId");
        if(StringUtils.isEmpty(userNo)||StringUtils.isEmpty(clientId)){
            throw new AccessException("参数缺失");
        }
        return new Profile(userNo, 0, clientId, null);
    }
    
    
}
