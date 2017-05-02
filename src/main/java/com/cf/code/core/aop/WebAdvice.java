/**
 * 
 */
package com.cf.code.core.aop;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cf.code.common.Constant.TransmitField;
import com.cf.code.core.exception.AccessException;
import com.cf.code.core.exception.BusinessException;

/**
 * @author congfeng
 *
 * @email congfeng@meicai.cn
 */
public abstract class WebAdvice implements MethodInterceptor{

    private static final Logger log = Logger.getLogger(WebAdvice.class);
    
    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        Method method = mi.getMethod();
        Class<?>[] pts = method.getParameterTypes();
        Class<?> rt = method.getReturnType();
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        try{
            Object accessProfile = access(method,request);
            if(accessProfile != null){
                for(int i=0;i<pts.length;i++){
                  if(pts[i] == accessProfile.getClass()){
                      mi.getArguments()[i] = accessProfile;
                      break;
                  }
              }
            }
        }catch(AccessException e){
            return returnException(rt,10, e.getMessage());
        }
        String callback = request.getParameter("callback");
        try{
            Object ret = mi.proceed();
            if(!StringUtils.isEmpty(callback)){
                if(rt != Object.class &&rt != MappingJacksonValue.class){
                    throw new Exception("返回类型不支持jsonp[Object|MappingJacksonValue]：" + rt.getName());
                }
                MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(ret);
                mappingJacksonValue.setJsonpFunction(callback);
                ret = mappingJacksonValue;
            }
            return ret;
        }catch(BusinessException e){
            String msg = e.getMessage();
            log.warn("服务失败：" + msg);
            return returnException(rt,30,msg);
        }catch(Exception e){
            String msg = "服务异常,日志检索码:" + UUID.randomUUID();
            log.error(msg, e);
            return returnException(rt,31,msg);
        }
    }
    
    protected Map<String, Object> returnException(Class<?> rt,int t,String m) throws IOException{
        HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        if(rt != Map.class&&rt != Object.class){
            response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE,m);
            return null;
        }
//        response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(TransmitField.Type, t);
        result.put(TransmitField.Msg, m);
        result.put(TransmitField.Status,503);
        return result;
    }
    
    protected abstract Object access(Method method,HttpServletRequest request)throws AccessException;
    
}
