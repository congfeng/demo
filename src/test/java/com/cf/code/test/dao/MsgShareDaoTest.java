/**
 * 
 */
package com.cf.code.test.dao;

import java.util.List;

import com.cf.code.common.StringUtil;
import com.cf.code.dao.MsgShareDao;
import com.cf.code.entity.MsgShare;
import com.cf.code.test.AbstractTestCase;

/**
 * @author congfeng
 *
 */
public class MsgShareDaoTest extends AbstractTestCase{

    MsgShareDao msgShareDao;
    
    MsgShareDao msgShareDaoRead;
    
    protected void init() {
        msgShareDao = context.getBean("msgShareDao", MsgShareDao.class);
        msgShareDaoRead = context.getBean("msgShareDaoRead", MsgShareDao.class);
    }
    
    public void testInsert(){
        MsgShare msgShare = new MsgShare();
//        必须
        msgShare.setMsgId(1);
        msgShare.setUserId(2);
        msgShareDao.insert(msgShare);
        log.info(msgShare.getId());
    }
    
    public void testDelete(){
        Integer id = 1;
        msgShareDao.delete(id);
        log.info("end");
    }
    
    public void testFind(){
        Integer id = 2;
        MsgShare msgShare = msgShareDaoRead.find(id);
        log.info(StringUtil.toJson(msgShare));
    }
    
    public void testQuery(){
        List<MsgShare> msgShareList = msgShareDaoRead.query();
        log.info(StringUtil.toJson(msgShareList));
    }
    
    public void testQueryPage(){
        Integer start = 0;
        Integer size = 5;
        List<MsgShare> msgShareList = msgShareDaoRead.queryPage(start, size);
        log.info(StringUtil.toJson(msgShareList));
    }
    
    public void testQueryCount(){
        int count = msgShareDaoRead.queryCount();
        log.info(count);
    }
    
}
