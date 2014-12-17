package at.bva.fsw_poc.simulation.hello2;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(name = "Hello2", targetNamespace = "http://hello2.simulation.fsw_poc.bva.at/")
public interface Hello2 {
	@WebMethod(operationName="sayHello2")
	@WebResult(name = "Hello2Response")
	Hello2Response sayHello2(@WebParam(name="Hello2Request")Hello2Request request);
}
