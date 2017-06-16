package net.popxix.api;

import java.util.HashMap;

import javax.json.JsonObject;

/**
 *
 * @author Pedro González
 *
 */
public interface Notification {

	void setMessage(HttpMessage mensaje);

	MessageBuilder withAttachment(HashMap<String, JsonObject> attachment );
}
