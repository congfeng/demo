/**
 * 
 */
package com.cf.code.job;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cf.code.dao.UserDao;

/**
 * @author congfeng
 *
 */
public class EmptyJob {

	private static Logger log = LogManager.getLogger(EmptyJob.class);
	
	@Resource(name = "userDao")
	UserDao userDao;
	
	public void doit(){
		log.info("------------------EmptyJob--------------" + userDao);
	}
	
}
