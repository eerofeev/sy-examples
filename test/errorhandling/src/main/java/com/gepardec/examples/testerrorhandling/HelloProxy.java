package com.gepardec.examples.testerrorhandling;

import com.gepardec.examples.testerrorhandling.generated.hello.SayHello;
import com.gepardec.examples.testerrorhandling.generated.hello.SayHelloResponse;

public interface HelloProxy {
	SayHelloResponse sayHello(SayHello sayHello);
}
