/**
 * 
 */
package com.cf.code.entity;

/**
 * @author congfeng
 *
 */
public class Community {

	private Integer id;
	
	private Integer ct;
	
	private Integer ut;
	
	private Byte status;
	
	private Integer userId;
	
	private String nick;
	
	private String description;
	
	private String portrait;
    
    private String cover;
	
	private Integer limitNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCt() {
        return ct;
    }

    public void setCt(Integer ct) {
        this.ct = ct;
    }

    public Integer getUt() {
        return ut;
    }

    public void setUt(Integer ut) {
        this.ut = ut;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Integer getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(Integer limitNum) {
        this.limitNum = limitNum;
    }

}
