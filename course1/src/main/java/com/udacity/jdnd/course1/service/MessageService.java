package com.udacity.jdnd.course1.service;

import com.udacity.jdnd.course1.mapper.MessageMapper;
import com.udacity.jdnd.course1.model.ChatForm;
import com.udacity.jdnd.course1.model.ChatMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    private String message;
    private List<ChatMessage> messageList;
    private MessageMapper messageMapper;

    public MessageService(String message, MessageMapper messasgeMapper) {
        System.out.println("Msg Service created");
        this.message = message;
        this.messageList = new ArrayList<>();
        this.messageMapper = messasgeMapper;
    }

    public String toUpperCase() {
        return message.toUpperCase();
    }

    public String toLowerCase() {
        return message.toLowerCase();
    }

    public void addMessage(ChatForm chatForm) {
        String message = chatForm.getMessage();
        switch(chatForm.getMessageType()) {
            case "2":
                message = message.toUpperCase();
                break;
            case "3":
                message = message.toLowerCase();
                break;
        }

        //messageList.add(new ChatMessage(chatForm.getUsername(), message));
        ChatMessage chatMessage = new ChatMessage(chatForm.getUsername(), message);
        messageMapper.addMessage(chatMessage);

    }

    public List<ChatMessage> getMessages() {
        //return messageList;
        return messageMapper.getMessages();
    }
}
