
package com.johnson.axes.ws.proxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.johnson.axes.ws.proxy package. 
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

    private final static QName _HelloResponse_QNAME = new QName("http://service.ws.axes.johnson.com/", "helloResponse");
    private final static QName _Hello_QNAME = new QName("http://service.ws.axes.johnson.com/", "hello");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.johnson.axes.ws.proxy
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link com.johnson.axes.ws.proxy.HelloResponse }
     * 
     */
    public HelloResponse createHelloResponse() {
        return new HelloResponse();
    }

    /**
     * Create an instance of {@link com.johnson.axes.ws.proxy.Hello_Type }
     * 
     */
    public Hello_Type createHello_Type() {
        return new Hello_Type();
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link com.johnson.axes.ws.proxy.HelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.axes.johnson.com/", name = "helloResponse")
    public JAXBElement<HelloResponse> createHelloResponse(HelloResponse value) {
        return new JAXBElement<HelloResponse>(_HelloResponse_QNAME, HelloResponse.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link com.johnson.axes.ws.proxy.Hello_Type }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.axes.johnson.com/", name = "hello")
    public JAXBElement<Hello_Type> createHello(Hello_Type value) {
        return new JAXBElement<Hello_Type>(_Hello_QNAME, Hello_Type.class, null, value);
    }

}
