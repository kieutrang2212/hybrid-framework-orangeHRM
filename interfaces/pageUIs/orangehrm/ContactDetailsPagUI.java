package pageUIs.orangehrm;

public class ContactDetailsPagUI {
    public static final String STREET1_TEXTBOX="xpath=//label[text()='Street 1']/parent::div/following-sibling::div/input";
    public static final String CITY_TEXTBOX="xpath=//label[text()='City']/parent::div/following-sibling::div/input";
    public static final String STATE_TEXTBOX="xpath=//label[text()='State/Province']/parent::div/following-sibling::div/input";
    public static final String POSTALCODE_TEXTBOX="xpath=//label[text()='Zip/Postal Code']/parent::div/following-sibling::div/input";

    public static final String SAVE_BUTTON_AT_CONTACT_DETAIL="xpath=//button[contains(string(),'Save')]";

    public static final String HOMETELEPHONE_TEXTBOX="xpath=//label[text()='Home']/parent::div/following-sibling::div/input";
    public static final String MOBILETEPLEPHONE_TEXTBOX="xpath=//label[text()='Mobile']/parent::div/following-sibling::div/input";
    public static final String WORKTEPEPHONE_TEXTBOX="xpath=//label[text()='Work']/parent::div/following-sibling::div/input";
    public static final String WORKEMAIL_TEXTBOX="xpath=//label[text()='Work Email']/parent::div/following-sibling::div/input";

    public static final String COUNTRY_DROPDOWN_PARENT="xpath=//label[text()='Country']/parent::div/following-sibling::div//i";
    public static final String COUNTRY_DROPDOWN_CHILD_ITEM="xpath=//label[text()='Country']/parent::div/following-sibling::div//div[@class='oxd-select-option']/span";
    public static final String COUNTRY_DROPDOWN_SELECTED_TEXT="xpath=//label[text()='Country']/parent::div/following-sibling::div//div[@class='oxd-select-text-input']";

    public static final String EMERGENCY_CONTACTS_BUTTON="xpath=//a[text()='Emergency Contacts']";
}
