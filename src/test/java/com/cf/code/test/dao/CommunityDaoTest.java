/**
 * 
 */
package com.cf.code.test.dao;

import java.util.List;

import com.cf.code.common.StringUtil;
import com.cf.code.dao.CommunityDao;
import com.cf.code.entity.Community;
import com.cf.code.entity.enums.CommonStatus;
import com.cf.code.test.AbstractTestCase;

/**
 * @author congfeng
 *
 * @email congfeng@meicai.cn
 */
public class CommunityDaoTest extends AbstractTestCase{

    CommunityDao communityDao;
    
    CommunityDao communityDaoRead;
    
    protected void init() {
        communityDao = context.getBean("communityDao", CommunityDao.class);
        communityDaoRead = context.getBean("communityDaoRead", CommunityDao.class);
    }
    
    public void testInsert(){
        Community community = new Community();
//        必须
        community.setNick("昵称");
        community.setUserId(100);
        community.setStatus(CommonStatus.CommunityEnable.value);
        community.setLimitNum(200);
//        非必须
        community.setDescription("描述");
        community.setPortrait("头像");
        community.setCover("封面");
        communityDao.insert(community);
        log.info(community.getId());
    }
    
    public void testDelete(){
        Integer id = 1;
        communityDao.delete(id);
        log.info("end");
    }
    
    public void testUpdate(){
        Integer id = 2;
        String nick = "nicktest";
        boolean b = communityDao.update(id, nick);
        log.info(b);
    }
    
    public void testUpdateStatus(){
        Integer id = 2;
        Byte status = CommonStatus.CommunityDisable.value;
        boolean b = communityDao.updateStatus(id, status);
        log.info(b);
    }
    
    public void testFind(){
        Integer id = 2;
        Community community = communityDaoRead.find(id);
        log.info(StringUtil.toJson(community));
    }
    
    public void testQuery(){
        List<Community> communityList = communityDaoRead.query();
        log.info(StringUtil.toJson(communityList));
    }
    
    public void testQueryPage(){
        Integer start = 0;
        Integer size = 5;
        List<Community> communityList = communityDaoRead.queryPage(start, size);
        log.info(StringUtil.toJson(communityList));
    }
    
    public void testQueryCount(){
        int count = communityDaoRead.queryCount();
        log.info(count);
    }
    
}
