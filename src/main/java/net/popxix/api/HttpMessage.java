package net.popxix.api;

import java.util.List;
import java.util.stream.Collector;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;


/**
 * Construye el mensaje HTTP y lo retorna como un JSON
 *
 * @author Pedro González
 *
 */
public class HttpMessage {

	private  JsonObjectBuilder json;

	private <T extends Receiver> HttpMessage(List<T> receivers) {
		json  = Json.createObjectBuilder();
		//TODO Landa. was made pre java 8. FIX IT	
		if(receivers.size() > 1){			   
				json.add("registration_ids", receivers.stream().map(entidad-> entidad.getNotificationToken()).collect(inJsonString()));
		} else  if( receivers.size() == 1){
		  		json.add("to", receivers.get(0).getNotificationToken());
		}
	}

	public static <T extends Receiver> NotificationBuilder nuevo(List<T> receivers){
		return new NotificationBuilder(new HttpMessage(receivers));
	}

	public HttpMessageOption options(){
		return new HttpMessageOption(json, this);
	}

	/**
	 *
	 * @param peticion
	 * @return
	 */
	public JsonObject build() {
		return json.build();
	}


	JsonObjectBuilder getJson(){
		return json;
	}
	
	Collector<String, ?, JsonArrayBuilder> inJsonString(){
		return Collector.of(Json::createArrayBuilder, JsonArrayBuilder::add, 
	    		  (acumulator, element)->{
	    			  acumulator.add(element);
	    			  return acumulator;});
	}

}
