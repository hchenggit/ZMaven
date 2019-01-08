package com.demo.rabbitmq.client.event;

import java.io.Serializable;

public class HcTest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String a;
	private String b;
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	@Override
	public String toString() {
		return "HcTest [a=" + a + ", b=" + b + "]";
	}
	
	

}
