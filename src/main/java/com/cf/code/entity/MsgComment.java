/**
 * 
 */
package com.cf.code.entity;

/**
 * @author congfeng
 *
 */
public class MsgComment {

    private Integer id;
    
    private Integer msgId;
    
    private Integer userId;
    
    private Integer ct;
    
    private Integer respondUserId;
    
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMsgId() {
        return msgId;
    }

    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCt() {
        return ct;
    }

    public void setCt(Integer ct) {
        this.ct = ct;
    }

    public Integer getRespondUserId() {
        return respondUserId;
    }

    public void setRespondUserId(Integer respondUserId) {
        this.respondUserId = respondUserId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}
