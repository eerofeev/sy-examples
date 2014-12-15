package at.bva.fsw_poc.simulation.hello;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(name = "Hello", targetNamespace = "http://hello.simulation.fsw_poc.bva.at/")
public interface Hello {
	@WebMethod(operationName="sayHello")
	@WebResult(name = "HelloResponse")
	HelloResponse sayHello(@WebParam(name="HelloRequest")HelloRequest request);
}
