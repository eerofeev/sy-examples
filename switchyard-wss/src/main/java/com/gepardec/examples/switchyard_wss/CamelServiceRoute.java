package com.gepardec.examples.switchyard_wss;

import org.apache.camel.builder.RouteBuilder;

public class CamelServiceRoute extends RouteBuilder {

	/**
	 * The Camel route is configured via this method.  The from endpoint is required to be a SwitchYard service.
	 */
	public void configure() {
		// TODO Auto-generated method stub
		from("switchyard://Hello2").log(
				"Received message for 'Hello2' : ${body}")
				.to("switchyard://Helloext2");
	}

}
