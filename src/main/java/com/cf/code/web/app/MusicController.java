/**
 * 
 */
package com.cf.code.web.app;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cf.code.core.exception.BusinessException;
import com.cf.code.dao.MusicCollectDao;
import com.cf.code.dao.MusicDao;
import com.cf.code.entity.Music;
import com.cf.code.entity.MusicCollect;

/**
 * @author congfeng
 *
 */
@Controller("App/MusicController")
@RequestMapping("/app/music")
public class MusicController {

	private static Logger log = LogManager.getLogger(MusicController.class);
	
	@Resource(name = "musicDao")
	MusicDao musicDao;
	
	@Resource(name = "musicDaoRead")
	MusicDao musicDaoRead;
	
	@Resource(name = "musicCollectDao")
	MusicCollectDao musicCollectDao;
	
	@Resource(name = "musicCollectDaoRead")
	MusicCollectDao musicCollectDaoRead;
	
	@RequestMapping(value = {"query"}, method = { RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object query(Model model,HttpSession session,
            @RequestParam(required = true) Byte category,
            @RequestParam(required = true) Integer pageNo){
		Integer size = 20;
		Integer start = pageNo*size;
		List<Music> musics = musicDaoRead.query2(category, start, size);
	    model.addAttribute(musics);
        return model;
    }
	
	@RequestMapping(value = {"collect"}, method = { RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object collect(Model model,HttpSession session,
            @RequestParam(required = true) Integer userId,
            @RequestParam(required = true) Integer musicId) throws BusinessException{
		Music music = musicDaoRead.find(musicId);
		if(music == null){
			throw new BusinessException("音乐不存在");
		}
		MusicCollect mc = new MusicCollect();
		mc.setUserId(userId);
		mc.setMusicId(musicId);
		mc.setName(music.getName());
		mc.setAuthor(music.getAuthor());
		mc.setSize(music.getSize());
		mc.setFilename(music.getFilename());
		mc.setCategory(music.getCategory());
		musicCollectDao.insert(mc);
        return model;
    }
	
	@RequestMapping(value = {"collect/cancel"}, method = { RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object collectCancel(Model model,HttpSession session,
            @RequestParam(required = true) Integer userId,
            @RequestParam(required = true) Integer musicId){
		musicCollectDao.delete2(userId, musicId);
        return model;
    }
	
}
