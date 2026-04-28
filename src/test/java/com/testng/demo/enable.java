package com.testng.demo;

import org.testng.annotations.Test;

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

public class enable {

    WebDriver driver;
    WebDriverWait wait;

    @Test(priority = 1)
    public void validation() throws InterruptedException {
    	
    	
        driver.findElement(By.id("login2")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername"))).sendKeys("admin");
        driver.findElement(By.id("loginpassword")).sendKeys("admin");

        driver.findElement(By.xpath("//button[text()='Log in']")).click();
    }
    
    @Test(priority = 3)
    public void invalid_with_prass() {
    	driver.findElement(By.id("login2")).click();
    	
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername"))).sendKeys("admin");
        driver.findElement(By.id("loginpassword")).sendKeys("788888");

        driver.findElement(By.xpath("//button[text()='Log in']")).click();    	
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
        
         
    	
    }
    
    @Test(priority = 2, enabled = false)
    public void invalid_with_username() {
    	driver.findElement(By.id("login2")).click();
    	
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername"))).sendKeys("1234");
        driver.findElement(By.id("loginpassword")).sendKeys("admin");

        driver.findElement(By.xpath("//button[text()='Log in']")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
        

    }

    @BeforeMethod
    public void beforeTest() {

        FirefoxOptions options = new FirefoxOptions();
        
         
         options.addArguments("--headless");
         options.addArguments("--width=2020");
         options.addArguments("--height=2080");

        driver = new FirefoxDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get("https://demoblaze.com/");
    }

    @AfterMethod
    public void afterTest() {
        driver.quit();
    }
}