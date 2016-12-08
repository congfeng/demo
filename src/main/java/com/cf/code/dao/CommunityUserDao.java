/**
 * 
 */
package com.cf.code.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cf.code.entity.CommunityUser;

/**
 * @author congfeng
 *
 * @email congfeng@meicai.cn
 */
public interface CommunityUserDao {

    public void insert(CommunityUser communityUser);
    
    public void delete(@Param("id") Integer id);
    
    public boolean update(@Param("id") Integer id,@Param("role") Byte role);
    
    public boolean updateStatus(@Param("id") Integer id,@Param("status") Byte status);

    public CommunityUser find(@Param("id") Integer id);
    
    public List<CommunityUser> query();
    
    public List<CommunityUser> queryPage(@Param("start") Integer start,@Param("size") Integer size);
    
    public int queryCount();
    
}
