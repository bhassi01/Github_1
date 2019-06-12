package tests;

import objects_ui.PageElements;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.DriverUtils;

import java.util.concurrent.TimeUnit;

public class TestCases {

    PageElements pageElements;
    WebDriver driver;
    String URL = "https://github.com";

    @BeforeTest
    private void openBrowser() {
        driver = DriverUtils.getDriver();
        driver.manage().window().maximize();
        pageElements = new PageElements(driver);
    }

    @Test
    private void click_on_sign_in() throws InterruptedException {
        driver.get(URL);
        pageElements.getSignInLink().click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        String currentURL = pageElements.getCurrentURL();
        Assert.assertEquals(currentURL, "https://github.com/login");

    }

    @Test
    private void mandate_field_login_page() throws InterruptedException {
        driver.get(URL);

        pageElements.getSignInLink().click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        pageElements.submitButton().click();
        Assert.assertTrue(pageElements.loginAlert().isDisplayed());
        Assert.assertTrue(pageElements.pageContainsText("Incorrect username or password"));

    }

    @Test
    private void errormsg_reset_password_page() throws InterruptedException {
        driver.get(URL);
        pageElements.getSignInLink().click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        pageElements.forgotPassword().click();
        pageElements.resetEmailTextBox().sendKeys("m.ie");
        pageElements.submitButton().click();
        Assert.assertTrue(pageElements.loginAlert().isDisplayed());
        Assert.assertTrue(pageElements.pageContainsText("Can't find that email, sorry."));
    }

    @Test
    private void errormsg_empty_email_reset_password_page() throws InterruptedException {
        driver.get(URL);

        pageElements.getSignInLink().click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        pageElements.forgotPassword().click();
        //Assert.assertEquals(pageElements.submit_without_mandate(), true);
        pageElements.resetEmailTextBox().sendKeys("");
        pageElements.submitButton().click();
        Assert.assertTrue(pageElements.loginAlert().isDisplayed());
    }

    @Test
    private void first_word_err_msg() throws InterruptedException {
        driver.get(URL);
        pageElements.getSignInLink().click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        pageElements.forgotPassword().click();
        pageElements.resetEmailTextBox().sendKeys("");
        pageElements.submitButton().click();
        Assert.assertTrue(pageElements.loginAlert().isDisplayed());
        Assert.assertTrue(pageElements.loginAlert().getText().startsWith("Can't"));
    }

    @Test
    private void click_on_sign_up() throws InterruptedException {
        driver.get(URL);
        pageElements.getSignUpLink().click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        String currentURL = pageElements.getCurrentURL();

        //Assert.assertEquals(currentURL, "https://github.com/join?source=header-home");
        Assert.assertTrue(currentURL.startsWith("https://github.com/join"));
    }

    @Test
    private void text_join_page() {
        driver.get(URL);
        pageElements.getSignUpLink().click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        Assert.assertTrue(pageElements.pageContainsText("Create your personal account"));
    }

    @Test
    private void check_create_accntbtn() {
        driver.get(URL);
        pageElements.getSignUpLink().click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        pageElements.signupTextfield_username().sendKeys("bhassi011");
        pageElements.signupTextfield_email_id().sendKeys("suyal45@gmail.com");
        pageElements.signupTextfield_password().sendKeys("suyal45@gmail.com");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertTrue(!pageElements.signupbtn_createaccnt().isEnabled());
    }

    @AfterTest
    private void closeBrowser() {
        DriverUtils.closeBrowser();
    }

}
