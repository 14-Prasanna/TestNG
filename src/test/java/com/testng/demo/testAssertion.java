package com.testng.demo;

import org.testng.Assert;
import org.testng.annotations.Test;

public class testAssertion {

    @Test
    public void test() {

        String str1 = new String("TestNG");
        String str2 = new String("TestNG");
        String str3 = null;
        String str4 = "TestNG";
        String str5 = "TestNG";

        int val1 = 5;
        int val2 = 6;

        Assert.assertEquals(str1, str2);
        System.out.println("Equal Assertion is successful");

        Assert.assertNotEquals(str4, "Java");
        System.out.println("NotEquals Assertion is successful");

        Assert.assertTrue(val1 < val2);
        System.out.println("True Assertion is successful");

        Assert.assertFalse(val1 > val2);
        System.out.println("False Assertion is successful");

        Assert.assertNotNull(str1);
        System.out.println("NotNull Assertion is successful");

        Assert.assertNull(str3);
        System.out.println("Null Assertion is successful");

        Assert.assertSame(str4, str5);
        System.out.println("Same Assertion is successful");

        Assert.assertNotSame(str1, str2);
        System.out.println("NotSame Assertion is successful");
    }
}