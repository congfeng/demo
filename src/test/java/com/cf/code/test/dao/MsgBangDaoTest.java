/**
 * 
 */
package com.cf.code.test.dao;

import java.util.List;

import com.cf.code.common.StringUtil;
import com.cf.code.dao.MsgBangDao;
import com.cf.code.entity.MsgBang;
import com.cf.code.test.AbstractTestCase;

/**
 * @author congfeng
 *
 */
public class MsgBangDaoTest extends AbstractTestCase{

    MsgBangDao msgBangDao;
    
    MsgBangDao msgBangDaoRead;
    
    protected void init() {
        msgBangDao = context.getBean("msgBangDao", MsgBangDao.class);
        msgBangDaoRead = context.getBean("msgBangDaoRead", MsgBangDao.class);
    }
    
    public void testInsert(){
        MsgBang msgBang = new MsgBang();
//        必须
        msgBang.setMsgId(1);
        msgBang.setUserId(2);
        msgBangDao.insert(msgBang);
        log.info(msgBang.getId());
    }
    
    public void testDelete(){
        Integer id = 1;
        msgBangDao.delete(id);
        log.info("end");
    }
    
    public void testFind(){
        Integer id = 2;
        MsgBang msgBang = msgBangDaoRead.find(id);
        log.info(StringUtil.toJson(msgBang));
    }
    
    public void testQuery(){
        List<MsgBang> msgBangList = msgBangDaoRead.query(null, null);
        log.info(StringUtil.toJson(msgBangList));
    }
    
    public void testQueryPage(){
        Integer start = 0;
        Integer size = 5;
        List<MsgBang> msgBangList = msgBangDaoRead.queryPage(null, null, size, size);
        log.info(StringUtil.toJson(msgBangList));
    }
    
    public void testQueryCount(){
        int count = msgBangDaoRead.queryCount(null,null);
        log.info(count);
    }
    
}
