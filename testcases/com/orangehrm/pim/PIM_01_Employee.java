package com.orangehrm.pim;

import commons.BaseTest;
import commons.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.orangehrm.*;
import reportConfig.ExtentTestManager;

import java.lang.reflect.Method;

public class PIM_01_Employee extends BaseTest {
    private WebDriver driver;
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private EmployeeListPageObject employeeListPage;
    private AddEmployeePageObject addEmployeePage;
    private PersonalDetailsPageObject personalDetailsPage;
    private ContactDetailsPageObject contactDetailsPage;
    private EmergencyContactsPageObject emergencyContactPage;

    @Parameters({"url","browser"})
    @BeforeClass
    public void beforeClass(String url, String browserName){
        driver=getBrowserDriver(browserName,url);
        this.browserName=browserName;

        firstName="Michael";
        lastName="Owen";
        nickName="golden_boy";
        driverLicenseNumber="D08954796";
        licenseExpiryDate="2024-01-03";
        ssnNumber="428- 79-4371";
        sinNumber="968563231";
        nationality="American";
        maritalStatus="Married";
        dateOfBirth="1986-10-10";
        genderStatus="Male";
        smokerStatus="Yes";

        street1="123 Main Street";
        city="New York";
        state="New York";
        postalCode="10001";
        country="United States";
        homeTelephone="(212) 555-1234";
        moblieTelephone="(917) 555-5678";
        workTelephone="(646) 555-7890";
        workEmail=getEmailAddress();

        nameOfRelative="Tom Hiddleston";
        relationship="Dad";
        homeTelephoneOfRalative="(212) 555-2235";

        loginPage= PageGeneratorManager.getLoginPage(driver);

        loginPage.enterToUsernameTextbox(GlobalConstants.ADMIN_ORANGE_HRM_USERNAME);
        loginPage.enterToPasswordTextbox(GlobalConstants.ADMIN_ORANGE_HRM_PASSWORD);
        dashboardPage=loginPage.clickToLoginButton();

        employeeListPage=dashboardPage.openPIMModule();
    }

    @Test
    public void Employee_01_Add_New(Method method){
        ExtentTestManager.startTest(method.getName()+" - Run on "+browserName.toUpperCase(), "Employee_01_Add_New");
        addEmployeePage=employeeListPage.clickToAddEmployeeButton();

        addEmployeePage.enterToFirstNameTextbox(firstName);
        addEmployeePage.enterToLastNameTextbox(lastName);
        employeeID=addEmployeePage.getEmployeeID();

        addEmployeePage.waitForSpinnerIconInvisible();
        addEmployeePage.clickToSaveButton();
        Assert.assertTrue(addEmployeePage.isSuccessMessageDisplayed("Successfully Saved"));
        addEmployeePage.waitForSpinnerIconInvisible();

        personalDetailsPage=PageGeneratorManager.getPersonalDetailsPage(driver);
        Assert.assertTrue(personalDetailsPage.isPersonalDetailsHeaderDisplayed());
        personalDetailsPage.waitForSpinnerIconInvisible();

       Assert.assertEquals( personalDetailsPage.getFirstNameValue(),firstName);
       Assert.assertEquals( personalDetailsPage.getLastNameValue(),lastName);
       Assert.assertEquals( personalDetailsPage.getEmployeeIDValue(),employeeID);

       employeeListPage=personalDetailsPage.clickToEmployeeListButton();
       employeeListPage.enterToEmployeeIDTextbox(employeeID);
       employeeListPage.clickToSearchButton();

       Assert.assertTrue(employeeListPage.isValueDisplayedAtColumnName("Id","1",employeeID));
       Assert.assertTrue(employeeListPage.isValueDisplayedAtColumnName("First (& Middle) Name","1",firstName));
       Assert.assertTrue(employeeListPage.isValueDisplayedAtColumnName("Last Name","1",lastName));

    }

