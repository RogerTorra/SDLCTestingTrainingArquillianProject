package com.gft.testingtraining.arquillian.ejb;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/** 
 * Small example of plain EJB 3.1
 */
@Stateless
@Path("/greeter")
public class Greeter {
    
    @GET
    @Path("/{name}")
    public String greet(@PathParam("name") String name) {
        return "Hello " + name;
    }
}
