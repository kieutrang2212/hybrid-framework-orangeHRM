package pageObjects.orangehrm;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class SalaryPageObject extends BaseActions {
    private WebDriver driver;

    public SalaryPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}
