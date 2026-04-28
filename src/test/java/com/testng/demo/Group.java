package com.testng.demo;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Group {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setupBrowser() {

        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        options.addArguments("--width=1920");
        options.addArguments("--height=1080");

        driver = new FirefoxDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get("https://demoblaze.com/");
    }

    @BeforeMethod
    public void openLoginPopup() {

        wait.until(ExpectedConditions.elementToBeClickable(By.id("login2"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername"))).clear();
        driver.findElement(By.id("loginpassword")).clear();
    }

    @Test(priority = 1, groups = {"Smoke_Test"})
    public void validation() {

        wait.until(ExpectedConditions.elementToBeClickable(By.id("login2"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername"))).clear();
        driver.findElement(By.id("loginusername")).sendKeys("admin");

        driver.findElement(By.id("loginpassword")).clear();
        driver.findElement(By.id("loginpassword")).sendKeys("admin");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Log in']"))).click();
    }
    
    
    @Test(priority = 2, groups = {"invalid"}, dependsOnGroups = "Smoke_Test")
    public void invalid_with_username() {

        driver.findElement(By.id("loginusername")).sendKeys("1234");
        driver.findElement(By.id("loginpassword")).sendKeys("admin");

        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

    @Test(priority = 3, groups = {"invalid"}, dependsOnGroups = "Smoke_Test")
    public void invalid_with_prass() {

        driver.findElement(By.id("loginusername")).sendKeys("admin");
        driver.findElement(By.id("loginpassword")).sendKeys("788888");

        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

    @AfterMethod
    public void closeLoginPopup() {

        try {
            driver.findElement(By.xpath("//div[@id='logInModal']//button[@class='close']")).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("logInModal")));
        } catch (Exception e) {
            // ignore if already closed
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}