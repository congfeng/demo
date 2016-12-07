/**
 * 
 */
package com.cf.code.test.dao;

import com.cf.code.dao.CommunityDao;
import com.cf.code.test.AbstractTestCase;

/**
 * @author congfeng
 *
 * @email congfeng@meicai.cn
 */
public class CommunityDaoTest extends AbstractTestCase{

    CommunityDao communityDao = context.getBean("communityDao", CommunityDao.class);
   
    CommunityDao communityDaoRead = context.getBean("communityDaoRead", CommunityDao.class);
    
    public void testInsert(){
//        List<Demo> demoList = demoDao.query(null, DemoType.TypeOne);
//        log.info(StringUtil.toJson(demoList.size()));
    }
    
}
