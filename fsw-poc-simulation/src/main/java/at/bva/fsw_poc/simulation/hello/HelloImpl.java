package at.bva.fsw_poc.simulation.hello;

import javax.annotation.security.RolesAllowed;
import javax.jws.WebService;

import org.jboss.ws.api.annotation.EndpointConfig;

@WebService(
		serviceName="Hello",
		endpointInterface="at.bva.fsw_poc.simulation.hello.Hello",
		wsdlLocation = "META-INF/hello.wsdl")
@EndpointConfig(configFile = "WEB-INF/jaxws-endpoint-config.xml", configName = "Custom WS-Security Endpoint")
//@InInterceptors(interceptors = { "org.jboss.wsf.stack.cxf.security.authentication.SubjectCreatingPolicyInterceptor" })
public class HelloImpl implements Hello {

	@Override
	@RolesAllowed("friend")
	public HelloResponse sayHello(HelloRequest request) {
		HelloResponse response = new HelloResponse();
		response.setValue("Hello, " + request.getValue());
		return response;
	}

}
