package net.popxix.api;


/**
 *
 * @author Pedro González
 *
 */
public class NotificationBuilder {

	private HttpMessage message;

	public NotificationBuilder(HttpMessage message) {
		this.message = message;
	}

	public <T extends Notification> T withNotification( Class<T> clazz){

		T notification = null;
		try {
			notification = clazz.newInstance();
			notification.setMessage(message);
		} catch (InstantiationException | IllegalAccessException e) {
			//TODO Add A logger
			e.printStackTrace();
		}
		return notification;
	}
}
