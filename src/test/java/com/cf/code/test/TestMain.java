/**
 * 
 */
package com.cf.code.test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @Version: 1.0
 * @Author: 丛峰
 * @Email: 3024992@qq.com
 */
public class TestMain {

	protected static Logger log = LogManager.getLogger(TestMain.class);
	
	static int i = 100;
	
	public static void main(String[] args) throws IOException {
	    List<String> ls = FileUtils.readLines(new File("/Users/congfeng/git/lx/saved_resource.html"));
	    for(int i=0;i<ls.size();i++){
	    	String info = ls.get(i).trim();
	    	if(!info.startsWith("<p><a href=\"/plugin")){
	    		continue;
	    	}
	    	String infod = info.toLowerCase();
	    	if(infod.indexOf("bluetooth") > -1
	    			||infod.indexOf("facebook") > -1
	    			||infod.indexOf("admob") > -1
	    			||infod.indexOf("paypal") > -1
	    			||infod.indexOf("google") > -1){
	    		continue;
	    	}
	    	String name = StringUtils.substringBetween(info, "\">", "</a>");
    		String url = StringUtils.substringBetween(info, "href=\"", "\">");
    		log.info((i+1)+"."+name+"：http://www.plugreg.com"+url);
	    }
	    System.out.println(ls.size());
	}
	
	
}
