package pageObjects.orangehrm;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.orangehrm.BaseActionsUI;
import pageUIs.orangehrm.PersonalDetailsPageUI;

public class BaseActions extends BasePage {
    private WebDriver driver;

    public BaseActions(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForSpinnerIconInvisible(){
        waitForListElementInvisible(driver, BaseActionsUI.SPINNER_ICON);
    }

    public boolean isSuccessMessageDisplayed(String messageContent) {
        waitForElementVisible(driver,BaseActionsUI.DYNAMIC_SUCCESS_MESSAGE,messageContent);
        return isElementDisplayed(driver,BaseActionsUI.DYNAMIC_SUCCESS_MESSAGE,messageContent);
    }

    public boolean isValueDisplayedAtColumnName(String columnName,String rowIndex, String rowValue) {
        int columnIndex=getListElementSize(driver,BaseActionsUI.DYNAMIC_INDEX_BY_COLUMN_NAME,columnName)+1;
        return isElementDisplayed(driver,BaseActionsUI.DYNAMIC_ROW_VALUE_BY_COLUMN_INDEX_ROW_INDEX,rowIndex,String.valueOf(columnIndex),rowValue);
    }

    public void clickRadioButtonByLabelName(String labelName) {
        clickToElementByJS(driver, BaseActionsUI.RADIO_BUTTON_BY_LABEL_NAME,labelName);
    }

    public void clickToCheckboxByLabelName(String labelName) {
        if(!isElementSelected(driver,BaseActionsUI.CHECKBOX_BY_LABEL_NAME,labelName)){
            clickToElementByJS(driver, BaseActionsUI.CHECKBOX_BY_LABEL_NAME,labelName);
        }
    }

    public boolean isRadioButtonSelectedByLabelName(String radioLabelName) {
        return isElementSelected(driver,BaseActionsUI.RADIO_BUTTON_BY_LABEL_NAME,radioLabelName);
    }

    public boolean isCheckboxSelectedByLabelName(String checkboxLabelName) {
        return isElementSelected(driver,BaseActionsUI.CHECKBOX_BY_LABEL_NAME,checkboxLabelName);
    }
}
