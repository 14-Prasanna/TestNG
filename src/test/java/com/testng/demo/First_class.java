package com.testng.demo;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;


import java.time.Duration;


public class First_class {

    WebDriver driver;
    WebDriverWait wait;

    @Parameters({"bow"})
    @BeforeMethod(alwaysRun = true)
    public void beforeTest(String bow) {

        switch (bow){
            case "firefox":
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless");
                options.addArguments("--width=1900");
                options.addArguments("--height=1500");

                driver = new FirefoxDriver(options);


                break;

            case "edge":
                EdgeOptions options1 = new EdgeOptions();
                options1.addArguments("--headless");
                options1.addArguments("--width=1900");
                options1.addArguments("--height=1500");

                driver = new EdgeDriver(options1);
                break;
        }

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get("https://demoblaze.com/");
    }

    @Parameters({"email", "pass"})
    @Test(priority = 1, groups = {"Smoke"})
    public void validation(String email, String pass) {

        System.out.println("Running Test: " + Thread.currentThread().getId()
                + " | Class: " + this.getClass().getSimpleName());


        driver.findElement(By.id("login2")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername"))).sendKeys(email);
        driver.findElement(By.id("loginpassword")).sendKeys(pass);

        driver.findElement(By.xpath("//button[text()='Log in']")).click();
    }

    @Test(priority = 2, expectedExceptions = {java.lang.Exception.class}, groups = {"reggresion"})
    public void invalid_with_username() throws Exception {

        System.out.println("Running Test: " + Thread.currentThread().getId()
                + " | Class: " + this.getClass().getSimpleName());

        driver.findElement(By.id("login2")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername"))).sendKeys("1234");
        driver.findElement(By.id("loginpassword")).sendKeys("admin");

        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();
        throw new Exception();
    }

    @Test(priority = 3, dependsOnMethods = "invalid_with_username", alwaysRun = true, groups = {"reggresion"})
    public void invalid_with_prass() {

        System.out.println("Running Test: " + Thread.currentThread().getId()
                + " | Class: " + this.getClass().getSimpleName());

        driver.findElement(By.id("login2")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername"))).sendKeys("admin");
        driver.findElement(By.id("loginpassword")).sendKeys("788888");

        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();
    }

    @AfterMethod(alwaysRun = true)
    public void afterTest() {
        driver.quit();
    }
}