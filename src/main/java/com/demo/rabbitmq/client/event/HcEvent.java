package com.demo.rabbitmq.client.event;


public class HcEvent extends com.infinova.venus.message.AbstractEvent<HcTest>{

	@Override
	public String getEventPath() {
		// TODO Auto-generated method stub
		return "event.hc";
	}

	

}
