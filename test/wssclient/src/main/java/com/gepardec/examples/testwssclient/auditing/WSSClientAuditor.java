package com.gepardec.examples.testwssclient.auditing;

import javax.inject.Named;

import org.switchyard.bus.camel.audit.Audit;

import com.gepardec.examples.testwssclient.auditing.SYAuditor.SYAuditorConfig;

@Audit
@Named("wssclientauditor")
@SYAuditorConfig(serviceName="HelloWSSClient", endpoint="soap1",externalServices={"HelloWSSClientExt"})
public class WSSClientAuditor extends LoggingAuditor{

}
