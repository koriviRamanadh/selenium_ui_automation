package com.test;

import org.testng.annotations.*;
import com.test.pages.ProductsPage;
import com.test.pages.Cartpage;
import com.test.config.ConfigReader;
import com.test.pages.LoginPage;
import org.testng.Assert;


public class CartTest extends BaseTest {

    private ProductsPage loginAndGetProductsPage(){
        LoginPage login = new LoginPage();
        return login.loginWithValidCredentials(ConfigReader.getUserName(),ConfigReader.getPassword());
    }

    @Test(description = "verify cart page opens correctly")
    public void testCartPageOpens(){
        ProductsPage productsPage = loginAndGetProductsPage();
        Cartpage cartPage = productsPage.goToCart();
        Assert.assertTrue(cartPage.isCartPageDisplayed(),"Cart page should be displayed");
    }

    @Test(description = "very item appears in cart after adding")
    public void testItemCart(){
        ProductsPage productsPage = loginAndGetProductsPage();
        productsPage.addFirstProductToCart();
        Cartpage cartPage = productsPage.goToCart();
        Assert.assertEquals(cartPage.getCartItemCount(), 1, "Cart should have i item");
    }

    @Test(description = "verify item can be removed from cart")
    public void testRemoveItemFromCart(){
        ProductsPage productsPage = loginAndGetProductsPage();
        productsPage.addFirstProductToCart();
        Cartpage cartPage = productsPage.goToCart();
        cartPage.removeFirstItem();
        Assert.assertEquals(cartPage.getCartItemCount(), 0,"Cart should be empty after removing item");
    }

    @Test(description = "verify multiple items can be added to cart")
    public void testMultipleItemsCart(){
        ProductsPage productsPage = loginAndGetProductsPage();
        productsPage.addProductToCart(0);
        productsPage.addProductToCart(1);
        Cartpage cartPage = productsPage.goToCart();
        Assert.assertEquals(cartPage.getCartItemCount(), 2, "cart should have 2 items");
    }
}
