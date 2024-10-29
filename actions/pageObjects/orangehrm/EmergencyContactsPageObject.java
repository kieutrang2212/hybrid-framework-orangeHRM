package pageObjects.orangehrm;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.jquery.HomePageUI;
import pageUIs.orangehrm.EmergencyContactsPageUI;
import pageUIs.orangehrm.PersonalDetailsPageUI;

public class EmergencyContactsPageObject extends BaseActions {
    private WebDriver driver;

    public EmergencyContactsPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void clickToAddButtonAtAssignedEmergencyContacts() {
        waitForElementClickable(driver, EmergencyContactsPageUI.ADD_BUTTON_AT_ASSIGNED_EMERGEENCY_CONTACT);
        clickToElement(driver, EmergencyContactsPageUI.ADD_BUTTON_AT_ASSIGNED_EMERGEENCY_CONTACT);
        waitForSpinnerIconInvisible();
    }

    public void enterToNameOfRelativeTextbox(String nameOfRelative) {
        waitForElementVisible(driver,EmergencyContactsPageUI.NAME_OF_RELATIVE_TEXTBOX);
        sendkeyToElement(driver,EmergencyContactsPageUI.NAME_OF_RELATIVE_TEXTBOX,nameOfRelative);
    }

    public void enterToRelationshipTextbox(String relationship) {
        waitForElementVisible(driver,EmergencyContactsPageUI.RELATIONSHIP_TEXTBOX);
        sendkeyToElement(driver,EmergencyContactsPageUI.RELATIONSHIP_TEXTBOX,relationship);
    }

    public void enterToHomeTelephoneOfRelativeTextbox(String homeTelephoneOfRalative) {
        waitForElementVisible(driver,EmergencyContactsPageUI.HOMETELEPHONE_OF_RELATIVE_TEXTBOX);
        sendkeyToElement(driver,EmergencyContactsPageUI.HOMETELEPHONE_OF_RELATIVE_TEXTBOX,homeTelephoneOfRalative);
    }

    public void clickToSaveAtSaveEmergencyContact() {
        waitForElementClickable(driver,EmergencyContactsPageUI.SAVE_BUTTON_AT_EMERGEENCY_CONTACT);
        clickToElement(driver,EmergencyContactsPageUI.SAVE_BUTTON_AT_EMERGEENCY_CONTACT);
    }

    public String getNameOfRelativeValue() {
        waitForElementVisible(driver, EmergencyContactsPageUI.NAME_OF_RELATIVE_TEXTBOX);
        return getElementAttribute(driver, EmergencyContactsPageUI.NAME_OF_RELATIVE_TEXTBOX,"value");
    }

    public String getRelationshipValue() {
        waitForElementVisible(driver, EmergencyContactsPageUI.RELATIONSHIP_TEXTBOX);
        return getElementAttribute(driver, EmergencyContactsPageUI.RELATIONSHIP_TEXTBOX,"value");
    }

    public String getHomeTelephoneOfRelativeValue() {
        waitForElementVisible(driver, EmergencyContactsPageUI.HOMETELEPHONE_OF_RELATIVE_TEXTBOX);
        return getElementAttribute(driver, EmergencyContactsPageUI.HOMETELEPHONE_OF_RELATIVE_TEXTBOX,"value");
    }

    public boolean isRowValuesDisplayed(String name,String relationnship,String homeTelephone){
        waitForElementVisible(driver, HomePageUI.DYNAMIC_ROW_VALUES,name,relationnship,homeTelephone);
        return isElementDisplayed(driver,HomePageUI.DYNAMIC_ROW_VALUES,name,relationnship,homeTelephone);
    }
}
