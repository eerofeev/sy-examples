package com.gepardec.examples.testwssclient;

import java.io.ByteArrayOutputStream;

import javax.xml.soap.SOAPMessage;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.message.MessageImpl;
import org.apache.cxf.phase.PhaseInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.handler.WSHandlerConstants;
import org.switchyard.Exchange;
import org.switchyard.component.soap.composer.SOAPBindingData;
import org.switchyard.component.soap.composer.SOAPMessageComposer;

public class SoapWssMessageComposer extends SOAPMessageComposer {  
    private static final String USERNAME = "kermit";  
    private static final String PASSWORD = "the-frog-1";  
    
    
    /** 
    * Adds Wsse-Headers to the outgoing SOAPMessage.<p/> 
    * {@inheritDoc} 
    */  
    @Override  
    public SOAPBindingData decompose(Exchange exchange, SOAPBindingData target) throws Exception {  
    SOAPBindingData soapBindingData = super.decompose(exchange, target);  
    SOAPMessage esbSoapMessage = soapBindingData.getSOAPMessage();  
    
    
    addWssHeaderToSoapMessage(esbSoapMessage);
    ByteArrayOutputStream out = new ByteArrayOutputStream();esbSoapMessage.writeTo(out);System.out.println(new String(out.toByteArray()));
    return soapBindingData;  
    }  
    
    
    
    
    private void addWssHeaderToSoapMessage(SOAPMessage esbSoapMessage) {  
    WSS4JOutInterceptor wss4JOutHandler = new WSS4JOutInterceptor();  
    PhaseInterceptor<SoapMessage> handler = wss4JOutHandler.createEndingInterceptor();  
    
    
    SoapMessage wss4jSoapMessage = new SoapMessage(new MessageImpl());  
    wss4jSoapMessage.setContent(SOAPMessage.class, esbSoapMessage);  
    
    
    // add wsse:UsernameToken  
    wss4jSoapMessage.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);  
    wss4jSoapMessage.put(WSHandlerConstants.USER, USERNAME);  
    wss4jSoapMessage.put("password", PASSWORD);  
    wss4jSoapMessage.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);  
    // add 'wsse:Nonce' and 'wsu:Created'  
    wss4jSoapMessage.put(WSHandlerConstants.ADD_UT_ELEMENTS, "Nonce Created");  
    
    
    // add wsse-headers to the soap-message  
    handler.handleMessage(wss4jSoapMessage);  
    }  
  }  


