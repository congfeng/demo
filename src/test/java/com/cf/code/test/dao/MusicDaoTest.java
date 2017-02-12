/**
 * 
 */
package com.cf.code.test.dao;

import java.util.List;

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
				musics = musicDaoRead.queryPage(mc.value, pageSize*pageNo, pageSize);
				log.info(mc + "-" + pageNo + "-" + JSONObject.toJSONBytes(musics));
				pageNo++;
			}while(musics.size() > 0);
//			List<Music> musics = musicDaoRead.queryPage(mc.value);
//			ossService.uploadMusicList(mc, musics, 1);
		}
	}
    
}
