package com.testng.demo;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class dependsOnMethod {
	
	
	@Test
	public void test1() {
		System.out.println("The test run first1");
		
	}
	
	@Test(priority = 2)
	public void a() {
		System.out.println("The test runs....");
	}
	
	
	@Test(dependsOnMethods = "test1")
	public void b() {
		System.out.println("The test runs in DependsOnTest1");
	}
	
	
	@BeforeTest
	public void beforeMethod() {
		System.out.println("The test runs in beforeMethod");
	}
	
	@AfterTest
	public void AfterMethod() {
		System.out.println("The test runs in AfterMethod");
	}

}
