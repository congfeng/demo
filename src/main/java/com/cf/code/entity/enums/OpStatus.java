/**
 * 
 */
package com.cf.code.entity.enums;

/**
 * @author congfeng
 *
 */
public enum OpStatus {

    Success(10,"操作成功"),
    Failure(20,"操作失败"),
    Exception(30,"操作异常"),
    ;
    
    private OpStatus(Integer value,String desc){
        this.value = value;
        this.desc = desc;
    };
    
    public final Integer value;
    public final String desc;
    
}
