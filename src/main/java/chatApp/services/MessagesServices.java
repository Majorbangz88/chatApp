package chatApp.services;

import chatApp.data.models.Chats;
import chatApp.dtos.requests.SendMessageRequest;

public interface MessagesServices {
    void sendMessage(SendMessageRequest sendMessageRequest, Chats chat);
}
