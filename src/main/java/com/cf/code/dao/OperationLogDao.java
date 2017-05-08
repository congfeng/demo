/**
 * 
 */
package com.cf.code.dao;

import org.apache.ibatis.annotations.Param;

import com.cf.code.entity.OperationLog;

/**
 * @author congfeng
 *
 */
public interface OperationLogDao {
	
	public void insert(OperationLog opLog);
    
    public OperationLog findLatest(@Param("type") Integer type,@Param("status") Integer status);
    
    public void updateStatus(@Param("id") Integer id,@Param("status") Integer status);
    
}
