
package com.idea.store.sim;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.idea.store.sim package. 
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

    private final static QName _FindAllSimsRequest_QNAME = new QName("http://www.store.idea.com/sim", "findAllSimsRequest");
    private final static QName _FindAllSimsResponse_QNAME = new QName("http://www.store.idea.com/sim", "findAllSimsResponse");
    private final static QName _FindSimResponse_QNAME = new QName("http://www.store.idea.com/sim", "findSimResponse");
    private final static QName _FindSimRequest_QNAME = new QName("http://www.store.idea.com/sim", "findSimRequest");
    private final static QName _Sfault_QNAME = new QName("http://www.store.idea.com/sim", "sfault");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.idea.store.sim
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Sim }
     * 
     */
    public Sim createSim() {
        return new Sim();
    }

    /**
     * Create an instance of {@link DetailMessage }
     * 
     */
    public DetailMessage createDetailMessage() {
        return new DetailMessage();
    }

    /**
     * Create an instance of {@link Void }
     * 
     */
    public Void createVoid() {
        return new Void();
    }

    /**
     * Create an instance of {@link SimList }
     * 
     */
    public SimList createSimList() {
        return new SimList();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Void }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.store.idea.com/sim", name = "findAllSimsRequest")
    public JAXBElement<Void> createFindAllSimsRequest(Void value) {
        return new JAXBElement<Void>(_FindAllSimsRequest_QNAME, Void.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SimList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.store.idea.com/sim", name = "findAllSimsResponse")
    public JAXBElement<SimList> createFindAllSimsResponse(SimList value) {
        return new JAXBElement<SimList>(_FindAllSimsResponse_QNAME, SimList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sim }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.store.idea.com/sim", name = "findSimResponse")
    public JAXBElement<Sim> createFindSimResponse(Sim value) {
        return new JAXBElement<Sim>(_FindSimResponse_QNAME, Sim.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.store.idea.com/sim", name = "findSimRequest")
    public JAXBElement<String> createFindSimRequest(String value) {
        return new JAXBElement<String>(_FindSimRequest_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DetailMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.store.idea.com/sim", name = "sfault")
    public JAXBElement<DetailMessage> createSfault(DetailMessage value) {
        return new JAXBElement<DetailMessage>(_Sfault_QNAME, DetailMessage.class, null, value);
    }

}
