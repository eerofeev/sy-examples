package com.gepardec.examples.switchyard_auditing.hello2;

import java.lang.reflect.UndeclaredThrowableException;

import javax.inject.Inject;

import org.switchyard.component.bean.Reference;
import org.switchyard.component.bean.Service;

import com.gepardec.examples.switchyard_auditing.generated.converter.Code;
import com.gepardec.examples.switchyard_auditing.generated.converter.Convert;
import com.gepardec.examples.switchyard_auditing.generated.converter.Request;
import com.gepardec.examples.switchyard_auditing.generated.hello2.Hello2Response;
import com.gepardec.examples.switchyard_auditing.generated.hello2.SayHello2;
import com.gepardec.examples.switchyard_auditing.generated.hello2.SayHello2Response;

@Service(Hello2Proxy.class)
public class Hello2ProxyBean implements Hello2Proxy {
	
	@Inject
	@Reference("Hello2ProxyMediateIn")
	Hello2Proxy hello2Mediator;
	
	@Override
	public SayHello2Response sayHello2(SayHello2 sayHello2) {
		Convert convert = new Convert();
		Request request = new Request();
		request.setCurrencyCodeFrom(Code.EUR);
		request.setCurrencyCodeTo(Code.EUR);
		convert.setConverterRequest(request);
		
		try{
			SayHello2Response response = hello2Mediator.sayHello2(sayHello2);
			System.out.println(response);
			return response;
		} catch (UndeclaredThrowableException e) {
			/*SayHello2Response response = new SayHello2Response();
			Hello2Response hello2Response = new Hello2Response();
			hello2Response.setValue("ERROR " + e.getUndeclaredThrowable().getCause());
			response.setHello2Response(hello2Response);
			System.out.println(response);
			return response;*/
			throw new Hello2Exception(e.getUndeclaredThrowable().getCause().toString());
		}
	}

}
