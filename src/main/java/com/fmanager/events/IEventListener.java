package main.java.com.fmanager.events;


import org.springframework.context.ApplicationListener;

public interface IEventListener<E extends Event<?>> extends ApplicationListener<Event<?>> {

}
