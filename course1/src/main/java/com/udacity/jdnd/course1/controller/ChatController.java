package com.udacity.jdnd.course1.controller;

import com.udacity.jdnd.course1.model.ChatForm;
import com.udacity.jdnd.course1.model.ChatMessage;
import com.udacity.jdnd.course1.service.MessageService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ChatController {
    private MessageService messageService;

    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/chat")
    public String getChat(@ModelAttribute("ChatForm") ChatForm chatForm, Model model) {
        model.addAttribute("messageList", messageService.getMessages());
        return "chat";
    }

    @PostMapping("/chat")
    public String submitChat(@ModelAttribute("ChatForm") ChatForm chatForm, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        chatForm.setUsername(authentication.getName());

        messageService.addMessage(chatForm);

        model.addAttribute("messageList", messageService.getMessages());

        return "chat";
    }
}
