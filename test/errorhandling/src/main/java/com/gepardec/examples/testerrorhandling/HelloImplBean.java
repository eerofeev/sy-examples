package com.gepardec.examples.testerrorhandling;

import org.switchyard.component.bean.Service;

import com.gepardec.examples.testerrorhandling.generated.hello.HelloResponse;
import com.gepardec.examples.testerrorhandling.generated.hello.SayHello;
import com.gepardec.examples.testerrorhandling.generated.hello.SayHelloResponse;

@Service(value = HelloProxy.class, name = "HelloImpl")
public class HelloImplBean implements HelloProxy {

	@Override
	public SayHelloResponse sayHello(SayHello sayHello) {
		if("error".equals(sayHello.getString())){
			throw new RuntimeException("Invalid string value 'error'");
		}
		SayHelloResponse response = new SayHelloResponse();
		HelloResponse helloResponse = new HelloResponse();
		helloResponse.setErrorCode(0);
		helloResponse.setErrorDescription(null);
		helloResponse.setValue("Hello, " + sayHello.getString());
		response.setHelloResponse(helloResponse);
		return response;
	}

}
