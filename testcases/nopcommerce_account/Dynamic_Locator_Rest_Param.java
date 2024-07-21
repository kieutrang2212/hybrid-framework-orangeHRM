package nopcommerce_account;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.user.*;


public class Dynamic_Locator_Rest_Param extends BaseTest {
    private WebDriver driver;
    private HomePageObject homePage;
    private UserLoginPageObject loginPage;
    private CustomerPageObject customerPage;
    private RegisterPageObject registerPage;
    private OrderPageObject orderPage;
    private RewardPointPageObject rewardPointPage;
    private AddressPageObject addressPage;
    private String emailAddress= getEmailAddress();

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        homePage = PageGeneratorManager.getHomePage(driver);

    }


    @Test
    public void User_01_Register_Success() {
        registerPage =homePage.clickToRegisterLink();


        registerPage.enterToFirstNameTextbox("  Trang");
        registerPage.enterToLastNameTextbox("Nguyen");
        registerPage.enterToEmailTextbox(emailAddress);
        registerPage.enterToPasswordTextbox("123456");
        registerPage.enterToConfirmPasswordTextbox("123456");

        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getRegisterSuccessMessageText(),"Your registration completed");
    }

    @Test
    public void User_02_Login_Success(){
        homePage =registerPage.clickToNopCommerceLogo();

        homePage.clickToLogoutLink();
        loginPage =homePage.clickToLoginLink();

        loginPage.enterToEmailTextbox(emailAddress);
        loginPage.enterToPasswordTextbox("123456");

        homePage =loginPage.clickToLoginButton();

        customerPage =homePage.clickToMyAccountLink();



        Assert.assertEquals(customerPage.getFirstNameTextboxAttributeValue(),"Trang");
        Assert.assertEquals(customerPage.getLastNameTextboxAttributeValue(),"Nguyen");
        Assert.assertEquals(customerPage.getEmailAddressTextboxAttributeValue(),emailAddress);
    }

    @Test
    public void User_03_Switch_Page(){
        //Customer Page-> Address Page
        addressPage=(AddressPageObject) customerPage.openDynamicSideBarPage("Addresses");
        //Address Page -> Order Page
        orderPage=(OrderPageObject) addressPage.openDynamicSideBarPage("Orders");
        //Order Page -> Customer Page
        customerPage=(CustomerPageObject) orderPage.openDynamicSideBarPage("Customer info");
        //Customer Page -> Order Page
        orderPage=(OrderPageObject) customerPage.openDynamicSideBarPage("Orders");
        //Order Page -> Address Page
        addressPage=(AddressPageObject) orderPage.openDynamicSideBarPage("Addresses");
        // Address Page -> RewardPointPage
        rewardPointPage=(RewardPointPageObject) addressPage.openDynamicSideBarPage("Reward points");


    }
    @Test
    public void User_04_Switch_Page(){
       rewardPointPage.openDynamicSideBarPageByName("Customer info");
       customerPage=PageGeneratorManager.getCustomerPage(driver);

       customerPage.openDynamicSideBarPageByName("Addresses");
       addressPage=PageGeneratorManager.getAddressPage(driver);
    }

    @AfterClass
    public void afterClass() {
        closedBrowser();
    }
}
