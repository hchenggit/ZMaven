package com.demo.rabbitmq.client.event;

public class ZDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HcEvent event = new HcEvent();//内部事件
    	HcTest msg = new HcTest();
    	msg.setA("a");
    	event.setMsg(msg);
    	
    	//eventService.pubEvent(event);
    	//event.getEventType();
	}

}
