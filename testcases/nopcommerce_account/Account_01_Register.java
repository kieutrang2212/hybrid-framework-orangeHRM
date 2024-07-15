package nopcommerce_account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.CustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

import java.time.Duration;
import java.util.Random;

public class Account_01_Register {
    private WebDriver driver;
    private String projectPath = System.getProperty("user.dir");
    private HomePageObject homePage;
    private LoginPageObject loginPage;
    private CustomerPageObject customerPage;
    private RegisterPageObject registerPage;
    private String emailAddress= getEmailAddress();

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath+"\\browserDrivers\\geckodriver.exe");
        driver= new FirefoxDriver();
        driver.get("https://demo.nopcommerce.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        homePage = new HomePageObject(driver);

    }

    @Test
    public void User_01_Register_Empty_Data() {
        homePage.clickToRegisterLink();

        registerPage= new RegisterPageObject(driver);
        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getFirstNameErrorMessageText(),"First name is required.");
//        Assert.assertEquals(registerPage.getLastNameErrorMessageText(),"Last name is required.");
        Assert.assertEquals(registerPage.getEmailErrorMessageText(),"Email is required.");
        Assert.assertEquals(registerPage.getConfirmPasswordErrorMessageText(),"Password is required.");
    }

    @Test
    public void User_02_Register_Invalid_Email() {
        registerPage.clickToNopCommerceLogo();

        homePage = new HomePageObject(driver);
        homePage.clickToRegisterLink();

        registerPage = new RegisterPageObject(driver);

        registerPage.enterToFirstNameTextbox("Trang");
        registerPage.enterToLastNameTextbox("Nguyen");
        registerPage.enterToEmailTextbox("trang@123@123");
        registerPage.enterToPasswordTextbox("123456");
        registerPage.enterToConfirmPasswordTextbox("123456");

        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getEmailErrorMessageText(),"Please enter a valid email address.");
    }

    @Test
    public void User_03_Register_Invalid_Password() {
        registerPage.clickToNopCommerceLogo();

        homePage = new HomePageObject(driver);
        homePage.clickToRegisterLink();

        registerPage = new RegisterPageObject(driver);

        registerPage.enterToFirstNameTextbox("Trang");
        registerPage.enterToLastNameTextbox("Nguyen");
        registerPage.enterToEmailTextbox("trang342@gnail.com");
        registerPage.enterToPasswordTextbox("123");
        registerPage.enterToConfirmPasswordTextbox("123");

        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getPasswordErrorMessageText(),"<p>Password must meet the following rules: </p><ul><li>must have at least 6 characters and not greater than 64 characters</li></ul>");
    }

    @Test
    public void User_04_Register_Incorrect_Cofirm_Password() {
        registerPage.clickToNopCommerceLogo();

        homePage = new HomePageObject(driver);
        homePage.clickToRegisterLink();

        registerPage = new RegisterPageObject(driver);

        registerPage.enterToFirstNameTextbox("Trang");
        registerPage.enterToLastNameTextbox("Nguyen");
        registerPage.enterToEmailTextbox("trang342@gmail.com");
        registerPage.enterToPasswordTextbox("123456");
        registerPage.enterToConfirmPasswordTextbox("456789");

        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getConfirmPasswordErrorMessageText(),"The password and confirmation password do not match.");
    }

    @Test
    public void User_05_Register_Success() {
        registerPage.clickToNopCommerceLogo();

        homePage = new HomePageObject(driver);
        homePage.clickToRegisterLink();

        registerPage = new RegisterPageObject(driver);

        registerPage.enterToFirstNameTextbox("  Trang");
        registerPage.enterToLastNameTextbox("Nguyen");
        registerPage.enterToEmailTextbox(emailAddress);
        registerPage.enterToPasswordTextbox("123456");
        registerPage.enterToConfirmPasswordTextbox("123456");

        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getRegisterSuccessMessageText(),"Your registration completed");
    }

    @Test
    public void User_06_Login_Success(){
        registerPage.clickToNopCommerceLogo();

        homePage = new HomePageObject(driver);
        homePage.clickToLogoutLink();
        homePage.clickToLoginLink();

        loginPage = new LoginPageObject(driver);

        loginPage.enterToEmailTextbox(emailAddress);
        loginPage.enterToPasswordTextbox("123456");

        loginPage.clickToLoginButton();

        homePage = new HomePageObject(driver);
        homePage.clickToMyAccountLink();

        customerPage = new CustomerPageObject(driver);

        Assert.assertEquals(customerPage.getFirstNameTextboxAttributeValue(),"Trang");
        Assert.assertEquals(customerPage.getLastNameTextboxAttributeValue(),"Nguyen");
        Assert.assertEquals(customerPage.getEmailAddressTextboxAttributeValue(),emailAddress);
    }

    public String getEmailAddress(){
        Random rand= new Random();
        String emailAddress="trang"+rand.nextInt(99999)+"@gmail.com";
        return emailAddress;
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
