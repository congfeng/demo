/**
 * 
 */
package com.cf.code.entity.enums;

/**
 * @author congfeng
 *
 * @email congfeng@meicai.cn
 */
public enum CommunityUserRole {

    Creator(10,"创建者"),
    Manager(11,"管理员"),
    User(20,"用户"),
    ;
    
    private CommunityUserRole(Integer value,String desc){
        this.value = value.byteValue();
        this.desc = desc;
    };
    
    public final Byte value;
    public final String desc;
    
}
