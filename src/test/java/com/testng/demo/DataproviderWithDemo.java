package com.testng.demo;

import java.time.Duration;
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
import org.testng.asserts.SoftAssert;

public class DataproviderWithDemo {

    private static ThreadLocal<WebDriver> driver1 = new ThreadLocal<>();
    ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();
    
    SoftAssert soft;

    public WebDriver getDriver() {
        return driver1.get();
    }

    public WebDriverWait getWait() {
        return wait.get();
    }

    @Parameters({"url", "bow"})
    @BeforeMethod
    public void beforeMethod(String url, String bow) {

        if (bow.equalsIgnoreCase("firefox")) {
            driver1.set(new FirefoxDriver());
        } else if (bow.equalsIgnoreCase("edge")) {
            driver1.set(new EdgeDriver());
        }

        wait.set(new WebDriverWait(getDriver(), Duration.ofSeconds(10)));

        getDriver().manage().window().maximize();
        getDriver().get(url);
    }

    @AfterMethod
    public void aftermethod() {
        if (getDriver() != null) {
            getDriver().quit();
            driver1.remove();
            wait.remove();
        }
    }

    @Test(dataProvider ="validate", dataProviderClass = Provides.class)
    public void validation(String email, String pass) {
    	
        soft = new SoftAssert();   

        getDriver().findElement(By.id("login2")).click();

        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername"))).sendKeys(email);
        getDriver().findElement(By.id("loginpassword")).sendKeys(pass);

        getDriver().findElement(By.xpath("//button[text()='Log in']")).click();
        
        WebElement logout = getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id = 'logout2']")));
        String display = logout.getCssValue("display");
        
       
        soft.assertEquals("block", display);
        System.out.println("Valid user_name and password");
        
        soft.assertAll(); 

    }

    @Test(dataProvider ="invalidate", dataProviderClass = Provides.class)
    public void invalid(String cemails, String wpass) {
    	
    	soft = new SoftAssert();

        getDriver().findElement(By.id("login2")).click();

        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername"))).sendKeys(cemails);
        getDriver().findElement(By.id("loginpassword")).sendKeys(wpass);

        getDriver().findElement(By.xpath("//button[text()='Log in']")).click();

        getWait().until(ExpectedConditions.alertIsPresent());
        Alert alert = getDriver().switchTo().alert();
        String text = alert.getText();        
        alert.accept();
        soft.assertEquals("Wrong password.", text );
        System.out.print("The invalid Username and password");
        
        soft.assertAll();
        
        
    }
}