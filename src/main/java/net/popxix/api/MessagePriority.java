package net.popxix.api;

/**
 *
 * @author Pedro González
 *
 */
public enum MessagePriority {

	NORMAL("normal"),
	HIGH("high");

	private String priority;

	private MessagePriority(String prioridad) {
		this.priority = prioridad;
	}

	public String getPriority() {
		return priority;
	}
}
