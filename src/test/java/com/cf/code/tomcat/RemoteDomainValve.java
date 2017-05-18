/**
 * 
 */
package com.cf.code.tomcat;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.RequestFilterValve;

/**
 * @author congfeng
 *
 */
public class RemoteDomainValve extends RequestFilterValve{
	
	private static final String info =
	        "com.cf.code.tomcat.RemoteDomainValve/1.0";

    public String getInfo() {
        return (info);

    }
	
	@Override
	public void invoke(Request request, Response response) throws IOException, ServletException {
		String domain = request.getRequest().getServerName();
		try{
			this.process(domain, request, response);
		}catch (Throwable e) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN,"@~@");
		}
		
	}
	
}
