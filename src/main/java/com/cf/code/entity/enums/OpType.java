/**
 * 
 */
package com.cf.code.entity.enums;

/**
 * @author congfeng
 *
 */
public enum OpType {

	UpdateMusic(10,"修改音乐（增删改）"),
	ColletMusic(20,"收藏音乐（收藏、取消）"),
    UpdateMusicList(30,"更新音乐列表"),
    UpdateMusicColletNum(40,"更新音乐收藏数量"),
    ;
    
    private OpType(Integer value,String desc){
        this.value = value;
        this.desc = desc;
    };
    
    public final Integer value;
    public final String desc;
    
}
