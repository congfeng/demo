/**
 * 
 */
package com.cf.code.test.dao;

import java.util.List;

import com.cf.code.common.StringUtil;
import com.cf.code.dao.MsgDao;
import com.cf.code.entity.Msg;
import com.cf.code.entity.enums.CommonStatus;
import com.cf.code.entity.enums.MsgScope;
import com.cf.code.test.AbstractTestCase;

/**
 * @author congfeng
 *
 */
public class MsgDaoTest extends AbstractTestCase{

    MsgDao msgDao;
    
    MsgDao msgDaoRead;
    
    protected void init() {
        msgDao = context.getBean("msgDao", MsgDao.class);
        msgDaoRead = context.getBean("msgDaoRead", MsgDao.class);
    }
    
    public void testInsert(){
        Msg msg = new Msg();
//        必须
        msg.setStatus(CommonStatus.MsgEnable.value);
        msg.setUserId(1);
        msg.setCommunityId(0);
        msg.setScope(MsgScope.PUBLICCommunity.value);
        msg.setBangNum(0);
        msg.setShareNum(0);
        msg.setCommentNum(0);
        msg.setContent("content的的");
//        非必须
        msg.setImage1("image1");
        msg.setImage2("image2");
        msg.setImage3("image3");
        msg.setImage4("image4");
        msg.setImage5("image5");
        msg.setImage6("image6");
        msgDao.insert(msg);
        log.info(msg.getId());
    }
    
    public void testDelete(){
        Integer id = 1;
        msgDao.delete(id);
        log.info("end");
    }
    
    public void testUpdate(){
        Integer id = 2;
        Byte scope = MsgScope.PUBLIC.value;
        boolean b = msgDao.update(id, scope);
        log.info(b);
    }
    
    public void testUpdateStatus(){
        Integer id = 2;
        Byte status = CommonStatus.MsgDisable.value;
        boolean b = msgDao.updateStatus(id, status);
        log.info(b);
    }
    
    public void testFind(){
        Integer id = 2;
        Msg msg = msgDaoRead.find(id);
        log.info(StringUtil.toJson(msg));
    }
    
    public void testQuery(){
        List<Msg> msgList = msgDaoRead.query();
        log.info(StringUtil.toJson(msgList));
    }
    
    public void testQueryPage(){
        Integer start = 0;
        Integer size = 5;
        List<Msg> msgList = msgDaoRead.queryPage(start, size);
        log.info(StringUtil.toJson(msgList));
    }
    
    public void testQueryCount(){
        int count = msgDaoRead.queryCount();
        log.info(count);
    }
    
}
