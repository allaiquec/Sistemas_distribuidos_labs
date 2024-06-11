package com.sdlab.ws.app;

import java.util.List;
 
import javax.jws.WebService;
 
@WebService(endpointInterface = "com.sdlab.ws.app.SOAPI")
public class SOAPImpl implements SOAPI{
 
    @Override
    public List<User> getUsers() {
        return User.getUsers();
    }
 
    @Override
    public void addUser(User user) {
        User.getUsers().add(user);
    }
 
}