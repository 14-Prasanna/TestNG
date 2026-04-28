package com.testng.demo;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SoftAssertion {

    WebDriver driver;
    WebDriverWait wait;
    SoftAssert sa = new SoftAssert();
    
    

    @Test(priority = 7)
    public void validation() {

        wait.until(ExpectedConditions.elementToBeClickable(By.id("login2"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername"))).clear();
        driver.findElement(By.id("loginusername")).sendKeys("admin");

        
        
        driver.findElement(By.id("loginpassword")).sendKeys("admin");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Log in']"))).click();
    }

    @Test(priority = -1)
    public void invalid_with_prass() {

        wait.until(ExpectedConditions.elementToBeClickable(By.id("login2"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername"))).clear();
        driver.findElement(By.id("loginusername")).sendKeys("admin");

        
        driver.findElement(By.id("loginpassword")).sendKeys("788888");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Log in']"))).click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        String msg = alert.getText();
        alert.accept();
        String exp = "Wrong password.";

        Assert.assertEquals(msg, exp, "Popup message is correct!");
        System.out.println("Popup message is correct!");
        

    }

    @Test(priority = 1)
    public void invalid_with_username() {

        wait.until(ExpectedConditions.elementToBeClickable(By.id("login2"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername"))).clear();
        driver.findElement(By.id("loginusername")).sendKeys("1234");

       
        driver.findElement(By.id("loginpassword")).sendKeys("admin");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Log in']"))).click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        String msg = alert.getText();
        String exp = "User does not exist.";
        alert.accept();


        sa.assertEquals(msg, exp);
        System.out.println("Correct pop up user not exists");
        
        sa.assertAll();

    }

    @BeforeMethod
    public void beforeTest() {

        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        options.addArguments("--width=1920");
        options.addArguments("--height=1080");

        driver = new FirefoxDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0)); 
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.get("https://demoblaze.com/");
    }

    @AfterMethod
    public void afterTest() {
        driver.quit();
    }
}