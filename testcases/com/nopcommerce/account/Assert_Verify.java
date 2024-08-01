package com.nopcommerce.account;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.admin.AdminDashboardPageObject;
import pageObjects.admin.AdminLoginPageObject;
import pageObjects.user.HomePageObject;
import pageObjects.user.RegisterPageObject;
import pageObjects.user.UserLoginPageObject;

public class Assert_Verify extends BaseTest {
    private WebDriver driver;
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private String emailAddress= getEmailAddress();

    @Parameters({"browser"})
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName);

        homePage = PageGeneratorManager.getHomePage(driver);

    }

    @Test
    public void User_01_Register_Success() {
        verifyFalse(homePage.isRegisterLinkDisplayed());

        registerPage =homePage.clickToRegisterLink();

        registerPage.clickToRegisterButton();

        verifyEquals(registerPage.getFirstNameErrorMessageText(),"First name is required.");

        verifyEquals(registerPage.getLastNameErrorMessageText(),"Last name is required");

        registerPage.enterToFirstNameTextbox("  Trang");
        registerPage.enterToLastNameTextbox("Nguyen");
        registerPage.enterToEmailTextbox(emailAddress);
        registerPage.enterToPasswordTextbox("123456");
        registerPage.enterToConfirmPasswordTextbox("123456");

        registerPage.clickToRegisterButton();

        verifyEquals(registerPage.getRegisterSuccessMessageText(),"Your registration completed");

    }

    @AfterClass
    public void afterClass() {
        closedBrowser();
    }
}
