package pageObjects.user;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.user.HomePageUI;

public class HomePageObject extends BasePage {
    WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click to Register link")
    public RegisterPageObject clickToRegisterLink() {
        waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
        clickToElement(driver,HomePageUI.REGISTER_LINK);
        return PageGeneratorManager.getRegisterPage(driver);
    }

    @Step("Click to Login link")
    public UserLoginPageObject clickToLoginLink() {
        waitForElementClickable(driver,HomePageUI.LOGIN_LINK);
        clickToElement(driver,HomePageUI.LOGIN_LINK);
        return PageGeneratorManager.getUserLoginPage(driver);
    }

    @Step("Click to My Account link")
    public CustomerPageObject clickToMyAccountLink() {
        waitForElementClickable(driver,HomePageUI.MY_ACCOUNT_LINK);
        clickToElement(driver,HomePageUI.MY_ACCOUNT_LINK);
        return PageGeneratorManager.getCustomerPage(driver);
    }

    @Step("Click to Logout link")
    public void clickToLogoutLink() {
        waitForElementClickable(driver,HomePageUI.LOGOUT_LINK);
        clickToElement(driver,HomePageUI.LOGOUT_LINK);
    }

    @Step("Verify the register link is displayed")
    public boolean isRegisterLinkDisplayed() {
        waitForElementVisible(driver,HomePageUI.REGISTER_LINK);
        return isElementDisplayed(driver,HomePageUI.REGISTER_LINK);
    }
}
