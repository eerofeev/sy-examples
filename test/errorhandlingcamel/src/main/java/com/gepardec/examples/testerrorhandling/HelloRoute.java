package com.gepardec.examples.testerrorhandling;

import org.apache.camel.builder.RouteBuilder;

public class HelloRoute extends RouteBuilder {

	/**
	 * The Camel route is configured via this method.  The from endpoint is required to be a SwitchYard service.
	 */
	public void configure() {
		
		onException(Exception.class)
		.handled(true)
		.process(new HelloFaultProcessor());
		
		
		from("switchyard://Hello")
				.to("switchyard://HelloImpl");
	}

}
