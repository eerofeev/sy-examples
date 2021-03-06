package com.gepardec.examples.switchyard_auditing;

import org.apache.camel.builder.RouteBuilder;

public class CamelServiceRoute extends RouteBuilder {

	/**
	 * The Camel route is configured via this method.  The from endpoint is required to be a SwitchYard service.
	 */
	public void configure() {
		
		
		onException(Exception.class)
		.handled(true)
		.process(new ConverterFaultProcessor());
		
		from("switchyard://ConverterProxy").log(
				"Received message for 'ConverterProxy' : ${body}").
				to("switchyard://ConverterProxyExt");
	}

}
