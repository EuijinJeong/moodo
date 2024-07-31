package com.example.modoo.controller;

import com.example.modoo.dto.ChatMessageDto;
import com.example.modoo.dto.ChatRoomDto;
import com.example.modoo.service.ChatService;
import com.example.modoo.service.UserEmailLookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    UserEmailLookupService userEmailLookupService;

    /**
     * 판매자의 상점 아이디를 기반으로 채팅방을 생성하거나 찾는 메서드
     * @param storeId: 판매자의 상점 아이디
     * @return 생성되거나 찾은 채팅방의 정보
     */
    @PostMapping("/go-to-chatroom/{storeId}")
    @MessageMapping("/chat.sendMessage")
    public ResponseEntity<ChatRoomDto> gotoChatRoom(@PathVariable Long storeId) {

        // 사용자의 이메일 정보를 가져옴
        String userEmail = userEmailLookupService.getCurrentUserEmail();

        // 사용자의 이메일정보를 서비스계층에 전달 ( 반환타입이 Dto임.)
        ChatRoomDto chatRoomDto = chatService.findOrCreateChatRoom(userEmail, storeId);

        // 채팅방 정보를 클라이언트에게 반환
        return ResponseEntity.ok(chatRoomDto);
    }

    /**
     * 현재 로그인한 사용자가 참여하고 있는 모든 채팅방 목록을 가져온느 메서드
     * @return 사용자가 참여하고 있는 채팅방 목록
     */
    @GetMapping("/chat-room-lists")
    public ResponseEntity<List<ChatRoomDto>> getUserChatRoomLists() {
        String userEmail = userEmailLookupService.getCurrentUserEmail(); // 현재 로그인한 사용자의 정보 가져옴
        List<ChatRoomDto> chatRooms = chatService.getUserChatRooms(userEmail);
        return ResponseEntity.ok(chatRooms);
    }

    /**
     * 특정 채팅방의 세부 정보를 가져오는 메서드
     * @param roomId: 채팅방 ID
     * @return 채팅방의 세부 정보와 메세지 목록
     */
    @GetMapping("/chat-rooms/{roomId}")
    public ResponseEntity<Map<String, Object>> getChatRoomDetails(@PathVariable Long roomId) {
        ChatRoomDto chatRoomDto = chatService.getChatRoomById(roomId);
        List<ChatMessageDto> messageDto = chatService.getMessageByChatRoomId(roomId);

        // TODO: 아래 주석 자세하게 작성할 것.
        Map<String, Object> response = new HashMap<>();
        response.put("chatRoom", chatRoomDto);
        response.put("messages", messageDto);

        return ResponseEntity.ok(response);
    }




}
