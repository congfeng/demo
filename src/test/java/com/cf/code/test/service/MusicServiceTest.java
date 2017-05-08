/**
 * 
 */
package com.cf.code.test.service;

import java.util.List;

import com.cf.code.dao.MusicCollectDao;
import com.cf.code.dao.MusicDao;
import com.cf.code.entity.Music;
import com.cf.code.entity.enums.MusicCategory;
import com.cf.code.entity.enums.OpType;
import com.cf.code.service.OpLogService;
import com.cf.code.test.AbstractTestCase;

/**
 * @author congfeng
 *
 */
public class MusicServiceTest extends AbstractTestCase{

	MusicDao musicDao;
	
	MusicDao musicDaoRead;
	
	MusicCollectDao musicCollectDao;
	
	MusicCollectDao musicCollectDaoRead;
	
	OpLogService opLogService;
	
    protected void init() {
    	musicDao = context.getBean("musicDao", MusicDao.class);
    	musicDaoRead = context.getBean("musicDaoRead", MusicDao.class);
    	musicCollectDao = context.getBean("musicCollectDao", MusicCollectDao.class);
    	musicCollectDaoRead = context.getBean("musicCollectDaoRead", MusicCollectDao.class);
		opLogService = context.getBean("opLogService", OpLogService.class);
    }
    
    public void testUpdateCollects(){
    	int opLatestTime = opLogService.getOpLatestTime(OpType.UpdateMusicColletNum);
    	int opId = opLogService.saveOp4Init(OpType.UpdateMusicColletNum);
    	for(MusicCategory mc:MusicCategory.values()){
    		int latestTime = this.musicCollectDaoRead.queryLatestTime(mc.value);
    		if(latestTime <= opLatestTime){
    			continue ;
    		}
    		Integer pageNo = 0;
			Integer pageSize = 20;
			List<Music> musics = null;
			do{
				musics = musicDaoRead.query1(mc.value, pageSize*pageNo, pageSize);
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
    	opLogService.opSuccess(opId);
    }
    
    public void testUpdateMusicList(){
//    	int opLatestTime = getOpLatestTime(OpType.UpdateMusicList);
//    	int opId = saveOp(OpType.UpdateMusicList);
//    	for(MusicCategory mc:MusicCategory.values()){
//			int latestTime = this.musicDaoRead.queryLatestTime(mc.value);
//			
//		}
    }
    
}
