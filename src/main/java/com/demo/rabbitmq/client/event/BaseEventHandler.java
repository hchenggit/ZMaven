package com.demo.rabbitmq.client.event;

import java.lang.reflect.ParameterizedType;

/**
 * 事件处理的基础类
 * 
 * @author yzhb
 * 
 * @param <T>
 */
public abstract class BaseEventHandler<T extends com.infinova.venus.event.Event> implements com.infinova.venus.event.EventListener<T> {

    @Override
    public String getListenerName() {
        return this.getClass().getName();
    }

    @Override
    public String getEventName() {
        return getEntityClass().getName();
    }
    
    protected Class<T> getEntityClass() {
        @SuppressWarnings("unchecked")
        Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return entityClass;
    }
}
