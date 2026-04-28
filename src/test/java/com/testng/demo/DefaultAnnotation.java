package com.testng.demo;

import org.testng.annotations.Test;

public class DefaultAnnotation {
	
	
	@Test(priority = 3)
	public void A(){
		System.out.println("hii");
	}
	
	@Test
	public void B() {
		System.out.println("hello");
	}
	
	@Test(enabled=false)
	public void C() {
		System.out.println("Bye");
		
	}
	
	
	

}
