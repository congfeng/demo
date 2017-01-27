/**
 * 
 */
package com.cf.code.entity.enums;

/**
 * @author congfeng
 *
 */
public enum MusicCategory {
	
	FoGe(10,"佛歌"),
    FanYue(20,"梵乐"),
    JingZhou(30,"经咒"),
    ZanJi(40,"赞偈"),
    ChanYin(50,"消息禁用"),
    ;
    
    private MusicCategory(Integer value,String desc){
        this.value = value.byteValue();
        this.desc = desc;
    };
    
    public final Byte value;
    public final String desc;
}
