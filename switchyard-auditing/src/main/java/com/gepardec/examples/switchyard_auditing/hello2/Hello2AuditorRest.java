package com.gepardec.examples.switchyard_auditing.hello2;

import javax.inject.Named;

import org.switchyard.bus.camel.audit.Audit;

import com.gepardec.examples.switchyard_auditing.LoggingAuditor;
import com.gepardec.examples.switchyard_auditing.SYAuditor.SYAuditorConfig;

@Audit
@Named("hello2 auditor rest")
@SYAuditorConfig(serviceName="Hello2Proxy", endpoint="rest2",externalServices={"Converter", "Hello2Ext"})
public class Hello2AuditorRest extends LoggingAuditor {
	public Hello2AuditorRest() {
		super();
	}
}
