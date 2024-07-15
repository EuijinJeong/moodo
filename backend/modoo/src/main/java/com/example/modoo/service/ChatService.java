package com.example.modoo.service;

import com.example.modoo.dto.ChatMessageDto;
import com.example.modoo.entity.ChatMessage;
import com.example.modoo.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatService {
    @Autowired
    private ChatMessageRepository chatMessageRepository;

    public ChatMessage saveMessage(ChatMessageDto messageDto) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSender(messageDto.getSender());
        chatMessage.setReceiver(messageDto.getReceiver());
        chatMessage.setContent(messageDto.getContent());
        chatMessage.setTimeStamp(LocalDateTime.now());
        return chatMessageRepository.save(chatMessage);
    }

    public List<ChatMessage> getMessages(String sender, String receiver) {
        List<ChatMessage> messages = chatMessageRepository.findBySenderAndReceiver(sender, receiver);
        messages.addAll(chatMessageRepository.findByReceiverAndSender(sender, receiver));
        return messages;
    }
}
