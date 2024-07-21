package pageObjects.user;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.user.MyAccountSideBarPageUI;

public class MyAccountSideBarPageObject extends BasePage {
    WebDriver driver;

    public MyAccountSideBarPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public AddressPageObject openAddressPage(){
        waitForElementClickable(driver, MyAccountSideBarPageUI.ADDRESS_LINK_TEXT);
        clickToElement(driver, MyAccountSideBarPageUI.ADDRESS_LINK_TEXT);
        return PageGeneratorManager.getAddressPage(driver);
    }

    public OrderPageObject openOrderPage(){
        waitForElementClickable(driver, MyAccountSideBarPageUI.ORDER_LINK_TEXT);
        clickToElement(driver, MyAccountSideBarPageUI.ORDER_LINK_TEXT);
        return PageGeneratorManager.getOrderPage(driver);
    }

    public RewardPointPageObject openRewardPointPage(){
        waitForElementClickable(driver, MyAccountSideBarPageUI.REWARD_POINT_LINK_TEXT);
        clickToElement(driver, MyAccountSideBarPageUI.REWARD_POINT_LINK_TEXT);
        return PageGeneratorManager.getRewardPointPage(driver);
    }

    public CustomerPageObject openCustomerPage(){
        waitForElementClickable(driver, MyAccountSideBarPageUI.CUSTOMER_LINK_TEXT);
        clickToElement(driver, MyAccountSideBarPageUI.CUSTOMER_LINK_TEXT);
        return PageGeneratorManager.getCustomerPage(driver);
    }




}
