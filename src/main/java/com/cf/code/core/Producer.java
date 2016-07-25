/**
 * 
 */
package com.cf.code.core;

import java.util.UUID;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

/**
 * @author congfeng
 *
 * @email congfeng@meicai.cn
 */
public class Producer extends Thread{
    
    private static Logger log = LogManager.getLogger(Producer.class);
    
    private String topic;
    
    DefaultMQProducer producer;
    
    public Producer(String address,String instanceName,String topic){
        this.topic = topic;
        producer = new DefaultMQProducer("rmq-group-producer");
        producer.setNamesrvAddr(address);
        producer.setInstanceName(instanceName);
    }
    
    @Override
    public void run() {
        try {
            producer.start();
            log.debug("producer start.");
        } catch (MQClientException e) {
            log.error("producer start error", e);
            return ;
        }
        try {
            int i = 0;
            while(i++ >= 0){
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    break;
                }
                String msg = "congfeng"+i;
                SendResult result = producer.send(new Message(
                            this.topic,null,UUID.randomUUID().toString(),
                            msg.getBytes("UTF-8")));
                log.info("-------sendMsg-"+msg+"-------"+JSON.toJSONString(result));
            }
        } catch (Exception e) {
            log.error("-------sendMsg----error", e);
        }
    }

    @Override
    public void interrupt() {
        producer.shutdown();
        log.debug("producer shutdown.");
        super.interrupt();
    }
    
}
