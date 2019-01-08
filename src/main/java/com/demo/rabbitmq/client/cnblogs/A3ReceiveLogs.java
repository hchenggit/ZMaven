package com.demo.rabbitmq.client.cnblogs;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
/**
 * 
 * @author huangch
 * Since:JDK 8
 * Date:2019年1月8日上午10:21:23
 * @Copyright2018,huangch@szinfinova.com All Rights Reserved
 */
public class A3ReceiveLogs {
	private static final String EXCHANGE_NAME = "logs";

	  public static void main(String[] argv) throws Exception {

	    ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();

	    channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
	    String queueName = channel.queueDeclare().getQueue();
	    channel.queueBind(queueName, EXCHANGE_NAME, "");
	    
	    System.out.println(" [*] Waiting for messages. To exit press CTRL+C " + queueName);

	    QueueingConsumer consumer = new QueueingConsumer(channel);
	    channel.basicConsume(queueName, true, consumer);

	    while (true) {
	      QueueingConsumer.Delivery delivery = consumer.nextDelivery();
	      String message = new String(delivery.getBody());

	      System.out.println(" [x] Received '" + message + "'");   
	    }
	  }
}