    @Test
    public void Employee_02_Personal_Details(Method method){
        ExtentTestManager.startTest(method.getName()+" - Run on "+browserName.toUpperCase(), "Employee_02_Personal_Details");

        personalDetailsPage= employeeListPage.clickToEditIconByEmployeeID(employeeID);
        Assert.assertTrue(personalDetailsPage.isPersonalDetailsHeaderDisplayed());

        Assert.assertEquals( personalDetailsPage.getFirstNameValue(),firstName);
        Assert.assertEquals( personalDetailsPage.getLastNameValue(),lastName);
        Assert.assertEquals( personalDetailsPage.getEmployeeIDValue(),employeeID);

        personalDetailsPage.enterToDriverLicenseNumberTextbox(driverLicenseNumber);

        personalDetailsPage.enterToLicenseExpiryDatePicker(licenseExpiryDate);

        personalDetailsPage.selectToNationalityDropdown(nationality);

        personalDetailsPage.selectToMaritalStatusDropdown(maritalStatus);

        personalDetailsPage.enterToDateOfBirthDatePicker(dateOfBirth);

        personalDetailsPage.clickRadioButtonByLabelName(genderStatus);

        personalDetailsPage.clickToSaveButtonAtPersonalDetailPart();

        Assert.assertTrue(personalDetailsPage.isSuccessMessageDisplayed("Successfully Updated"));
        personalDetailsPage.waitForSpinnerIconInvisible();

        Assert.assertEquals(personalDetailsPage.getNationalityDropdownSelectedText(),nationality);
        Assert.assertEquals(personalDetailsPage.getMaritalDropdownSelectedText(),maritalStatus);

        Assert.assertTrue(personalDetailsPage.isRadioButtonSelectedByLabelName(genderStatus));

    }

    @Test
    public void Employee_03_Contact_Details(Method method){
        ExtentTestManager.startTest(method.getName()+" - Run on "+browserName.toUpperCase(), "Employee_03_Contact_Details");

       contactDetailsPage=personalDetailsPage.clickToContactDetailsButton();
       contactDetailsPage.waitForSpinnerIconInvisible();

       contactDetailsPage.enterToStreet1Textbox(street1);

       contactDetailsPage.enterToCityTextbox(city);

       contactDetailsPage.enterToStateTextbox(state);

       contactDetailsPage.enterToPostalCodeTextbox(postalCode);

       contactDetailsPage.selectToCountryDropdown(country);

       contactDetailsPage.enterToHomeTelephoneTextbox(homeTelephone);

       contactDetailsPage.enterToMoblieTelephoneTextox(moblieTelephone);

       contactDetailsPage.enterToWorkTelephonrTextbox(workTelephone);

       contactDetailsPage.enterToWorkEmailTextbox(workEmail);

       contactDetailsPage.clickToSaveButtonAtContactDetailPart();

        Assert.assertTrue(contactDetailsPage.isSuccessMessageDisplayed("Successfully Updated"));
        contactDetailsPage.waitForSpinnerIconInvisible();

        Assert.assertEquals( contactDetailsPage.getStreet1Value(),street1);
        Assert.assertEquals( contactDetailsPage.getCityValue(),city);
        Assert.assertEquals( contactDetailsPage.getStateValue(),state);
        Assert.assertEquals(contactDetailsPage.getCountryDropdownSelectedText(),country);



    }

    @Test
    public void Employee_04_Emergency_Contacts(Method method){
        ExtentTestManager.startTest(method.getName()+" - Run on "+browserName.toUpperCase(), "Employee_04_Emergency_Contacts");

        emergencyContactPage = contactDetailsPage.clickToEmergencyContacts();

        emergencyContactPage.waitForSpinnerIconInvisible();

        emergencyContactPage.clickToAddButtonAtAssignedEmergencyContacts();

        emergencyContactPage.waitForSpinnerIconInvisible();

        emergencyContactPage.enterToNameOfRelativeTextbox(nameOfRelative);

        emergencyContactPage.enterToRelationshipTextbox(relationship);

        emergencyContactPage.enterToHomeTelephoneOfRelativeTextbox(homeTelephoneOfRalative);

        emergencyContactPage.clickToSaveAtSaveEmergencyContact();

        Assert.assertTrue(emergencyContactPage.isSuccessMessageDisplayed("Successfully Saved"));

        emergencyContactPage.waitForSpinnerIconInvisible();
    }


    @AfterClass
    public void afterClass(){
        closedBrowser();
    }
    private String browserName,employeeID,firstName,lastName,nickName;
    private String driverLicenseNumber,licenseExpiryDate,ssnNumber,sinNumber,nationality,maritalStatus;
    private String dateOfBirth,genderStatus,smokerStatus;
    private String street1,city,state,postalCode,country,homeTelephone,moblieTelephone,workTelephone,workEmail;
    private String nameOfRelative,relationship,homeTelephoneOfRalative;
}
