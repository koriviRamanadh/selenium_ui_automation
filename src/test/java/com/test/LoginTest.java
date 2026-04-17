package com.test;

import com.test.pages.LoginPage;
import com.test.pages.ProductsPage;
import org.testng.annotations.*;
import org.testng.Assert;

import com.test.config.ConfigReader;
public class LoginTest extends BaseTest{

    @Test(description = "verify successful login with valid credentials")
    public void loginWithValidCredentials(){
        LoginPage login = new LoginPage();
        Assert.assertTrue(login.isLoginPageDisplayed());
        ProductsPage products = login.loginWithValidCredentials(ConfigReader.getUserName(),ConfigReader.getPassword());
        Assert.assertTrue(products.isProductsPageDisplayed());
    }

    @Test(description = "Verify error message on invalid login")
    public void testInvalidLogin(){
        LoginPage login = new LoginPage();
        Assert.assertTrue(login.isLoginPageDisplayed());
        login.loginWithValidCredentials(ConfigReader.getInvalidUsername(),ConfigReader.getInvalidPassword());
        Assert.assertTrue(login.isErrorDisplayed(),"Error message should be displayed");
        Assert.assertTrue(login.getErrorMessage().contains("Username and password do not match"), "Correct message should be displayed");
    }

    @Test(description = "Verify login with empty password")
    public void testEmptyPassword() {
        LoginPage loginPage = new LoginPage();
        loginPage.loginWithInvalidCredentials(ConfigReader.getUserName(), "");

        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error for empty password should show");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Password is required"));
    }

    @Test(description = "Verify login with empty username")
    public void testEmptyUsername() {
        LoginPage loginPage = new LoginPage();
        loginPage.loginWithInvalidCredentials("", ConfigReader.getPassword());
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error for empty username should show");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username is required"));
    }

    @Test(description = "Verify logout functionality")
    public void testLogout() {
        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = loginPage.loginWithValidCredentials(ConfigReader.getUserName(),ConfigReader.getPassword());
        productsPage.logout();
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Should return to login page after logout");
        //Uncomment this to check the screenshot feature 
        //Assert.assertTrue(false,"To showcase that screenshot is capturing");
    }


}
