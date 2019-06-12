package objects_ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageElements {

    WebDriver driver;

    public PageElements(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getSignInLink() {
        return driver.findElement(By.linkText("Sign in"));
    }

    public WebElement getSignUpLink() {
        return driver.findElement(By.linkText("Sign up"));
    }


    public WebElement submitButton() {
        return driver.findElement(By.xpath("//input[@type='submit']"));
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    public WebElement loginAlert() {
        return driver.findElement(By.xpath("//div[@id='js-flash-container']"));
    }

    public WebElement forgotPassword() {
        return driver.findElement(By.xpath("//a[contains(text(),'Forgot password?')]"));
    }

    public WebElement resetEmailTextBox() {
        return driver.findElement(By.id("email_field"));
    }

    public WebElement signupTextfield_username() {
        return driver.findElement(By.id("user_login"));
    }

    public WebElement signupTextfield_email_id() {
        return driver.findElement(By.id("user_email"));
    }

    public WebElement signupTextfield_password() {
        return driver.findElement(By.id("user_password"));
    }

    public WebElement signupbtn_createaccnt() {
        return driver.findElement(By.id("signup_button"));
    }

    public boolean pageContainsText(String errorText) {
        return driver.getPageSource().contains(errorText);
    }


}
