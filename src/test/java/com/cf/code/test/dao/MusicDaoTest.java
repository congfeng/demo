/**
 * 
 */
package com.cf.code.test.dao;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cf.code.dao.MusicCollectDao;
import com.cf.code.dao.MusicDao;
import com.cf.code.entity.Music;
import com.cf.code.entity.MusicCollect;
import com.cf.code.entity.enums.MusicCategory;
import com.cf.code.test.AbstractTestCase;

/**
 * @author congfeng
 *
 */
public class MusicDaoTest extends AbstractTestCase{

//	@Resource(name = "musicDao")
	MusicDao musicDao;
	
//	@Resource(name = "musicDaoRead")
	MusicDao musicDaoRead;
	
//	@Resource(name = "musicCollectDao")
	MusicCollectDao musicCollectDao;
	
//	@Resource(name = "musicCollectDaoRead")
	MusicCollectDao musicCollectDaoRead;
	
    protected void init() {
    	musicDao = context.getBean("musicDao", MusicDao.class);
    	musicDaoRead = context.getBean("musicDaoRead", MusicDao.class);
    	musicCollectDao = context.getBean("musicCollectDao", MusicCollectDao.class);
    	musicCollectDaoRead = context.getBean("musicCollectDaoRead", MusicCollectDao.class);
    }
    
    public void testInsert(){
    	Music music = new Music();
		music.setCategory(MusicCategory.FanYue.value);
		music.setName("test");
		music.setAuthor("1");
		music.setFilename("2");
		music.setSize("3");
		musicDao.insert(music);
        log.info(JSON.toJSONString(music));
    }
    
    public void testDelete(){
    	this.musicDao.delete(227);
        log.info("");
    }
    
    public void testUpdateCollects(){
    	boolean b = musicDao.updateCollects(228, 100);
        log.info(b);
    }
    
    public void testUpdate(){
    	boolean b = this.musicDao.update(228, "", "");
        log.info(b);
    }
    
    public void testQueryLatestTime(){
    	int latestTime = this.musicDaoRead.queryLatestTime(MusicCategory.FanYue.value);
        log.info(latestTime);
    }
    
    public void testQueryCountByMusic(){
    	int collects = this.musicCollectDaoRead.queryCountByMusic(43);
        log.info(collects+"");
    }
    
    public void testQueryByUser(){
    	List<MusicCollect> mcollects = this.musicCollectDaoRead.queryByUser(1);
        log.info(mcollects);
    }
    
    public void testUpdateMusicList(){
    	for(MusicCategory mc:MusicCategory.values()){
			Integer pageNo = 0;
			Integer pageSize = 5;
			List<Music> musics = null;
			do{
				musics = musicDaoRead.query1(mc.value, pageSize*pageNo, pageSize);
				log.info(mc + "-" + pageNo + "-" + JSONObject.toJSONBytes(musics));
				pageNo++;
				for(Music music:musics){
					Integer musicId = music.getId();
					int collects = musicCollectDaoRead.queryCountByMusic(musicId);
					if(music.getCollects().intValue() != collects){
						musicDao.updateCollects(musicId, collects);
					}
				}
			}while(musics.size() > 0);
		}
	}
    
}
