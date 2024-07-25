package pageObjects.jquery;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.jquery.HomePageUI;

import java.util.ArrayList;
import java.util.List;

public class HomePageObject extends BasePage {
    WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void sendKeyToColumnTextboxByName(String columnName,String valueToSend) {
        waitForElementVisible(driver, HomePageUI.COLUMN_TEXTBOX_BY_NAME,columnName);
        sendkeyToElement(driver, HomePageUI.COLUMN_TEXTBOX_BY_NAME,valueToSend,columnName);
    }

    public void clickToPageByNumber(String pageNumber){
        waitForElementClickable(driver,HomePageUI.PAGE_LINK_BY_NUMBER,pageNumber);
        clickToElement(driver,HomePageUI.PAGE_LINK_BY_NUMBER,pageNumber);
    }

    public boolean isPageActiveByNumber(String pageNumber){
        waitForElementVisible(driver,HomePageUI.PAGE_LINK_ACTIVE_BY_NUMBER,pageNumber);
        return isElementDisplayed(driver,HomePageUI.PAGE_LINK_ACTIVE_BY_NUMBER,pageNumber);
    }

    public boolean isRowValuesDisplayed(String female,String country,String male, String total){
        waitForElementVisible(driver,HomePageUI.DYNAMIC_ROW_VALUES,female,country,male,total);
        return isElementDisplayed(driver,HomePageUI.DYNAMIC_ROW_VALUES,female,country,male,total);
    }

    public void clickToRowActionByCountryName(String country,String rowAction){
        waitForElementClickable(driver,HomePageUI.ROW_ACTION_BY_COUNTRY,country,rowAction);
        clickToElement(driver,HomePageUI.ROW_ACTION_BY_COUNTRY,country,rowAction);
    }

    public List<String> getAllPageValuesByColumnName(String columnName){
        List<String> allValues=new ArrayList<String>();
        List<WebElement> allPageLinks= getListWebElement(driver,HomePageUI.ALL_PAGE_LINK);

        int columnIndex=getListElementSize(driver,HomePageUI.COLUMN_INDEX_BY_COLUMN_NAME,columnName)+1;

        for(WebElement pagelink: allPageLinks){
            pagelink.click();
            sleepInSeconds(2);

            List<WebElement> allRowValues=getListWebElement(driver,HomePageUI.ALL_VALUE_BY_COLUMN_INDEX,String.valueOf(columnIndex));

            for(WebElement rowValue:allRowValues){
                allValues.add(rowValue.getText());
            }
        }return allValues;
    }

    public void enterToTextboxByColumnNameAndRowIndex(String columnName, String rowIndex,String valueToSend) {
        int columnIndex=getListElementSize(driver,HomePageUI.DYNAMIC_COLUMN_INDEX_BY_COLUMN_NAME,columnName)+1;
        waitForElementVisible(driver,HomePageUI.DYNAMIC_TEXTBOX_BY_COLUMN_NAME_AND_ROW_INDEX,rowIndex,String.valueOf(columnIndex));
        sendkeyToElement(driver,HomePageUI.DYNAMIC_TEXTBOX_BY_COLUMN_NAME_AND_ROW_INDEX,valueToSend,rowIndex,String.valueOf(columnIndex));
    }

    public void clickToCheckboxByColumnNameAndRowIndex(String columnName, String rowIndex) {
        int columnIndex=getListElementSize(driver,HomePageUI.DYNAMIC_COLUMN_INDEX_BY_COLUMN_NAME,columnName)+1;
        waitForElementClickable(driver,HomePageUI.DYNAMIC_CHECKBOX_BY_COLUMN_NAME_AND_ROW_INDEX,rowIndex,String.valueOf(columnIndex));
        clickToElement(driver,HomePageUI.DYNAMIC_CHECKBOX_BY_COLUMN_NAME_AND_ROW_INDEX,rowIndex,String.valueOf(columnIndex));
    }

    public void selectDropdownByColumnNameAndRowIndex(String columnName, String rowIndex,String DropdownItem) {
        int columnIndex=getListElementSize(driver,HomePageUI.DYNAMIC_COLUMN_INDEX_BY_COLUMN_NAME,columnName)+1;
        waitForElementClickable(driver,HomePageUI.DYNAMIC_DROPDOWN_BY_COLUMN_NAME_AND_ROW_INDEX,rowIndex,String.valueOf(columnIndex));
        selectItemInDefaultDropdown(driver,HomePageUI.DYNAMIC_DROPDOWN_BY_COLUMN_NAME_AND_ROW_INDEX,DropdownItem,rowIndex,String.valueOf(columnIndex));
    }
}