package com.testng.demo;

import org.testng.annotations.DataProvider;

public class Provides {
	
	@DataProvider(name = "validate")
    public Object[][] data() {
        return new Object[][] {
            {"admin", "admin" }
        };
    }

	@DataProvider(name = "invalidate" , parallel = true)
    public Object[][] datas() {
        return new Object[][] {
            { "admin", "12343" },
            { "wronguser", "admin" }
        };
    }
}
