package com.gepardec.examples.testerrorhandling;

import com.gepardec.examples.testerrorhandlingcamel.generated.hello.SayHello;
import com.gepardec.examples.testerrorhandlingcamel.generated.hello.SayHelloResponse;

public interface HelloProxy {
	SayHelloResponse sayHello(SayHello sayHello);
}
