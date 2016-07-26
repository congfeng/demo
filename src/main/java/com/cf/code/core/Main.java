/**
 * 
 */
package com.cf.code.core;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author congfeng
 *
 * @email congfeng@meicai.cn
 */
public class Main {
    
    private static Logger log = LogManager.getLogger(Main.class);
    
    private static final String ADDRESS = "127.0.0.1:9876";
    
    private static final String INSTANCENAME = "cftest";
  
    private static final String TOPIC = "cf_topic";

    public static void main(String[] args){
        Thread producer = new Producer(ADDRESS, INSTANCENAME, TOPIC);
        producer.start();
        Thread consumer = new Consumer(ADDRESS, INSTANCENAME, TOPIC);
        consumer.start();
        try {
            if(args == null||args.length == 0){
//                CountDownLatch latch = new CountDownLatch(1);
//                latch.await();
                Thread.sleep(10000);
                log.info("args----------------100000");
            }else{
                int minutes = Integer.valueOf(args[0]);
                Thread.sleep(minutes*60*1000);
                log.info("args----------------"+minutes);
            }
        }catch(Throwable t){
            log.error("error", t);
        }finally{
            producer.interrupt();
            consumer.interrupt();
            System.exit(1);
        }
    }
    
}
