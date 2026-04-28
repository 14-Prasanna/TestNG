package com.testng.demo;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class second_class {

    WebDriver driver;
    WebDriverWait wait;

    
    @Parameters({"bow" , "url"})
    @BeforeMethod
    public void beforeTest(String bow, String url) {
    	
    	switch(bow) {
    		
    	case "firefox":
    		driver = new FirefoxDriver();
    		break;
    	case "edge":
    		driver = new EdgeDriver();
    		break;
    	
    	}
     
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get(url);
    }

    @Parameters({"email", "pass"})
    @Test(priority = 1)
    public void validation(String email, String pass) {

        System.out.println("Running Test: " + Thread.currentThread().getId()
                + " | Class: " + this.getClass().getSimpleName());

        driver.findElement(By.id("login2")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername"))).sendKeys(email);
        driver.findElement(By.id("loginpassword")).sendKeys(pass);

        driver.findElement(By.xpath("//button[text()='Log in']")).click();
        
        
    }

    
    @Test(priority = 1)
    public void summa() {

        System.out.println("Running Test: " + Thread.currentThread().getId()
                + " | Class: " + this.getClass().getSimpleName());
        
        System.out.println("Multi-Thread");
        
        WebElement a = driver.findElement(By.id("login2"));
        System.out.println(a.getText());

    }
    
    @Parameters({"wemails", "cpass"})
    @Test(priority = 2, expectedExceptions = {java.lang.Exception.class}, groups = {"reggresion"})
    public void invalid_with_username(String wemails, String cpass) throws Exception {

        System.out.println("Running Test: " + Thread.currentThread().getId()
                + " | Class: " + this.getClass().getSimpleName());

        driver.findElement(By.id("login2")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername"))).sendKeys(wemails);
        driver.findElement(By.id("loginpassword")).sendKeys(cpass);

        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();
        throw new Exception();
    }

    @Parameters({"cemail", "wpass"})
    @Test(priority = 3, dependsOnMethods = "invalid_with_username", alwaysRun = true, groups = {"reggresion"})
    public void invalid_with_prass(String cemails, String wpass) {

        System.out.println("Running Test: " + Thread.currentThread().getId()
                + " | Class: " + this.getClass().getSimpleName());

        driver.findElement(By.id("login2")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername"))).sendKeys(cemails);
        driver.findElement(By.id("loginpassword")).sendKeys(wpass);

        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();
    }



    @AfterMethod
    public void afterTest() {
        driver.quit();
    }
}