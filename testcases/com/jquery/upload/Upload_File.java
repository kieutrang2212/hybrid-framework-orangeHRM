package com.jquery.upload;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.jquery.HomePageObject;
import pageObjects.jquery.PageGeneratorManager;
import pageObjects.jquery.UploadPageObject;

import java.util.ArrayList;
import java.util.List;


public class Upload_File extends BaseTest {
    private WebDriver driver;
    private UploadPageObject uploadPage;
    private String canh1="canh1.jpg";
    private String anh2="anh2.jpg";
    private String[] fileNames={canh1,anh2};

    @Parameters({"browser","url"})
    @BeforeClass
    public void beforeClass(String browserName,String url) {
        driver = getBrowserDriver(browserName,url);

        uploadPage=PageGeneratorManager.getUploadPage(driver);
    }


    @Test
    public void TC_01_Upload_Single_File() {
        uploadPage.uploadMultipleFiles(driver,canh1);
        uploadPage.sleepInSeconds(2);

        uploadPage.uploadMultipleFiles(driver,anh2);
        uploadPage.sleepInSeconds(2);

        Assert.assertTrue(uploadPage.isFileLoadedSuccess(canh1));
        Assert.assertTrue(uploadPage.isFileLoadedSuccess(anh2));

        uploadPage.clickToStartButtonOnEachFile();

        Assert.assertTrue(uploadPage.isFileUpLoadedSuccess(canh1));
        Assert.assertTrue(uploadPage.isFileUpLoadedSuccess(anh2));

    }

    @Test
    public void TC_02_Upload_Multiple_Files(){
        uploadPage.refreshToCurrentPage(driver);

        uploadPage.uploadMultipleFiles(driver,fileNames);

        Assert.assertTrue(uploadPage.isFileLoadedSuccess(canh1));
        Assert.assertTrue(uploadPage.isFileLoadedSuccess(anh2));

        uploadPage.clickToStartButtonOnEachFile();

        Assert.assertTrue(uploadPage.isFileUpLoadedSuccess(canh1));
        Assert.assertTrue(uploadPage.isFileUpLoadedSuccess(anh2));

    }

    @AfterClass
    public void afterClass() {
        closedBrowser();
    }
}
