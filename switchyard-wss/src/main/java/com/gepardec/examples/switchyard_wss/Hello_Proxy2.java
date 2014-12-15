package com.gepardec.examples.switchyard_wss;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.gepardec.examples.switchyard_wss.generated.hello.SayHello;
import com.gepardec.examples.switchyard_wss.generated.hello.SayHelloResponse;

@Path("/hello2")
public interface Hello_Proxy2 {
	@POST
	@Path("/")
	SayHelloResponse sayHello(SayHello sayHello);
}
