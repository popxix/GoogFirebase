package net.popxix.api;

import java.util.HashMap;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * Constructor de una notificación
 *
 * @author Pedro González
 *
 */
public class AndroidNotification implements Notification {

	private JsonObjectBuilder json;

	private HttpMessage message;

	public AndroidNotification() {
	  json = Json.createObjectBuilder();
	}

	public AndroidNotification notify(String title, String description){
		
		return title(title).description(description);
	}

	private AndroidNotification title(String title){
		json.add("title", title);
		return this;
	}

	private AndroidNotification description(String description){
		json.add("body", description);
		this.message.getJson().add("notification", json);
		return this;
	}

	public AndroidNotification icon(String icon){
		json.add("icon", icon);
		return this;
	}

	public AndroidNotification sound(String sound){
		json.add("sound", sound);
		return this;
	}

	public AndroidNotification group(String tag){
		json.add("tag", tag);
		return this;
	}

	/**
	 * Color del Icono en formato #rrggbb
	 *
	 * @param iconColor
	 * @return
	 */
	public AndroidNotification iconColor(String iconColor){
		json.add("color", iconColor);
		return this;
	}

	public AndroidNotification onClick(String clickAction){
		json.add("click_action", clickAction);
		return this;
	}

	@Override
	public void setMessage(HttpMessage message) {
		this.message = message;
	}

	@Override
	public MessageBuilder withAttachment(HashMap<String, JsonObject> adjunto) {			
		if(null != adjunto && !adjunto.isEmpty()){
			JsonObjectBuilder builder = Json.createObjectBuilder();
			//XXX FIX IT I Don't Like
			adjunto.entrySet().stream().forEach(elem -> builder.add(elem.getKey(), elem.getValue()));
			this.message.getJson().add("data", builder);
		}
		return new MessageBuilder(this.message);
	}
}
