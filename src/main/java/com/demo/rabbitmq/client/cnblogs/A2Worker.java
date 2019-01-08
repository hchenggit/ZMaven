package com.demo.rabbitmq.client.cnblogs;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
/**
 * Consumer
 * @author huangch
 * Since:JDK 8
 * Date:2019年1月8日上午10:04:23
 * @Copyright2018,huangch@szinfinova.com All Rights Reserved
 */
public class A2Worker {
	private static final String TASK_QUEUE_NAME = "task_queue";

	  public static void main(String[] argv) throws Exception {

	    ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();
	    
	    channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
	    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
	    //保证在接收端一个消息没有处理完时不会接收另一个消息，即接收端发送了ack后才会接收下一个消息。在这种情况下发送端会尝试把消息发送给下一个not busy的接收端
	    channel.basicQos(1);
	    
	    QueueingConsumer consumer = new QueueingConsumer(channel);
	    //接收消息时使autoAck为false，即不自动会发ack，由channel.basicAck()在消息处理完成后发送消息
	    channel.basicConsume(TASK_QUEUE_NAME, false, consumer);
	    
	    while (true) {
	      QueueingConsumer.Delivery delivery = consumer.nextDelivery();
	      String message = new String(delivery.getBody());
	      
	      System.out.println(" [x] Received '" + message + "'");
	      doWork(message);
	      System.out.println(" [x] Done");

	      channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
	    }         
	  }
	  
	  private static void doWork(String task) throws InterruptedException {
	    for (char ch: task.toCharArray()) {
	      if (ch == '.') Thread.sleep(1000);
	    }
	  }
}
