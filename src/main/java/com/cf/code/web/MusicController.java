/**
 * 
 */
package com.cf.code.web;

import java.text.DecimalFormat;
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
import org.springframework.web.multipart.MultipartFile;

import com.cf.code.common.FileUtil;
import com.cf.code.common.Pager;
import com.cf.code.core.aop.AccessVerifier;
import com.cf.code.core.exception.BusinessException;
import com.cf.code.dao.MusicCollectDao;
import com.cf.code.dao.MusicDao;
import com.cf.code.entity.Music;
import com.cf.code.service.CloudService;

/**
 * @author congfeng
 *
 */
@Controller("MusicController")
@RequestMapping("/music")
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
	
	@Resource(name = "cloudCosService")
	CloudService cloudService;
	
	@AccessVerifier
	@RequestMapping(value = {"list"}, method = { RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object list(Model model,HttpSession session,
            @RequestParam(required = true) Byte category,
            @RequestParam(required = true) Integer pageNo){
		if(pageNo == null) {
			pageNo = 1;
		}
		Pager pager = new Pager();
		pager.setPageNo(pageNo);
		int count = this.musicDaoRead.queryCount(category);
		pager.setCount(count);
		List<Music> musics = musicDaoRead.query1(category, pager.getStartIndex(), pager.getPageSize());
	    model.addAttribute(musics);
	    model.addAttribute("musics", musics); 
	    model.addAttribute("pager", pager);
        return model;
    }
	
	@AccessVerifier
	@RequestMapping(value = {"add"}, method = { RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object add(Model model,HttpSession session,
    		@RequestParam(required = true) Byte category,
            @RequestParam(required = true) String name,
            @RequestParam(required = false) String author,
            @RequestParam(required = true) Double duration,
            @RequestParam(value = "music", required = true) MultipartFile musicFile) throws Exception{
		String fileName = musicFile.getOriginalFilename();
		String filesize = FileUtil.getDataSize(musicFile.getSize());
		byte[] data = musicFile.getBytes();
		cloudService.uploadMusic(category, fileName, data);
		Music music = new Music();
		music.setCategory(category);
		music.setName(name);
		music.setAuthor(author);
		music.setFilename(fileName);
		music.setFilesize(filesize);
		String soundsize = this.soundsizeFormat(duration);
		music.setSoundsize(soundsize);
		musicDao.insert(music);
        return model;
    }
	
	@AccessVerifier
	@RequestMapping(value = {"find"}, method = { RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object find(Model model,HttpSession session,
            @RequestParam(required = true) Integer id){
		Music music = this.musicDaoRead.find(id);
	    model.addAttribute("music",music);
        return model;
    }
	
	@AccessVerifier
	@RequestMapping(value = {"delete"}, method = { RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object delete(Model model,HttpSession session,
            @RequestParam(required = true) Integer id) throws BusinessException{
		int num = this.musicCollectDaoRead.queryCountByMusic(id);
		if(num > 0){
			throw new BusinessException("已被用户收藏");
		}
		Music music = this.musicDaoRead.find(id);
		this.musicDao.delete(id);
		this.cloudService.deleteMusic(music.getCategory(), music.getFilename());
        return model;
    }
	
	@AccessVerifier
	@RequestMapping(value = {"update"}, method = { RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object update(Model model,HttpSession session,
            @RequestParam(required = true) Integer id,
            @RequestParam(required = true) String name,
            @RequestParam(required = true) String author){
		this.musicDao.update(id, name, author);
        return model;
    }
	
	@AccessVerifier
	@RequestMapping(value = {"update/soundsize"}, method = { RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object updateSoundsize(Model model,
    		@RequestParam(required = true) Integer id,
            @RequestParam(required = true) Double duration){
		Music music = this.musicDaoRead.find(id);
		String soundsize = this.soundsizeFormat(duration);
		if(!music.getSoundsize().equals(soundsize)){
			this.musicDao.updateSoundsize(id, soundsize);
		}
        return model;
    }
	
	private String soundsizeFormat(Double duration){
		String soundsize = (int)(duration/60) + ":" + new DecimalFormat("00").format((int)(duration%60));
		return soundsize;
	}
	
}
