/**
 * SOAPImplService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sdlab.ws.app;

public interface SOAPImplService extends javax.xml.rpc.Service {
    public java.lang.String getSOAPImplPortAddress();

    public com.sdlab.ws.app.SOAPI getSOAPImplPort() throws javax.xml.rpc.ServiceException;

    public com.sdlab.ws.app.SOAPI getSOAPImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
