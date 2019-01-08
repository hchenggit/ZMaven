package com.demo.rabbitmq.client.event;

import java.io.Serializable;


public class HcEvent extends com.infinova.venus.message.AbstractEvent<HcTest> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getEventPath() {
		// TODO Auto-generated method stub
		return "event.hc";
	}

	

}
