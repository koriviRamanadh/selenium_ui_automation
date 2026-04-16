package com.test.utils;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.test.config.ConfigReader;

public class DriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

     private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();

        //  Disable Google Password Manager breach detection popup
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false); // this disables the breach warning

        options.setExperimentalOption("prefs", prefs);

        //  Also suppress general Chrome password popups
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-features=PasswordCheck");

        return options;
    }


    public static void initDriver(){
        String browser = ConfigReader.getProperty("browser").toLowerCase();
        WebDriver webDriver;

        switch(browser){
            case "firefox":
                webDriver = new FirefoxDriver();
                break;
            case "chrome" :
            default:
                webDriver = new ChromeDriver(getChromeOptions());
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
