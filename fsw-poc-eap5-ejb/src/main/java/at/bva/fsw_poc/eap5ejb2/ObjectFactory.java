//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.02 at 09:04:57 PM CET 
//


package at.bva.fsw_poc.eap5ejb2;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the at.bva.fsw_poc.eap5ejb2 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Eap5Ejb2Response_QNAME = new QName("http://eap5ejb2.fsw_poc.bva.at/", "eap5Ejb2Response");
    private final static QName _Eap5Ejb2Request_QNAME = new QName("http://eap5ejb2.fsw_poc.bva.at/", "eap5Ejb2Request");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: at.bva.fsw_poc.eap5ejb2
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Eap5Ejb2Response }
     * 
     */
    public Eap5Ejb2Response createEap5Ejb2Response() {
        return new Eap5Ejb2Response();
    }

    /**
     * Create an instance of {@link Eap5Ejb2Request }
     * 
     */
    public Eap5Ejb2Request createEap5Ejb2Request() {
        return new Eap5Ejb2Request();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Eap5Ejb2Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://eap5ejb2.fsw_poc.bva.at/", name = "eap5Ejb2Response")
    public JAXBElement<Eap5Ejb2Response> createEap5Ejb2Response(Eap5Ejb2Response value) {
        return new JAXBElement<Eap5Ejb2Response>(_Eap5Ejb2Response_QNAME, Eap5Ejb2Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Eap5Ejb2Request }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://eap5ejb2.fsw_poc.bva.at/", name = "eap5Ejb2Request")
    public JAXBElement<Eap5Ejb2Request> createEap5Ejb2Request(Eap5Ejb2Request value) {
        return new JAXBElement<Eap5Ejb2Request>(_Eap5Ejb2Request_QNAME, Eap5Ejb2Request.class, null, value);
    }

}
