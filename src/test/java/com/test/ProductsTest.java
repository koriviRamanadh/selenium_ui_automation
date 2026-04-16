package com.test;

import com.test.config.ConfigReader;
import com.test.pages.LoginPage;
import com.test.pages.ProductsPage;
import org.testng.annotations.*;
import org.testng.Assert;

public class ProductsTest {

    private ProductsPage loginAndGetProductsPage(){
        LoginPage login = new LoginPage();
        return login.loginWithValidCredentials(ConfigReader.getUserName(),ConfigReader.getPassword());
    }

    @Test(description = "Verify products are displayed on products page")
    public void testProductsDisplayed(){
        ProductsPage productsPage = loginAndGetProductsPage();
        int count = productsPage.getProductCount();
        Assert.assertTrue(count > 0, "At least one product should be displayed");
    }

    @Test(description = "verify cart badge updates when product is added")
    public void testAddProductToCart(){
        ProductsPage productsPage = loginAndGetProductsPage();
        productsPage.addFirstProductToCart();
        String badgeCount = productsPage.getCartBadgeCount();
        Assert.assertEquals(badgeCount, "1", "cart badege should show 1 after adding a product");
    }

    @Test(description = "verify products sort by price Low to high")
    public void testSortProductsByPriceLowToHigh(){
        ProductsPage productsPage = loginAndGetProductsPage();
        productsPage.selectSortOption("Price (low to high)");
        Assert.assertTrue(productsPage.isProductsPageDisplayed(), "Page should remain on products after sorting");
    }

    @Test(description = "verify products sort by name A to Z")
    public void testSortProductsByNameAZ(){
        ProductsPage productsPage = loginAndGetProductsPage();
        productsPage.selectSortOption("Name (A to Z");
        Assert.assertTrue(productsPage.isProductsPageDisplayed(),"Page should remain the same on products after sorting");
    }
}
