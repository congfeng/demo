/**
 * 
 */
package com.cf.code.web;

import java.io.IOException;
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
import com.cf.code.dao.MusicCollectDao;
import com.cf.code.dao.MusicDao;
import com.cf.code.entity.Music;
import com.cf.code.service.OssService;

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
	
	@Resource(name = "ossService")
	OssService ossService;
	
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
		List<Music> musics = musicDaoRead.queryPage(category, pager.getStartIndex(), pager.getPageSize());
	    model.addAttribute(musics);
	    model.addAttribute("musics", musics); 
	    model.addAttribute("pager", pager);
        return model;
    }
	
	@RequestMapping(value = {"add"}, method = { RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object add(Model model,HttpSession session,
    		@RequestParam(required = true) Byte category,
            @RequestParam(required = true) String name,
            @RequestParam(required = false) String author,
            @RequestParam(value = "music", required = true) MultipartFile musicFile) throws IOException{
		String fileName = musicFile.getOriginalFilename();
		String size = FileUtil.getDataSize(musicFile.getSize());
		byte[] data = musicFile.getBytes();
		ossService.uploadMusic(category, fileName, data);
		Music music = new Music();
		music.setCategory(category);
		music.setName(name);
		music.setAuthor(author);
		music.setFilename(fileName);
		music.setSize(size);
		musicDao.insert(music);
        return model;
    }
	
	@RequestMapping(value = {"find"}, method = { RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object find(Model model,HttpSession session,
            @RequestParam(required = true) Integer id){
		Music music = this.musicDaoRead.find(id);
	    model.addAttribute("music",music);
        return model;
    }
	
	@RequestMapping(value = {"delete"}, method = { RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object delete(Model model,HttpSession session,
            @RequestParam(required = true) Integer id){
		this.musicDao.delete(id);
        return model;
    }
	
	@RequestMapping(value = {"update"}, method = { RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object update(Model model,HttpSession session,
            @RequestParam(required = true) Integer id,
            @RequestParam(required = true) String name,
            @RequestParam(required = true) String author){
		this.musicDao.update(id, name, author);
        return model;
    }
	
}
