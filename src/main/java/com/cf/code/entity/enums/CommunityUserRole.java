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

    Creator(10,"1"),
    Manager(11,"2"),
    User(20,"2"),
    ;
    
    private CommunityUserRole(Integer value,String desc){
        this.value = value.byteValue();
        this.desc = desc;
    };
    
    public final Byte value;
    public final String desc;
    
}
