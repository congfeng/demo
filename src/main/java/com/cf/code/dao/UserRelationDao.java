/**
 * 
 */
package com.cf.code.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cf.code.entity.UserRelation;

/**
 * @author congfeng
 *
 * @email congfeng@meicai.cn
 */
public interface UserRelationDao {

    public void insert(UserRelation userRelation);
    
    public void delete(@Param("id") Integer id);
    
    public boolean update(@Param("id") Integer id,@Param("isFriend") Boolean isFriend,
            @Param("isOpFriend") Boolean isOpFriend,@Param("isEnemy") Boolean isEnemy);
    
    public UserRelation find(@Param("id") Integer id);
    
    public List<UserRelation> query();
    
    public List<UserRelation> queryPage(@Param("start") Integer start,@Param("size") Integer size);
    
    public int queryCount();
    
}
