/**
 * 
 */
package com.cf.code.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cf.code.entity.Community;

/**
 * @author congfeng
 *
 * @email congfeng@meicai.cn
 */
public interface CommunityDao {
    
    public void insert(Community community);
    
    public void delete(@Param("id") Integer id);
    
    public boolean update(@Param("id") Integer id,@Param("nick") String nick);
    
    public boolean updateStatus(@Param("id") Integer id,@Param("status") Byte status);

    public Community find(@Param("id") Integer id);
    
    public List<Community> query();
    
    public List<Community> queryPage(@Param("start") Integer start,@Param("size") Integer size);
    
    public int queryCount();
    
}
