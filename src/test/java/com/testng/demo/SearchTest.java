package com.testng.demo;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.testng.demo.MyListener;

@Listeners(MyListener.class)
public class SearchTest {

    @Test
    public void searchTest() {
        System.out.println("Executing Search Test");
        Assert.assertTrue(true);
    }
}