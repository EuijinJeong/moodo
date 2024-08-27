import React from 'react';
import "../css/chat_message_bubble.css"

const ChatMessageBubble = ({ message, isOwnMessage }) => {
    return (
        <div className={`chat-bubble ${isOwnMessage ? 'own-message' : ''}`}>
            <p>{message}</p>
        </div>
    );
};

export default ChatMessageBubble;