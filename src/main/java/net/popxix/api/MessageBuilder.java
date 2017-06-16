package net.popxix.api;

/***
 *
 * @author Pedro González
 *
 */
public class MessageBuilder {

	private HttpMessage message;

	public MessageBuilder(HttpMessage message) {
		this.message = message;
	}

	public HttpMessage get(){
		return this.message;
	}
}
