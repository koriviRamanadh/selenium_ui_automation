package com.test;
import com.test.utils.DriverManager;
import com.test.utils.Screenshot;

import org.testng.annotations.*;
import com.test.config.ConfigReader;
import org.testng.ITestResult;



public class BaseTest {
    @BeforeMethod
    public void setUp(){
         DriverManager.initDriver();
         DriverManager.getDriver().get(ConfigReader.getBaseURL());
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        if(ITestResult.FAILURE == result.getStatus()){
            Screenshot.attachScreenshot(DriverManager.getDriver(), result.getName());
        }
        DriverManager.quitDriver();
    }

}

