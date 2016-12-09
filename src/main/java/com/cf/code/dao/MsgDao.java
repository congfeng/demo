/**
 * 
 */
package com.cf.code.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cf.code.entity.Msg;

/**
 * @Version: 1.0
 * @Author: 丛峰
 * @Email: 3024992@qq.com
 */
public interface MsgDao {

    public void insert(Msg msg);
    
    public void delete(@Param("id") Integer id);
    
    public boolean update(@Param("id") Integer id,@Param("scope") Byte scope);
    
    public boolean updateStatus(@Param("id") Integer id,@Param("status") Byte status);

    public Msg find(@Param("id") Integer id);
    
    public List<Msg> query(@Param("scopes") Byte[] scopes,
            @Param("userId") Integer userId,@Param("communityId") Integer communityId,
            @Param("lastId") Integer lastId,@Param("limit") Integer limit);
    
    public List<Msg> queryPage(@Param("start") Integer start,@Param("size") Integer size);
    
    public int queryCount();
    
    public void updateCounter(@Param("id") Integer id,@Param("bangNum") Integer bangNum,
            @Param("shareNum") Integer shareNum,@Param("commentNum") Integer commentNum);
    
}
