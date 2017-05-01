/**
 * 
 */
package com.cf.code.service;

import java.util.List;

import com.cf.code.entity.Music;
import com.cf.code.entity.enums.MusicCategory;

/**
 * @author congfeng
 *
 */
public interface OssService {

	public void uploadMusicList(MusicCategory mc,List<Music> musics,Integer pageNo);
	
	public void uploadMusic(Byte category,String fileName,byte[] data);
	
}
