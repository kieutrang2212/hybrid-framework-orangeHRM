package pageObjects.orangehrm;

import org.openqa.selenium.WebDriver;
import pageUIs.orangehrm.ContactDetailsPagUI;
import pageUIs.orangehrm.PersonalDetailsPageUI;

public class ContactDetailsPageObject extends BaseActions {
    private WebDriver driver;

    public ContactDetailsPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void enterToStreet1Textbox(String street1) {
        waitForElementVisible(driver, ContactDetailsPagUI.STREET1_TEXTBOX);
        sendkeyToElement(driver, ContactDetailsPagUI.STREET1_TEXTBOX,street1);
    }

    public void enterToCityTextbox(String city) {
        waitForElementVisible(driver, ContactDetailsPagUI.CITY_TEXTBOX);
        sendkeyToElement(driver, ContactDetailsPagUI.CITY_TEXTBOX,city);
    }

    public void enterToStateTextbox(String state) {
        waitForElementVisible(driver, ContactDetailsPagUI.STATE_TEXTBOX);
        sendkeyToElement(driver, ContactDetailsPagUI.STATE_TEXTBOX,state);
    }

    public void enterToPostalCodeTextbox(String postalCode) {
        waitForElementVisible(driver, ContactDetailsPagUI.POSTALCODE_TEXTBOX);
        sendkeyToElement(driver, ContactDetailsPagUI.POSTALCODE_TEXTBOX,postalCode);
    }

    public void selectToCountryDropdown(String countryName) {
        waitForElementClickable(driver, ContactDetailsPagUI.COUNTRY_DROPDOWN_PARENT);
        selectItemInDropdown(driver,ContactDetailsPagUI.COUNTRY_DROPDOWN_PARENT,ContactDetailsPagUI.COUNTRY_DROPDOWN_CHILD_ITEM,countryName);
    }

    public void enterToHomeTelephoneTextbox(String homeTelephone) {
        waitForElementVisible(driver, ContactDetailsPagUI.HOMETELEPHONE_TEXTBOX);
        sendkeyToElement(driver, ContactDetailsPagUI.HOMETELEPHONE_TEXTBOX,homeTelephone);
    }

    public void enterToMoblieTelephoneTextox(String moblieTelephone) {
        waitForElementVisible(driver, ContactDetailsPagUI.MOBILETEPLEPHONE_TEXTBOX);
        sendkeyToElement(driver, ContactDetailsPagUI.MOBILETEPLEPHONE_TEXTBOX,moblieTelephone);
    }

    public void enterToWorkTelephonrTextbox(String workTelephone) {
        waitForElementVisible(driver, ContactDetailsPagUI.WORKTEPEPHONE_TEXTBOX);
        sendkeyToElement(driver, ContactDetailsPagUI.WORKTEPEPHONE_TEXTBOX,workTelephone);
    }

    public void enterToWorkEmailTextbox(String workEmail) {
        waitForElementVisible(driver, ContactDetailsPagUI.WORKEMAIL_TEXTBOX);
        sendkeyToElement(driver, ContactDetailsPagUI.WORKEMAIL_TEXTBOX,workEmail);
    }

    public void clickToSaveButtonAtContactDetailPart() {
        waitForElementClickable(driver,ContactDetailsPagUI.SAVE_BUTTON_AT_CONTACT_DETAIL);
        clickToElement(driver,ContactDetailsPagUI.SAVE_BUTTON_AT_CONTACT_DETAIL);
    }

    public String getStreet1Value() {
        waitForElementVisible(driver, ContactDetailsPagUI.STREET1_TEXTBOX);
        return getElementAttribute(driver, ContactDetailsPagUI.STREET1_TEXTBOX,"value");
    }

    public String getCityValue() {
        waitForElementVisible(driver, ContactDetailsPagUI.CITY_TEXTBOX);
        return getElementAttribute(driver, ContactDetailsPagUI.CITY_TEXTBOX,"value");
    }

    public String getStateValue() {
        waitForElementVisible(driver, ContactDetailsPagUI.STATE_TEXTBOX);
        return getElementAttribute(driver, ContactDetailsPagUI.STATE_TEXTBOX,"value");
    }

    public String getCountryDropdownSelectedText() {
        return getElementText(driver,ContactDetailsPagUI.COUNTRY_DROPDOWN_SELECTED_TEXT);
    }

    public EmergencyContactsPageObject clickToEmergencyContacts() {
        waitForElementClickable(driver,ContactDetailsPagUI.EMERGENCY_CONTACTS_BUTTON);
        clickToElement(driver,ContactDetailsPagUI.EMERGENCY_CONTACTS_BUTTON);
        return PageGeneratorManager.getEmergencyContactsPage(driver);
    }
}
