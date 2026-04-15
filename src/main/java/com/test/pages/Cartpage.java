package com.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class Cartpage extends BasePage{
    
    private final By cartItems = By.className("cart_item");
    private final By removeButtons = By.xpath("//button[text() = 'remove']");
    private final By checkoutButton = By.id("checkout");
    private final By continueButton = By.id("continue-shopping");
    private final By itemNames = By.className("inventory_item_name");

    public int getCartItemcount(){
        return driver.findElements(cartItems).size();
    }

    public List<WebElement> getCartItemNames(){
        return driver.findElements(itemNames);
    }

    public void removeItems(int index){
        List<WebElement> buttons = driver.findElements(removeButtons);
        if(!buttons.isEmpty()){
            buttons.get(index).click();
        }
    }

    public void clickCheckout(){
        click(checkoutButton);
    }

    public void continueShopping(){
        click(continueButton);
    }
}
