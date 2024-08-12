package pageObjects.orangehrm;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class ImmigrationPageObject extends BaseActions {
    private WebDriver driver;

    public ImmigrationPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}
