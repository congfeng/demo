/**
 * 
 */
package com.cf.code.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.cf.code.dao.OperationLogDao;
import com.cf.code.entity.OperationLog;
import com.cf.code.entity.enums.OpStatus;
import com.cf.code.entity.enums.OpType;
import com.cf.code.service.OpLogService;

/**
 * @author congfeng
 *
 */
@Service("opLogService")
public class OpLogServiceImpl implements OpLogService{

	private static Logger log = LogManager.getLogger(OpLogServiceImpl.class);

	@Resource(name = "operationLogDao")
	OperationLogDao opDao;
	
	@Resource(name = "operationLogDaoRead")
	OperationLogDao opDaoRead;
	
	@Override
	public int saveOp4Init(OpType opType) {
    	OperationLog opLog = new OperationLog();
    	opLog.setUserId(0);
    	opLog.setUserName("admin");
    	opLog.setType(opType.value);
    	opLog.setStatus(OpStatus.Init.value);
    	opLog.setRelatedNo("");
    	opLog.setRelatedName("");
    	opLog.setDesc("");
    	opDao.insert(opLog);
    	return opLog.getId();
    }

	@Override
	public int getOpLatestTime(OpType opType) {
    	OperationLog opLog = opDaoRead.findLatest(opType.value, OpStatus.Success.value);
    	if(opLog == null){
    		return -1;
    	}
    	return opLog.getTime();
    }

	@Override
	public void opSuccess(Integer id) {
		opDao.updateStatus(id, OpStatus.Success.value);
	}

	@Override
	public void opException(Integer id) {
		opDao.updateStatus(id, OpStatus.Exception.value);
	}
	
}
