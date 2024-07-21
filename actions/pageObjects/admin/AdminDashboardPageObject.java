package pageObjects.admin;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.admin.AdminDashboardUI;

public class AdminDashboardPageObject extends BasePage {
    private WebDriver driver;

    public AdminDashboardPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public AdminLoginPageObject clickToLogoutLink() {
        waitForElementClickable(driver, AdminDashboardUI.LOGOUT_LINK);
        clickToElement(driver, AdminDashboardUI.LOGOUT_LINK);
        return PageGeneratorManager.getAdminLoginPage(driver);
    }
}
