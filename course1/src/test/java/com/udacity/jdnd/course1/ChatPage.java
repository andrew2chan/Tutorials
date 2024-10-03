package com.udacity.jdnd.course1;

import com.udacity.jdnd.course1.model.ChatMessage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;
import java.util.List;

public class ChatPage {
    @FindBy(id = "messageText")
    private WebElement messageText;

    @FindBy(className = "messages")
    private List<WebElement> messageList;

    public ChatPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void setMessageText(String messageTextToSet) {
        messageText.sendKeys(messageTextToSet);
    }

    public String getMessageText() {
        return messageText.getText();
    }

    public boolean containsUsernameAndPassword(String username) {
        for(WebElement we : messageList) {
            String[] split = we.getText().split(":");
            if(split[0].contains(username) || split[1].contains(messageText.getAttribute("value"))) return true;
        }

        return false;
    }

    public void submit() {
        messageText.submit();
    }
}
