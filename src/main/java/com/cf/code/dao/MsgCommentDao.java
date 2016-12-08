/**
 * 
 */
package com.cf.code.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cf.code.entity.MsgComment;

/**
 * @author congfeng
 *
 * @email congfeng@meicai.cn
 */
public interface MsgCommentDao {

    public void insert(MsgComment msgComment);
    
    public void delete(@Param("id") Integer id);
    
    public MsgComment find(@Param("id") Integer id);
    
    public List<MsgComment> query();
    
    public List<MsgComment> queryPage(@Param("start") Integer start,@Param("size") Integer size);
    
    public int queryCount();
    
}
