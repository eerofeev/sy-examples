package com.gepardec.examples.switchyard_wss;

import static org.switchyard.policy.SecurityPolicy.AUTHORIZATION;
import static org.switchyard.policy.SecurityPolicy.CLIENT_AUTHENTICATION;
import static org.switchyard.policy.SecurityPolicy.CONFIDENTIALITY;

import org.switchyard.annotations.Requires;
import org.switchyard.component.bean.Service;

import com.gepardec.examples.switchyard_wss.generated.hello.HelloResponse;
import com.gepardec.examples.switchyard_wss.generated.hello.SayHello;
import com.gepardec.examples.switchyard_wss.generated.hello.SayHelloResponse;

@Service(Hello_Proxy.class)
@Requires(security = {CONFIDENTIALITY, CLIENT_AUTHENTICATION, AUTHORIZATION})
public class HelloBean implements Hello_Proxy {

	@Override
	public SayHelloResponse sayHello(SayHello sayHello) {
		HelloResponse hr = new HelloResponse();
		hr.setValue("Hello, " + sayHello.getHelloRequest().getValue());
		SayHelloResponse shr = new SayHelloResponse();
		shr.setHelloResponse(hr);
		return shr;
	}

}
