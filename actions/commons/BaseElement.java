package commons;

import org.openqa.selenium.WebDriver;
import pageObjects.user.HomePageObject;
import pageUIs.user.BaseElementUI;
import pageUIs.user.RegisterPageUI;

public class BaseElement extends BasePage {
    WebDriver driver;

    public BaseElement(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToHeaderLinkByName(String pageName){
        waitForElementClickable(driver,BaseElementUI.DYNAMIC_HEADER_LINK_BY_NAME,pageName);
        clickToElement(driver,BaseElementUI.DYNAMIC_HEADER_LINK_BY_NAME,pageName);
    }

    public HomePageObject clickToNopCommerceLogo() {
        waitForElementClickable(driver, BaseElementUI.NOPCOMMERCE_LOGO);
        clickToElement(driver,BaseElementUI.NOPCOMMERCE_LOGO);
        return new HomePageObject(driver);
    }

    public void clickToButtonByText(String buttonText){
        waitForElementClickable(driver,BaseElementUI.DYNAMIC_BUTTON_BY_TEXT,buttonText);
        clickToElement(driver,BaseElementUI.DYNAMIC_BUTTON_BY_TEXT,buttonText);
    }

    public String getTextboxErrorMessageById(String errorMessageId){
        waitForElementVisible(driver,BaseElementUI.DYNAMIC_TEXTBOX_ERROR_MSG_BY_ID,errorMessageId);
        return getElementText(driver,BaseElementUI.DYNAMIC_TEXTBOX_ERROR_MSG_BY_ID,errorMessageId);
    }

    public void enterToTextboxById(String textboxId,String valueToSendkey){
        waitForElementVisible(driver,BaseElementUI.DYNAMIC_TEXTBOX_BY_ID,textboxId);
        sendkeyToElement(driver,BaseElementUI.DYNAMIC_TEXTBOX_BY_ID,valueToSendkey,textboxId);
    }

    public String getTextboxAttributeValueById(String textboxId){
        waitForElementVisible(driver,BaseElementUI.DYNAMIC_TEXTBOX_BY_ID,textboxId);
        return getElementAttribute(driver,BaseElementUI.DYNAMIC_TEXTBOX_BY_ID,"value",textboxId);
    }
}
