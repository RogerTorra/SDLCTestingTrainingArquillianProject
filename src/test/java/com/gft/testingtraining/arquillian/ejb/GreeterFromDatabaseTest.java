package com.gft.testingtraining.arquillian.ejb;

import com.gft.testingtraining.arquillian.domain.BasicModelUnit;
import org.junit.*;

import javax.persistence.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GreeterFromDatabaseTest {
    
    GreeterFromDatabase greeter;
    
    @Before
    public void setUp() {
        greeter = new GreeterFromDatabase();
    }

    @Test
    public void testGreet() throws Exception {
        EntityManager em = mock(EntityManager.class);
        BasicModelUnit expected = new BasicModelUnit();
        when(em.find(BasicModelUnit.class, 0L)).thenReturn(expected);
        greeter.em = em;

        assertEquals(expected, greeter.greet(0L));
    }
    @Test
    public void testSave() throws Exception {
        EntityManager em = mock(EntityManager.class);
        em.persist(any());
        greeter.em = em;

        greeter.save("Message");
    }
}
