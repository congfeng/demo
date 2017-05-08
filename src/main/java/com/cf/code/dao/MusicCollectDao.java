/**
 * 
 */
package com.cf.code.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cf.code.entity.MusicCollect;

/**
 * @author congfeng
 *
 */
public interface MusicCollectDao {

    public void insert(MusicCollect mc);
    
    public void inserts(@Param("mcList") List<MusicCollect> mcList);
    
    public void delete(@Param("id") Integer id);
    
    public void delete2(@Param("userId") Integer userId,@Param("musicId") Integer musicId);
    
    public List<MusicCollect> queryByUser(@Param("userId") Integer userId);
    
    public int queryCountByMusic(@Param("musicId") Integer musicId);
    
    public int queryLatestTime(@Param("category") Byte category);
    
}
