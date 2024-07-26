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

// TODO: 페이지 이동은 되는데, 리스트 형태로 이거 화면에 안뜸.. UI도 확인해야 하는데 일단 데베 설계부터 확실히 해야 할 듯.

export default ChatRoomList;