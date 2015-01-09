package com.gepardec.examples.testvalidation;

import com.gepardec.examples.testvalidation.generated.hello.SayHello;
import com.gepardec.examples.testvalidation.generated.hello.SayHelloResponse;

public interface HelloProxy {
	SayHelloResponse sayHello(SayHello sayHello);
}
