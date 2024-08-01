package com.nopcommerce.account;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.user.HomePageObject;
import pageObjects.user.RegisterPageObject;

public class Log extends BaseTest {
    private WebDriver driver;
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private String emailAddress= getEmailAddress();
    private String firstName, lastName,password;

    @Parameters({"browser"})
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        homePage = PageGeneratorManager.getHomePage(driver);

        firstName="Trang";
        lastName="Nguyen";
        password="123456";
    }

    @Test
    public void User_01_Register_Success() {
        log.info("Register - Step 01: Verify Register link is displayed");
        verifyFalse(homePage.isRegisterLinkDisplayed());

        log.info("Register - Step 02: Click to Register link");
        registerPage =homePage.clickToRegisterLink();

        log.info("Register - Step 03: Click to Register button");
        registerPage.clickToRegisterButton();

        log.info("Register - Step 04: Verify error message at FirstName textbox");
        verifyEquals(registerPage.getFirstNameErrorMessageText(),"First name is required.");

        log.info("Register - Step 05: Verify error message at LastName textbox");
        verifyEquals(registerPage.getLastNameErrorMessageText(),"Last name is required");

        log.info("Register - Step 06: Enter to FirstName textbox"+firstName);
        registerPage.enterToFirstNameTextbox(firstName);

        log.info("Register - Step 07: Enter to LastName textbox"+lastName);
        registerPage.enterToLastNameTextbox(lastName);

        log.info("Register - Step 08: Enter to Email Address textbox "+emailAddress);
        registerPage.enterToEmailTextbox(emailAddress);

        log.info("Register - Step 09: Enter to Password textbox "+password);
        registerPage.enterToPasswordTextbox(password);

        log.info("Register - Step 10: Enter to Confirm Password textbox "+password);
        registerPage.enterToConfirmPasswordTextbox(password);

        log.info("Register - Step 11: Click to Register button");
        registerPage.clickToRegisterButton();

        log.info("Register - Step 12: Verify success message is displayed");
        verifyEquals(registerPage.getRegisterSuccessMessageText(),"Your registration completed");

    }

    @AfterClass
    public void afterClass() {
        closedBrowser();
    }
}
