package com.testng.demo;

import org.testng.annotations.DataProvider;

public class Datassss {
	
	@DataProvider(name = "validate" , parallel = true)
    public Object[][] data() {
        return new Object[][] {
            { "admin", "admin" },
            { "wronguser", "wrongpass" }
        };
    }

}
