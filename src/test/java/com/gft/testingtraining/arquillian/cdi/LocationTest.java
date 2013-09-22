package com.gft.testingtraining.arquillian.cdi;

import org.junit.*;

import static org.junit.Assert.*;

public class LocationTest {

    Location location = new Location();

    @Test
    public void testFrom() throws Exception {
        assertEquals("SDLCTestingTraining", location.from());
    }
}
