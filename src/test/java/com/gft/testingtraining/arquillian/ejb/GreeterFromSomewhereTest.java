package com.gft.testingtraining.arquillian.ejb;

import com.gft.testingtraining.arquillian.cdi.Location;
import org.junit.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GreeterFromSomewhereTest {
    
    GreeterFromSomewhere greeter;
    
    @Before
    public void setUp() {
        greeter = new GreeterFromSomewhere();
    }
    
    @Test
    public void testGreetLocated() throws Exception {
        Location location = mock(Location.class);
        String where = "Nowhere";
        when(location.from()).thenReturn(where);
        greeter.location = location;

        String who = "World";
        String expected = "Hello " + who + " from " + where + "\n";
        String actual = greeter.greet(who);
        
        assertEquals(expected, actual);        
    }
}
