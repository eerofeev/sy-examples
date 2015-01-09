package com.gepardec.examples.testmonitoring.util;

import com.google.gson.GsonBuilder;

public class ActivityDBReaderRequestBuilder {
	private String expression;
	private String format;
	
	public ActivityDBReaderRequestBuilder() {
		format = "jpql";
		expression = "select c from ActivityType c where 1=1";
	}
	
	public ActivityDBReaderRequestBuilder fromTimeStamp(long ts){
		if(ts > 0 && !expression.contains("timestamp  >=")){
			expression = expression + " and timestamp >=" + ts;
		}
		return this;
	}
	
	public ActivityDBReaderRequestBuilder toTimeStamp(long ts){
		if(ts > 0 && !expression.contains("timestamp  <=")){
			expression = expression + " and timestamp <=" + ts;
		}
		return this;
	}
	
	public ActivityDBReaderRequestBuilder service(String service){
		if(service !=null && !service.isEmpty()){
			expression = expression + " and serviceType like '%" + service +"'";
		}
		return this;
	}
	
	public String toGson(){
		expression = expression + " order by timestamp";
		return new GsonBuilder().create().toJson(this);
	}
}
