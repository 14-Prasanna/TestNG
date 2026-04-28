package com.testng.demo;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class DatasProvides {

    public static ThreadLocal<WebDriver> driver1 = new ThreadLocal<>();
    WebDriverWait wait;

    public WebDriver getDriver() {
        return driver1.get();
    }

    @BeforeMethod
    public void beforemethod() {

        driver1.set(new FirefoxDriver());

        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

        getDriver().manage().window().maximize();
        getDriver().get("https://demoblaze.com/");
    }

    @AfterMethod
    public void aftermethod() {
        if(getDriver() != null) {
            getDriver().quit();
            driver1.remove();
        }
    }
    

    @Test(dataProvider = "validate", dataProviderClass = Datassss.class)
    public void validation(String email, String pass) {

        getDriver().findElement(By.id("login2")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername"))).sendKeys(email);
        getDriver().findElement(By.id("loginpassword")).sendKeys(pass);

        getDriver().findElement(By.xpath("//button[text()='Log in']")).click();
    }
}