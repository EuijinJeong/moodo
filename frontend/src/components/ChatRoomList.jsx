import React from 'react';
import ChatRoomListItem from "./ChatRoomListItem";

const ChatRoomList = ({ messages = [], setSelectedChat }) => {
    return (
        <div className="chat-list">
            <h2>전체 대화</h2>
            {messages.map((msg, index) => (
                <ChatRoomListItem key={index} message={msg} onClick={() => setSelectedChat(msg)} />
            ))}
        </div>
    );
};

export default ChatRoomList;