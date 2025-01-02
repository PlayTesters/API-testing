package org.example.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class TestHooks {
    @Before
    public void setup() {
        System.out.println("Starting test...");
    }

    @After
    public void teardown() {
        System.out.println("Test completed.");
    }
}
