/**
 * 
 */
package com.cf.code.core;

import java.util.concurrent.CountDownLatch;

/**
 * @author congfeng
 *
 * @email congfeng@meicai.cn
 */
public class Main {
    
    private static final String ADDRESS = "127.0.0.1:9876";
    
    private static final String INSTANCENAME = "cftest";
  
    private static final String TOPIC = "cf_topic";

    public static void main(String[] args) throws Exception{
        Thread producer = new Producer(ADDRESS, INSTANCENAME, TOPIC);
        producer.start();
        Thread consumer = new Consumer(ADDRESS, INSTANCENAME, TOPIC);
        consumer.start();
        try {
            if(args == null){
                CountDownLatch latch = new CountDownLatch(1);
                latch.await();
            }else{
                System.out.println("------------"+args[0]);
                int minutes = Integer.valueOf(args[0]);
                Thread.sleep(minutes*60*1000);
            }
        }finally{
            producer.interrupt();
            consumer.interrupt();
            System.exit(1);
        }
    }
    
}
