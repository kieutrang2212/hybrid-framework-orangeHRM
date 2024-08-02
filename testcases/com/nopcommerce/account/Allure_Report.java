package com.nopcommerce.account;

import commons.BaseTest;
import commons.PageGeneratorManager;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.user.HomePageObject;
import pageObjects.user.RegisterPageObject;


@Epic("Account")
@Feature("Create Account")
public class Allure_Report extends BaseTest {
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
        verifyFalse(homePage.isRegisterLinkDisplayed());

        registerPage =homePage.clickToRegisterLink();

        registerPage.clickToRegisterButton();

        verifyEquals(registerPage.getFirstNameErrorMessageText(),"First name is required.");

        verifyEquals(registerPage.getLastNameErrorMessageText(),"Last name is required");

        registerPage.enterToFirstNameTextbox(firstName);

        registerPage.enterToLastNameTextbox(lastName);

        registerPage.enterToEmailTextbox(emailAddress);

        registerPage.enterToPasswordTextbox(password);

        registerPage.enterToConfirmPasswordTextbox(password);

        registerPage.clickToRegisterButton();

        verifyEquals(registerPage.getRegisterSuccessMessageText(),"Your registration completed");

    }

    @AfterClass
    public void afterClass() {
        closedBrowser();
    }
}
