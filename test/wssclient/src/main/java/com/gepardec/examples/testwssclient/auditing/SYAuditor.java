package com.gepardec.examples.testwssclient.auditing;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import javax.security.auth.Subject;

import org.apache.camel.Exchange;
import org.switchyard.Service;
import org.switchyard.ServiceReference;
import org.switchyard.bus.camel.audit.Auditor;
import org.switchyard.bus.camel.processors.Processors;

public abstract class SYAuditor implements Auditor{
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public static @interface SYAuditorConfig{
		String endpoint();
		String serviceName();
		String[] externalServices();
	}
	
	public static final String SY_PHASE = "org.switchyard.bus.camel.phase";
	public static final String SY_PHASE_IN = "IN";
	public static final String SY_PHASE_OUT = "OUT";
	public static final String SY_GATEWAY = "org.switchyard.exchangeGatewayName";
	public static final String SY_CONSUMER = "org.switchyard.bus.camel.consumer";
	public static final String SY_PROVIDER = "org.switchyard.bus.camel.provider";
	public static final String SY_SERVICE = "org.switchyard.serviceName";
	public static final String SY_OPERATION = "org.switchyard.operationName";
	
	public static final String SYAUDITOR_SERVICE_NAME = "syauditor.serviceName";
	public static final String SYAUDITOR_ENDPOINT_NAME = "syauditor.endpointName";
	public static final String SYAUDITOR_EXTERNAL_SERVICES = "syauditor.externalServices";
	public static final String SYAUDITOR_IN_PAYLOAD = "syauditor.inPayload";
	public static final String SYAUDITOR_OUT_PAYLOAD = "syauditor.outPayload";
	public static final String SYAUDITOR_PRINCIPAL = "syauditor.principal";
	
	private String serviceName;
	private String endpointName;
	private String[] externalServices;
	
	public SYAuditor() throws IllegalArgumentException {
		if(!this.getClass().isAnnotationPresent(SYAuditorConfig.class)){
			throw new IllegalArgumentException("LoggingAuditor must be annotated with @LoggingAuditorConfig");
		}
		SYAuditorConfig loggingAuditorConfig = this.getClass().getAnnotation(SYAuditorConfig.class);
		
		if(loggingAuditorConfig.serviceName() == null || loggingAuditorConfig.serviceName().isEmpty()){
			throw new IllegalArgumentException("LoggingAuditorConfig.serviceName can not be empty");
		}
		if(loggingAuditorConfig.endpoint() == null || loggingAuditorConfig.endpoint().isEmpty()){
			throw new IllegalArgumentException("LoggingAuditorConfig.endpoint can not be empty");
		}
		if(loggingAuditorConfig.externalServices() == null || loggingAuditorConfig.externalServices().length == 0){
			throw new IllegalArgumentException("LoggingAuditorConfig.externalServices can not be empty");
		}
		
		serviceName = loggingAuditorConfig.serviceName();
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
				&& exchange.getProperty(SY_CONSUMER, null, ServiceReference.class).getName().getLocalPart().endsWith(serviceName)
		){
			exchange.setProperty(SYAUDITOR_SERVICE_NAME, serviceName);
			exchange.setProperty(SYAUDITOR_ENDPOINT_NAME, endpointName);
			exchange.setProperty(SYAUDITOR_EXTERNAL_SERVICES, externalServices);
			exchange.setProperty(SYAUDITOR_IN_PAYLOAD, exchange.getIn().toString());
			exchange.setProperty("id", exchange.hashCode());
			onEndpointEntry(exchange);
		}
		if(
				serviceName.equals(exchange.getProperty(SYAUDITOR_SERVICE_NAME))
				&& endpointName.equals(exchange.getProperty(SYAUDITOR_ENDPOINT_NAME))
				&& processor.name().equals(Processors.PROVIDER_CALLBACK.toString())
				&& exchange.getProperty(SY_PHASE).toString().equals(SY_PHASE_IN)
				&& exchange.getProperty(SY_PROVIDER) != null
				&& contains(exchange.getProperty(SYAUDITOR_EXTERNAL_SERVICES, new String[]{}, String[].class), exchange.getProperty(SY_PROVIDER, null, Service.class).getName().getLocalPart())
		){
			onExternalServiceCallBegin(exchange.getProperty(SY_PROVIDER, null, Service.class).getName().getLocalPart(), exchange);
		}
		
		//System.out.println("Before " + processor.name() + " # " + propertiesToString(exchange.getProperties()));
		
	}

	@Override
	public void afterCall(Processors processor, Exchange exchange) {
		
		//System.out.println(this.getClass().getName() + " After " + processor.name() + " # " + propertiesToString(exchange.getProperties()));
		
		
		if(processor.name().equals(Processors.SECURITY_PROCESS.toString())){
			Subject s = ((org.switchyard.security.context.SecurityContext)exchange.getProperty("org.switchyard.security.context.SecurityContext")).getSubject("other");
			exchange.setProperty(SYAUDITOR_PRINCIPAL, s);
		}
		
		if(
				serviceName.equals(exchange.getProperty(SYAUDITOR_SERVICE_NAME))
				&& endpointName.equals(exchange.getProperty(SYAUDITOR_ENDPOINT_NAME))
				&& processor.name().equals(Processors.CONSUMER_CALLBACK.toString())
				&& exchange.getProperty(SY_PHASE).toString().equals(SY_PHASE_OUT)
				&& exchange.getProperty(SY_GATEWAY) != null
				&& exchange.getProperty(SY_GATEWAY).equals(exchange.getProperty(SYAUDITOR_ENDPOINT_NAME))
				&& exchange.hashCode() == exchange.getProperty("id", Integer.class)
		){
			//System.out.println("After " + processor.name() + " # " + propertiesToString(exchange.getProperties()));
			exchange.setProperty(SYAUDITOR_OUT_PAYLOAD, exchange.getIn().toString());
			onEndpointExit(exchange);
			exchange.removeProperty(SYAUDITOR_ENDPOINT_NAME);
		}
		
		if(
				serviceName.equals(exchange.getProperty(SYAUDITOR_SERVICE_NAME))
				&& endpointName.equals(exchange.getProperty(SYAUDITOR_ENDPOINT_NAME))
				&& processor.name().equals(Processors.PROVIDER_CALLBACK.toString())
				&& exchange.getProperty(SY_PHASE).toString().equals(SY_PHASE_OUT)
				&& exchange.getProperty(SY_PROVIDER) != null
				&& contains(exchange.getProperty(SYAUDITOR_EXTERNAL_SERVICES, new String[]{}, String[].class), exchange.getProperty(SY_PROVIDER, null, Service.class).getName().getLocalPart())
		){
			onExternalServiceCallEnd(exchange.getProperty(SY_PROVIDER, null, Service.class).getName().getLocalPart(), exchange);
		}

		
		
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
		buffer.append("################### >>> PROPERTIES ################ ");
		
		buffer.append(System.getProperty("line.separator"));
		for(String key : properties.keySet()){
			buffer.append("		").append(key).append(" :: ").append(properties.get(key)).append(System.getProperty("line.separator"));
		}
		buffer.append("################### <<< END PROPERTIES ################");
		buffer.append(System.getProperty("line.separator"));
		return buffer.toString();
	}
}
