/**
 * 
 */
package com.cf.code.web.app;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cf.code.core.aop.AccessVerifier;
import com.cf.code.core.exception.BusinessException;
import com.cf.code.dao.MsgBangDao;
import com.cf.code.dao.MsgCollectDao;
import com.cf.code.dao.MsgCommentDao;
import com.cf.code.dao.MsgDao;
import com.cf.code.dao.MsgShareDao;
import com.cf.code.entity.Msg;
import com.cf.code.entity.MsgBang;
import com.cf.code.entity.MsgCollect;
import com.cf.code.entity.MsgComment;
import com.cf.code.entity.MsgShare;
import com.cf.code.entity.Profile;
import com.cf.code.entity.enums.CommonStatus;
import com.cf.code.entity.enums.MsgScope;
import com.cf.code.service.MsgService;

/**
 * @author congfeng
 *
 * @email congfeng@meicai.cn
 */
@Controller
@RequestMapping("/app/msg")
public class MsgController {

    @Resource(name = "msgService")
    MsgService msgService;
    
    @Resource(name = "msgDao")
    MsgDao msgDao;
    
    @Resource(name = "msgDaoRead")
    MsgDao msgDaoRead;
    
    @Resource(name = "msgBangDao")
    MsgBangDao msgBangDao;
    
    @Resource(name = "msgBangDaoRead")
    MsgBangDao msgBangDaoRead;
    
    @Resource(name = "msgCollectDao")
    MsgCollectDao msgCollectDao;
    
    @Resource(name = "msgCollectDaoRead")
    MsgCollectDao msgCollectDaoRead;
    
    @Resource(name = "msgCommentDao")
    MsgCommentDao msgCommentDao;
    
    @Resource(name = "msgCommentDaoRead")
    MsgCommentDao msgCommentDaoRead;
    
    @Resource(name = "msgShareDao")
    MsgShareDao msgShareDao;
    
    @Resource(name = "msgShareDaoRead")
    MsgShareDao msgShareDaoRead;
    
