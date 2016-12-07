/**
 * 
 */
package com.cf.code.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cf.code.core.net.EmailMsgSender;
import com.cf.code.dao.MsgDao;
import com.cf.code.entity.Msg;
import com.cf.code.service.MsgService;

/**
 * @author congfeng
 *
 */
@Service("msgService")
public class MsgServiceImpl implements MsgService{

	@Resource(name = "msgDao")
	MsgDao msgDao;
	
	@Resource(name = "msgDaoRead")
	MsgDao msgDaoRead;
	
	
	EmailMsgSender msgSender = new EmailMsgSender("channel_warning@126.com","12345679","用户信件");
	
	@Override
	public void sender(Msg msg) {
	    
	}
	
}
