package com.demo.rabbitmq.client.event;

import java.io.IOException;
import java.util.HashMap;

import com.demo.rabbitmq.client.Producer;

public class ZProducer {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		Producer producer = new Producer("v2200");
	       int i = 0;
	       while(true){i ++;
	    	   Thread.sleep(5000);
	    	   HcEvent event = new HcEvent();//内部事件
		       HcTest msg = new HcTest();
		       msg.setA("v2200.a");
		       msg.setB(i+"");
		       event.setMsg(msg);
	           producer.sendMessage(event);
	           System.out.println("v2200 send Number " + i + " sent."+event.getMsg());
	       }
	}

}
