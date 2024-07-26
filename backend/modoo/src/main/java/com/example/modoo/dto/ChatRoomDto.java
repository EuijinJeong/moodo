package com.example.modoo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatRoomDto {
    private Long sender;
    private Long receiver;
    private String lastMessage;
    private LocalDateTime lastMessageTime;
}
