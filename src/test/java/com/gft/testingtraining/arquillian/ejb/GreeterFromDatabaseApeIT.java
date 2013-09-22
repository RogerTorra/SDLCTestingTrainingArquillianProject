package com.gft.testingtraining.arquillian.ejb;

import com.gft.testingtraining.arquillian.domain.BasicModelUnit;
import org.jboss.arquillian.container.test.api.*;
import org.jboss.arquillian.junit.*;
import org.jboss.arquillian.persistence.*;
import org.jboss.shrinkwrap.api.*;
import org.jboss.shrinkwrap.api.asset.*;
import org.jboss.shrinkwrap.api.spec.*;
import org.junit.*;
import org.junit.runner.*;

import javax.ejb.*;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
@Cleanup(phase = TestExecutionPhase.BEFORE)
public class GreeterFromDatabaseApeIT {

    @Deployment
    public static Archive<?> deploy(){
        return ShrinkWrap.create(WebArchive.class, "test.war")
                         .addClasses(GreeterFromDatabase.class, BasicModelUnit.class)
                         .addClass(GreeterFromDatabaseApeIT.class)
                         .addAsResource("META-INF/persistence.xml")
                         .addAsResource("log4j.xml")
                         .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @EJB GreeterFromDatabase greeter;
    
    @Test //@UsingDataSet("msg1.yml")
    public void testGreet() throws Exception {
        Long id = 100L;
        BasicModelUnit message = greeter.greet(id);
        assertNotNull("No message found in DB", message);
        assertEquals("ID is not the same", id, message.getId());
        assertNotNull("Message text is null", message.getText());
    }

    @Test @ShouldMatchDataSet(value="expected-msg.yml")
    public void testSave() throws Exception {
        greeter.save("Hi");
    }
}
