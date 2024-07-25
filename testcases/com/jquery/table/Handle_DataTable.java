package com.jquery.table;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.jquery.HomePageObject;
import pageObjects.jquery.PageGeneratorManager;

import java.util.ArrayList;
import java.util.List;


public class Handle_DataTable extends BaseTest {
    private WebDriver driver;
    private HomePageObject homePage;
    private List<String> allValueUI=new ArrayList<String>();
    private List<String> allValueDB=new ArrayList<String>();


    @Parameters({"browser","url"})
    @BeforeClass
    public void beforeClass(String browserName,String url) {
        driver = getBrowserDriver(browserName,url);

        homePage= PageGeneratorManager.getHomePage(driver);
    }


    @Test
    public void TC_01_Search() {
        //Search du lieu trong 1 table
        homePage.sendKeyToColumnTextboxByName("Females","384187");
        homePage.sleepInSeconds(2);

        homePage.sendKeyToColumnTextboxByName("Country","Afghanistan");
        homePage.sleepInSeconds(2);

        homePage.sendKeyToColumnTextboxByName("Total","791312");
        homePage.sleepInSeconds(2);
    }

    @Test
    public void TC_02_Paging(){
        homePage.clickToPageByNumber("10");
        homePage.sleepInSeconds(2);
        Assert.assertTrue(homePage.isPageActiveByNumber("10"));
        homePage.refreshToCurrentPage(driver);

    }

    @Test
    public void TC_03_Displayed(){
        Assert.assertTrue(homePage.isRowValuesDisplayed("384187","Afghanistan","407124","791312"));
    }

    @Test
    public void TC_04_Icon_Button_Checkbox(){
        homePage.clickToRowActionByCountryName("Afghanistan","remove");
        homePage.refreshToCurrentPage(driver);

        homePage.clickToRowActionByCountryName("AFRICA","edit");
        homePage.refreshToCurrentPage(driver);

    }

    @Test
    public void TC_05_Get_All_Column_Values(){
        allValueUI=homePage.getAllPageValuesByColumnName("Country");

//        allValueDB= homePage.getAllPageValuesByColumnNameInDB("Country");
//
//        Assert.assertEquals(allValueUI,allValueDB);
    }

    @Test
    public void TC_06_Action_By_Index(){

        homePage.openPageUrl(driver,"https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/");

        homePage.enterToTextboxByColumnNameAndRowIndex("Company","1","Bayer Munich");

        homePage.selectDropdownByColumnNameAndRowIndex("Country","3","United Kingdom");

        homePage.clickToCheckboxByColumnNameAndRowIndex("NPO?","2");
    }
    @AfterClass
    public void afterClass() {
        closedBrowser();
    }
}
