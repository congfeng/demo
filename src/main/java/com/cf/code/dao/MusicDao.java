/**
 * 
 */
package com.cf.code.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cf.code.entity.Music;

/**
 * @author congfeng
 *
 */
public interface MusicDao {
	
    public boolean updateCollects(@Param("id") Integer id,@Param("collects") Integer collects);
    
    public Music find(@Param("id") Integer id);
    
    public List<Music> query(@Param("category") Byte category);
    
    public List<Music> queryPage(@Param("category") Byte category,
    		@Param("start") Integer start,@Param("size") Integer size);
    
    public int queryCount(@Param("category") Byte category);
    
}
