package com.nopcommerce.cookie;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.user.CustomerPageObject;
import pageObjects.user.HomePageObject;
import pageObjects.user.RegisterPageObject;
import pageObjects.user.UserLoginPageObject;

import java.util.Set;

public class Product extends BaseTest {
    private WebDriver driver;
    private HomePageObject homePage;
    private UserLoginPageObject loginPage;
    private CustomerPageObject customerPage;

    @Parameters("browser")
    @BeforeTest
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        homePage = PageGeneratorManager.getHomePage(driver);

        loginPage =homePage.clickToLoginLink();

        loginPage.setCookies(driver,Common_Register.cookies);
        loginPage.sleepInSeconds(5);
        loginPage.refreshToCurrentPage(driver);

        customerPage =homePage.clickToMyAccountLink();

        Assert.assertEquals(customerPage.getFirstNameTextboxAttributeValue(),Common_Register.firstName);
        Assert.assertEquals(customerPage.getLastNameTextboxAttributeValue(),Common_Register.lastName);
        Assert.assertEquals(customerPage.getEmailAddressTextboxAttributeValue(),Common_Register.emailAddress);
    }

    @Test
    public void Product_01_Create_New_Product(){

    }

    @Test
    public void Product_02_Edit_Product(){

    }

    @Test
    public void Product_03_Delete_Product(){

    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closedBrowser();
    }
}
