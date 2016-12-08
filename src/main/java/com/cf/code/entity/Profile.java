/**
 * 
 */
package com.cf.code.entity;

import java.io.Serializable;

/**
 * @Version: 1.0
 * @Author: 丛峰
 * @Email: 3024992@qq.com
 */
public class Profile implements Serializable{

	public Profile(Integer userId,String clientId){
	    this.userId = userId;
	    this.clientId = clientId;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5082406578385226552L;

	private Integer userId;
 
	private String clientId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
	
}
