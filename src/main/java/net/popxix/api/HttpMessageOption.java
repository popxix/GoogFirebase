package net.popxix.api;

import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author Pedro González
 *
 */
public class HttpMessageOption {

	private  JsonObjectBuilder json;

	private HttpMessage message;

	public HttpMessageOption(JsonObjectBuilder json, HttpMessage message) {
		this.json = json;
		this.message = message;
	}

	public HttpMessageOption mensajesColapsable(String llave){
		json.add("collapse_key", llave);
		return this;
	}

	public HttpMessageOption messagePriority(MessagePriority priority){
		json.add("priority", priority.getPriority());
		return this;
	}

	public HttpMessageOption timeToLive(int time){
		json.add("time_to_live", time);
		return this;
	}

	public HttpMessageOption paquete(String nombre){
		json.add("restricted_package_name", nombre);
		return this;
	}

	public HttpMessageOption isTestMode(boolean testMode){
		json.add("dry_run", testMode);
		return this;
	}

	public JsonObject build(){
		return this.message.build();
	}

}
