/**
 * 
 */
package com.cf.code.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cf.code.entity.User;

/**
 * @Version: 1.0
 * @Author: 丛峰
 * @Email: 3024992@qq.com
 */
public interface UserDao {

    public void insert(User user);
    
    public void delete(@Param("id") Integer id);
    
    public boolean update(@Param("id") Integer id,@Param("password") String password,@Param("nick") String nick
            ,@Param("portrait") String portrait,@Param("cover") String cover,@Param("description") String description);
    
    public boolean updateStatus(@Param("id") Integer id,@Param("status") Byte status);
    
    public boolean updateClientId(@Param("id") Integer id,@Param("clientId") String clientId);

    public User find(@Param("id") Integer id);
    
    public List<User> query();
    
    public List<User> queryPage(@Param("start") Integer start,@Param("size") Integer size);
    
    public int queryCount();
    
    public User findByPhone(@Param("phone") String phone);
    
    public User findByClientId(@Param("id") Integer id,@Param("clientId") String clientId);
    
}
