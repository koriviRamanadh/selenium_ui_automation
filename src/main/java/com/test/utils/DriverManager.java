package com.test.utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.test.config.ConfigReader;

public class DriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver(){
        String browser = ConfigReader.getProperty("browser").toLowerCase();
        WebDriver webDriver;

        switch(browser){
            case "firefox":
                webDriver = new FirefoxDriver();
                break;
            case "chrome" :
            default:
                webDriver = new ChromeDriver();
                break;
        }
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ConfigReader.getPageLoadTimeouts()));
        webDriver.manage().window().maximize();

        driver.set(webDriver);
    }

    public static WebDriver getDriver(){
        if(driver.get() == null){
            throw new RuntimeException("WebDriver not initialized call initDriver() first");
        }
        return driver.get();
    }

    public static void quitDriver(){
        if(driver.get() != null){
            driver.get().quit();
            driver.remove();
        }
    }
}
