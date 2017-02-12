/**
 * 
 */
package com.cf.code.job;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cf.code.dao.MusicCollectDao;
import com.cf.code.dao.MusicDao;
import com.cf.code.entity.Music;
import com.cf.code.entity.enums.MusicCategory;
import com.cf.code.service.OssService;

/**
 * @author congfeng
 *
 */
public class MusicJob {

	private static Logger log = LogManager.getLogger(MusicJob.class);
	
	@Resource(name = "musicDao")
	MusicDao musicDao;
	
	@Resource(name = "musicDaoRead")
	MusicDao musicDaoRead;
	
	@Resource(name = "musicCollectDaoRead")
	MusicCollectDao musicCollectDaoRead;
	
	@Resource(name = "ossService")
	OssService ossService;
	
	public void updateCollects(){
		for(MusicCategory mc:MusicCategory.values()){
			List<Music> musics = musicDaoRead.query(mc.value);
			for(Music music:musics){
				Integer musicId = music.getId();
				int collects = musicCollectDaoRead.queryCountByMusic(musicId);
				musicDao.updateCollects(musicId, collects);
			}
		}
	}
	
	public void updateMusicList(){
		for(MusicCategory mc:MusicCategory.values()){
			Integer pageNo = 0;
			Integer pageSize = 20;
			List<Music> musics = null;
			do{
				musics = musicDaoRead.queryPage(mc.value, pageSize*pageNo, pageSize);
				ossService.uploadMusicList(mc, musics, pageNo++);
			}while(musics.size() > 0);
		}
	}
	
}