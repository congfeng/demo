/**
 * 
 */
package com.cf.code.core;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;

/**
 * @author congfeng
 *
 * @email congfeng@meicai.cn
 */
public class Consumer extends Thread{
    
    private static Logger log = LogManager.getLogger(Consumer.class);
    
    private String topic;
    
    DefaultMQPushConsumer consumer;
    
    public Consumer(String address,String instanceName,String topic){
        this.topic = topic;
        consumer = new DefaultMQPushConsumer("rmq-group-consumer");
        consumer.setNamesrvAddr(address);
        consumer.setInstanceName(instanceName);
    }
    
    @Override
    public void run() {
        try {
            consumer.subscribe(topic,null);
            log.debug("consumer subscribe.");
        } catch (MQClientException e) {
            log.error("consumer subscribe error",e);
            return ;
        }
        consumer.registerMessageListener(new MessageListenerConcurrently(){
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    try {
                        log.info("-------receivedMsg-------"+new String(msg.getBody(),"UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        log.error("-------receivedMsg-------error", e);
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        log.debug("consumer registerMessageListener.");
        try {
            consumer.start();
            log.debug("consumer start.");
        } catch (MQClientException e) {
            log.error("consumer start error",e);
            shutdown();
        }
    }
    
    private void shutdown(){
        consumer.unsubscribe(this.topic);
        log.debug("consumer unsubscribe.");
        consumer.shutdown();
        log.debug("consumer shutdown.");
    }

    @Override
    public void interrupt() {
        shutdown();
        super.interrupt();
    }

}
