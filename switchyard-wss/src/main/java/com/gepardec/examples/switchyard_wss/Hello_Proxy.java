package com.gepardec.examples.switchyard_wss;

import com.gepardec.examples.switchyard_wss.generated.hello.SayHello;
import com.gepardec.examples.switchyard_wss.generated.hello.SayHelloResponse;

public interface Hello_Proxy {
	SayHelloResponse sayHello(SayHello sayHello);
}
