package main.java.com.fmanager.events;

import main.java.com.fmanager.models.event.BaseEvent;

public class TestEventModel extends BaseEvent {
	
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
