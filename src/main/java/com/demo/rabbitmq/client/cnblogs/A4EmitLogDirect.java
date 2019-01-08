package com.demo.rabbitmq.client.cnblogs;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
/**
 * Producer
 * 场景4：Routing (按路线发送接收)
 * 使用场景：发送端按routing key发送消息，不同的接收端按不同的routing key接收消息。
 * @author huangch
 * Since:JDK 8
 * Date:2019年1月8日上午10:45:09
 * @Copyright2018,huangch@szinfinova.com All Rights Reserved
 */
public class A4EmitLogDirect {
	private static final String EXCHANGE_NAME = "direct_logs";

	  public static void main(String[] argv) throws Exception {

	    ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();

	    channel.exchangeDeclare(EXCHANGE_NAME, "direct");

	    String severity = getSeverity(argv);
	    String message = getMessage(argv);

	    channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes());
	    System.out.println(" [x] Sent '" + severity + "':'" + message + "'");

	    channel.close();
	    connection.close();
	  }
	  
	  private static String getSeverity(String[] strings){
	    if (strings.length < 1)
	            return "info";
	    return strings[0];
	  }

	  private static String getMessage(String[] strings){ 
	    if (strings.length < 2)
	            return "Hello World!";
	    return joinStrings(strings, " ", 1);
	  }
	  
	  private static String joinStrings(String[] strings, String delimiter, int startIndex) {
	    int length = strings.length;
	    if (length == 0 ) return "";
	    if (length < startIndex ) return "";
	    StringBuilder words = new StringBuilder(strings[startIndex]);
	    for (int i = startIndex + 1; i < length; i++) {
	        words.append(delimiter).append(strings[i]);
	    }
	    return words.toString();
	  }
}
