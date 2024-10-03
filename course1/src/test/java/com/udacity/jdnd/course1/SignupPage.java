package com.udacity.jdnd.course1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {
    @FindBy(name = "firstname")
    private WebElement firstname;

    @FindBy(name = "lastname")
    private WebElement lastname;

    @FindBy(name = "username")
    private WebElement username;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(id = "success-msg")
    private WebElement successMsg;

    public SignupPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void setFirstname(String firstnameToSet) {
        firstname.sendKeys(firstnameToSet);
    }

    public void setLastname(String lastnameToSet) {
        lastname.sendKeys(lastnameToSet);
    }

    public void setUsername(String usernameToSet) {
        username.sendKeys(usernameToSet);
    }

    public void setPassword(String passwordToSet) {
        password.sendKeys(passwordToSet);
    }

    public String getUsername() {
        return username.getText();
    }

    public String successfulRegistration() {
        return successMsg.getText();
    }

    public void submit() {
        password.submit();
    }
}
