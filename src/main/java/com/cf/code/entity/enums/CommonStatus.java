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

    UserEnable(10,"1"),
    UserDisable(11,"2"),
    UserRelation1(20,"2"),
    UserRelation2(21,"2"),
    Msg1(30,"2"),
    Msg2(31,"2"),
    Community1(40,"2"),
    Community2(40,"2"),
    ;
    
    private CommonStatus(Integer value,String desc){
        this.value = value.byteValue();
        this.desc = desc;
    };
    
    public final Byte value;
    public final String desc;
    
}
