/**
 * 
 */
package com.cf.code.entity.enums;

/**
 * @author congfeng
 *
 * @email congfeng@meicai.cn
 */
public enum CommonStatus {

    UserEnable(10,""),
    UserDisable(11,""),
    UserRelation1(20,""),
    UserRelation2(21,""),
    MsgEnable(30,""),
    MsgDisable(31,""),
    Community1(40,""),
    Community2(41,""),
    CommunityUser1(50,""),
    CommunityUser2(51,""),
    ;
    
    private CommonStatus(Integer value,String desc){
        this.value = value.byteValue();
        this.desc = desc;
    };
    
    public final Byte value;
    public final String desc;
    
}
