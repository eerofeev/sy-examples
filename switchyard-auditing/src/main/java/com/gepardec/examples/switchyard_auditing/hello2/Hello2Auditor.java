package com.gepardec.examples.switchyard_auditing.hello2;

import javax.inject.Named;

import org.switchyard.bus.camel.audit.Audit;

import com.gepardec.examples.switchyard_auditing.LoggingAuditor;
import com.gepardec.examples.switchyard_auditing.SYAuditor.SYAuditorConfig;

@Audit
@Named("hello2 auditor")
@SYAuditorConfig(serviceName="Hello2Proxy", endpoint="soap1",externalServices={"Converter", "Hello2Ext"})
public class Hello2Auditor extends LoggingAuditor {
	public Hello2Auditor() {
		super();
	}
}
