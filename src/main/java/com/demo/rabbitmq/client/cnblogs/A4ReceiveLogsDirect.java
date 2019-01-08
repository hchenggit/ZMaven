package com.demo.rabbitmq.client.cnblogs;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
/**
 * 接收端和场景3的区别：

	在绑定queue和exchange的时候使用了routing key，即从该exchange上只接收routing key指定的消息。
 * @author huangch
 * Since:JDK 8
 * Date:2019年1月8日上午10:49:16
 * @Copyright2018,huangch@szinfinova.com All Rights Reserved
 */
public class A4ReceiveLogsDirect {
	private static final String EXCHANGE_NAME = "direct_logs";

	  public static void main(String[] argv) throws Exception {

	    ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();

	    channel.exchangeDeclare(EXCHANGE_NAME, "direct");
	    String queueName = channel.queueDeclare().getQueue();
	    
	    if (argv.length < 1){
	      System.err.println("Usage: ReceiveLogsDirect [info] [warning] [error]");
	      System.exit(1);
	    }
	    
	    for(String severity : argv){    
	      channel.queueBind(queueName, EXCHANGE_NAME, severity);
	    }
	    
	    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

	    QueueingConsumer consumer = new QueueingConsumer(channel);
	    channel.basicConsume(queueName, true, consumer);

	    while (true) {
	      QueueingConsumer.Delivery delivery = consumer.nextDelivery();
	      String message = new String(delivery.getBody());
	      String routingKey = delivery.getEnvelope().getRoutingKey();

	      System.out.println(" [x] Received '" + routingKey + "':'" + message + "'");   
	    }
	  }
}
