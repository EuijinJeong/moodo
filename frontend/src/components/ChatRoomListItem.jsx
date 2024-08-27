import React, {useEffect} from 'react';
import "../css/chat-room-list-item.css"
import message from "sockjs-client/lib/transport/lib/buffered-sender";

const ChatRoomListItem = ({ room, onClick }) => {
    const lastMessage = room.lastMessage || "대화 내용 없음"; // 대화 내용이 없을 경우 대비
    const lastMessageTime = room.lastMessageTime
        ? new Date(room.lastMessageTime).toLocaleDateString()
        : "시간 정보 없음"; // 시간 정보가 없을 경우 대비

    return (
        <div className="chat-list-item" onClick={onClick}>
            <div className="chat-user">사용자명{message.sender}</div>
            <div className="chat-preview">{lastMessage}</div>
            <div className="chat-time">{lastMessageTime}</div>
        </div>
    );
};

export default ChatRoomListItem;