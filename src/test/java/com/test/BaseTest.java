package com.test;
import com.test.utils.DriverManager;
import org.testng.annotations.*;
import com.test.config.ConfigReader;


public class BaseTest {
    @BeforeMethod
    public void setUp(){
         DriverManager.initDriver();
         DriverManager.getDriver().get(ConfigReader.getBaseURL());
    }

    @AfterMethod
    public void tearDown(){
        DriverManager.quitDriver();
    }

}

