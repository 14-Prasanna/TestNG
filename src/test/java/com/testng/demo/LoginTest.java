package com.testng.demo;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.testng.demo.MyListener;

@Listeners(MyListener.class)
public class LoginTest {

    @Test
    public void loginTest() {
        System.out.println("Executing Login Test");
        Assert.assertTrue(true);
    }
}