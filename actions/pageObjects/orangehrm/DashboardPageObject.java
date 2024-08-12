package pageObjects.orangehrm;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.orangehrm.DashboardPageUI;

public class DashboardPageObject extends BaseActions {
    private WebDriver driver;

    public DashboardPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public EmployeeListPageObject openPIMModule() {
        waitForElementClickable(driver, DashboardPageUI.PIM_MODULE);
        clickToElement(driver, DashboardPageUI.PIM_MODULE);
        waitForSpinnerIconInvisible();
        return PageGeneratorManager.getEmployeeListPage(driver);
    }
}
