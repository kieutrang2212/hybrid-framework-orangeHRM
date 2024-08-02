//package com.nopcommerce.account;
//
//import com.aventstack.extentreports.Status;
//import commons.BaseTest;
//import commons.PageGeneratorManager;
//import org.openqa.selenium.WebDriver;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Parameters;
//import org.testng.annotations.Test;
//import pageObjects.user.HomePageObject;
//import pageObjects.user.RegisterPageObject;
//import reportConfig.ExtentTestManager;
//
//import java.lang.reflect.Method;
//
//public class Extent_V5 extends BaseTest {
//    private WebDriver driver;
//    private HomePageObject homePage;
//    private RegisterPageObject registerPage;
//    private String emailAddress= getEmailAddress();
//    private String firstName, lastName,password;
//
//    @Parameters({"browser"})
//    @BeforeClass
//    public void beforeClass(String browserName) {
//        driver = getBrowserDriver(browserName);
//        homePage = PageGeneratorManager.getHomePage(driver);
//
//        firstName="Trang";
//        lastName="Nguyen";
//        password="123456";
//    }
//
//    @Test
//    public void User_01_Register_Success(Method method) {
//        ExtentTestManager.startTest(method.getName(), "Register Success");
//        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 01: Navigate to 'Register' page");
//
//
//        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 01: Verify Register link is displayed");
//        verifyFalse(homePage.isRegisterLinkDisplayed());
//
//        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 02: Click to Register link");
//        registerPage =homePage.clickToRegisterLink();
//
//        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 03: Click to Register button");
//        registerPage.clickToRegisterButton();
//
//        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 04: Verify error message at FirstName textbox");
//        verifyEquals(registerPage.getFirstNameErrorMessageText(),"First name is required.");
//
//        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 05: Verify error message at LastName textbox");
//        verifyEquals(registerPage.getLastNameErrorMessageText(),"Last name is required");
//
//        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 06: Enter to FirstName textbox"+firstName);
//        registerPage.enterToFirstNameTextbox(firstName);
//
//        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 07: Enter to LastName textbox"+lastName);
//        registerPage.enterToLastNameTextbox(lastName);
//
//        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 08: Enter to Email Address textbox "+emailAddress);
//        registerPage.enterToEmailTextbox(emailAddress);
//
//        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 09: Enter to Password textbox "+password);
//        registerPage.enterToPasswordTextbox(password);
//
//        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 10: Enter to Confirm Password textbox "+password);
//        registerPage.enterToConfirmPasswordTextbox(password);
//
//        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 11: Click to Register button");
//        registerPage.clickToRegisterButton();
//
//        ExtentTestManager.getTest().log(Status.INFO, "Register - Step 12: Verify success message is displayed");
//        verifyEquals(registerPage.getRegisterSuccessMessageText(),"Your registration completed");
//
//    }
//
//    @AfterClass
//    public void afterClass() {
//        closedBrowser();
//    }
//}
