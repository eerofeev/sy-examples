package com.gepardec.examples.testerrorhandling;

import javax.xml.namespace.QName;

import org.switchyard.Exchange;
import org.switchyard.component.soap.SOAPMessages;
import org.switchyard.component.soap.composer.SOAPBindingData;
import org.switchyard.component.soap.composer.SOAPMessageComposer;

public class HelloSoapMessageComposer extends SOAPMessageComposer {

	 @Override
	 public SOAPBindingData decompose(Exchange exchange, SOAPBindingData target)
	   throws Exception {
	  SOAPBindingData data = target;
	  try{
	   data = super.decompose(exchange, target);
	  }catch(Exception e){
		  Throwable cause = e.getCause();
		  if(cause instanceof HelloException){
			  data.getSOAPMessage().getSOAPBody().addFault(new QName(Integer.toString(((HelloException)cause).code)), ((HelloException)cause).getMessage());
		  } else {
			  data.getSOAPMessage().getSOAPBody().addFault(new QName("100"), e.getMessage());
		  }
	  }
	        try {
	            getContextMapper().mapTo(exchange.getContext(), data);
	        } catch (Exception ex) {
	            throw SOAPMessages.MESSAGES.failedToMapContextPropertiesToSOAPMessage(ex);
	        }
	  
	        return data;  
	 }
	}
