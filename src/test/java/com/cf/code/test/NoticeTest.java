/**
 * 
 */
package com.cf.code.test;

import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpState;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.cf.code.core.exception.MsgSendException;
import com.cf.code.core.net.EmailMsgSender;
import com.cf.code.core.net.EmailMsgSender.EmailSendMsgType;
import com.cf.code.core.net.EmailMsgSender.EmailTargetDataType;
import com.cf.code.core.net.HttpGetMsgSender;

/**
 * @author congfeng
 *
 * @email congfeng@meicai.cn
 */
public class NoticeTest {
    
    private static Logger log = LogManager.getLogger(NoticeTest.class);
    
    static EmailMsgSender eMsgSender = new EmailMsgSender("congfengemail@sina.com","CongFeng","在线支付预警");

    static String url = "https://mqv2.sprucetec.com/v1/jobs/?status=4&issue_status=1&start=2016-11-17+00%3A00%3A00&end=2016-12-19+00%3A00%3A00&page=&topic=pay_account_offline&channel=pay_service_offline";

    static HttpGetMsgSender msgSender = new HttpGetMsgSender(){
        protected HttpState getHttpState(){
            HttpState state = new HttpState();  
            Cookie mycookie = new Cookie(
                    "mqv2.sprucetec.com", 
                    "utoken", 
                    "admin_user_48062_web_2380f5363ae97602bf41d077c53b7aa9",   
                    "/", null, false);  
            state.addCookie(mycookie);
            return state;
        }
    };
    
    static Set<String> Msgs = new HashSet<String>();

    /**
     * 
    tms_1:1
    tms_2:2
    tms_3:3,4,5,6,7
    tms_4:8,9,10,11,12
    tms_5:13,14,15,16,17
    tms_6:18,19,20,21,22,28
    tms_7:23,24,25,26,27
    tms_8:29,30,31,57
    
    SELECT tb.driver_name,tb.driver_phone,tw.receive_customer_name,rec.*
      FROM `taskbill_waybill_receipt_detail` rec,taskbill_waybill tw,taskbill tb
     WHERE rec.taskbill_no = tb.taskbill_no 
       and rec.waybill_no = tw.waybill_no 
       and rec.`waybill_no` = '8561862300'
       
     * @param args
     * @throws MsgSendException
     */
    public static void main(String[] args) throws MsgSendException {
        Timer timer = new Timer();  
        timer.schedule(new TimerTask(){
            @Override
            public void run() {
                try {
                    String resText = msgSender.send(url, null);
                    if(resText.indexOf("\"total\":0") == -1){
                        log.warn("出现异常："+resText);
                        if(!Msgs.contains(resText)){
                            EmailTargetDataType target = new EmailTargetDataType("发现异常", "13521735685@139.com","congfeng@meicai.cn");
                            EmailSendMsgType msg = new EmailSendMsgType(resText);
                            eMsgSender.send(target, msg);
                            Msgs.add(resText);
                        }
                    }else{
                        log.info(resText);
                    }
                } catch (MsgSendException e) {
                    log.error("监控服务异常", e);
                }
            }
        }, 1000, 30000);
    }
    
}
