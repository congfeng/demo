/**
 * 
 */
package com.cf.code.test.dao;

import java.util.List;

import com.cf.code.common.StringUtil;
import com.cf.code.dao.MsgCollectDao;
import com.cf.code.entity.MsgCollect;
import com.cf.code.test.AbstractTestCase;

/**
 * @author congfeng
 *
 */
public class MsgCollectDaoTest extends AbstractTestCase{

    MsgCollectDao msgCollectDao;
    
    MsgCollectDao msgCollectDaoRead;
    
    protected void init() {
        msgCollectDao = context.getBean("msgCollectDao", MsgCollectDao.class);
        msgCollectDaoRead = context.getBean("msgCollectDaoRead", MsgCollectDao.class);
    }
    
    public void testInsert(){
        MsgCollect msgCollect = new MsgCollect();
//        必须
        msgCollect.setMsgId(1);
        msgCollect.setUserId(2);
        msgCollectDao.insert(msgCollect);
        log.info(msgCollect.getId());
    }
    
    public void testDelete(){
        Integer id = 1;
        msgCollectDao.delete(id);
        log.info("end");
    }
    
    public void testFind(){
        Integer id = 2;
        MsgCollect msgCollect = msgCollectDaoRead.find(id);
        log.info(StringUtil.toJson(msgCollect));
    }
    
    public void testQuery(){
        List<MsgCollect> msgCollectList = msgCollectDaoRead.query();
        log.info(StringUtil.toJson(msgCollectList));
    }
    
    public void testQueryPage(){
        Integer start = 0;
        Integer size = 5;
        List<MsgCollect> msgCollectList = msgCollectDaoRead.queryPage(start, size);
        log.info(StringUtil.toJson(msgCollectList));
    }
    
    public void testQueryCount(){
        int count = msgCollectDaoRead.queryCount();
        log.info(count);
    }
    
}
