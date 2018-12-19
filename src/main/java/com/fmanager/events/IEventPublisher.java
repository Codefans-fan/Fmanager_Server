package main.java.com.fmanager.events;


public interface IEventPublisher {
	
	public void publishEvent(Event<?> event);
	
}
