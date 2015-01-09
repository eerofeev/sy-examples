package com.gepardec.examples.testwssclient;

import com.gepardec.examples.testwssclient.generated.hello.SayHello;
import com.gepardec.examples.testwssclient.generated.hello.SayHelloResponse;

public interface HelloProxy {
	SayHelloResponse sayHello(SayHello sayHello);
}
