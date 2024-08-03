package com.facebook.home;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.facebook.HomePageObject;
import pageObjects.facebook.PageGeneratorManager;

public class Element_Undisplayed extends BaseTest {
    private WebDriver driver;
    private HomePageObject homePage;

    @Parameters({"browser","url"})
    @BeforeClass
    public void beforeClass(String browserName, String urlValue) {
        driver = getBrowserDriver(browserName,urlValue);

        homePage = PageGeneratorManager.getHomePage(driver);
    }

    @Test
    public void Home_01_Element_Displayed() {
        homePage.clickToCreateNewAccountButton();

        log.info("Verify FirstName textbox is displayed");
        verifyTrue(homePage.isFirstNameTextboxDisplayed());

        log.info("Verify LastName textbox is displayed");
        verifyTrue(homePage.isSurNameTextboxDisplayed());

        log.info("Verify Email textbox is displayed");
        verifyTrue(homePage.isEmailTextboxDisplayed());

        log.info("Verify Password textbox is displayed");
        verifyTrue(homePage.isPasswordTextboxDisplayed());

        homePage.enterToEmailTextbox("trang@gmail.com");

        log.info("Verify Confirm Email textbox is displayed");
        verifyTrue(homePage.isConfirmEmailTextboxDisplayed());
    }

    @Test
    public void Home_02_Element_Undisplayed_Displayed_In_HTML() {
        homePage.enterToEmailTextbox("");
        homePage.sleepInSeconds(3);

        log.info("Verify Confirm Email textbox is not displayed");
        verifyTrue(homePage.isConfirmEmailTextboxUnDisplayed());

    }

    @Test
    public void Home_03_Element_Undisplayed_Displayed_Not_In_HTML() {
        homePage.clickToCloseSignUpPopup();

        log.info("Verify FirstName textbox is not displayed");
        verifyTrue(homePage.isFirstNameTextboxUnDisplayed());

        log.info("Verify LastName textbox is not displayed");
        verifyTrue(homePage.isLastNameTextboxUnDisplayed());

        log.info("Verify Email textbox is not displayed");
        verifyTrue(homePage.isEmailTextboxUnDisplayed());

        log.info("Verify Password textbox is not displayed");
        verifyTrue(homePage.isPasswordTextboxUnDisplayed());

    }

    @AfterClass
    public void afterClass() {
        closedBrowser();
    }
}
