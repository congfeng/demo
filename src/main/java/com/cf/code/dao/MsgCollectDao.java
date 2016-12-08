/**
 * 
 */
package com.cf.code.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cf.code.entity.MsgCollect;

/**
 * @author congfeng
 *
 * @email congfeng@meicai.cn
 */
public interface MsgCollectDao {

    public void insert(MsgCollect msgCollect);
    
    public void delete(@Param("id") Integer id);
    
    public MsgCollect find(@Param("id") Integer id);
    
    public List<MsgCollect> query();
    
    public List<MsgCollect> queryPage(@Param("start") Integer start,@Param("size") Integer size);
    
    public int queryCount();
    
}
