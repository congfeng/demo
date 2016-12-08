/**
 * 
 */
package com.cf.code.test.dao;

import java.util.List;

import com.cf.code.common.StringUtil;
import com.cf.code.dao.UserRelationDao;
import com.cf.code.entity.UserRelation;
import com.cf.code.test.AbstractTestCase;

/**
 * @author congfeng
 *
 */
public class UserRelationDaoTest extends AbstractTestCase{

    UserRelationDao userRelationDao;
    
    UserRelationDao userRelationDaoRead;
    
    protected void init() {
        userRelationDao = context.getBean("userRelationDao", UserRelationDao.class);
        userRelationDaoRead = context.getBean("userRelationDaoRead", UserRelationDao.class);
    }
    
    public void testInsert(){
        UserRelation userRelation = new UserRelation();
//        必须
        userRelation.setUserId(1);
        userRelation.setOpUserId(2);
        userRelation.setIsFriend(true);
        userRelation.setIsOpFriend(false);
        userRelation.setIsEnemy(false);
        userRelationDao.insert(userRelation);
        log.info(userRelation.getId());
    }
    
    public void testDelete(){
        Integer id = 1;
        userRelationDao.delete(id);
        log.info("end");
    }
    
    public void testUpdate(){
        Integer id = 2;
        Boolean isFriend = false;
        Boolean isOpFriend = true;
        Boolean isEnemy = true;
        boolean b = userRelationDao.update(id, isFriend, isOpFriend, isEnemy);
        log.info(b);
    }
    
    public void testFind(){
        Integer id = 2;
        UserRelation userRelation = userRelationDaoRead.find(id);
        log.info(StringUtil.toJson(userRelation));
    }
    
    public void testQuery(){
        List<UserRelation> userRelationList = userRelationDaoRead.query();
        log.info(StringUtil.toJson(userRelationList));
    }
    
    public void testQueryPage(){
        Integer start = 0;
        Integer size = 5;
        List<UserRelation> userRelationList = userRelationDaoRead.queryPage(start, size);
        log.info(StringUtil.toJson(userRelationList));
    }
    
    public void testQueryCount(){
        int count = userRelationDaoRead.queryCount();
        log.info(count);
    }
    
}
