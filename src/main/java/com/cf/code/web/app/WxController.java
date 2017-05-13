/**
 * 
 */
package com.cf.code.web.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cf.code.common.Constant;
import com.cf.code.core.exception.BusinessException;
import com.cf.code.core.net.HttpClientUtil;
import com.cf.code.dao.MusicCollectDao;
import com.cf.code.dao.MusicDao;
import com.cf.code.dao.UserDao;
import com.cf.code.entity.Music;
import com.cf.code.entity.MusicCollect;
import com.cf.code.entity.User;
import com.cf.code.entity.enums.MusicCategory;

/**
 * @author congfeng
 */
@Controller("App/WxController")
@RequestMapping("/app/wx")
public class WxController {
	
	private static Logger log = LogManager.getLogger(WxController.class);
	
	private static final String AuthorizationURL = "https://api.weixin.qq.com/sns/jscode2session";
	
	@Resource(name = "userDao")
	UserDao userDao;
	
	@Resource(name = "userDaoRead")
	UserDao userDaoRead;
	
	@Resource(name = "musicDaoRead")
	MusicDao musicDaoRead;
	
	@Resource(name = "musicCollectDao")
	MusicCollectDao musicCollectDao;
	
	@Resource(name = "musicCollectDaoRead")
	MusicCollectDao musicCollectDaoRead;
	
	@Value("#{sys.AppId}")
	String AppID;
	
	@Value("#{sys.AppSecret}")
	String AppSecret;
	
	@RequestMapping(value = {"login"}, method = { RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object login(Model model,HttpSession session,
            @RequestParam(required = true) String code,
            @RequestParam(required = false) String rawData,
            @RequestParam(required = false) String signature,
            @RequestParam(required = false) String encryptedData,
            @RequestParam(required = false) String iv,
            @RequestParam(required = false) String musicIds) throws Exception{
		Map<String,String> params = new HashMap<String,String>();
		params.put("grant_type", "authorization_code");
		params.put("appid", AppID);
		params.put("secret", AppSecret);
		params.put("js_code", code);
		String resText = HttpClientUtil.httpGetRequest(AuthorizationURL,params);
		String session_key = StringUtils.substringBetween(resText, "\"session_key\":\"","\"");
		String openid = StringUtils.substringBetween(resText, "\"openid\":\"","\"");
		if(StringUtils.isEmpty(resText)||StringUtils.isEmpty(session_key)||StringUtils.isEmpty(openid)){
			throw new BusinessException("获取sessionkey失败"+resText);
		}
		//TODO 暂时使用id=clientId、openid=unionId
		User user = userDaoRead.findByUnionId(openid);
		if(user == null){
			user = new User();
			user.setUnionId(openid);
			this.userDao.insertWx(user);
		}
		Integer userId = user.getId();
		List<MusicCollect> mcollects = this.musicCollectDaoRead.queryByUser(user.getId());
		List<MusicCollect> mcollectsNew = new ArrayList<MusicCollect>();
		JSONObject mCategorys = JSON.parseObject(musicIds);
		for(MusicCategory mc:MusicCategory.values()){
			JSONArray mids = mCategorys.getJSONArray(mc.value+"");
			Iterator<Object> iter = mids.iterator();
			while(iter.hasNext()){
				Integer musicId = (Integer)iter.next();
				MusicCollect mcollect = new MusicCollect();
				mcollect.setUserId(userId);
				mcollect.setMusicId(musicId);
				mcollect.setCategory(mc.value);
				if(mcollects.contains(mcollect)){
					continue ;
				}
				Music music = musicDaoRead.find(musicId);
				mcollect.setName(music.getName());
				mcollect.setAuthor(music.getAuthor());
				mcollect.setSize(music.getSize());
				mcollect.setFilename(music.getFilename());
				mcollects.add(mcollect);
				mcollectsNew.add(mcollect);
			}
		}
		if(mcollectsNew.size() > 0){
			musicCollectDao.inserts(mcollectsNew);
		}
	    model.addAttribute(Constant.ClientId, user.getId());
	    model.addAttribute("mcollects", mcollects);
        return model;
    }
	
}
