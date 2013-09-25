package com.gft.testingtraining.arquillian;

import org.junit.runner.*;
import org.junit.runners.*;
import com.gft.testingtraining.arquillian.ejb.*;
import com.gft.testingtraining.arquillian.cdi.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        GreeterFromSomewhereTest.class,
        com.gft.testingtraining.arquillian.cdi.GreeterTest.class,
        GreeterFromDatabaseTest.class,
        SecurityInterceptorTest.class,
        com.gft.testingtraining.arquillian.cdi.GreeterTest.class,
        LocationTest.class})
public class TestSuite {
}
