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
	
	public void insert(Music music);
    
    public void delete(@Param("id") Integer id);
	
    public boolean updateCollects(@Param("id") Integer id,@Param("collects") Integer collects);
    
    public boolean update(@Param("id") Integer id,
    		@Param("name") String name,
    		@Param("author") String author);
    
    public Music find(@Param("id") Integer id);
    
    public List<Music> query1(@Param("category") Byte category,
    		@Param("start") Integer start,@Param("size") Integer size);
    
    public List<Music> query2(@Param("category") Byte category,
    		@Param("start") Integer start,@Param("size") Integer size);
    
    public int queryCount(@Param("category") Byte category);
    
    public int queryLatestTime(@Param("category") Byte category);
    
}
