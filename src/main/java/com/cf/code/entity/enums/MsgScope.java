/**
 * 
 */
package com.cf.code.entity.enums;

/**
 * @author congfeng
 *
 * @email congfeng@meicai.cn
 */
public enum MsgScope {
    
    PUBLIC(10,"大厅"),
    Community(11,"社区"),
    PUBLICCommunity(12,"大厅和社区"),
    Temple(13,"寺院"),
    ;
    
    private MsgScope(Integer value,String desc){
        this.value = value.byteValue();
        this.desc = desc;
    };
    
    public final Byte value;
    public final String desc;
    
}
