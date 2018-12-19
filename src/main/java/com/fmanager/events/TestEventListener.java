package main.java.com.fmanager.events;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class TestEventListener {
	
	@Async
	@EventListener
	public void onApplicationEvent(Event<TestEventModel> event) {
		System.out.println("time :"+event.getTimestamp());
	}
	
	
}
