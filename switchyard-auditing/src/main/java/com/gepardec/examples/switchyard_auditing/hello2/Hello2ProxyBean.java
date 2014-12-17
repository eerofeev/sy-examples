package com.gepardec.examples.switchyard_auditing.hello2;

import javax.inject.Inject;

import org.switchyard.component.bean.Reference;
import org.switchyard.component.bean.Service;

import com.gepardec.examples.switchyard_auditing.generated.converter.Code;
import com.gepardec.examples.switchyard_auditing.generated.converter.Convert;
import com.gepardec.examples.switchyard_auditing.generated.converter.Request;
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
		
		return hello2Mediator.sayHello2(sayHello2);
	}

}
