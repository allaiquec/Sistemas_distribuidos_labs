package com.sdlab.ws.app;

public class SOAPIProxy implements com.sdlab.ws.app.SOAPI {
  private String _endpoint = null;
  private com.sdlab.ws.app.SOAPI sOAPI = null;
  
  public SOAPIProxy() {
    _initSOAPIProxy();
  }
  
  public SOAPIProxy(String endpoint) {
    _endpoint = endpoint;
    _initSOAPIProxy();
  }
  
  private void _initSOAPIProxy() {
    try {
      sOAPI = (new com.sdlab.ws.app.SOAPImplServiceLocator()).getSOAPImplPort();
      if (sOAPI != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sOAPI)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sOAPI)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sOAPI != null)
      ((javax.xml.rpc.Stub)sOAPI)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.sdlab.ws.app.SOAPI getSOAPI() {
    if (sOAPI == null)
      _initSOAPIProxy();
    return sOAPI;
  }
  
  public com.sdlab.ws.app.User[] getUsers() throws java.rmi.RemoteException{
    if (sOAPI == null)
      _initSOAPIProxy();
    return sOAPI.getUsers();
  }
  
  public void addUser(com.sdlab.ws.app.User arg0) throws java.rmi.RemoteException{
    if (sOAPI == null)
      _initSOAPIProxy();
    sOAPI.addUser(arg0);
  }
  
  
}