package com.example.modoo.repository;

import com.example.modoo.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {
    Optional<ChatRoom> findBySenderIdAndReceiverId(Long senderId, Long receiverId);

    // TODO: 로그인한 사용자의 정보만으로 그에 따른 채팅방 리스트를 데이터베이스에서 가져오게 하는 메소드 만들어야 함

    /**
     * 그럼 사용자랑 조인을 해야함..?
     *
     * @param senderId: 현재 로그인한 사용자 (구매자, 메세지를 보내는 사람)
     * @return: 이사람이 sender인 ChatRoom을 리스트 형태로 반환함.
     */
    List<ChatRoom> findBySenderId(Long senderId);
}

