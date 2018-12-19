package main.java.com.fmanager.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import main.java.com.fmanager.events.Event;
import main.java.com.fmanager.events.IEventPublisher;

@Service
public class EventPublisherServiceImpl implements IEventPublisher {

	private Logger logger = LoggerFactory.getLogger(EventPublisherServiceImpl.class);
	
	@Autowired
    private ApplicationEventPublisher applicationEventPublisher;
	
	@Override
	public void publishEvent(Event<?> event) {
		logger.debug("publish event:"+ event.getEventData().getClass());
		applicationEventPublisher.publishEvent(event);
	}

}
