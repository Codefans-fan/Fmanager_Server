package main.java.com.fmanager.events;

import org.apache.shiro.util.Assert;
import org.springframework.context.ApplicationEvent;
import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;

import main.java.com.fmanager.models.event.BaseEvent;

public class Event<T extends BaseEvent> extends ApplicationEvent implements ResolvableTypeProvider{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final T eventData;
	
	public Event(Object source, T eventData) {
		super(source);
		Assert.notNull(eventData, "Event data must not be null");
		this.eventData = eventData;
	}
	
	
	public T getEventData() {
		return this.eventData;
	}

	@Override
	public ResolvableType  getResolvableType() {
		return ResolvableType.forClassWithGenerics(getClass(), ResolvableType.forInstance(eventData));
	}

}
