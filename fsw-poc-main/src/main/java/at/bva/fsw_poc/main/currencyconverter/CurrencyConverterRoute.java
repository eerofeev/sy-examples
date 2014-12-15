package at.bva.fsw_poc.main.currencyconverter;

import org.apache.camel.builder.RouteBuilder;

import at.bva.fsw_poc.main.SYEnvironment;
import at.bva.fsw_poc.main.ServiceDefenitions;

public class CurrencyConverterRoute extends RouteBuilder {

	/**
	 * The Camel route is configured via this method.  The from endpoint is required to be a SwitchYard service.
	 */
	public void configure() {
		// TODO Auto-generated method stub
		from(SYEnvironment.getCamelEndpoint(ServiceDefenitions.ComponentService.CURRENCY_CONVERTER)).log(
				"Received message for 'CurrencyConverter' : ${body}")
				.to(SYEnvironment.getCamelEndpoint(ServiceDefenitions.CompositeReference.CURRENCY_CONVERTER_EXTERNAL));
	}

}
