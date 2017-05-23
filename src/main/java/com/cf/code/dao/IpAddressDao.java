/**
 * 
 */
package com.cf.code.dao;

import org.apache.ibatis.annotations.Param;

import com.cf.code.entity.IpAddress;

/**
 * @author congfeng
 *
 */
public interface IpAddressDao {

	public void insert(IpAddress ipAddress);
	
	public String findAddress(@Param("ip") String ip);
	
}
