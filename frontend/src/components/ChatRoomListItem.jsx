import React, {useEffect} from 'react';
import "../css/chat-room-list-item.css"

const ChatRoomListItem = ({ message, onClick }) => {

    return (
        <div className="chat-list-item" onClick={onClick}>
            <div className="chat-user">사용자명{message.sender}</div>
            <div className="chat-preview">대화 미리보기{message.content}</div>
            <div className="chat-preview">대화 시간{new Date(message.timestamp).toLocaleDateString()}</div>
        </div>
    );
};

export default ChatRoomListItem;