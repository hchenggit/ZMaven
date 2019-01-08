package com.demo.rabbitmq.client.cnblogs;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
/**
 * Producer
 * 使用场景：发布、订阅模式，发送端发送广播消息，多个接收端接收。
 * 发送消息到一个名为“logs”的exchange上，使用“fanout”方式发送，
 * 	即广播消息，不需要使用queue，发送端不需要关心谁接收。
 * @author huangch
 * Since:JDK 8
 * Date:2019年1月8日上午10:21:13
 * @Copyright2018,huangch@szinfinova.com All Rights Reserved
 */
public class A3EmitLog {
	private static final String EXCHANGE_NAME = "logs";

	  public static void main(String[] argv) throws Exception {

	    ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();
	    //广播消息
	    channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

	    String message = getMessage(argv);

	    channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
	    System.out.println(" [x] Sent '" + message + "'");

	    channel.close();
	    connection.close();
	  }
	  
	  private static String getMessage(String[] strings){
	    if (strings.length < 1)
	            return "info: Hello World!";
	    return joinStrings(strings, " ");
	  }
	  
	  private static String joinStrings(String[] strings, String delimiter) {
	    int length = strings.length;
	    if (length == 0) return "";
	    StringBuilder words = new StringBuilder(strings[0]);
	    for (int i = 1; i < length; i++) {
	        words.append(delimiter).append(strings[i]);
	    }
	    return words.toString();
	  }
}
