package com.gft.testingtraining.arquillian.ejb;


import javax.ejb.*;
import javax.inject.*;
import javax.interceptor.*;
import javax.ws.rs.*;
import com.gft.testingtraining.arquillian.cdi.Location;

/** 
 * Small example of EJB 3.1, with CDI injection
 */
@Stateless
@Path("/greeterlocated")
@Interceptors({SecurityInterceptor.class})
public class GreeterFromSomewhere {
    
    @Inject
    Location location;
    
    @GET
    @Path("/{name}")
    public String greet(@PathParam("name") String name) {
        return "Hello " + name + " from " + location.from() + "\n";
    }
}
