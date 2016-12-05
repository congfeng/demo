/**
 * 
 */
package com.cf.code.test;

import java.util.ArrayList;
import java.util.List;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.Userinfos;
import com.taobao.api.request.OpenimUsersAddRequest;
import com.taobao.api.request.OpenimUsersDeleteRequest;
import com.taobao.api.request.OpenimUsersGetRequest;
import com.taobao.api.request.OpenimUsersUpdateRequest;
import com.taobao.api.response.OpenimUsersAddResponse;
import com.taobao.api.response.OpenimUsersDeleteResponse;
import com.taobao.api.response.OpenimUsersGetResponse;
import com.taobao.api.response.OpenimUsersUpdateResponse;

/**
 * @author congfeng
 *
 * @email congfeng@meicai.cn
 */
public class ImTest {

    static String url = "http://gw.api.taobao.com/router/rest";
    static String appkey = "23555011";
    static String secret = "1c2043f46e347e21b2594e052d025cee";
    
    public static void main(String[] args) throws ApiException {
//        addUser("test1","ddddd1");
        addUser("test2","ddddd2");
//        updateUser("test1","ddddd2");
//        getUser("test2");
//        delUser("test2");
    }
    
    static void getUser(String userid) throws ApiException{
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        OpenimUsersGetRequest req = new OpenimUsersGetRequest();
        req.setUserids(userid);
        OpenimUsersGetResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }
    
    static void delUser(String userid) throws ApiException{
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        OpenimUsersDeleteRequest req = new OpenimUsersDeleteRequest();
        req.setUserids(userid);
        OpenimUsersDeleteResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }
    
    static void updateUser(String userid,String password) throws ApiException{
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        OpenimUsersUpdateRequest req = new OpenimUsersUpdateRequest();
        List<Userinfos> list = new ArrayList<Userinfos>();
        Userinfos userInfo = new Userinfos();
        list.add(userInfo);
        userInfo.setUserid(userid);
        userInfo.setPassword(password);
        req.setUserinfos(list);
        OpenimUsersUpdateResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }
    
    static void addUser(String userid,String password) throws ApiException{
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        OpenimUsersAddRequest req = new OpenimUsersAddRequest();
        List<Userinfos> list = new ArrayList<Userinfos>();
        Userinfos userInfo = new Userinfos();
        list.add(userInfo);
        userInfo.setUserid(userid);
        userInfo.setPassword(password);
        userInfo.setNick(userid+"nick");
        req.setUserinfos(list);
        OpenimUsersAddResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }
    
}
