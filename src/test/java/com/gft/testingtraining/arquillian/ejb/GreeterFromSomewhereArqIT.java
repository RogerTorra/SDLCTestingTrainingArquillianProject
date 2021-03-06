package com.gft.testingtraining.arquillian.ejb;

import com.gft.testingtraining.arquillian.cdi.Location;
import org.jboss.arquillian.container.test.api.*;
import org.jboss.arquillian.junit.*;
import org.jboss.shrinkwrap.api.*;
import org.jboss.shrinkwrap.api.asset.*;
import org.jboss.shrinkwrap.api.spec.*;
import org.junit.*;
import org.junit.runner.*;

import javax.ejb.*;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class GreeterFromSomewhereArqIT {

    @Deployment
    public static Archive<?> deploy(){
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                         .addClasses(GreeterFromSomewhere.class, Location.class, SecurityInterceptor.class)
                         .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
      
    @EJB GreeterFromSomewhere greeter;
 
    @Test
    public void testGreet() throws Exception {
        String who = "World";
        String expected = "Hello " + who + " from SDLCTestingTraining\n";
        String actual = greeter.greet(who);
        
        assertEquals(expected, actual);
    }
}
