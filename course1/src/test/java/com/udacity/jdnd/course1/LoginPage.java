package com.udacity.jdnd.course1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage {
    @FindBy(name = "username")
    private WebElement username;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(className = "alert")
    private List<WebElement> alerts;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void setUsername(String usernameToSet) {
        username.sendKeys(usernameToSet);
    }

    public void setPassword(String passwordToSet) {
        password.sendKeys(passwordToSet);
    }

    public int getAlertCount() {
        return alerts.size();
    }

    public void submit() {
        password.submit();
    }
}
