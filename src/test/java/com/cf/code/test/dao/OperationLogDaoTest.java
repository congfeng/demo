/**
 * 
 */
package com.cf.code.test.dao;

import com.alibaba.fastjson.JSON;
import com.cf.code.dao.OperationLogDao;
import com.cf.code.entity.OperationLog;
import com.cf.code.entity.enums.OpStatus;
import com.cf.code.entity.enums.OpType;
import com.cf.code.test.AbstractTestCase;

/**
 * @author congfeng
 *
 */
public class OperationLogDaoTest extends AbstractTestCase{

	OperationLogDao opDao;
    
	OperationLogDao opDaoRead;
	
	protected void init() {
		opDao = context.getBean("operationLogDao", OperationLogDao.class);
		opDaoRead = context.getBean("operationLogDaoRead", OperationLogDao.class);
    }
    
    public void testInsert(){
    	OperationLog opLog = new OperationLog();
    	opLog.setUserId(0);
    	opLog.setUserName("admin");
    	opLog.setType(OpType.ColletMusic.value);
    	opLog.setStatus(OpStatus.Success.value);
    	opLog.setRelatedNo("");
    	opLog.setRelatedName("");
    	opLog.setDesc("dddddd的点点滴滴多");
    	opDao.insert(opLog);
        log.info(opLog);
    }
    
    public void testFindLatest(){
    	OperationLog opLog = opDaoRead.findLatest(100, 1);
        log.info(JSON.toJSONString(opLog));
    }
	
}
