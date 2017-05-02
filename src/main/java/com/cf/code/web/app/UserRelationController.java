/**
 * 
 */
package com.cf.code.web.app;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cf.code.core.aop.AccessVerifier;
import com.cf.code.core.exception.BusinessException;
import com.cf.code.dao.UserDao;
import com.cf.code.dao.UserRelationDao;
import com.cf.code.entity.Profile;
import com.cf.code.entity.User;
import com.cf.code.entity.UserRelation;

/**
 * @author congfeng
 *
 * @email congfeng@meicai.cn
 */
@Controller("App/UserRelationController")
@RequestMapping("/app/userrelation")
public class UserRelationController {

    @Resource(name = "userDaoRead")
    UserDao userDaoRead;
    
    @Resource(name = "userRelationDao")
    UserRelationDao userRelationDao;
    
    @Resource(name = "userRelationDaoRead")
    UserRelationDao userRelationDaoRead;
    
    @AccessVerifier
    @RequestMapping(value = {"friend/list"}, method = { RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object friendList(@RequestParam(required = false)Profile profile){
        List<UserRelation> userRelationList = userRelationDaoRead.query(profile.getUserId(),null,true, null, false);
        return userRelationList;
    }
    
    @AccessVerifier
    @RequestMapping(value = {"friend/add"}, method = { RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object friendAdd(@RequestParam(required = false)Profile profile,
            @RequestParam(required = true) Integer opUserId) throws BusinessException{
        User opUser = userDaoRead.find(opUserId);
        if(opUser == null){
            throw new BusinessException("对方的用户不存在");
        }
        UserRelation opUserRelation = userRelationDao.findByUser(opUserId, profile.getUserId());
        Boolean isOpFriend = (opUserRelation != null&&opUserRelation.getIsFriend());
        UserRelation userRelation = userRelationDao.findByUser(profile.getUserId(),opUserId);
        if(userRelation != null){
            userRelationDao.update(userRelation.getId(), true, null, null);
        }else{
            userRelation = new UserRelation();
            userRelation.setUserId(profile.getUserId());
            userRelation.setOpUserId(opUserId);
            userRelation.setIsFriend(true);
            userRelation.setIsOpFriend(isOpFriend);
            userRelation.setIsEnemy(false);
            userRelationDao.insert(userRelation);
        }
        return true;
    }
    
    @AccessVerifier
    @RequestMapping(value = {"friend/delete"}, method = { RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object friendDelete(@RequestParam(required = false)Profile profile,
            @RequestParam(required = true) Integer opUserId) throws BusinessException{
        User opUser = userDaoRead.find(opUserId);
        if(opUser == null){
            throw new BusinessException("对方的用户不存在");
        }
        UserRelation opUserRelation = userRelationDao.findByUser(opUserId, profile.getUserId());
        if(opUserRelation != null&&opUserRelation.getIsOpFriend()){
            userRelationDao.update(opUserRelation.getId(), null, false, null);
        }
        UserRelation userRelation = userRelationDao.findByUser(profile.getUserId(),opUserId);
        if(userRelation == null||!userRelation.getIsFriend()){
            return false;
        }else{
            userRelationDao.update(userRelation.getId(), false, null, null);
            return true;
        }
    }
    
    @AccessVerifier
    @RequestMapping(value = {"enemy/add"}, method = { RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object enemyAdd(@RequestParam(required = false)Profile profile,
            @RequestParam(required = true) Integer opUserId) throws BusinessException{
    	User opUser = userDaoRead.find(opUserId);
        if(opUser == null){
            throw new BusinessException("对方的用户不存在");
        }
        UserRelation opUserRelation = userRelationDao.findByUser(opUserId, profile.getUserId());
        Boolean isOpFriend = (opUserRelation != null&&opUserRelation.getIsFriend());
        UserRelation userRelation = userRelationDao.findByUser(profile.getUserId(),opUserId);
        if(userRelation == null){
        	userRelation = new UserRelation();
            userRelation.setUserId(profile.getUserId());
            userRelation.setOpUserId(opUserId);
            userRelation.setIsFriend(false);
            userRelation.setIsOpFriend(isOpFriend);
            userRelation.setIsEnemy(true);
            userRelationDao.insert(userRelation);
        }else{
        	userRelationDao.update(userRelation.getId(), null, null, true);
        }
        return true;
    }
    
    @AccessVerifier
    @RequestMapping(value = {"enemy/delete"}, method = { RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object enemyDelete(@RequestParam(required = false)Profile profile,
            @RequestParam(required = true) Integer opUserId) throws BusinessException{
    	User opUser = userDaoRead.find(opUserId);
        if(opUser == null){
            throw new BusinessException("对方的用户不存在");
        }
        UserRelation userRelation = userRelationDao.findByUser(profile.getUserId(),opUserId);
        if(userRelation == null||!userRelation.getIsEnemy()){
            return false;
        }else{
            userRelationDao.update(userRelation.getId(), null, null, false);
            return true;
        }
    }
    
}
