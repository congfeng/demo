/**
 * 
 */
package com.cf.code.web.app;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @RequestMapping(value = {"add"}, method = { RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object add(@RequestParam(required = false)Profile profile
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
        communityId = (Integer) ObjectUtils.defaultIfNull(communityId, 0);
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
    @RequestMapping(value = {"list"}, method = { RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object list(Model model
            ,@RequestParam(required = true) String scope
            ,@RequestParam(required = false) Integer userId
            ,@RequestParam(required = false) Integer communityId
            ,@RequestParam(required = false) Integer lastId
            ,@RequestParam(required = false) Integer limit) throws BusinessException{
        MsgScope msgScope = null;
        try {
            msgScope = MsgScope.valueOf(scope);
        } catch (Exception e) {
            throw new BusinessException("scope非法");
        }
        communityId = (Integer) ObjectUtils.defaultIfNull(communityId, 0);
        limit = (Integer) ObjectUtils.defaultIfNull(limit, 20);
        Byte[] scopes = new Byte[]{msgScope.value};
        List<Msg> msgs = msgDaoRead.query(scopes, userId, communityId, lastId, limit);
        for(Msg msg:msgs){
            lastId = msg.getId();
            List<MsgComment> msgComments = msgCommentDaoRead.queryByMsgId(lastId);
            msg.setMsgComments(msgComments);
        }
        model.addAttribute("msgs", msgs);
        model.addAttribute("lastId", lastId);
        return model;
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
        msgDao.updateCounter(msgId,1, null, null);
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
        msgDao.updateCounter(msgId,null, 1, null);
        return true;
    }
    
    @AccessVerifier
    @RequestMapping(value = {"comment/add"}, method = { RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object commentAdd(@RequestParam(required = false)Profile profile
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
        msgDao.updateCounter(msgId,null, null, 1);
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
        msgDao.updateCounter(msgComment.getMsgId(),null, null, -1);
        return true;
    }
    
    @AccessVerifier
    @RequestMapping(value = {"collect/add"}, method = { RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object collectAdd(@RequestParam(required = false)Profile profile,
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
    
    @RequestMapping(value = {"test"}, method = { RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object test(Model model
            ,@RequestParam(required = false) Integer sizeMsg
            ,@RequestParam(required = false) Integer sizeComment
            ,@RequestParam(required = false) Integer wordCountMsg
            ,@RequestParam(required = false) Integer wordCountComment){
        List<Msg> msgs = new ArrayList<Msg>();
        for(int i=0;i<sizeMsg;i++){
            Msg msg = new Msg();
            msg.setBangNum(20);
            msg.setCommentNum(20);
            msg.setShareNum(20);
            msg.setCt((int)(System.currentTimeMillis()/1000));
            msg.setUt((int)(System.currentTimeMillis()/1000));
            msg.setScope((byte)1);
            msg.setStatus((byte)1);
            msg.setUserId(100000);
            msg.setCommunityId(100010000);
            msg.setId(10001000);
            msg.setImage1(UUID.randomUUID().toString());
            msg.setImage2(UUID.randomUUID().toString());
            msg.setImage3(UUID.randomUUID().toString());
            msg.setImage4(UUID.randomUUID().toString());
            msg.setImage5(UUID.randomUUID().toString());
            msg.setImage6(UUID.randomUUID().toString());
            StringBuilder msgContent = new StringBuilder();
            for(int ii=0;ii<wordCountMsg;ii++){
                msgContent.append("字");
            }
            msg.setContent(msgContent.toString());
            List<MsgComment> msgComments = new ArrayList<MsgComment>();
            for(int j=0;j<sizeComment;j++){
                MsgComment msgComment = new MsgComment();
                StringBuilder msgCommentContent = new StringBuilder();
                for(int jj=0;jj<wordCountComment;jj++){
                    msgCommentContent.append("评");
                }
                msgComment.setCt((int)(System.currentTimeMillis()/1000));
                msgComment.setId(100010000);
                msgComment.setMsgId(10001000);
                msgComment.setUserId(100000);
                msgComment.setOpUserId(100000);
                msgComment.setContent(msgCommentContent.toString());
                msgComments.add(msgComment);
            }
            msg.setMsgComments(msgComments);
            msgs.add(msg);
        }
        model.addAttribute("msgs", msgs);
        model.addAttribute("sizeMsg", sizeMsg);
        model.addAttribute("sizeComment", sizeComment);
        model.addAttribute("wordCountMsg", wordCountMsg);
        model.addAttribute("wordCountComment", wordCountComment);
        return model;
    }
}
