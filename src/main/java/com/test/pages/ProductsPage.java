package com.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import org.openqa.selenium.support.ui.Select;

public class ProductsPage extends BasePage {

    //Locators
    private final By pageTitle        = By.className("title");
    private final By productNames = By.className("inventory_item_name");
    private final By addToCartButtons = By.xpath("//button[text()='Add to cart']");
    //private final By cartBadge = By.className("shopping_cart_badge");
    private final By cartIcon = By.className("shoppin_cart_link");
    private final By sortDropDown = By.className("product_sort_container");
    private final By menu = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");

    //Actions
    public List<WebElement> getAllProducts(){
        return driver.findElements(productNames);
    }

    public int getProductCount(){
        return getAllProducts().size();
    }

    public void addProductTocart(int index){
        List<WebElement> buttons = driver.findElements(addToCartButtons);
        if(index < buttons.size()){
            buttons.get(index).click();
        }
    }

    public void selectSortOption(String visibleText){
        new Select(driver.findElement(sortDropDown)).selectByContainsVisibleText(visibleText);
    }

    public Cartpage goToCart(){
        click(cartIcon);
        return new Cartpage();
    }

    public void logout(){
        click(menu);
        click(logoutLink);
    }

    //Verifications
    public boolean isProductsPageDisplayed() {
        return isDisplayed(pageTitle) && getText(pageTitle).equals("Products");
    }
}
