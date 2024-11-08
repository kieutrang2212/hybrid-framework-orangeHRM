package pageObjects.jquery;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.jquery.UploadPageUI;

import java.util.List;

public class UploadPageObject extends BasePage {
    WebDriver driver;

    public UploadPageObject(WebDriver driver) {
        this.driver = driver;
    }


    public boolean isFileLoadedSuccess(String fileName) {
        waitForElementVisible(driver, UploadPageUI.FILE_LOADED_BY_NAME,fileName);
        return isElementDisplayed(driver, UploadPageUI.FILE_LOADED_BY_NAME,fileName);
    }

    public void clickToStartButtonOnEachFile() {
        List<WebElement> startButtons= getListWebElement(driver,UploadPageUI.MULTIPLE_START_BUTTON);
        for (WebElement button: startButtons){
            waitForElementClickable(driver,button);
            clickToElement(driver,button);
        }
    }

    public boolean isFileUpLoadedSuccess(String fileName) {
        waitForElementVisible(driver, UploadPageUI.FILE_LOADED_BY_NAME,fileName);
        return isElementDisplayed(driver, UploadPageUI.FILE_LOADED_BY_NAME,fileName);
    }
}
