package com.gepardec.examples.switchyard_auditing.hello2;

import javax.xml.namespace.QName;

import org.switchyard.Exchange;
import org.switchyard.component.soap.SOAPMessages;
import org.switchyard.component.soap.composer.SOAPBindingData;
import org.switchyard.component.soap.composer.SOAPMessageComposer;

public class Hello2SoapMessageComposer extends SOAPMessageComposer {

	 @Override
	 public SOAPBindingData decompose(Exchange exchange, SOAPBindingData target)
	   throws Exception {
	  SOAPBindingData data = target;
	  try{
	   data = super.decompose(exchange, target);
	  }catch(Exception e){
	   data.getSOAPMessage().getSOAPBody().addFault(new QName("100"), e.getMessage());
	  }
	        try {
	            getContextMapper().mapTo(exchange.getContext(), data);
	        } catch (Exception ex) {
	            throw SOAPMessages.MESSAGES.failedToMapContextPropertiesToSOAPMessage(ex);
	        }
	  
	        return data;  
	 }
	}
