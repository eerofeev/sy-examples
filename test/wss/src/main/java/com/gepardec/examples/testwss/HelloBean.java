package com.gepardec.examples.testwss;

import org.switchyard.component.bean.Service;

import com.gepardec.examples.testwss.generated.hello.SayHello;
import com.gepardec.examples.testwss.generated.hello.SayHelloResponse;

@Service(value = HelloProxy.class, name = "Hello")
public class HelloBean implements HelloProxy {

	@Override
	public SayHelloResponse sayHello(SayHello sayHello) {
		SayHelloResponse response = new SayHelloResponse();
		response.setString("Hello, " + sayHello.getString());
		return response;
	}

}
