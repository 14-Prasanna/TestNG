package com.testng.demo;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Annotations {
	
	@BeforeTest
	public void init() {
		System.out.println("Hefva");
	}
	
	@AfterTest
	public void init1() {
		System.out.println("Hefva bfsn ");
	}
	
	@BeforeClass
	public void init11() {
		System.out.println("iowe rw[o");
	}
	
	@AfterClass
	public void init111() {
		System.out.println("efhewvs[wsva");
	}
	
	@BeforeMethod
	public void init1111() {
		System.out.println("Hefva");
	}
	
	@AfterMethod
	public void init11111() {
		System.out.println("awihbvs ");
	}
	
	@BeforeSuite
	public void init11111111() {
		System.out.println("Hefva");
	}
	
	@AfterSuite
	public void init111111() {
		System.out.println("awihbvs ");
	}
	
	@Test
	public void init2() {
		System.out.println("eEIFP FEW");
	}
	
	@Test
	public void init22() {
		System.out.println("WPEF	V	V");
	}
	

}
