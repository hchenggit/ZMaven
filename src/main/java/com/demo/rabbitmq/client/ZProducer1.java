package com.demo.rabbitmq.client;

import java.util.HashMap;

public class ZProducer1 {
	public static void main(String[] args) throws Exception{
		
       Producer producer = new Producer("queue");
       int i = 0;
       while(true){i ++;
    	   Thread.sleep(5000);
    	   HashMap message = new HashMap();
           message.put("message number", i);
           producer.sendMessage(message);
           System.out.println("ZProducer1 Message Number " + i + " sent.");
       }
      
   }
}
