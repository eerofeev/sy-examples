package com.gepardec.examples.testerrorhandling;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class HelloFaultProcessor implements Processor{
	/*@Override
	public void process(Exchange exchange) throws Exception {
		
		Exception e = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
		
		SayHelloResponse response = new SayHelloResponse();
		HelloResponse helloResponse = new HelloResponse();
		helloResponse.setErrorCode(1);
		helloResponse.setErrorDescription(e.getCause().getMessage());
		response.setHelloResponse(helloResponse);
		
		exchange.getOut().setBody(response);
		
		exchange.setProperty(Exchange.ERRORHANDLER_HANDLED, false);
	      exchange.removeProperty(Exchange.EXCEPTION_CAUGHT);
	}*/
	
	@Override
	public void process(Exchange exchange) throws Exception {
		
		Exception e = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
		
		throw new HelloException(1, "Exception: " + e.getCause().getMessage());

	}
}
