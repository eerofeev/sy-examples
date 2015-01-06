package com.gepardec.examples.switchyard_auditing;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.gepardec.examples.switchyard_auditing.generated.converter.Code;
import com.gepardec.examples.switchyard_auditing.generated.converter.ConvertResponse;
import com.gepardec.examples.switchyard_auditing.generated.converter.Response;

public class ConverterFaultProcessor implements Processor{
	@Override
	public void process(Exchange exchange) throws Exception {
		
		Exception e = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
		
		ConvertResponse response = new ConvertResponse();
		Response r = new Response();
		System.out.println("ERROR " + e.getClass().getName() + " -- " + e.getMessage() + " -- " + e.getCause().getMessage());
		r.setAmount(-1);
		r.setCurrencyCode(Code.CAD);
		response.setConverterResponse(r);
		
		exchange.getOut().setBody(response);
		
		exchange.setProperty(Exchange.ERRORHANDLER_HANDLED, false);
	      exchange.removeProperty(Exchange.EXCEPTION_CAUGHT);
	}
}
