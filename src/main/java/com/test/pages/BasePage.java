package com.test.pages;

import com.test.config.ConfigReader;
import com.test.utils.DriverManager;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
/*
BasePage - parent class for all the Page Objects
methods are reusable for all the pages
*/
public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(){
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplictWait()));
        PageFactory.initElements(driver,this);
    }
    
    protected WebElement waitForElement(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickable(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    protected void click(By locator){
        waitForClickable(locator).click();
    }

    protected void type(By locator, String text){
        WebElement element = waitForElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator){
        return waitForElement(locator).getText();
    }

    public boolean isDisplayed(By loacator){
        try{
            return driver.findElement(loacator).isDisplayed();
        }catch(Exception e){
            return false;
        }
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }  
}
