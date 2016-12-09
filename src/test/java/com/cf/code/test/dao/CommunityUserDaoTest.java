/**
 * 
 */
package com.cf.code.test.dao;

import java.util.List;

import com.cf.code.common.StringUtil;
import com.cf.code.dao.CommunityUserDao;
import com.cf.code.entity.CommunityUser;
import com.cf.code.entity.enums.CommonStatus;
import com.cf.code.entity.enums.CommunityUserRole;
import com.cf.code.test.AbstractTestCase;

/**
 * @author congfeng
 *
 */
public class CommunityUserDaoTest extends AbstractTestCase{

    CommunityUserDao communityUserDao;
    
    CommunityUserDao communityUserDaoRead;
    
    protected void init() {
        communityUserDao = context.getBean("communityUserDao", CommunityUserDao.class);
        communityUserDaoRead = context.getBean("communityUserDaoRead", CommunityUserDao.class);
    }
    
    public void testInsert(){
        CommunityUser communityUser = new CommunityUser();
//        必须
        communityUser.setStatus(CommonStatus.CommunityUserApply.value);
        communityUser.setUserId(1);
        communityUser.setCommunityId(1);
        communityUser.setRole(CommunityUserRole.Creator.value);
        communityUserDao.insert(communityUser);
        log.info(communityUser.getId());
    }
    
    public void testDelete(){
        Integer id = 1;
        communityUserDao.delete(id);
        log.info("end");
    }
    
    public void testUpdate(){
        Integer id = 2;
        Byte role = CommunityUserRole.Manager.value;
        boolean b = communityUserDao.update(id,role);
        log.info(b);
    }
    
    public void testUpdateStatus(){
        Integer id = 2;
        Byte status = CommonStatus.CommunityUserEnable.value;
        boolean b = communityUserDao.updateStatus(id, status);
        log.info(b);
    }
    
    public void testFind(){
        Integer id = 2;
        CommunityUser communityUser = communityUserDaoRead.find(id);
        log.info(StringUtil.toJson(communityUser));
    }
    
    public void testQuery(){
        List<CommunityUser> communityUserList = communityUserDaoRead.query();
        log.info(StringUtil.toJson(communityUserList));
    }
    
    public void testQueryPage(){
        Integer start = 0;
        Integer size = 5;
        List<CommunityUser> communityUserList = communityUserDaoRead.queryPage(start, size);
        log.info(StringUtil.toJson(communityUserList));
    }
    
    public void testQueryCount(){
        int count = communityUserDaoRead.queryCount();
        log.info(count);
    }
    
}
