package com.sdlab.ws.app;

import javax.xml.ws.Endpoint;
 
public class PublishServices {
 
    public static void main(String[] args) {
             
        /*
         *  Se publican los servicios a trav�s de un servidor virtual. 
            El puerto puede ser cualquiera.
            Un vez ejecutada la aplicaci�n, se publica el contrato WSDL
          */
          
        Endpoint.publish("http://localhost:1516/WS/Users", new SOAPImpl());
 
    }
 
}
