/**
 * 
 */
package com.cf.code.test.dao;

import java.util.List;

import com.cf.code.common.StringUtil;
import com.cf.code.dao.MsgCommentDao;
import com.cf.code.entity.MsgComment;
import com.cf.code.test.AbstractTestCase;

/**
 * @author congfeng
 *
 */
public class MsgCommentDaoTest extends AbstractTestCase{

    MsgCommentDao msgCommentDao;
    
    MsgCommentDao msgCommentDaoRead;
    
    protected void init() {
        msgCommentDao = context.getBean("msgCommentDao", MsgCommentDao.class);
        msgCommentDaoRead = context.getBean("msgCommentDaoRead", MsgCommentDao.class);
    }
    
    public void testInsert(){
        MsgComment msgComment = new MsgComment();
//        必须
        msgComment.setMsgId(1);
        msgComment.setUserId(2);
        msgComment.setContent("content的");
//      非必须
        msgComment.setOpUserId(34);
        msgCommentDao.insert(msgComment);
        log.info(msgComment.getId());
    }
    
    public void testDelete(){
        Integer id = 1;
        msgCommentDao.delete(id);
        log.info("end");
    }
    
    public void testFind(){
        Integer id = 2;
        MsgComment msgComment = msgCommentDaoRead.find(id);
        log.info(StringUtil.toJson(msgComment));
    }
    
    public void testQuery(){
        List<MsgComment> msgCommentList = msgCommentDaoRead.query();
        log.info(StringUtil.toJson(msgCommentList));
    }
    
    public void testQueryPage(){
        Integer start = 0;
        Integer size = 5;
        List<MsgComment> msgCommentList = msgCommentDaoRead.queryPage(start, size);
        log.info(StringUtil.toJson(msgCommentList));
    }
    
    public void testQueryCount(){
        int count = msgCommentDaoRead.queryCount();
        log.info(count);
    }
    
}
