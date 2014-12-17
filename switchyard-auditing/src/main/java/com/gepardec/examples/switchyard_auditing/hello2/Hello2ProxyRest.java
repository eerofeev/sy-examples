package com.gepardec.examples.switchyard_auditing.hello2;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gepardec.examples.switchyard_auditing.generated.hello2.SayHello2;
import com.gepardec.examples.switchyard_auditing.generated.hello2.SayHello2Response;

@Path("/")
public interface Hello2ProxyRest extends Hello2Proxy{
	@Override
	@POST
	@Path("/hello2")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	SayHello2Response sayHello2(SayHello2 sayHello2);
}
