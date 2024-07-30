import React, {useEffect, useState} from 'react';
import ChatRoomList from "./ChatRoomList";

const ChatRoomWrapper = ({storeId}) => {
    const [selectedChat, setSelectedChat] = useState(null);

    useEffect(() => {
        // Fetch or initialize chat rooms if needed when storeId changes
    }, [storeId]);
    return (
        <div>
            <h1>전체 대화</h1>
            <ChatRoomList setSelectedChat={selectedChat} storeId={{storeId}} />
            {selectedChat && (
                <div>
                    <h2>Selected Chat</h2>
                    <p>{selectedChat.chatRoomTitle}</p>
                    <p>{selectedChat.lastMessage}</p>
                </div>
            )}
        </div>
    );
};

export default ChatRoomWrapper;