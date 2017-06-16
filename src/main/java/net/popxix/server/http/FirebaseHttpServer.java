package net.popxix.server.http;

import static org.apache.commons.lang3.StringUtils.join;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 * FirebBase Http Server
 *
 * @author Pedro González
 *
 */
public class FirebaseHttpServer {


	private HttpURLConnection httpUrl;

	private static final int CONNECTION_TIMEOUT = 10000;

	private FirebaseHttpServer(String url, String token) {
		try {
			URL conexion = new URL(url);
			httpUrl = (HttpURLConnection) conexion.openConnection();
			httpUrl.setRequestProperty("Content-Type", "application/json");
			httpUrl.setRequestProperty("Authorization", join("key=", token));
			httpUrl.setRequestMethod("POST");
			httpUrl.setDoOutput(true);
			httpUrl.setUseCaches(false);
			httpUrl.setConnectTimeout(CONNECTION_TIMEOUT);
		}catch (MalformedURLException ex){
			//logger.error("Error en la URL " + ex.getMessage());
		}catch (IOException e) {
			//logger.error("Error al realizar la conexión: " + e.getMessage());
		}
	}

	public static FirebaseHttpServer connect(String url, String token) {
		return new FirebaseHttpServer(url, token);
	}

	public JsonObject send(JsonObject json){
		try {
				if(null != json){
		            try(OutputStreamWriter oStreamWrt = new OutputStreamWriter(httpUrl.getOutputStream())){
						oStreamWrt.write(Objects.toString(json));
						oStreamWrt.flush();
		             }
				}
				if (httpUrl.getResponseCode() == HttpURLConnection.HTTP_OK){
					try(JsonReader jReader = Json.createReader(httpUrl.getInputStream())){
						return jReader.readObject();
					}
				} 
		} catch (IOException e) {
			//logger.error("Error al momento de enviar el mensaje: " +e.getMessage());
		}
			httpUrl.disconnect();
			return Json.createObjectBuilder().build();
	}
}