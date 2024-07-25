package com.nopcommerce.account;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.user.CustomerPageObject;
import pageObjects.user.HomePageObject;
import pageObjects.user.UserLoginPageObject;
import pageObjects.user.RegisterPageObject;

public class Account_01_Register extends BaseTest {
    private WebDriver driver;
    private HomePageObject homePage;
    private UserLoginPageObject loginPage;
    private CustomerPageObject customerPage;
    private RegisterPageObject registerPage;
    private String emailAddress= getEmailAddress();

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        homePage = PageGeneratorManager.getHomePage(driver);

    }

    @Test
    public void User_01_Register_Empty_Data() {
        registerPage= homePage.clickToRegisterLink();

        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getFirstNameErrorMessageText(),"First name is required.");
        Assert.assertEquals(registerPage.getLastNameErrorMessageText(),"Last name is required.");
        Assert.assertEquals(registerPage.getEmailErrorMessageText(),"Email is required.");
        Assert.assertEquals(registerPage.getConfirmPasswordErrorMessageText(),"Password is required.");
    }

    @Test
    public void User_02_Register_Invalid_Email() {
        homePage =registerPage.clickToNopCommerceLogo();

        registerPage =homePage.clickToRegisterLink();

        registerPage.enterToFirstNameTextbox("Trang");
        registerPage.enterToLastNameTextbox("Nguyen");
        registerPage.enterToEmailTextbox("trang@123@123");
        registerPage.enterToPasswordTextbox("123456");
        registerPage.enterToConfirmPasswordTextbox("123456");

        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getEmailErrorMessageText(),"Please enter a valid email address.");
    }

    @Test
    public void User_03_Register_Invalid_Password() {
        homePage =registerPage.clickToNopCommerceLogo();

        registerPage =homePage.clickToRegisterLink();

        registerPage.enterToFirstNameTextbox("Trang");
        registerPage.enterToLastNameTextbox("Nguyen");
        registerPage.enterToEmailTextbox("trang342@gnail.com");
        registerPage.enterToPasswordTextbox("123");
        registerPage.enterToConfirmPasswordTextbox("123");

        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getPasswordErrorMessageText(),"<p>Password must meet the following rules: </p><ul><li>must have at least 6 characters and not greater than 64 characters</li></ul>");
    }

    @Test
    public void User_04_Register_Incorrect_Cofirm_Password() {
        homePage =registerPage.clickToNopCommerceLogo();

        registerPage =homePage.clickToRegisterLink();

        registerPage.enterToFirstNameTextbox("Trang");
        registerPage.enterToLastNameTextbox("Nguyen");
        registerPage.enterToEmailTextbox("trang342@gmail.com");
        registerPage.enterToPasswordTextbox("123456");
        registerPage.enterToConfirmPasswordTextbox("456789");

        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getConfirmPasswordErrorMessageText(),"The password and confirmation password do not match.");
    }

    @Test
    public void User_05_Register_Success() {
        homePage =registerPage.clickToNopCommerceLogo();

        registerPage =homePage.clickToRegisterLink();


        registerPage.enterToFirstNameTextbox("  Trang");
        registerPage.enterToLastNameTextbox("Nguyen");
        registerPage.enterToEmailTextbox(emailAddress);
        registerPage.enterToPasswordTextbox("123456");
        registerPage.enterToConfirmPasswordTextbox("123456");

        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getRegisterSuccessMessageText(),"Your registration completed");
    }

    @Test
    public void User_06_Login_Success(){
        homePage =registerPage.clickToNopCommerceLogo();

        homePage.clickToLogoutLink();
        loginPage =homePage.clickToLoginLink();

        loginPage.enterToEmailTextbox(emailAddress);
        loginPage.enterToPasswordTextbox("123456");

        homePage =loginPage.clickToLoginButton();

        customerPage =homePage.clickToMyAccountLink();



        Assert.assertEquals(customerPage.getFirstNameTextboxAttributeValue(),"Trang");
        Assert.assertEquals(customerPage.getLastNameTextboxAttributeValue(),"Nguyen");
        Assert.assertEquals(customerPage.getEmailAddressTextboxAttributeValue(),emailAddress);
    }

    @AfterClass
    public void afterClass() {
        closedBrowser();
    }
}
