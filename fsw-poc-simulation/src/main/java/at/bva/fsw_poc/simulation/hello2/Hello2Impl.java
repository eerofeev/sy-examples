package at.bva.fsw_poc.simulation.hello2;

import javax.jws.WebService;

@WebService(
		serviceName="Hello2",
		endpointInterface="at.bva.fsw_poc.simulation.hello2.Hello2")
//@InInterceptors(interceptors = { "org.jboss.wsf.stack.cxf.security.authentication.SubjectCreatingPolicyInterceptor" })
public class Hello2Impl implements Hello2 {

	@Override
	public Hello2Response sayHello2(Hello2Request request) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Hello2Response response = new Hello2Response();
		response.setValue("Hello, " + request.getValue());
		return response;
	}

}
