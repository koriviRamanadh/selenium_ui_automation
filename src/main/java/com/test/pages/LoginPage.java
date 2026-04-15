package com.test.pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage{

    //Locators
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButtton = By.id("login-button");

    //Actions
    public void enterUsername(String username){
        type(usernameField, username);
    }

    public void enterPassword(String password){
        type(passwordField, password);
    }

    public void clickLogin(){
        click(loginButtton);
    }

    public ProductsPage loginWithValidCredentials(String username, String password){
        enterUsername(username);
        enterPassword(password);
        clickLogin();
        return new ProductsPage();
    }

    public void loginWithInvalidCredentials(String username, String password){
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
    
}
