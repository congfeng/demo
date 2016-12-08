/**
 * 
 */
package com.cf.code.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cf.code.entity.MsgBang;

/**
 * @author congfeng
 *
 * @email congfeng@meicai.cn
 */
public interface MsgBangDao {

    public void insert(MsgBang msgBang);
    
    public void delete(@Param("id") Integer id);
    
    public MsgBang find(@Param("id") Integer id);
    
    public List<MsgBang> query(@Param("msgId") Integer msgId,@Param("userId") Integer userId);
    
    public List<MsgBang> queryPage(@Param("msgId") Integer msgId,@Param("userId") Integer userId,
            @Param("start") Integer start,@Param("size") Integer size);
    
    public int queryCount(@Param("msgId") Integer msgId,@Param("userId") Integer userId);
    
}
