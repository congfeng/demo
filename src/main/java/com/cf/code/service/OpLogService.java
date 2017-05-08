/**
 * 
 */
package com.cf.code.service;

import com.cf.code.entity.enums.OpType;

/**
 * @author congfeng
 *
 */
public interface OpLogService {

	public int saveOp4Init(OpType opType);
	
	public int getOpLatestTime(OpType opType);
	
	public void opSuccess(Integer id);
	
	public void opException(Integer id);
	
}
