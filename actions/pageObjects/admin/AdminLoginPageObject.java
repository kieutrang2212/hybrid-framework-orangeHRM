package pageObjects.admin;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.admin.AdminLoginPageUI;

public class AdminLoginPageObject extends BasePage {
    private WebDriver driver;

    public AdminLoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void enterToEmailTextbox(String emailAddress) {
        waitForElementVisible(driver, AdminLoginPageUI.EMAIL_ADDRESS_TEXTBOX);
        sendkeyToElement(driver, AdminLoginPageUI.EMAIL_ADDRESS_TEXTBOX,emailAddress);

    }

    public void enterToPasswordTextbox(String password) {
        waitForElementVisible(driver,AdminLoginPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver,AdminLoginPageUI.PASSWORD_TEXTBOX,password);
    }

    public AdminDashboardPageObject clickToLoginButton() {
        waitForElementClickable(driver,AdminLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver,AdminLoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getAdminDashboardPage(driver);
    }

    public AdminDashboardPageObject loginToAdmin(String emailAddress,String password){
        enterToEmailTextbox(emailAddress);
        enterToPasswordTextbox(password);
        return clickToLoginButton();
    }
}
