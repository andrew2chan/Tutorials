package com.udacity.jdnd.course1.model;

public class ChatMessage {
    private Integer messageid;
    private String username;
    private String messagetext;

    public ChatMessage(String username, String messagetext) {
        this.username = username;
        this.messagetext = messagetext;
    }

    public Integer getMessageid() {
        return messageid;
    }

    public void setMessageid(Integer messageid) {
        this.messageid = messageid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessagetext() {
        return messagetext;
    }

    public void setMessagetext(String messagetext) {
        this.messagetext = messagetext;
    }

    public String showMessage() {
        return username + ": " + messagetext;
    }
}
