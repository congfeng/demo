/**
 * 
 */
package com.cf.code.core;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @Version: 1.0
 * @Author: 丛峰
 * @Email: 3024992@qq.com
 */
public class MyContextLoader {
	
	private static Logger log = LogManager.getLogger(MyContextLoader.class);
	
	public static volatile String uploadFolder;
	
	public static volatile String uploadPath;
	
	private static volatile String imHost;
	
	private static volatile Integer imPort;
	
	public void init(){
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				destroy();
			}
		}));
	}

	public void destroy(){
		
	}

	public void setUploadFolder(String uploadFolder) {
		this.uploadFolder = uploadFolder;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public void setImHost(String imHost) {
		this.imHost = imHost;
	}

	public void setImPort(Integer imPort) {
		this.imPort = imPort;
	}
	
	public static String getImAddress(){
		return "http://"+imHost+":"+imPort;
	}
}
