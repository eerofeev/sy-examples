package com.gepardec.examples.testerrorhandling;

import java.lang.reflect.UndeclaredThrowableException;

import javax.inject.Inject;

import org.switchyard.component.bean.Reference;
import org.switchyard.component.bean.Service;

import com.gepardec.examples.testerrorhandling.generated.hello.SayHello;
import com.gepardec.examples.testerrorhandling.generated.hello.SayHelloResponse;

@Service(value = HelloProxy.class, name = "Hello")
public class HelloBean implements HelloProxy {
	
	@Inject
	@Reference("HelloImpl")
	HelloProxy helloImpl;

	@Override
	public SayHelloResponse sayHello(SayHello sayHello) {
		
		SayHelloResponse response = null;
		
		try {
			response = helloImpl.sayHello(sayHello);
		} catch (UndeclaredThrowableException e){
			throw new HelloException(1, "Exception: " + e.getUndeclaredThrowable().getCause().toString());
		}
		
		return response;
		
	}

}
