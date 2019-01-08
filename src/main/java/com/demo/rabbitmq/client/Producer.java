package com.demo.rabbitmq.client;

import java.io.IOException;
import java.io.Serializable;

import org.apache.commons.lang.SerializationUtils;
/**
 * 生产者（消息发送者）
 * @author huangch
 * Since:JDK 8
 * Date:2019年1月7日下午3:37:29
 * @Copyright2018,huangch@szinfinova.com All Rights Reserved
 */
public class Producer extends EndPoint{
	public Producer(String endPointName) throws IOException
    {
        super(endPointName);
    }

    public void sendMessage(Serializable object) throws IOException
    {
        channel.basicPublish("", endPointName, null, SerializationUtils.serialize(object));
    }
}
