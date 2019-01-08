package com.demo.rabbitmq.client.event;


import org.springframework.stereotype.Service;
import com.infinova.venus.message.AbstractNotifyEvent;
/**
 * 通知前端的事件
 * @author huangch
 * Since:JDK 7
 * Date:2018-9-6上午10:08:09
 * @Copyright2018,huangch@szinfinova.com All Rights Reserved
 */
@Service
public class HcTestEvent extends AbstractNotifyEvent<HcTest>{

	@Override
	public String getEventPath() {
		// TODO Auto-generated method stub
		return "event.hc.test";
	}

}
