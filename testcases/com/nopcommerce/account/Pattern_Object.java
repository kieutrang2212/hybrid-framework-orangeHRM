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
import pageObjects.user.RegisterPageObject;
import pageObjects.user.UserLoginPageObject;

public class Pattern_Object extends BaseTest {
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
        homePage.clickToHeaderLinkByName("Register");
        registerPage=PageGeneratorManager.getRegisterPage(driver);

        registerPage.clickToButtonByText("Register");

        Assert.assertEquals(registerPage.getTextboxErrorMessageById("LastName"),"Last name is required.");
        Assert.assertEquals(registerPage.getTextboxErrorMessageById("Email"),"Email is required.");
        Assert.assertEquals(registerPage.getTextboxErrorMessageById("ConfirmPassword"),"Password is required.");
    }

    @Test
    public void User_02_Register_Invalid_Email() {
        homePage =registerPage.clickToNopCommerceLogo();

        homePage.clickToHeaderLinkByName("Register");
        registerPage=PageGeneratorManager.getRegisterPage(driver);

        registerPage.enterToTextboxById("FirstName","Trang");
        registerPage.enterToTextboxById("LastName","Nguyen");
        registerPage.enterToTextboxById("Email","trang@123@123");
        registerPage.enterToTextboxById("Password","123456");
        registerPage.enterToTextboxById("ConfirmPassword","123456");


        registerPage.clickToButtonByText("Register");

        Assert.assertEquals(registerPage.getTextboxErrorMessageById("Email"),"Please enter a valid email address.");
    }

    @Test
    public void User_03_Register_Invalid_Password() {
        homePage =registerPage.clickToNopCommerceLogo();

        homePage.clickToHeaderLinkByName("Register");
        registerPage=PageGeneratorManager.getRegisterPage(driver);

        registerPage.enterToTextboxById("FirstName","Trang");
        registerPage.enterToTextboxById("LastName","Nguyen");
        registerPage.enterToTextboxById("Email","trang342@gnail.com");
        registerPage.enterToTextboxById("Password","123");
        registerPage.enterToTextboxById("ConfirmPassword","123");

        registerPage.clickToButtonByText("Register");;

        Assert.assertEquals(registerPage.getTextboxErrorMessageById("ConfirmPassword"),"<p>Password must meet the following rules: </p><ul><li>must have at least 6 characters and not greater than 64 characters</li></ul>");
    }

    @Test
    public void User_04_Register_Incorrect_Cofirm_Password() {
        homePage =registerPage.clickToNopCommerceLogo();

        homePage.clickToHeaderLinkByName("Register");
        registerPage=PageGeneratorManager.getRegisterPage(driver);


        registerPage.enterToTextboxById("FirstName","Trang");
        registerPage.enterToTextboxById("LastName","Nguyen");
        registerPage.enterToTextboxById("Email","trang342@gnail.com");
        registerPage.enterToTextboxById("Password","123456");
        registerPage.enterToTextboxById("ConfirmPassword","456123");


        registerPage.clickToButtonByText("Register");

        Assert.assertEquals(registerPage.getTextboxErrorMessageById("ConfirmPassword"),"The password and confirmation password do not match.");
    }

    @Test
    public void User_05_Register_Success() {
        homePage =registerPage.clickToNopCommerceLogo();

        homePage.clickToHeaderLinkByName("Register");
        registerPage=PageGeneratorManager.getRegisterPage(driver);

        registerPage.enterToTextboxById("FirstName","Trang");
        registerPage.enterToTextboxById("LastName","Nguyen");
        registerPage.enterToTextboxById("Email",emailAddress);
        registerPage.enterToTextboxById("Password","123456");
        registerPage.enterToTextboxById("ConfirmPassword","123456");

        registerPage.clickToButtonByText("Register");

        Assert.assertEquals(registerPage.getRegisterSuccessMessageText(),"Your registration completed");
    }

    @Test
    public void User_06_Login_Success(){
        homePage =registerPage.clickToNopCommerceLogo();

        homePage.clickToHeaderLinkByName("Log out");

        homePage.clickToHeaderLinkByName("Log in");
        loginPage=PageGeneratorManager.getUserLoginPage(driver);

        loginPage.enterToTextboxById("Email",emailAddress);
        loginPage.enterToTextboxById("Password","123456");

        loginPage.clickToButtonByText("Log in");
        homePage =PageGeneratorManager.getHomePage(driver);

        homePage.clickToHeaderLinkByName("My account");
        customerPage=PageGeneratorManager.getCustomerPage(driver);

        Assert.assertEquals(customerPage.getTextboxAttributeValueById("value"),"Trang");
        Assert.assertEquals(customerPage.getTextboxAttributeValueById("value"),"Nguyen");
        Assert.assertEquals(customerPage.getTextboxAttributeValueById("value"),emailAddress);
    }

    @AfterClass
    public void afterClass() {
        closedBrowser();
    }
}
