package com.listeners;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners extends LoginDemo implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started : " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed : " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed : " + result.getName());

        try {
            captureScreenshot(result.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void captureScreenshot(String testName) throws IOException {

        WebDriver driver = driver1.get();

        // Absolute folder path
        String folderPath = "D:\\TestNG\\com.testng\\screenshots";

        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        File destination = new File(folderPath + "\\" + testName + ".png");

        FileUtils.copyFile(source, destination);

        System.out.println("Screenshot Captured Successfully : " + destination.getAbsolutePath());
    }
}