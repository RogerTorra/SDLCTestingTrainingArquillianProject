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

import javax.annotation.*;
import javax.ejb.*;
import javax.persistence.*;
import javax.sql.DataSource;
import javax.transaction.*;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class GreeterFromDatabaseIT {

    @Deployment
    public static Archive<?> deploy(){
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                         .addClasses(GreeterFromDatabase.class, BasicModelUnit.class)
                         .addClass(DatabaseInitializer.class)
                         .addAsResource("META-INF/persistence.xml")
                         .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @EJB GreeterFromDatabase greeter;
    
    @Resource(mappedName="java:/jdbc/sample")
    DataSource ds;

    @PersistenceContext
    EntityManager em;

    @Resource
    UserTransaction tx;

    @Before
    public void init() throws Exception {
        DatabaseInitializer dbInit = new DatabaseInitializer(ds.getConnection());

        dbInit.clearData();
        dbInit.insertData();

        em.clear();
    }
    
    @Test
    public void testGreet() throws Exception {
        Long id = 100L;
        BasicModelUnit message = greeter.greet(id);
        assertNotNull("No message found in DB", message);
        assertEquals("ID is not the same", id, message.getId());
        assertNotNull("Message text is null", message.getText());
    }
}
