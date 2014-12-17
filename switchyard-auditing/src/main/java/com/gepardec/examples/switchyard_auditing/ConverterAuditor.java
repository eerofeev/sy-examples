package com.gepardec.examples.switchyard_auditing;

import javax.inject.Named;

import org.switchyard.bus.camel.audit.Audit;

import com.gepardec.examples.switchyard_auditing.SYAuditor.SYAuditorConfig;

@Audit
@Named("converter auditor")
@SYAuditorConfig(serviceName="ConverterProxy", endpoint="rest1",externalServices={"Converter"})
public class ConverterAuditor extends LoggingAuditor {
	public ConverterAuditor() {
		super();
	}
}