    @AccessVerifier
    @RequestMapping(value = {"create"}, method = { RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object create(@RequestParam(required = false)Profile profile
            ,@RequestParam(required = true) String scope
            ,@RequestParam(required = false) Integer communityId
            ,@RequestParam(required = false) String content
            ,@RequestParam(required = false) MultipartFile image1
            ,@RequestParam(required = false) MultipartFile image2
            ,@RequestParam(required = false) MultipartFile image3
            ,@RequestParam(required = false) MultipartFile image4
            ,@RequestParam(required = false) MultipartFile image5
            ,@RequestParam(required = false) MultipartFile image6) throws BusinessException{
        if(StringUtils.isEmpty(content)&&image1==null){
            throw new BusinessException("图文信息不能为空");
        }
        MsgScope msgScope = null;
        try {
            msgScope = MsgScope.valueOf(scope);
        } catch (Exception e) {
            throw new BusinessException("scope非法");
        }
        if(communityId == null){
            communityId = 0;
        }
        Msg msg = new Msg();
        msg.setStatus(CommonStatus.MsgEnable.value);
        msg.setUserId(profile.getUserId());
        msg.setCommunityId(communityId);
        msg.setScope(msgScope.value);
        msg.setBangNum(0);
        msg.setShareNum(0);
        msg.setCommentNum(0);
        msg.setContent(content);
        msg.setImage1("image1");
        msg.setImage2("image2");
        msg.setImage3("image3");
        msg.setImage4("image4");
        msg.setImage5("image5");
        msg.setImage6("image6");
        msgDao.insert(msg);
        return msg;
    }
    
    @AccessVerifier
    @RequestMapping(value = {"delete"}, method = { RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object delete(@RequestParam(required = false)Profile profile
            ,@RequestParam(required = true) Integer msgId) throws BusinessException{
        Msg msg = msgService.load(msgId);
        if(!msg.getUserId().equals(profile.getUserId())){
            throw new BusinessException("不能删除别人的图文信息");
        }
        msgDao.delete(msgId);
        return true;
    }
    
    @AccessVerifier
    @RequestMapping(value = {"query"}, method = { RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object query(@RequestParam(required = false)Profile profile
            ,@RequestParam(required = true) String newpassword){
        return true;
    }
    
    @AccessVerifier
    @RequestMapping(value = {"find"}, method = { RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object find(@RequestParam(required = false)Profile profile
            ,@RequestParam(required = true) Integer msgId) throws BusinessException{
        Msg msg = msgService.load(msgId);
        return msg;
    }
    
    @AccessVerifier
    @RequestMapping(value = {"bang"}, method = { RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object bang(@RequestParam(required = false)Profile profile
            ,@RequestParam(required = true) Integer msgId) throws BusinessException{
        Msg msg = msgService.load(msgId);
        int count = msgBangDaoRead.queryCount(msgId,profile.getUserId());
        if(count > 0){
            throw new BusinessException("已点过赞了");
        }
        MsgBang msgBang = new MsgBang();
        msgBang.setMsgId(msgId);
        msgBang.setUserId(profile.getUserId());
        msgBangDao.insert(msgBang);
        return true;
    }
    
    @AccessVerifier
    @RequestMapping(value = {"share"}, method = { RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object share(@RequestParam(required = false)Profile profile
            ,@RequestParam(required = true) Integer msgId) throws BusinessException{
        Msg msg = msgService.load(msgId);
        MsgShare msgShare = new MsgShare();
        msgShare.setMsgId(msgId);
        msgShare.setUserId(profile.getUserId());
        msgShareDao.insert(msgShare);
        return true;
    }
    
    @AccessVerifier
    @RequestMapping(value = {"comment/create"}, method = { RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object commentCreate(@RequestParam(required = false)Profile profile
            ,@RequestParam(required = true) Integer msgId
            ,@RequestParam(required = true) String content
            ,@RequestParam(required = false) Integer opUserId) throws BusinessException{
        Msg msg = msgService.load(msgId);
        MsgComment msgComment = new MsgComment();
        msgComment.setMsgId(msgId);
        msgComment.setUserId(profile.getUserId());
        msgComment.setContent(content);
        msgComment.setOpUserId(opUserId);
        msgCommentDao.insert(msgComment);
        return msgComment;
    }
    
    @AccessVerifier
    @RequestMapping(value = {"comment/delete"}, method = { RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object commentDelete(@RequestParam(required = false)Profile profile
            ,@RequestParam(required = true) Integer commentId) throws BusinessException{
        MsgComment msgComment = msgCommentDaoRead.find(commentId);
        if(msgComment == null){
            throw new BusinessException("评论不存在");
        }
        if(!msgComment.getUserId().equals(profile.getUserId())){
            throw new BusinessException("不能删除别人的评论");
        }
        msgCommentDao.delete(commentId);
        return true;
    }
    
    @AccessVerifier
    @RequestMapping(value = {"collect/create"}, method = { RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object collectCreate(@RequestParam(required = false)Profile profile,
            @RequestParam(required = true) Integer msgId) throws BusinessException{
        Msg msg = msgService.load(msgId);
        MsgCollect msgCollect = new MsgCollect();
        msgCollect.setMsgId(msgId);
        msgCollect.setUserId(profile.getUserId());
        msgCollectDao.insert(msgCollect);
        return msgCollect;
    }
    
    @AccessVerifier
    @RequestMapping(value = {"collect/delete"}, method = { RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object collectDelete(@RequestParam(required = false)Profile profile,
            @RequestParam(required = true) Integer collectId) throws BusinessException{
        MsgCollect msgCollect = msgCollectDaoRead.find(collectId);
        if(msgCollect == null){
            throw new BusinessException("收藏不存在");
        }
        if(!msgCollect.getUserId().equals(profile.getUserId())){
            throw new BusinessException("不能删除别人的收藏");
        }
        msgCollectDao.delete(collectId);
        return true;
    }
    
}
