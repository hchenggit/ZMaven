package com.demo.rabbitmq.client.event;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.SerializationUtils;

import com.infinova.venus.message.AbstractEvent;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class ZQueueConsumer {
	public static void main(String[] args) throws IOException, ShutdownSignalException, ConsumerCancelledException, InterruptedException {
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();
	
	    channel.queueDeclare("v2200", false, false, false, null);
	    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
	    
	    QueueingConsumer consumer = new QueueingConsumer(channel);
	    channel.basicConsume("v2200", true, consumer);
	    
	    while (true) {
	      QueueingConsumer.Delivery delivery = consumer.nextDelivery();
	      System.out.println(delivery.getEnvelope().getRoutingKey());
	      Object object = SerializationUtils.deserialize(delivery.getBody());
	      if( object instanceof AbstractEvent ){
	    	  AbstractEvent event = (HcEvent) object;
	    	  System.out.println(" [x] v2200 '" + event.getEventPath() + "'"+event.getMsg());
	      }else if( object instanceof Map ){
	    	  System.out.println(" [x] other '" + object + "'");
	      }
	    }
	}
}
