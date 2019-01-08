package com.demo.rabbitmq.client.cnblogs;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
/**
 * Producer
 * 使用场景：简单的发送与接收，没有特别的处理
 * @author huangch
 * Since:JDK 8
 * Date:2019年1月8日上午9:48:32
 * @Copyright2018,huangch@szinfinova.com All Rights Reserved
 */
public class A1Send {
	private final static String QUEUE_NAME = "hello";

	  public static void main(String[] argv) throws Exception {
	                
	    ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();

	    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
	    String message = "Hello World!";
	    channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
	    System.out.println(" [x] Sent '" + message + "'");
	    
	    channel.close();
	    connection.close();
	  }
}
