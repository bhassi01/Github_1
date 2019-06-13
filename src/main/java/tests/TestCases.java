package tests;

import objects_ui.PageElements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.DriverUtils;

import java.util.concurrent.TimeUnit;

public class TestCases {

    private PageElements pageElements;
    private WebDriver driver;
    private String URL = "https://github.com";

    @BeforeTest
    //opening chrome browser and maximising window
    private void openBrowser() {
        driver = DriverUtils.getDriver();
        driver.manage().window().maximize();
        pageElements = new PageElements(driver);
    }

    //    Verify that by clicking on Sign In button, user is reirected to login page
    @Test
    private void click_on_sign_in() {
        driver.get(URL);
        pageElements.getSignInLink().click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        String currentURL = pageElements.getCurrentURL();
        Reporter.log("Current URL is " + currentURL, true);
        Assert.assertEquals(currentURL, "https://github.com/login");
    }

    //  Verify that user name and password fields are mandatory in Login page
    @Test
    private void mandate_field_login_page() {
        driver.get(URL);
        pageElements.getSignInLink().click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        pageElements.submitButton().click();
        Assert.assertTrue(pageElements.loginAlert().isDisplayed());
        Reporter.log("Login Alert is displayed", true);
        Assert.assertTrue(pageElements.pageContainsText("Incorrect username or password"));
        Reporter.log("Page contains text - 'Incorrect username or password'", true);


    }

    //  Verify that inserting m.ie into email field in the Reset_Pasword page displays message "Can't find that email, sorry"
    @Test
    private void errormsg_reset_password_page() {
        driver.get(URL);
        pageElements.getSignInLink().click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        pageElements.forgotPassword().click();
        pageElements.resetEmailTextBox().sendKeys("m.ie");
        pageElements.submitButton().click();
        Assert.assertTrue(pageElements.loginAlert().isDisplayed());
        Reporter.log("Login Alert is displayed", true);
        Assert.assertTrue(pageElements.pageContainsText("Can't find that email, sorry."));
        Reporter.log("Page contains text - 'Can't find that email, sorry.'", true);
    }

    //  Verify that inserting empty value into email field in the Reset_Pasword page displays message "Can't find that email, sorry"
    @Test
    private void errormsg_empty_email_reset_password_page() {
        driver.get(URL);

        pageElements.getSignInLink().click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        pageElements.forgotPassword().click();
        pageElements.resetEmailTextBox().sendKeys("");
        pageElements.submitButton().click();
        Assert.assertTrue(pageElements.loginAlert().isDisplayed());
        Reporter.log("Login Alert is displayed", true);
    }

    //  Verify that first word in error message in Reset_Pasword page is "Can't"
    @Test
    private void first_word_err_msg() {
        driver.get(URL);
        pageElements.getSignInLink().click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        pageElements.forgotPassword().click();
        pageElements.resetEmailTextBox().sendKeys("");
        pageElements.submitButton().click();
        Assert.assertTrue(pageElements.loginAlert().isDisplayed());
        Assert.assertTrue(pageElements.loginAlert().getText().startsWith("Can't"));
        Reporter.log("Alert text starts with 'Can't'", true);

    }

    //  Verify that clicking on Sign Up button will redirect user to Join Github page
    @Test
    private void click_on_sign_up() {
        driver.get(URL);
        pageElements.getSignUpLink().click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        String currentURL = pageElements.getCurrentURL();

        //Assert.assertEquals(currentURL, "https://github.com/join?source=header-home");
        Assert.assertTrue(currentURL.startsWith("https://github.com/join"));
        Reporter.log("User is successfully directed to Join Github Page", true);
    }

    //  Verify that Join Github page contains text "Create you personal account"
    @Test
    private void text_join_page() {
        driver.get(URL);
        pageElements.getSignUpLink().click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        Assert.assertTrue(pageElements.pageContainsText("Create your personal account"));
        Reporter.log("Page contains text - 'Create your personal account'", true);
    }

    //  Verify that Create Account Button is grayed when an existing email id is inserted in Join Github page
    @Test
    private void join_github_page() {
        driver.get(URL);
        pageElements.getSignUpLink().click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        pageElements.signupTextfield_username().sendKeys("bhassi011");
        pageElements.signupTextfield_email_id().sendKeys("suyal45@gmail.com");
        pageElements.signupTextfield_password().sendKeys("suyal45@gmail.com");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 15);
//        wait.until(pageElements.signupbtn_createaccnt().isEnabled());
        Assert.assertTrue(!pageElements.signupbtn_createaccnt().isEnabled());
        Reporter.log("Sign Up button is grayed out on using existing e-mail", true);
    }

    @AfterTest
    private void closeBrowser() {
        DriverUtils.closeBrowser();
    }

}
