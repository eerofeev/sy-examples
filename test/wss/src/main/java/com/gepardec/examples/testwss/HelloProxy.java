package com.gepardec.examples.testwss;

import com.gepardec.examples.testwss.generated.hello.SayHello;
import com.gepardec.examples.testwss.generated.hello.SayHelloResponse;

public interface HelloProxy {
	SayHelloResponse sayHello(SayHello sayHello);
}
