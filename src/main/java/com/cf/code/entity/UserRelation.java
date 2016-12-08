/**
 * 
 */
package com.cf.code.entity;

/**
 * @author congfeng
 *
 * @email congfeng@meicai.cn
 */
public class UserRelation {

    private Integer id;
    
    private Integer ct;
    
    private Integer ut;
    
    private Integer userId;
    
    private Integer opUserId;
    
    private Boolean isFriend;
    
    private Boolean isOpFriend;
    
    private Boolean isEnemy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOpUserId() {
        return opUserId;
    }

    public void setOpUserId(Integer opUserId) {
        this.opUserId = opUserId;
    }

    public Integer getCt() {
        return ct;
    }

    public void setCt(Integer ct) {
        this.ct = ct;
    }

    public Boolean getIsFriend() {
        return isFriend;
    }

    public void setIsFriend(Boolean isFriend) {
        this.isFriend = isFriend;
    }

    public Boolean getIsOpFriend() {
        return isOpFriend;
    }

    public void setIsOpFriend(Boolean isOpFriend) {
        this.isOpFriend = isOpFriend;
    }

    public Boolean getIsEnemy() {
        return isEnemy;
    }

    public void setIsEnemy(Boolean isEnemy) {
        this.isEnemy = isEnemy;
    }

    public Integer getUt() {
        return ut;
    }

    public void setUt(Integer ut) {
        this.ut = ut;
    }
    
}
