package com.test;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import com.test.config.ConfigReader;
public class BaseTest {

    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeClass
    public void setUp(){
         driver = new ChromeDriver();
         wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
         driver.get(ConfigReader.getProperty("baseURL"));
    }

    @Test
    public void baseTest(){
        assertTrue(true);
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
