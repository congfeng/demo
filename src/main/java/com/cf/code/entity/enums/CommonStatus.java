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

    UserEnable(10,"用户可用"),
    UserDisable(11,"用户禁用"),
    MsgEnable(20,"消息可用"),
    MsgDisable(21,"消息禁用"),
    ;
    
    private CommonStatus(Integer value,String desc){
        this.value = value.byteValue();
        this.desc = desc;
    };
    
    public final Byte value;
    public final String desc;
    
}
