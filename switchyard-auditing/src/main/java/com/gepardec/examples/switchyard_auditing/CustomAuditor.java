package com.gepardec.examples.switchyard_auditing;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Named;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.switchyard.bus.camel.audit.Audit;

import com.gepardec.examples.switchyard_auditing.LoggingAuditor.LoggingAuditorConfig;

@Audit
@Named("custom auditor")
@LoggingAuditorConfig(endpoint="rest1",externalServices={"Converter"})
public class CustomAuditor extends LoggingAuditor{
	
	private static final Logger LOG = LoggerFactory.getLogger(CustomAuditor.class);
	
	private static SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss.SSS");
	private final static String START_TIME="auditing.time.start";
	private final static String EXTERNAL_SERVICE_TIME="auditing.time.external";

	@Override
	public void onEndpointEntry(Exchange exchange) {
		exchange.setProperty(START_TIME, System.currentTimeMillis());
	}

	@Override
	public void onEndpointExit(Exchange exchange) {
		long startTime = exchange.getProperty(START_TIME, 0.0, Long.class);
		long commonTime = System.currentTimeMillis() - startTime;
		long externalTime = exchange.getProperty(EXTERNAL_SERVICE_TIME, 0.0, Long.class);
		long serviceTime = commonTime - externalTime;
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(System.getProperty("line.separator"));
		buffer.append("################### >>> SERVICE CALL ################");
		
		buffer.append(System.getProperty("line.separator"));
		buffer.append("		").append("Call time").append(" :: ").append(timestampFormat.format(new Date(startTime)));
		
		buffer.append(System.getProperty("line.separator"));
		buffer.append("		").append("Service").append(" :: ").append(exchange.getProperty(SY_SERVICE));

		buffer.append(System.getProperty("line.separator"));
		buffer.append("		").append("Operation").append(" :: ").append(exchange.getProperty(SY_OPERATION));
		
		buffer.append(System.getProperty("line.separator"));
		buffer.append("		").append("Service execution duration").append(" :: ").append(timeFormat.format(new Date(serviceTime)));
		
		buffer.append(System.getProperty("line.separator"));
		buffer.append("		").append("External calls duration").append(" :: ").append(timeFormat.format(new Date(externalTime)));
		
		buffer.append(System.getProperty("line.separator"));
		buffer.append("		").append("Common call duration").append(" :: ").append(timeFormat.format(new Date(commonTime)));
		
		buffer.append(System.getProperty("line.separator"));		
		buffer.append("################### <<< SERVICE CAL ################");
		buffer.append(System.getProperty("line.separator"));
		LOG.info(buffer.toString());
		
	}

	@Override
	public void onExternalServiceCallBegin(String serviceName, Exchange exchange) {
		long currentExternal = exchange.getProperty(EXTERNAL_SERVICE_TIME, 0.0, Long.class);
		exchange.setProperty(EXTERNAL_SERVICE_TIME, System.currentTimeMillis() - currentExternal);		
	}

	@Override
	public void onExternalServiceCallEnd(String serviceName, Exchange exchange) {
		long currentExternal = exchange.getProperty(EXTERNAL_SERVICE_TIME, 0.0, Long.class);
		exchange.setProperty(EXTERNAL_SERVICE_TIME, System.currentTimeMillis() - currentExternal);
	}

}
