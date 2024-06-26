import React from 'react';
import UserDashboardHeader from "../components/UserDashboardHeader";
import ChatRoomList from "../components/ChatRoomList";
import ChatRoom from "../components/ChatRoom";

const ChatRoomPage = () => {
    return (
        <div>
            <UserDashboardHeader />
            <ChatRoomList />
            <ChatRoom />
        </div>
    );
};

export default ChatRoomPage;