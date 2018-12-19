package main.java.com.fmanager.models.event;

public abstract class BaseEvent {
	
	private long timestamp;

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
}
