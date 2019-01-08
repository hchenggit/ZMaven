package com.demo.rabbitmq.client.event;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.infinova.venus.pojo.LogEvent;
/**
 * 内部事件，不通知前端！
 * @author huangch
 * Since:JDK 7
 * Date:2018-11-26上午11:06:05
 * @Copyright2018,huangch@szinfinova.com All Rights Reserved
 */
@Service("Hc.HcEventHandler")
public class HcEventHandler extends BaseEventHandler<HcEvent>{

	@Override
	public void process(HcEvent event) throws Exception {
		/**
		 * 内部事件触发 做点什么事情
		 */
		System.out.println("------------------HcEventHandler");
		System.out.println(event.getMsg());
	}

	@Override
	public void notify(LogEvent logNotify) throws Exception {
		// TODO Auto-generated method stub
		HcEvent event = new HcEvent();
		event.setMsg(JSON.parseObject(logNotify.getMessage(),HcTest.class));
		process(event);
	}

}
