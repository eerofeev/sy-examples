package at.bva.fsw_poc.main;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class StringConverterRoute extends RouteBuilder {

	/**
	 * The Camel route is configured via this method.  The from endpoint is required to be a SwitchYard service.
	 */
	public void configure() {
		// TODO Auto-generated method stub
		from("switchyard://StringConverter")
		.process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				System.out.println(exchange.getIn().getHeaders());
				
			}
		})
		.log(
				"Received message for 'StringConverter' : ${body}").
				to("switchyard://StringConverterEJB?operationName=toUpperCase");
	}

}
