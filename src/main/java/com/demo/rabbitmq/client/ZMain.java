package com.demo.rabbitmq.client;

import java.util.HashMap;
/**
 * 119 -36 83
 * @author huangch
 * Since:JDK 8
 * Date:2019年1月8日下午1:04:27
 * @Copyright2018,huangch@szinfinova.com All Rights Reserved
 */
public class ZMain {

	public static void main(String[] args) throws Exception{
		 // 创建消费者，即消息接收者，并启动线程
        QueueConsumer consumer = new QueueConsumer("queue");
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        // 创建生产者，即消息发送者
        Producer producer = new Producer("queue");

        // 循环发送消息
        for (int i = 0; i < 20; i++)
        {
            HashMap message = new HashMap();
            message.put("message number", i);
            producer.sendMessage(message);
            System.out.println("Message Number " + i + " sent.");
        }
    }
	
	
}
