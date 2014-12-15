package at.bva.fsw_poc.simulation.currencyconverter;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(name = "Converter", targetNamespace = "http://currencyconverter.simulation.fsw_poc.bva.at/")
public interface Converter {
	@WebMethod(operationName="convert")
	@WebResult(name = "converterResponse")
	public Response convert(@WebParam(name="converterRequest")Request request);

}
