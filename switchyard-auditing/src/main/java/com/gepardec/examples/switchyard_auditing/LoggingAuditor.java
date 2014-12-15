package com.gepardec.examples.switchyard_auditing;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import org.apache.camel.Exchange;
import org.switchyard.Service;
import org.switchyard.bus.camel.audit.Auditor;
import org.switchyard.bus.camel.processors.Processors;

public abstract class LoggingAuditor implements Auditor{
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public static @interface LoggingAuditorConfig{
		String endpoint();
		String[] externalServices();
	}
	
	public static final String SY_PHASE = "org.switchyard.bus.camel.phase";
	public static final String SY_PHASE_IN = "IN";
	public static final String SY_PHASE_OUT = "OUT";
	public static final String SY_GATEWAY = "org.switchyard.exchangeGatewayName";
	public static final String SY_PROVIDER = "org.switchyard.bus.camel.provider";
	public static final String SY_SERVICE = "org.switchyard.serviceName";
	public static final String SY_OPERATION = "org.switchyard.operationName";
	
	private String endpointName;
	private String[] externalServices;
	
	public LoggingAuditor() throws IllegalArgumentException {
		if(!this.getClass().isAnnotationPresent(LoggingAuditorConfig.class)){
			throw new IllegalArgumentException("LoggingAuditor must be annotated with @LoggingAuditorConfig");
		}
		LoggingAuditorConfig loggingAuditorConfig = this.getClass().getAnnotation(LoggingAuditorConfig.class);
		if(loggingAuditorConfig.endpoint() == null || loggingAuditorConfig.endpoint().isEmpty()){
			throw new IllegalArgumentException("LoggingAuditorConfig.endpoint can not be empty");
		}
		if(loggingAuditorConfig.externalServices() == null || loggingAuditorConfig.externalServices().length == 0){
			throw new IllegalArgumentException("LoggingAuditorConfig.externalServices can not be empty");
		}
		
		endpointName = loggingAuditorConfig.endpoint();
		externalServices = loggingAuditorConfig.externalServices();
	}
	

	@Override
	public void beforeCall(Processors processor, Exchange exchange) {
		if(
				processor.name().equals(Processors.CONSUMER_INTERCEPT.toString())
				&& exchange.getProperty(SY_PHASE).toString().equals(SY_PHASE_IN)
				&& exchange.getProperty(SY_GATEWAY) != null
				&& exchange.getProperty(SY_GATEWAY).equals(endpointName)
		){
			onEndpointEntry(exchange);
		}
		
		if(
				processor.name().equals(Processors.PROVIDER_CALLBACK.toString())
				&& exchange.getProperty(SY_PHASE).toString().equals(SY_PHASE_IN)
				&& exchange.getProperty(SY_PROVIDER) != null
				&& contains(externalServices, exchange.getProperty(SY_PROVIDER, null, Service.class).getName().getLocalPart())
		){
			onExternalServiceCallBegin(exchange.getProperty(SY_PROVIDER, null, Service.class).getName().getLocalPart(), exchange);
		}
		
		//System.out.println("Before " + processor.name() + " # " + propertiesToString(exchange.getProperties()));
		
	}

	@Override
	public void afterCall(Processors processor, Exchange exchange) {
		if(
				processor.name().equals(Processors.CONSUMER_CALLBACK.toString())
				&& exchange.getProperty(SY_PHASE).toString().equals(SY_PHASE_OUT)
				&& exchange.getProperty(SY_GATEWAY) != null
				&& exchange.getProperty(SY_GATEWAY).equals(endpointName)
		){
			onEndpointExit(exchange);
		}
		
		if(
				processor.name().equals(Processors.PROVIDER_CALLBACK.toString())
				&& exchange.getProperty(SY_PHASE).toString().equals(SY_PHASE_OUT)
				&& exchange.getProperty(SY_PROVIDER) != null
				&& contains(externalServices, exchange.getProperty(SY_PROVIDER, null, Service.class).getName().getLocalPart())
		){
			onExternalServiceCallEnd(exchange.getProperty(SY_PROVIDER, null, Service.class).getName().getLocalPart(), exchange);
		}

		//System.out.println("Before " + processor.name() + " # " + propertiesToString(exchange.getProperties()));
		
	}
	
	public abstract void onEndpointEntry(Exchange exchange);
	public abstract void onEndpointExit(Exchange exchange);
	public abstract void onExternalServiceCallBegin(String serviceName, Exchange exchange);
	public abstract void onExternalServiceCallEnd(String serviceName, Exchange exchange);
	
	public static boolean contains(String [] samples, String toCheck){
		
		for(int i = 0; i < samples.length; i++){
			if(toCheck.equals(samples[i])){
				return true;
			}
		}
		
		return false;
	}
	
	private static String propertiesToString(Map<String, Object> properties){
		StringBuffer buffer = new StringBuffer();
		buffer.append(System.getProperty("line.separator"));
		buffer.append("################### >>> PROPERTIES ################");
		buffer.append(System.getProperty("line.separator"));
		for(String key : properties.keySet()){
			buffer.append("		").append(key).append(" :: ").append(properties.get(key)).append(System.getProperty("line.separator"));
		}
		buffer.append("################### <<< END PROPERTIES ################");
		buffer.append(System.getProperty("line.separator"));
		return buffer.toString();
	}
}
