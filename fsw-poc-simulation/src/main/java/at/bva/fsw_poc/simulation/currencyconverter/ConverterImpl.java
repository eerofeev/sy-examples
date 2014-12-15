package at.bva.fsw_poc.simulation.currencyconverter;

import javax.jws.WebService;

import at.bva.remote_ejb.StringConverter;
import at.gepardec.jboss5.ejb_invoker.JBoss5Context;

@WebService(serviceName="Converter", endpointInterface="at.bva.fsw_poc.simulation.currencyconverter.Converter")
public class ConverterImpl implements Converter {
	
	private void test(){

			JBoss5Context  ctx = new JBoss5Context("jnp://127.0.0.1:1399", "admin", "admin");
			StringConverter converter = ctx.lookup(
					"Converter/remote-at.bva.remote_ejb.StringConverter", StringConverter.class);
			System.out.println(converter.toLowerCase("ASDFGH"));

		
	}

	@Override
	public Response convert(Request request) {
		Response response= new Response();
		if(request.getCurrencyCodeFrom().equals(Code.EUR) && request.getCurrencyCodeTo().equals(Code.USD)){
			response.setAmount(1.3);
		} else {
			response.setAmount(0);
		}
		response.setCurrencyCode(request.getCurrencyCodeTo());
		test();
		return response;
	}

}
