/**
 * 
 */
package com.cf.code.test.dao;

import java.util.List;

import com.cf.code.common.StringUtil;
import com.cf.code.dao.UserDao;
import com.cf.code.entity.User;
import com.cf.code.entity.enums.CommonStatus;
import com.cf.code.test.AbstractTestCase;

/**
 * @author congfeng
 *
 */
public class UserDaoTest extends AbstractTestCase{

    UserDao userDao;
    
    UserDao userDaoRead;
    
    protected void init() {
        userDao = context.getBean("userDao", UserDao.class);
        userDaoRead = context.getBean("userDaoRead", UserDao.class);
    }
    
    public void testInsert(){
        User user = new User();
//      必须
        user.setStatus(CommonStatus.UserEnable.value);
        user.setPhone("phone");
        user.setPassword("password");
        user.setIsTemple(false);
        user.setLevelName("levelName的");
        user.setLevel(1);
        user.setScore(2);
        user.setMerits(3);
        user.setPrayNum(4L);
        user.setClientId("dddd");
//        非必须
        user.setNick("nick说的");
        user.setPortrait("portrait 方法");
        user.setCover("cover");
        user.setDescription("description");
        userDao.insert(user);
        log.info(user.getId());
    }
    
    public void testDelete(){
        Integer id = 1;
        userDao.delete(id);
        log.info("end");
    }
    
    public void testUpdate(){
        Integer id = 2;
        String password = "dd";
        String nick = "nicktes111111t";
        String portrait = "portrait11111";
        String cover = "cover11111";
        String description = "description11111";
        boolean b = userDao.update(id, password, nick, portrait, cover, description);
        log.info(b);
    }
    
    public void testUpdateStatus(){
        Integer id = 2;
        Byte status = CommonStatus.UserDisable.value;
        boolean b = userDao.updateStatus(id, status);
        log.info(b);
    }
    
    public void testUpdateClientId(){
        Integer id = 2;
        String clientId = "ddd";
        boolean b = userDao.updateClientId(id, clientId);
        log.info(b);
    }
    
    public void testFind(){
        Integer id = 2;
        User user = userDaoRead.find(id);
        log.info(StringUtil.toJson(user));
    }
    
    public void testQuery(){
        List<User> userList = userDaoRead.query();
        log.info(StringUtil.toJson(userList));
    }
    
    public void testQueryPage(){
        Integer start = 0;
        Integer size = 5;
        List<User> userList = userDaoRead.queryPage(start, size);
        log.info(StringUtil.toJson(userList));
    }
    
    public void testQueryCount(){
        int count = userDaoRead.queryCount();
        log.info(count);
    }
    
    public void testFindByPhone(){
        String phone = "phone";
        User user = userDaoRead.findByPhone(phone);
        log.info(StringUtil.toJson(user));
    }
    
    public void testFindByClientId(){
        Integer id = 3;
        String clientId = "d";
        User user = userDaoRead.findByClientId(id,clientId);
        log.info(StringUtil.toJson(user));
    }
    
}
