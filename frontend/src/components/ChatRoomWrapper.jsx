import React, {useEffect, useState} from 'react';
import ChatRoomList from "./ChatRoomList";
import ChatRoom from "./ChatRoom";

const ChatRoomWrapper = ({ storeId, roomId }) => {
    const [selectedChat, setSelectedChat] = useState(null);

    // FIXME: setSelectedChat가 함수가 아니라서 대화 리스트 클릭하면 오류 발생함 이거 고쳐야함.
    useEffect(() => {
        // Fetch or initialize chat rooms if needed when storeId changes
    }, [storeId]);
    return (
        <div>
            <h1>전체 대화</h1>
            <ChatRoomList storeId={storeId} setSelectedChat={setSelectedChat} />
            {selectedChat && (
                <ChatRoom roomId={selectedChat.id} />
            )}
        </div>
    );
};

export default ChatRoomWrapper;