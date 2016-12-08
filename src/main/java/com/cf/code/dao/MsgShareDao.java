/**
 * 
 */
package com.cf.code.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cf.code.entity.MsgShare;

/**
 * @author congfeng
 *
 * @email congfeng@meicai.cn
 */
public interface MsgShareDao {

    public void insert(MsgShare msgShare);
    
    public void delete(@Param("id") Integer id);
    
    public MsgShare find(@Param("id") Integer id);
    
    public List<MsgShare> query();
    
    public List<MsgShare> queryPage(@Param("start") Integer start,@Param("size") Integer size);
    
    public int queryCount();
    
}
