package com.example.modoo.controller;

import com.example.modoo.dto.ChatMessageDto;
import com.example.modoo.entity.ChatMessage;
import com.example.modoo.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @PostMapping("/go-to-chatroom")
    @MessageMapping("/chat.sendMessage")
    public void receiveMessage(@RequestBody ChatMessageDto messageDto) {
        ChatMessage chatMessage = chatService.saveMessage(messageDto);
        messagingTemplate.convertAndSend("/topic/" + messageDto.getReceiver(), chatMessage);
    }

    @GetMapping("/chat/messages")
    public List<ChatMessage> getMessages(@RequestParam String sender, @RequestParam String receiver) {
        return chatService.getMessages(sender, receiver);
    }
}
